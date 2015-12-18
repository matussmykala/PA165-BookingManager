package cz.muni.fi.pa165.bookingmanager.service;

import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * Room service layer, basic and nontrivial methods declaration
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
     * Change price of room
     *
     * @param room - room where price is changed
     * @param price - price of room
     */
    public void changeRoomPrice(Room room, BigDecimal price) throws DataAccessException;

    /**
     * Change number of beds in room
     *
     * @param room - room where price is changed
     * @param numberOfBeds - new number of beds in room
     */
    public void changeNumberOfBeds(Room room, int numberOfBeds) throws DataAccessException;

    /**
     * Find rooms by price
     *
     * @param price - price of rooms which have to be found
     * @return rooms with specific price
     */
    public List<Room> findByPrice(BigDecimal price) throws DataAccessException;

    /**
     * Find rooms by number of beds
     *
     * @param numberOfBeds - number of beds in room
     * @return rooms with specific number of beds
     */
    public List<Room> findByNumberOfBeds(int numberOfBeds) throws DataAccessException;

    /**
     * Find rooms by specific hotel
     *
     * @param id
     * @return rooms of specific hotel
     */
    public List<Room> findByHotel(Long id) throws DataAccessException;

    /**
     * Find rooms with reservation in specific time window
     *
     * @param from
     * @param to
     * @return rooms with reservation in specific time window
     */
    public List<Room> findReservedRoomsAtSpecificTime(Date from, Date to) throws DataAccessException;

    /**
     * Find free rooms in specific time window
     *
     * @param from
     * @param to
     * @return free rooms in specific time window
     */
    public List<Room> findFreeRoomsAtSpecificTime(Date from, Date to) throws DataAccessException;
}
