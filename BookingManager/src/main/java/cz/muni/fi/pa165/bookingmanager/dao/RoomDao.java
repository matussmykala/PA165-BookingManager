/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entity.Room;
import java.util.List;

/**
 *
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
public interface RoomDao {
    /**
     * Create entry for room
     * 
     * @param room
     */
    public void create(Room room);
    
    /**
     * Find room by given id
     * 
     * @param id
     * @return
     */
    public Room findById(Long id);
    
    /**
     * Return list of all rooms
     * 
     * @return
     */
    public List<Room> findAll();
    
    /**
     * Update room entry
     * 
     * @param room
     */
    public void update(Room room);
    
    /**
     * Delete room entry
     * 
     * @param room
     */
    public void delete(Room room);
}
