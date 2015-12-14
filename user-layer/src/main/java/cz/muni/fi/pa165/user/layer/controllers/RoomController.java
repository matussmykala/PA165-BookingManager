package cz.muni.fi.pa165.user.layer.controllers;


import cz.muni.fi.pa165.bookingmanager.dto.HotelDTO;
import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import cz.muni.fi.pa165.bookingmanager.facade.HotelFacade;
import cz.muni.fi.pa165.bookingmanager.facade.RoomFacade;
import cz.muni.fi.pa165.bookingmanager.service.HotelService;
import cz.muni.fi.pa165.bookingmanager.service.facade.RoomFacadeImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
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


@Controller
@RequestMapping("/room")
public class RoomController {

    final static Logger log = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomFacade roomFacade;// = new RoomFacadeImpl();
    
    @Autowired
    private HotelFacade hotelFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("rooms", roomFacade.getAllRooms());
        return "room/list";
    }
    
    @RequestMapping(value = "/hotelList/{id}", method = RequestMethod.GET)
    public String findByHotel(@PathVariable("id") long id, Model model){
        model.addAttribute("room",roomFacade.findByHotel(id));
        return "room/list";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model){
       model.addAttribute("hotels", hotelFacade.getAllHotels()); 
       return "room/search";
    }
    
    @RequestMapping(value = "/free-rooms", method = RequestMethod.GET)
    //@RequestMapping(value = "/free-rooms/?hotelId={hotelId}&startDate={startDate}&endDate={endDate}", method = RequestMethod.GET)
    public String listOfFreeRoomsOfHotel(@RequestParam long hotelId, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        HotelDTO hotelDTO = hotelFacade.getHotelById(hotelId);
        List<RoomDTO> rooms = hotelFacade.findFreeRoomInRange(hotelDTO, startDate, endDate);
        for (RoomDTO room : rooms){
            log.info("Room id: "+room.getId()+" Room name:"+room.getName()+" Start:"+startDate.toString()+" end:"+endDate.toString());
        }        
        model.addAttribute("rooms", rooms);
        return "room/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        RoomDTO room = roomFacade.getRoomById(id);
        roomFacade.deleteRoom(id);
        redirectAttributes.addFlashAttribute("alert_success", "Room \"" + room.getName() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/room/list").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("room", roomFacade.getRoomById(id));
        return "room/view";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newRoom(Model model){
        model.addAttribute("roomCreate", new RoomDTO());
        model.addAttribute("hotels", hotelFacade.getAllHotels());
        return "room/new";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
     public String create(@Valid @ModelAttribute("roomCreate") RoomDTO formBean,@RequestParam long hotelId, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }  
            return "room/new";
        }
        //create room
        HotelDTO hotel = hotelFacade.getHotelById(hotelId);
        formBean.setHotel(hotel);
        roomFacade.createRoom(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "room " + formBean.getName() + " was created");
        return "redirect:" + uriBuilder.path("/room/list").toUriString();
    } 
     
     @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editRoom(@PathVariable("id") long id, Model model,UriComponentsBuilder uriBuilder) {
        model.addAttribute("room",roomFacade.getRoomById(id));
        model.addAttribute("hotels", hotelFacade.getAllHotels());
        return "room/edit";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateRoom(@PathVariable("id") long id, @RequestParam long hotelId, @Valid @ModelAttribute("room") RoomDTO updatedRoom, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder){
        
         if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }  
            return "room/edit/{id}";
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
    
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String numberOfBedsFilter(@RequestParam String filterType, @RequestParam String filter, Model model, UriComponentsBuilder uriBuilder) {
               
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
