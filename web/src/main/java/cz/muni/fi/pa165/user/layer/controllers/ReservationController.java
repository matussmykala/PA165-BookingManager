package cz.muni.fi.pa165.user.layer.controllers;

import cz.muni.fi.pa165.bookingmanager.dto.CustomerDTO;
import java.util.Date;

import cz.muni.fi.pa165.bookingmanager.dto.ReservationCreateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.ReservationDTO;
import cz.muni.fi.pa165.bookingmanager.facade.ReservationFacade;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author matus
 */
@Controller
@RequestMapping("/reservation")
public class ReservationController
{
    @Autowired
    private ReservationFacade reservationFacade;

    private long roomId;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("reservations", reservationFacade.getAllReservations());
        return "reservation/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("reservation", reservationFacade.getReservationById(id));
        return "reservation/view";
    }

    @RequestMapping(value = "/pickdate/{roomId}")
    public String pickDate(@PathVariable long roomId, Model model){
        this.roomId = roomId;
        return "reservation/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newProduct(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                             @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
                             UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, ServletRequest r) {
        ReservationCreateDTO reservationCreateDTO = new ReservationCreateDTO();
        reservationCreateDTO.setRoomId(this.roomId);

        HttpServletRequest request = (HttpServletRequest) r;
        HttpSession session = request.getSession();

        CustomerDTO auth = (CustomerDTO) session.getAttribute("authenticatedUser");

        reservationCreateDTO.setCustomerId(auth.getId());
        reservationCreateDTO.setStartOfReservation(startDate);
        reservationCreateDTO.setEndOfReservation(endDate);
        boolean success = false;
        try{
            success = reservationFacade.createReservation(reservationCreateDTO);
        }
        catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("alert_danger", "Reservation of room \"" + reservationCreateDTO.getRoomId() +
                    "\" of customer \"" + reservationCreateDTO.getCustomerId() + "\" wasn't created. Wrong dates were picked.");
            return "redirect:" + uriBuilder.path("/reservation/pickdate/{id}").buildAndExpand(this.roomId).encode().toUriString();
        }
        if (success) {
            redirectAttributes.addFlashAttribute("alert_success", "Reservation of room \"" + reservationCreateDTO.getRoomId() +
                    "\" of customer \"" + reservationCreateDTO.getCustomerId() + "\" was created.");
            return "redirect:" + uriBuilder.path("/customer/view/{id}").buildAndExpand(auth.getId()).encode().toUriString();
        } else{
            redirectAttributes.addFlashAttribute("alert_danger", "Reservation of room \"" + reservationCreateDTO.getRoomId() +
                    "\" of customer \"" + reservationCreateDTO.getCustomerId() + "\" wasn't created. The room is not free " +
                    "in picked time range.");
            return "redirect:" + uriBuilder.path("/reservation/pickdate/{id}").buildAndExpand(this.roomId).encode().toUriString();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        ReservationDTO reservationDTO = reservationFacade.getReservationById(id);
        reservationFacade.cancelReservation(id);
        redirectAttributes.addFlashAttribute("alert_success", "Reservation of room \"" + reservationDTO.getRoom().getName() +
                "\" of customer \"" + reservationDTO.getCustomer().getName() + " " +
                reservationDTO.getCustomer().getSurname() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/reservation/list").toUriString();
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editReservation(@PathVariable("id") long id, Model model) {
        model.addAttribute("reservation",reservationFacade.getReservationById(id));
        return "reservation/edit";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateReservation(@PathVariable("id") long id,
                                    @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                    @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
                                    RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder){

        ReservationDTO reservation = reservationFacade.getReservationById(id);

        boolean success = false;
        try{
            success = reservationFacade.updateReservation(id, reservation.getCustomer().getId(), reservation.getRoom().getId(),
                    startDate, endDate);
        }
        catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("alert_danger", "Reservation " + id + " wasn't updated. Incorrect dates were picked.");
            return "redirect:" + uriBuilder.path("/reservation/edit/{id}").buildAndExpand(id).encode().toUriString();
        }
        if (success){
            redirectAttributes.addFlashAttribute("alert_success", "Reservation " + id + " was updated.");
            return "redirect:" + uriBuilder.path("/reservation/view/{id}").buildAndExpand(id).encode().toUriString();
        }
        else{
            redirectAttributes.addFlashAttribute("alert_danger", "Reservation " + id + " wasn't updated. Room is not free in this time range.");
            return "redirect:" + uriBuilder.path("/reservation/edit/{id}").buildAndExpand(id).encode().toUriString();
        }
    }
}
