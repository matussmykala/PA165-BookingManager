/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.facade;

import java.util.Date;
import java.util.List;

/**
 * Interface for facade layer
 * 
 * @author ivet
 */
public interface HotelFacade {
    
    /**
     * Create Hotel
     * 
     * @param hotel - hotel which will be created
     * @return id of created hotel
     */
    public Long createHotel(HotelCreateDTO hotel);
    
    /**
     * Remove hotel
     * 
     * @param HotelId - id of hotel which will be removed
     */
    public void deleteHotel(Long HotelId);
    
    /**
     * Get hotel by its id
     * 
     * @param id - id of hotel which may be found
     * @return HotelDTO - required hotel
     */
    public HotelDTO getHotelById(Long id);
    
    /**
     * Get all hotels
     * 
     * @return List<HotelDTO> - list of all hotels which are in system
     */
    public List<HotelDTO> getAllHotels();
    
    /**
     * Add room to hotel
     * 
     * @param HotelId - id of hotel into which room will be added
     * @param RoomId - id of added room
     */
    public void addRoom(Long HotelId, Long RoomId);
    
    /**
     * Remove room from hotel
     * 
     * @param HotelId - id of hotel from which room will be removed
     * @param RoomId - id of removed room
     */
    public void deleteRoom(Long HotelId, Long RoomId);
    
    /**
     * Get all rooms in hotel 
     * Method will be implemented after implementation of RoomDTO and RoomFacade
     * 
     * @param HotelId - id of hotel whose rooms user wants to know
     * @return List<RoomDTO> - list of rooms in hotel
     */
    /*
    public List<RoomDTO> getAllRoomsInHotel(Long HotelId);
    */
    
     /**
     * Get all free rooms in hotel 
     * Method will be implemented after implementation of RoomDTO and RoomFacade
     * 
     * @param HotelId - id of hotel whose free rooms user wants to know
     * @param startOfPeriod - from which date room has to be free
     * @param endOfPeriod - until which date room has to be free
     * @return List<RoomDTO> - list of free rooms in hotel during determined days
     */
    /*
    public List<RoomDTO> getAllFreeRoomsInHotel(Long HotelId, Date startOfPeriod, Date endOfPeriod);
    */
}
