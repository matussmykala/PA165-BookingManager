package cz.muni.fi.pa165.user.layer.controllers;

import cz.muni.fi.pa165.bookingmanager.dto.HotelDTO;
import cz.muni.fi.pa165.bookingmanager.dto.ReservationDTO;
import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import cz.muni.fi.pa165.bookingmanager.facade.HotelFacade;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import org.springframework.context.MessageSource;

/**
* Spring MVC controller for administrating rooms
*
* @author Martin Cuchran
*/
@Controller
@RequestMapping("/room")
public class RoomController {

    final static Logger log = LoggerFactory.getLogger(RoomController.class);

    @Inject
    private RoomFacade roomFacade;

    @Inject
    private HotelFacade hotelFacade;

    @Inject
    private ReservationFacade reservationFacade;
    
    @Inject
    private MessageSource messageSource;

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
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, Locale locale){
        RoomDTO room = roomFacade.getRoomById(id);
        List<ReservationDTO> reservations = new ArrayList<>();
        reservations = reservationFacade.getAllReservationsOfRoom(id);
        if(reservations.size()>0){
             String message = messageSource.getMessage("room.message.delete.has.reservations", null, locale);
             redirectAttributes.addFlashAttribute("alert_info", message);
             return "redirect:" + uriBuilder.path("/room/list").toUriString();
        }
        roomFacade.deleteRoom(id);
        String message = messageSource.getMessage("room.message.delete.successful", null, locale);
        redirectAttributes.addFlashAttribute("alert_success", message);
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
    @RequestMapping(value = "/create", method = RequestMethod.GET)
     public String create(@Valid @ModelAttribute("roomCreate") RoomDTO room,@RequestParam long hotelId, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {

        if ((room.getName()).equals("")){
            String message = messageSource.getMessage("room.message.create.name.empty", null, locale);
            model.addAttribute("alert_info", message);
            model.addAttribute("hotels", hotelFacade.getAllHotels());
   
            return "room/new";
        }
        
        if (room.getPrice() == null){
            String message = messageSource.getMessage("room.message.create.price.empty", null, locale);
            model.addAttribute("alert_info", message);
            model.addAttribute("hotels", hotelFacade.getAllHotels());
        
            return "room/new";
        }
        
        if (room.getHotel() == null){
            String message = messageSource.getMessage("room.message.create.hotel.not.chose", null, locale);
            model.addAttribute("alert_info", message);
            model.addAttribute("hotels", hotelFacade.getAllHotels());
        
            return "room/new";
        }
        
        if (room.getNumberOfBeds() <= 0){
            String message = messageSource.getMessage("room.message.create.number.of.beds", null, locale);
            model.addAttribute("alert_info", message);
            model.addAttribute("hotels", hotelFacade.getAllHotels());
        
            return "room/new";
        }
        
        HotelDTO hotel = hotelFacade.getHotelById(hotelId);
        room.setHotel(hotel);
        roomFacade.createRoom(room);
        String message = messageSource.getMessage("room.message.create.successful", null, locale);
        redirectAttributes.addFlashAttribute("alert_success", message);
        return "redirect:" + uriBuilder.path("/room/list").toUriString();
    }

    /**
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
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateRoom(@PathVariable("id") long id, @RequestParam long hotelId, @Valid @ModelAttribute("room") RoomDTO updatedRoom, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale){
       
         
        if ((updatedRoom.getName()).equals("")){
            String message = messageSource.getMessage("room.message.update.name.empty", null, locale);
            redirectAttributes.addFlashAttribute("alert_info", message);
            model.addAttribute("hotels", hotelFacade.getAllHotels());
            return "redirect:" + uriBuilder.path("/room/edit/{id}").buildAndExpand(id).encode().toUriString();
   
            //return "room/edit/{id}";
        }
        
        if (updatedRoom.getPrice() == null){
            String message = messageSource.getMessage("room.message.update.price.empty", null, locale);
            redirectAttributes.addFlashAttribute("alert_info", message);
            model.addAttribute("hotels", hotelFacade.getAllHotels());
        
            return "redirect:" + uriBuilder.path("/room/edit/{id}").buildAndExpand(id).encode().toUriString();
   
        }
        
        if (updatedRoom.getHotel() == null){
            String message = messageSource.getMessage("room.message.update.hotel.not.chose", null, locale);
            redirectAttributes.addFlashAttribute("alert_info", message);
            model.addAttribute("hotels", hotelFacade.getAllHotels());
        
            return "redirect:" + uriBuilder.path("/room/edit/{id}").buildAndExpand(id).encode().toUriString();
   
        }
        
        if (updatedRoom.getNumberOfBeds() <= 0){
            String message = messageSource.getMessage("room.message.update.number.of.beds", null, locale);
            redirectAttributes.addFlashAttribute("alert_info", message);
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

        String message = messageSource.getMessage("room.message.update.successful", null, locale);
        redirectAttributes.addFlashAttribute("alert_success", message);
        return "redirect:" + uriBuilder.path("/room/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    /**
     * Filters list of room. Filter is specified by name of attribute and value of attribute. Data are filtrated based on these attributes.
     *
     * @param filterType, filter, model
     * @return JSP page
     */
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String numberOfBedsFilter(@RequestParam String filterType, @RequestParam String filter, Model model, Locale locale) {
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
                String message = messageSource.getMessage("room.message.filter.unknown", null, locale);
                model.addAttribute("alert_danger", message);
        }
        if(rooms==null || rooms.size()<1){
            String message = messageSource.getMessage("room.message.filter.nodata", null, locale);
            model.addAttribute("alert_info", message);
        }
        model.addAttribute("rooms", rooms);
        model.addAttribute("filter", filter);
        model.addAttribute("filterType", filterType);
        return "room/list";
    }
}
