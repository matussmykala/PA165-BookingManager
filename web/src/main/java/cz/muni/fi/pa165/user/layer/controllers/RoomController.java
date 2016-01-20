package cz.muni.fi.pa165.user.layer.controllers;

import cz.muni.fi.pa165.bookingmanager.dto.HotelDTO;
import cz.muni.fi.pa165.bookingmanager.dto.ReservationDTO;
import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import cz.muni.fi.pa165.bookingmanager.facade.HotelFacade;
import cz.muni.fi.pa165.bookingmanager.facade.ReservationFacade;
import cz.muni.fi.pa165.bookingmanager.facade.RoomFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
* Spring MVC controller for administrating rooms
*
* @author Martin Cuchran
*/
@Controller
@RequestMapping("/room")
public class RoomController {

    final static Logger log = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomFacade roomFacade;

    @Autowired
    private HotelFacade hotelFacade;

    @Autowired
    private ReservationFacade reservationFacade;

    /**
     * Shows a list of rooms with the ability to add, view, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("rooms", roomFacade.getAllRooms());
        return "room/list";
    }

    /**
     * Shows a list of rooms by filter
     *
     * @param hotelId, startDate, endDate, model
     * @return JSP page name
     */
    @RequestMapping(value = "/free-rooms", method = RequestMethod.GET)
    public String listOfFreeRoomsOfHotel(@RequestParam long hotelId, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate, Model model) {
        HotelDTO hotelDTO = hotelFacade.getHotelById(hotelId);
        List<RoomDTO> rooms = hotelFacade.findFreeRoomInRange(hotelDTO, startDate, endDate);
        model.addAttribute("rooms", rooms);
        return "room/list";
    }

    /**
     * Removes room with specific ID
     *
     * @param id, model, uriBuilder, redirectAttributes
     * @return redirect to JSP
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        RoomDTO room = roomFacade.getRoomById(id);
        List<ReservationDTO> reservations = new ArrayList<>();
        reservations = reservationFacade.getAllReservationsOfRoom(id);
        if(reservations.size()>0){
             redirectAttributes.addFlashAttribute("alert_info", "Room \"" + room.getName() + "\" has reservations - it can not be deleted");
             return "redirect:" + uriBuilder.path("/room/list").toUriString();
        }
        roomFacade.deleteRoom(id);
        redirectAttributes.addFlashAttribute("alert_success", "Room \"" + room.getName() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/room/list").toUriString();
    }

    /*
     * Shows room with specific ID
     *
     * @param id, model
     * @return JSP page name
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("room", roomFacade.getRoomById(id));
        return "room/view";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newRoom(Model model){
        model.addAttribute("roomCreate", new RoomDTO());
        model.addAttribute("hotels", hotelFacade.getAllHotels());
        return "room/new";
    }

    /**
     * Creates room
     *
     * @param hotelId, bindingResult, model, redirectAttributes, uriBuilder
     * @return JSP page
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
     public String create(@Valid @ModelAttribute("roomCreate") RoomDTO room,@RequestParam long hotelId, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
/*
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            model.addAttribute("alert_info", "Fill all data");
            model.addAttribute("hotels", hotelFacade.getAllHotels());
            return "room/new";
        }
       */ 
        if ((room.getName()).equals("")){
            model.addAttribute("alert_info", "Name of room is empty");
            model.addAttribute("hotels", hotelFacade.getAllHotels());
   
            return "room/new";
        }
        
        if (room.getPrice() == null){
            model.addAttribute("alert_info", "Price of room is empty");
            model.addAttribute("hotels", hotelFacade.getAllHotels());
        
            return "room/new";
        }
        
        if (room.getHotel() == null){
            model.addAttribute("alert_info", "Hotel not chose");
            model.addAttribute("hotels", hotelFacade.getAllHotels());
        
            return "room/new";
        }
        
        if (room.getNumberOfBeds() <= 0){
            model.addAttribute("alert_info", "Number of beds must be greater than 0");
            model.addAttribute("hotels", hotelFacade.getAllHotels());
        
            return "room/new";
        }
        
        HotelDTO hotel = hotelFacade.getHotelById(hotelId);
        room.setHotel(hotel);
        roomFacade.createRoom(room);

        redirectAttributes.addFlashAttribute("alert_success", "room " + room.getName() + " was created");
        return "redirect:" + uriBuilder.path("/room/list").toUriString();
    }

    /*
     * Prepares edit form.
     *
     * @param id, model
     * @return JSP page name
     */
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editRoom(@PathVariable("id") long id, Model model) {
        model.addAttribute("room",roomFacade.getRoomById(id));
        model.addAttribute("hotels", hotelFacade.getAllHotels());
        return "room/edit";
    }

    /**
     * Updates room
     *
     * @param id, hotelId, modelAttribute, bindingResult, model, redirectAttributes, uriBuilder
     * @return JSP page
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateRoom(@PathVariable("id") long id, @RequestParam long hotelId, @Valid @ModelAttribute("room") RoomDTO updatedRoom, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder){
         /*if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            redirectAttributes.addFlashAttribute("alert_info", "Fill all data");
            
            return "redirect:" + uriBuilder.path("/room/edit/{id}").buildAndExpand(id).encode().toUriString();
   
        }*/
         
         
        if ((updatedRoom.getName()).equals("")){
            redirectAttributes.addFlashAttribute("alert_info", "Name of room is empty");
            model.addAttribute("hotels", hotelFacade.getAllHotels());
            return "redirect:" + uriBuilder.path("/room/edit/{id}").buildAndExpand(id).encode().toUriString();
   
            //return "room/edit/{id}";
        }
        
        if (updatedRoom.getPrice() == null){
            redirectAttributes.addFlashAttribute("alert_info", "Price of room is empty");
            model.addAttribute("hotels", hotelFacade.getAllHotels());
        
            return "redirect:" + uriBuilder.path("/room/edit/{id}").buildAndExpand(id).encode().toUriString();
   
        }
        
        if (updatedRoom.getHotel() == null){
            redirectAttributes.addFlashAttribute("alert_info", "Hotel not chose");
            model.addAttribute("hotels", hotelFacade.getAllHotels());
        
            return "redirect:" + uriBuilder.path("/room/edit/{id}").buildAndExpand(id).encode().toUriString();
   
        }
        
        if (updatedRoom.getNumberOfBeds() <= 0){
            redirectAttributes.addFlashAttribute("alert_info", "Number of beds must be greater than 0");
            model.addAttribute("hotels", hotelFacade.getAllHotels());
        
            return "redirect:" + uriBuilder.path("/room/edit/{id}").buildAndExpand(id).encode().toUriString();
   
        } 
         
        RoomDTO room = roomFacade.getRoomById(id);
        HotelDTO hotel = hotelFacade.getHotelById(hotelId);
        room.setName(updatedRoom.getName());
        room.setHotel(hotel);
        room.setNumberOfBeds(updatedRoom.getNumberOfBeds());
        room.setPrice(updatedRoom.getPrice());
        roomFacade.updateRoom(room);

        redirectAttributes.addFlashAttribute("alert_success", "Room " + id + " was updated");
        return "redirect:" + uriBuilder.path("/room/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    /**
     * Filters list of room. Filter is specified by name of attribute and value of attribute. Data are filtrated based on these attributes.
     *
     * @param filterType, filter, model
     * @return JSP page
     */
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String numberOfBedsFilter(@RequestParam String filterType, @RequestParam String filter, Model model) {
        List<RoomDTO> rooms;

        switch (filterType) {
            case "numberOfBeds":
                try{
                    int numberOfBeds = Integer.parseInt(filter);
                    rooms = roomFacade.getRoomsByNumberOfBeds(numberOfBeds);
                }catch (Exception e){
                    rooms = null;
                }
                break;
            case "price":
                try{
                    BigDecimal price = new BigDecimal(filter);
                    rooms = roomFacade.getRoomsByPrice(price);
                }catch (Exception e){
                    rooms = null;
                }
                break;
            case "hotelName":
                try{
                    HotelDTO hotel = hotelFacade.findByName(filter);
                    rooms = roomFacade.findByHotel(hotel.getId());
                }catch (Exception e){
                    rooms = null;
                }
                break;
            default:
                rooms = new ArrayList<>();
                model.addAttribute("alert_danger", "Unknown filter " + filterType);
        }
        if(rooms==null || rooms.size()<1){
            model.addAttribute("alert_info", "No data found");
        }
        model.addAttribute("rooms", rooms);
        return "room/list";
    }
}
