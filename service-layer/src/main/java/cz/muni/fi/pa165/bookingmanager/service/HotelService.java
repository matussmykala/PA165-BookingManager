/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.service;

import org.springframework.stereotype.Service;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ivet
 */
@Service
public interface HotelService {
    
    /**
     *
     * @param hotel
     * @return
     */
    public Hotel createHotel(Hotel hotel);
    
    public void removeHotel(Long id);
    
    public Hotel findById(Long id);
    
    public List<Hotel> findAll();
    
    public void addRoom(Hotel hotel, Room room);
    
    public void removeRoom(Hotel hotel, Room room);
    
    /**
     * Get all rooms in hotel 
     * Method will be implemented after implementation of RoomService
     * 
     * @param HotelId - id of hotel whose rooms user wants to know
     * @return List<Room> - list of rooms in hotel
     */
    public List<Room> getAllRoomsInHotel(Long HotelId);
    
    
     /**
     * Get all free rooms in hotel 
     * Method will be implemented after implementation of RoomService
     * 
     * @param HotelId - id of hotel whose free rooms user wants to know
     * @param startOfPeriod - from which date room has to be free
     * @param endOfPeriod - until which date room has to be free
     * @return List<Room> - list of free rooms in hotel during determined days
     */
    public List<Room> getAllFreeRoomsInHotel(Long HotelId, Date startOfPeriod, Date endOfPeriod);
   
    
    
    
    
}
