package cz.muni.fi.pa165.user.layer.controllers;


import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import cz.muni.fi.pa165.bookingmanager.facade.RoomFacade;
import cz.muni.fi.pa165.bookingmanager.service.facade.RoomFacadeImpl;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("rooms", roomFacade.getAllRooms());
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
        return "room/new";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
     public String create(@Valid @ModelAttribute("roomCreate") RoomDTO formBean, BindingResult bindingResult,
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
        roomFacade.createRoom(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "room " + formBean.getName() + " was created");
        return "redirect:" + uriBuilder.path("/room/list").toUriString();
    } 
     
     @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editRoom(@PathVariable("id") long id, Model model,UriComponentsBuilder uriBuilder) {
        model.addAttribute("room",roomFacade.getRoomById(id));
        return "room/edit";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateRoom(@PathVariable("id") long id, @Valid @ModelAttribute("room") RoomDTO updatedRoom, BindingResult bindingResult,
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
        room.setName(updatedRoom.getName());
        room.setNumberOfBeds(updatedRoom.getNumberOfBeds());
        room.setPrice(updatedRoom.getPrice());
        roomFacade.updateRoom(room);
         
                 
        redirectAttributes.addFlashAttribute("alert_success", "Room " + id + " was updated");
        return "redirect:" + uriBuilder.path("/room/view/{id}").buildAndExpand(id).encode().toUriString();
    } 
    
    @RequestMapping(value = "/search/{filterType}/{filter}", method = RequestMethod.GET)
    public String numberOfBedsFilter(@PathVariable("filterType") String filterType, @PathVariable("filter") int filter, Model model, UriComponentsBuilder uriBuilder) {
               
        List<RoomDTO> rooms;
        switch (filterType) {
            case "numberOfBeds":
                rooms = roomFacade.getRoomsByNumberOfBeds(filter);
                if(rooms.size()<1){
                    model.addAttribute("alert_info", "No data found");
                }
                break;
            default:
                rooms = new ArrayList<>();
                model.addAttribute("alert_danger", "Unknown filter " + filterType);
        }      
        model.addAttribute("rooms", rooms);
        return "room/list";
    }

}
