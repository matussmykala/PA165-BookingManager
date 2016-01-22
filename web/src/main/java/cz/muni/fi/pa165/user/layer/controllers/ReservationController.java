package cz.muni.fi.pa165.user.layer.controllers;

import cz.muni.fi.pa165.bookingmanager.dto.CustomerDTO;
import java.util.Date;
import java.util.Locale;

import cz.muni.fi.pa165.bookingmanager.dto.ReservationCreateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.ReservationDTO;
import cz.muni.fi.pa165.bookingmanager.facade.ReservationFacade;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Spring MVC Controller for management of hotel reservations.
 *
 * @author matus
 */
@Controller
@RequestMapping("/reservation")
public class ReservationController
{
    @Inject
    private MessageSource messageSource;

    @Autowired
    private ReservationFacade reservationFacade;

    private long roomId;

    /**
     * List of all reservations.
     *
     * @param model MVC model
     * @return  view of all reservations
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("reservations", reservationFacade.getAllReservations());
        return "reservation/list";
    }

    /**
     * Displays details of one reservation.
     *
     * @param id    reservation id
     * @param model MVC model
     * @return  view containing details of the reservation
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("reservation", reservationFacade.getReservationById(id));
        return "reservation/view";
    }

    /**
     * Controller method to pick reservation dates.
     *
     * @param roomId    room id
     * @param model MVC model
     * @return  view containing fields to pick reservation dates
     */
    @RequestMapping(value = "/pickdate/{roomId}")
    public String pickDate(@PathVariable long roomId, Model model){
        this.roomId = roomId;
        return "reservation/new";
    }

    /**
     * Controller method to create new reservation.
     *
     * @param startDate start date
     * @param endDate   end date
     * @param uriBuilder
     * @param redirectAttributes
     * @param r
     * @return  view containing fields to pick reservation dates
     */
    @RequestMapping(value = "/new/{reservationId}", method = RequestMethod.GET)
    public String newProduct(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                             @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
                             UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, ServletRequest r,
                             Locale locale) {
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
        catch (IllegalArgumentException | ValidationException e){
            String message = messageSource.getMessage("reservation.created.wrong_dates", null, locale);
            redirectAttributes.addFlashAttribute("alert_danger", message);
            return "redirect:" + uriBuilder.path("/reservation/pickdate/{id}").buildAndExpand(this.roomId).encode().toUriString();
        }
        if (success) {
            String message = messageSource.getMessage("reservation.created.successful", null, locale);
            redirectAttributes.addFlashAttribute("alert_success", message);
            return "redirect:" + uriBuilder.path("/customer/view/{id}").buildAndExpand(auth.getId()).encode().toUriString();
        } else{
            String message = messageSource.getMessage("reservation.created.not_free", null, locale);
            redirectAttributes.addFlashAttribute("alert_danger", message);
            return "redirect:" + uriBuilder.path("/reservation/pickdate/{id}").buildAndExpand(this.roomId).encode().toUriString();
        }
    }

    /**
     * Controller method for deletion of the reservation.
     *
     * @param id    reservation id
     * @param uriBuilder
     * @param redirectAttributes
     * @return view of all reservations
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes,
                         Locale locale){
        ReservationDTO reservationDTO = reservationFacade.getReservationById(id);
        reservationFacade.cancelReservation(id);
        String message = messageSource.getMessage("reservation.deleted", null, locale);
        redirectAttributes.addFlashAttribute("alert_success", message);
        return "redirect:" + uriBuilder.path("/reservation/list").toUriString();
    }

    /**
     * Controller method for editing the reservation.
     *
     * @param id    reservation id
     * @param model MVC model
     * @return  view containing form for editing of the reservation
     */
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editReservation(@PathVariable("id") long id, Model model) {
        model.addAttribute("reservation",reservationFacade.getReservationById(id));
        return "reservation/edit";
    }

    /**
     * Method checks correctness of user provided data and then updates
     * reservation information.
     *
     * @param id    reservation id
     * @param startDate start date
     * @param endOfReservation   end date
     * @param redirectAttributes
     * @param uriBuilder
     * @return  view containing updated information of the reservation
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateReservation(@PathVariable("id") long id,
                                    @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                    @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endOfReservation,
                                    RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale){

        ReservationDTO reservation = reservationFacade.getReservationById(id);

        boolean success = false;
        try{
            success = reservationFacade.updateReservation(id, reservation.getCustomer().getId(), reservation.getRoom().getId(),
                    startDate, endOfReservation);
        }
        catch (IllegalArgumentException | ValidationException e){
            String message = messageSource.getMessage("reservation.updated.wrong_dates", null, locale);
            redirectAttributes.addFlashAttribute("alert_danger", message);
            return "redirect:" + uriBuilder.path("/reservation/edit/{id}").buildAndExpand(id).encode().toUriString();
        }
        if (success){
            String message = messageSource.getMessage("reservation.updated.succesful", null, locale);
            redirectAttributes.addFlashAttribute("alert_success", message);
            return "redirect:" + uriBuilder.path("/reservation/view/{id}").buildAndExpand(id).encode().toUriString();
        }
        else{
            String message = messageSource.getMessage("reservation.updated.not_free", null, locale);
            redirectAttributes.addFlashAttribute("alert_danger", message);
            return "redirect:" + uriBuilder.path("/reservation/edit/{id}").buildAndExpand(id).encode().toUriString();
        }
    }
}
