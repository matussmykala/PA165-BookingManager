/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.facade;

import cz.muni.fi.pa165.bookingmanager.dto.ChangeImageDTO;
import cz.muni.fi.pa165.bookingmanager.dto.HotelCreateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.HotelDTO;
import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
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
     * Update Hotel
     * 
     * @param hotelDTO - hotel which will be updated
     */
    public void updateHotel(HotelDTO hotelDTO);
    
    /**
     * Remove hotel
     * 
     * @param HotelId - id of hotel which will be removed
     */
    public void deleteHotel(Long HotelId);
    
    /**
     * Get hotel by its id
     * 
     * @param HotelId - id of hotel which may be found
     * @return HotelDTO - required hotel
     */
    public HotelDTO getHotelById(Long HotelId);
    
    /**
     * Get all hotels
     * 
     * @return List<HotelDTO> - list of all hotels which are in system
     */
    public List<HotelDTO> getAllHotels();
    
     /**
      * Change image of hotel
      * @param image - that will be added to hotel description
      */
     public void changeImage(ChangeImageDTO image);
     
      /**
     * Find hotel by name
     * 
     * @param name - name of hotel
     * @return found hotel
     */
    public HotelDTO findByName(String name);
    
    /**
     * Find all hotels in city
     * 
     * @param adress
     * @return all hotels in city
     */
    public List<HotelDTO> findByAdress(String adress);
    
    /**
     * Find all free rooms in hotel in specific range of time
     * 
     * @param start
     * @param end
     * @return free rooms in hotel 
     */
    public List<RoomDTO> findFreeRoomInRange(Date start, Date end);
    
     
    
}
