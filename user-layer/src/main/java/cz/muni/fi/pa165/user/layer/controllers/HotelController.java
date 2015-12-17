/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.user.layer.controllers;

import cz.muni.fi.pa165.bookingmanager.dto.HotelCreateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.HotelDTO;
import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.facade.HotelFacade;
import cz.muni.fi.pa165.bookingmanager.facade.RoomFacade;
import cz.muni.fi.pa165.bookingmanager.service.RoomService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.format.annotation.DateTimeFormat;
//pridat logy

/**
 *
 * @author ivet
 */
@Controller
@RequestMapping("/hotel")
public class HotelController {
    
    @Autowired
    private HotelFacade hotelFacade;
    
    @Autowired
    private RoomFacade roomFacade;
    
    

    
    /**
     * Show a list of hotels
     * 
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("hotels", hotelFacade.getAllHotels());
        return "hotel/list";
    }
    
    
    
    /**
     * Delete hotel
     * 
     * @param id of deleted hotel
     * @param uriBuilder
     * @param redirectAttributes
     * @return 
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        HotelDTO hotel = hotelFacade.getHotelById(id);
        if(!(hotelFacade.findRoomsWithReservation(id).isEmpty())){
             redirectAttributes.addFlashAttribute("alert_info", "Hotel \"" + hotel.getName() + "\" has rooms with reservations - it can not be deleted");
             return "redirect:" + uriBuilder.path("/hotel/list").toUriString();
        }
        hotelFacade.deleteHotel(id);
        redirectAttributes.addFlashAttribute("alert_success", "Hotel \"" + hotel.getName() + "\" was deleted.");
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
    public String view(@PathVariable long id, Model model){
        model.addAttribute("hotel",hotelFacade.getHotelById(id));
        return "hotel/view";
    } 
    
    /**
     * Show hotel by name
     * 
     * @param name of hotel
     * @param model data to display
     * @return JSP page name
     */
    /*
    @RequestMapping(value = "/view/{name}", method = RequestMethod.GET)
    public String viewName(@PathVariable String name, Model model){
        model.addAttribute("hotel",hotelFacade.findByName(name));
        return "hotel/view";
    } 
    
     @RequestMapping(value = "/view/{address}", method = RequestMethod.GET)
     public String viewAddress(@PathVariable String address, Model model){
        model.addAttribute("hotel",hotelFacade.findByAddress(address));
        return "hotel/view";
    } 
    */
  
    
    @RequestMapping(value = "/find")
        public String findForm(Model model){
        
        return "hotel/find";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam String goal, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate, Model model, UriComponentsBuilder uriBuilder){

        if(goal==""){
            model.addAttribute("alert_info", "Name/Destination is empty");
            return "hotel/find"; 
        }
        if(startDate==null || endDate==null){
            model.addAttribute("alert_info", "Date is empty");
            return "hotel/find";
        }else{
            
            HotelDTO hotelDTO = new HotelDTO();
            try{
                hotelDTO = hotelFacade.findByName(goal);
            }catch (Exception e){
                hotelDTO = null;
            }
            
            List<HotelDTO> hotels = new ArrayList<>();
            try{
                hotels = hotelFacade.findByAddress(goal);            
            }catch (Exception e){
                hotels=null;
            }
            if(hotels.size()<1 && hotelDTO==null){
                model.addAttribute("alert_info", "No data found");
                return "room/list";
            }else{
                
                
                if(!hotels.contains(hotelDTO) && hotelDTO!=null){
                    hotels.add(hotelDTO);
                }
                
                if(hotels==null){
                    model.addAttribute("alert_info", "No data found");
                    return "room/list";
                }
                
                List<RoomDTO> rooms = new ArrayList<>();

                for(HotelDTO hotel : hotels){
                    rooms.addAll(hotelFacade.findFreeRoomInRangeChanged(hotel, startDate, endDate));
                }
                
                if(rooms.size()<1){
                    model.addAttribute("rooms", rooms);
                    model.addAttribute("alert_info", "No available rooms in this date range");
                    return "room/list";
                }else{
                    model.addAttribute("rooms", rooms);           
                    return "room/list";
                }
                
            }    
        }                 
    }
   
    
    /*
        @RequestMapping(value = "/find/{address}", method = RequestMethod.GET)
    public String address(@PathVariable String address, Model model){
        model.addAttribute("hotels",hotelFacade.findByAddress(address));
        return "hotel/list";
    } 
    */
    
      
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newHotel(Model model){
        model.addAttribute("hotelCreate", new HotelCreateDTO());
        return "hotel/new";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
     public String create(@Valid @ModelAttribute("hotelCreate") HotelCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
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
        Long id = hotelFacade.createHotel(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Hotel " + id + " was created");
        return "redirect:" + uriBuilder.path("/hotel/view/{id}").buildAndExpand(id).encode().toUriString();
    } 
     
       
       
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editHotel(@PathVariable("id") long id, Model model,UriComponentsBuilder uriBuilder) {
        model.addAttribute("hotel",hotelFacade.getHotelById(id));
        return "hotel/edit";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateHotel(@PathVariable("id") long id, @Valid @ModelAttribute("hotel") HotelDTO updatedHotel, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder){
        
         if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }  
            return "hotel/edit/{id}";
        }
         
        
        HotelDTO hotel = hotelFacade.getHotelById(id);
        hotel.setName(updatedHotel.getName());
        hotel.setAddress(updatedHotel.getAddress());
        hotel.setDescription(updatedHotel.getDescription());
        hotelFacade.updateHotel(hotel);
         
                 
        redirectAttributes.addFlashAttribute("alert_success", "Hotel " + id + " was updated");
        return "redirect:" + uriBuilder.path("/hotel/view/{id}").buildAndExpand(id).encode().toUriString();
    } 
    
    public class Filter{
        private String filter;
        public Filter(){
            
        }

        public String getFilter() {
            return filter;
        }

        public void setFilter(String filter) {
            this.filter = filter;
        }
        
        
    }
    
    
 
    
    
    
}
     
 
    
     
     

