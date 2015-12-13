package cz.muni.fi.pa165.user.layer.controllers;
import java.util.Date;

import javax.validation.Valid;

import cz.muni.fi.pa165.bookingmanager.dto.HotelDTO;
import cz.muni.fi.pa165.bookingmanager.dto.ReservationCreateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.ReservationDTO;
import cz.muni.fi.pa165.bookingmanager.facade.ReservationFacade;
import cz.muni.fi.pa165.bookingmanager.facade.RoomFacade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    final static Logger logger = LoggerFactory.getLogger(RoomController.class);

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

    public String customerList(@PathVariable long customerId, Model model) {
        model.addAttribute("reservations", reservationFacade.getFutureReservationsOfCustomer(customerId));
        return "userReservation/list";
    }

    @RequestMapping(value = "/pickdate/{roomId}")
    public String pickDate(@PathVariable long roomId, Model model){
        this.roomId = roomId;
        return "reservation/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newProduct(//@PathVariable long roomId,
                             //@RequestParam long customerId,
                             @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                             @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
                             UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        ReservationCreateDTO reservationCreateDTO = new ReservationCreateDTO();
        reservationCreateDTO.setRoomId(this.roomId);
        reservationCreateDTO.setCustomerId((long) 1);//customerId);
        reservationCreateDTO.setStartOfReservation(startDate);
        reservationCreateDTO.setEndOfReservation(endDate);
        reservationFacade.createReservation(reservationCreateDTO);

        redirectAttributes.addFlashAttribute("alert_success", "Reservation of room \"" + reservationCreateDTO.getRoomId() +
                "\" of customer \"" + reservationCreateDTO.getCustomerId() + "\" was created.");
        return "redirect:" + uriBuilder.path("/reservation/list").toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        ReservationDTO reservationDTO = reservationFacade.getReservationById(id);
        reservationFacade.cancelReservation(id);
        redirectAttributes.addFlashAttribute("alert_success", "Reservation of room \"" + reservationDTO.getRoom().getName() +
                "\" of customer \"" + reservationDTO.getCustomer().getName() + " " +
                reservationDTO.getCustomer().getSurname() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/reservation/list").toUriString();
    }
}
