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
     * Create hotel
     * 
     * @param hotel
     * @return just created hotel 
     */
    public Hotel createHotel(Hotel hotel);
    
    /**
     * Remove hotel
     * @param hotel - hotel which will be removed
     */
    public void deleteHotel(Hotel hotel);
    
    /**
     * Find hotel by id
     * 
     * @param id - id of hotel which has to be found
     * @return found hotel
     */
    public Hotel findById(Long id);
    
    /**
     * Find hotel by name
     * 
     * @param name - name of hotel
     * @return found hotel
     */
    public Hotel findByName(String name);
    
    /**
     * Show all hotels is system
     * 
     * @return all hotels in system
     */
    public List<Hotel> findAll();
    
    /**
     * Add room into hotel
     * 
     * @param hotel into which room will be added
     * @param room which will be added into hotel 
     */
    
    /**
    public void addRoom(Hotel hotel, Room room);
    
    /**
     * Remove room from hotel
     * 
     * @param hotel from which room will be removed
     * @param room which will be removed from hotel 
     */
    /**
    public void deleteRoom(Hotel hotel, Room room);
    
    /**
     * Get all rooms in hotel 
     * Method will be implemented after implementation of RoomService
     * 
     * @param HotelId - id of hotel whose rooms user wants to know
     * @return List<Room> - list of rooms in hotel
     */
    /**
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
    /**
    public List<Room> getAllFreeRoomsInHotel(Long HotelId, Date startOfPeriod, Date endOfPeriod);
    */
    
    
    
    
}