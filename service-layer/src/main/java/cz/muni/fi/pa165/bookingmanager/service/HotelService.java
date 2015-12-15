package cz.muni.fi.pa165.bookingmanager.service;

import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
     * @throws DataAccessException when error on DAO layer appears
     *
     */
    public void createHotel(Hotel hotel) throws DataAccessException;

    /**
     * Update hotel
     *
     * @param hotel which will be updated
     */
    public void updateHotel(Hotel hotel) throws DataAccessException;

    /**
     * Remove hotel
     * @param hotel - hotel which will be removed
     */
    public void deleteHotel(Hotel hotel) throws DataAccessException;

    /**
     * Find hotel by id
     *
     * @param id - id of hotel which has to be found
     * @return found hotel
     */
    public Hotel findById(Long id) throws DataAccessException;

    /**
     * Find hotel by name
     *
     * @param name - name of hotel
     * @return found hotel
     */
    public Hotel findByName(String name) throws DataAccessException;

    /**
     * Show all hotels is system
     *
     * @return all hotels in system
     */
    public List<Hotel> findAll() throws DataAccessException;


    /**
     * Find all hotels in city
     *
     * @param address in which city you want to find hotel
     * @return all hotels in city
     */
    public List<Hotel> findByAdress(String address);

    /**
     * Find all free rooms in hotel in specific range of time
     *
     * @hotel of which you want to know free rooms in range of time
     * @param start
     * @param end
     * @return free rooms in hotel
     */
    public List<Room> findFreeRoomInRange(Hotel hotel, Date start, Date end);

    /**
     * Find all booked rooms in hotel in specific range of time
     *
     * @hotel of which you want to know booked rooms in range of time
     * @param start
     * @param end
     * @return free rooms in hotel
     */
    public List<Room> findBookedRoomInRange(Hotel hotel, Date start, Date end);

    /**
     * Find all rooms which have reservation in hotel
     *
     * @hotel of which you want to know booked rooms in range of time
     *
     * @return free rooms in hotel
     */
    public List<Room> findRoomsWithReservation(Long id);

}
