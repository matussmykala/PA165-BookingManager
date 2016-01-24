/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.user.layer.controllers;

import cz.muni.fi.pa165.bookingmanager.dto.HotelCreateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.HotelDTO;
import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import cz.muni.fi.pa165.bookingmanager.facade.HotelFacade;
import cz.muni.fi.pa165.bookingmanager.facade.RoomFacade;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Spring MVC controller for administrating hotels
 *
 * @author ivet
 */
@Controller
@RequestMapping("/hotel")
public class HotelController {

    final static Logger log = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelFacade hotelFacade;

    @Autowired
    private RoomFacade roomFacade;
    
    @Autowired
    private MessageSource messageSource;
    
      
    Date date;

    /**
     * Show a list of hotels
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("hotels", hotelFacade.getAllHotels());
        return "hotel/list";
    }

    /**
     * Delete hotel
     *
     * @param id of deleted hotel
     * @param uriBuilder
     * @param redirectAttributes
     * @return JSP page name
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, Locale locale) {
        HotelDTO hotel = hotelFacade.getHotelById(id);
        if (!(hotelFacade.findRoomsWithReservation(id).isEmpty())) {
            redirectAttributes.addFlashAttribute("alert_info", "Hotel " + hotel.getName() + " " + messageSource.getMessage("hotel.nameIsEmpty", null, locale));
            return "redirect:" + uriBuilder.path("/hotel/list").toUriString();
        }
        hotelFacade.deleteHotel(id);
        redirectAttributes.addFlashAttribute("alert_success", "Hotel " + hotel.getName() + " " + messageSource.getMessage("hotel.wasDeleted", null, locale));
        return "redirect:" + uriBuilder.path("/hotel/list").toUriString();
    }

    /**
     * Show hotel by id
     *
     * @param id of hotel
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("hotel", hotelFacade.getHotelById(id));
        return "hotel/view";
    }

    /**
     * Show form for finding hotel
     *
     * @return
     */
    @RequestMapping(value = "/find")
    public String findForm() {

        return "hotel/find";
    }

    /**
     * Find free room according to name or address of hotel in specific range
     * of time
     *
     * @param goal name or address of hotel
     * @param startDate first day from that we want reservation
     * @param endDate last day to that we want reservation
     * @param model
     * @param uriBuilder
     * @return JSP page name
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam String goal, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, Model model, UriComponentsBuilder uriBuilder, Locale locale) {

        date = new Date();
        
        if (goal.equals("")) {
            model.addAttribute("alert_info", messageSource.getMessage("hotel.nameIsempty", null, locale));
            return "hotel/find";
        }
        if (startDate == null || endDate == null) {
            model.addAttribute("alert_info", messageSource.getMessage("hotel.dateIsEmpty", null, locale));
            return "hotel/find";
        }
        if (startDate.compareTo(endDate)>0){
            model.addAttribute("alert_info", messageSource.getMessage("hotel.wrongDateFormat", null, locale));
            return "hotel/find";
        }
        if(startDate.compareTo(date)<0){
            model.addAttribute("alert_info", messageSource.getMessage("hotel.dateInPast", null, locale));
            return "hotel/find";
        } else {

            HotelDTO hotelDTO = new HotelDTO();
            try {
                hotelDTO = hotelFacade.findByName(goal);
            } catch (Exception e) {
                hotelDTO = null;
            }

            List<HotelDTO> hotels = hotelFacade.findByAddress(goal);
            if (hotels.size() < 1 && hotelDTO == null) {
                model.addAttribute("alert_info", messageSource.getMessage("hotel.noData", null, locale));
                return "room/list";
            } else {

                if (!hotels.contains(hotelDTO) && hotelDTO != null) {
                    hotels.add(hotelDTO);
                }
                List<RoomDTO> rooms = new ArrayList<>();

                for (HotelDTO hotel : hotels) {
                    rooms.addAll(hotelFacade.findFreeRoomInRange(hotel, startDate, endDate));
                }

                if (rooms.size() < 1) {
                    model.addAttribute("rooms", rooms);
                    model.addAttribute("alert_info", messageSource.getMessage("hotel.noAvailableRoomInRange", null, locale));
                    return "room/list";
                } else {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    model.addAttribute("rooms", rooms);
                    String sD = df.format(startDate);
                    model.addAttribute("startDate", sD);
                    String eD = df.format(endDate);
                    model.addAttribute("endDate", eD );
                    return "room/list";
                }

            }
        }
    }



    /**
     * Display form for new hotel
     *
     * @param model
     * @return JSP page name
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newHotel(Model model) {
        model.addAttribute("hotelCreate", new HotelCreateDTO());
        return "hotel/new";
    }

    /**
     * Create new hotel
     *
     * @param hotel
     * @param bindingResult
     * @param model
     * @param redirectAttributes
     * @param uriBuilder
     * @return JSP page name
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@Valid @ModelAttribute("hotelCreate") HotelCreateDTO hotel, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "hotel/new";
        }
        //create hotel
        Long id = hotelFacade.createHotel(hotel);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Hotel " + id + " " + messageSource.getMessage("hotel.wasCreated", null, locale));
        return "redirect:" + uriBuilder.path("/hotel/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    /**
     * Show form to update hotel
     *
     * @param id id of updated hotel
     * @param model
     * @param uriBuilder
     * @return JSP page name
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editHotel(@PathVariable("id") long id, Model model, UriComponentsBuilder uriBuilder) {
        model.addAttribute("hotel", hotelFacade.getHotelById(id));
        return "hotel/edit";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateHotel(@PathVariable("id") long id, @Valid @ModelAttribute("hotel") HotelDTO updatedHotel, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {

         if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "hotel/edit";
        }
        
        HotelDTO hotel = hotelFacade.getHotelById(id);
        hotel.setName(updatedHotel.getName());
        hotel.setAddress(updatedHotel.getAddress());
        hotel.setDescription(updatedHotel.getDescription());
        hotelFacade.updateHotel(hotel);

        redirectAttributes.addFlashAttribute("alert_success", "Hotel " + id + " " + messageSource.getMessage("hotel.wasUpdated", null, locale));
        return "redirect:" + uriBuilder.path("/hotel/view/{id}").buildAndExpand(id).encode().toUriString();
    }

}












