/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.facade;

import cz.muni.fi.pa165.bookingmanager.dto.ChangeImageDTO;
import cz.muni.fi.pa165.bookingmanager.dto.HotelCreateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.HotelDTO;
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
    
     
    
}
