/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.user.layer.controllers;

import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.service.HotelService;
import cz.muni.fi.pa165.bookingmanager.service.RoomService;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ivet
 */
@Component
@Transactional 
@Controller
@RequestMapping("/data")
public class DataController {
    
  @Autowired
  HotelService hotelService;
  Date date;
  @Autowired
  RoomService roomService;
    
  @RequestMapping(value = "/data", method = RequestMethod.GET)
  public String Add(){
      
        this.createDate();
        Hotel voronez = hotel("Voronez","Brno","Luxusny hotel v Brne",date);
        Hotel park = hotel("Park Hotel","Praha","Pekny hotel v Prahe", date);
        this.createDate();
        Hotel arkadia = hotel("Arkadia","Banovce","Drahy maly hotel kde nikto nechodi", date);
        Hotel ira = hotel("Ira hotel", "Praha", "Miesto kde sa vzdy zmesti vela ludi", date);
        this.createDate();
        Hotel domSportu = hotel("Dom Sportu", "Bratislava", "Hotel sportovcov", date);
        Hotel ohlaHotel = hotel("Ohla Hotel", "Bratislava" , "Nejaky hotel", date);
        Hotel transylvania = hotel("Transylvania", "Brno", "Hotel priseriek", date);
        
        Room room1 = room("Izba-1",1,new BigDecimal("25.1"), voronez );
        Room room2 = room("Izba-2",2,new BigDecimal("25.2"), park );
        Room room3 = room("Izba-3",3,new BigDecimal("25.3"), arkadia );
        Room room4 = room("Izba-4",4,new BigDecimal("25.4"), ira );
        
        return "/home";
  }
    
      private void createDate(){
        Random rand = new Random();
        int  d = rand.nextInt(30) + 1;
        int  m = rand.nextInt(12) + 1;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, m);
        calendar.set(Calendar.DATE, d);
        this.date = calendar.getTime();
    }
   
    private Hotel hotel(String name, String address, String description, Date date) {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setDescription(description);
      //  hotel.setImage(readImage(imageFile));
      //  hotel.setImageMimeType(mimeType);
        hotel.setLastUpdateDay(date);
        hotelService.createHotel(hotel);
        return hotel;
    }
    
    private Room room(String name,int numberOfBeds, BigDecimal price, Hotel hotel){
        Room room = new Room();
        room.setName(name);
        room.setNumberOfBeds(numberOfBeds);
        room.setPrice(price);
        room.setHotel(hotel); 
        roomService.createRoom(room);
        return room;
    }
}
    


