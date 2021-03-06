package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import java.math.BigDecimal;
import java.util.Currency;

import java.util.List;

/**
 *
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
public interface RoomDao {
    /**
     * Create entry for room
     *
     * @param room room to be created
     */
    public void create(Room room);
    
    /**
     * Find room by given id
     *
     * @param id id of the room to look up
     * @return Room object if the room with given id was found, null otherwise
     */
    public Room findById(Long id);
    
    /**
     * Return list of all rooms
     *
     * @return list of all rooms stored in database
     */
    public List<Room> findAll();
    
    /**
     * Return list of rooms with specific price
     *
     * @price Price of room
     * @return list of rooms with specific price
     */
    public List<Room> findRoomByPrice(BigDecimal price);
    
    /**
     * Return list of rooms with specific number of beds
     *
     * @numberOfBeds number of beds
     * @return list of rooms with specific price
     */
    public List<Room> findRoomByNumberOfBeds(int numberOfBeds);
      
    
    
    /**
     * Update room entry
     *
     * @param room room object with updated information
     */
    public void update(Room room);
    
    
    /**
     * Delete room entry
     *
     * @param room room object that is to be removed from database
     */
    public void delete(Room room);
    
    /**
     * Find rooms in hotel
     * @param name 
     * @return rooms in hotel
     */
    public List<Room> findByHotel(Long id);
}
