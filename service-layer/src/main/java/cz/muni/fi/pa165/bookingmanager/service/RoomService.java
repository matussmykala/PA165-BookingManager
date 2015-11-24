package cz.muni.fi.pa165.bookingmanager.service;

import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Cuchran
 */

@Service
public interface RoomService {
    /**
     * Create room
     * 
     * @param room
     * @throws DataAccessException when error on DAO layer appears
     * @return createdHOtel
     */
    public Room createRoom(Room room) throws DataAccessException;
    
    /**
     * Update room
     * 
     * @param room which will be updated 
     */
    public void updateRoom(Room room) throws DataAccessException;
        
    /**
     * Remove room
     * @param room - room which will be removed
     */
    public void deleteRoom(Room room) throws DataAccessException;
    
    /**
     * Find room by id
     * 
     * @param id - id of room which has to be found
     * @return found room
     */
    public Room findById(Long id) throws DataAccessException;
    
    /**
     * Show all rooms is system
     * 
     * @return all rooms in system
     */
    public List<Room> findAll() throws DataAccessException;
    
    /**
     * Show all rooms with specific price
     * 
     * @param price - price of room
     * @param currency - price currency
     * @return all rooms with specific price
     */
    public List<Room> findAllRoomsByPrice(BigDecimal price, Currency currency) throws DataAccessException;
    
    /**
     * Show all rooms with specific number of beds
     * 
     * @param numberOfBeds - number of beds in room
     * @return all rooms with specific price
     */
    public List<Room> findAllRoomsByNumberOfBeds(int numberOfBeds) throws DataAccessException;
    
    /**
     * Show all rooms from specific hotel
     * 
     * @param hotel - hotel
     * @return all rooms with specific price
     */
    public List<Room> findAllRoomsByHotel(Hotel hotel) throws DataAccessException;
}
