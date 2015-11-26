/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import java.util.List;

/**
 *
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
public interface HotelDao {
    /**
     * Create entry for hotel
     * 
     * @param hotel
     */
    public void create(Hotel hotel);
    
    /**
     * Find hotel by given id
     * 
     * @param id
     * @return
     */
    public Hotel findById(Long id);
    
    /**
     * Find hotel by given name
     * 
     * @param name
     * @return
     */
    public Hotel findByName(String name);
    
    /**
     * Return list of all hotels
     * 
     * @return
     */
    public List<Hotel> findAll();
    
    /**
     * Update hotel entry
     * 
     * @param hotel
     */
    public void update(Hotel hotel);
    
    /**
     * Delete hotel entry
     * 
     * @param hotel
     */
    public void delete(Hotel hotel);
    
    /**
     * Find hotels in city
     * @param address
     * @return 
     */
    public List<Hotel> findByAdress(String address);
}
