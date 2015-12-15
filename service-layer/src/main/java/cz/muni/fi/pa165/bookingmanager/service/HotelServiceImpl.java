package cz.muni.fi.pa165.bookingmanager.service;

import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.dao.HotelDao;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDao;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ivet
 */
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private RoomService roomService;

    @Override
    public void createHotel(Hotel hotel) throws DataAccessException {
        try {
            hotelDao.create(hotel);
        } catch (DataAccessException dae) {
        };
    }

    @Override
    public void updateHotel(Hotel hotel) throws DataAccessException {
        try {
            hotelDao.update(hotel);
        } catch (DataAccessException dae) {
        };
    }

    @Override
    public void deleteHotel(Hotel hotel) throws DataAccessException {
        List<Room> roomsOfHotel = new ArrayList<>();
        roomsOfHotel.addAll(roomService.findByHotel(hotel.getId()));
        try {
            for(Room r:roomsOfHotel){
            roomService.deleteRoom(r);
            }
            hotelDao.delete(hotel);
        } catch (DataAccessException dae) {
        };
    }

    @Override
    public Hotel findById(Long id) throws DataAccessException {
        Hotel hotel = null;
        try {
            hotel = hotelDao.findById(id);
        } catch (DataAccessException dae) {
        };
        return hotel;
    }

    @Override
    public Hotel findByName(String name) throws DataAccessException {
        Hotel hotel = null;
        try {
            hotel = hotelDao.findByName(name);
        } catch (DataAccessException dae) {
        };
        return hotel;
    }

    @Override
    public List<Hotel> findAll() throws DataAccessException {
        List<Hotel> hotels = new ArrayList<>();
        try {
            hotels.addAll(hotelDao.findAll());
        } catch (DataAccessException dae) {
        };
        return Collections.unmodifiableList(hotels);
    }

    @Override
    public List<Hotel> findByAdress(String address) {
        List<Hotel> hotels = new ArrayList<>();
        try {
            hotels.addAll(hotelDao.findByAdress(address));
        } catch (DataAccessException dae) {
        };
        return Collections.unmodifiableList(hotels);
    }

    @Override
    public List<Room> findFreeRoomInRange(Hotel hotel, Date start, Date end) {
        List<Room> bookedRooms = new ArrayList<Room>();
        List<Room> roomsInHotel = new ArrayList<Room>();

        bookedRooms.addAll(roomService.findReservedRoomsAtSpecificTime(start, end));
        roomsInHotel.addAll(roomService.findByHotel(hotel.getId()));
        roomsInHotel.removeAll(bookedRooms);
        return Collections.unmodifiableList(roomsInHotel);
    }

    @Override
    public List<Room> findBookedRoomInRange(Hotel hotel, Date start, Date end) {
        List<Room> freeRooms = new ArrayList<Room>();
        List<Room> roomsInHotel = new ArrayList<Room>();

        freeRooms.addAll(roomService.findFreeRoomsAtSpecificTime(start, end));
        roomsInHotel.addAll(roomService.findByHotel(hotel.getId()));
        roomsInHotel.removeAll(freeRooms);
        return Collections.unmodifiableList(roomsInHotel);
    }
    
    @Override
    public List<Room> findRoomsWithReservation(Long id){
        List<Room> rooms = new ArrayList<Room>();
        List<Room> forRemoveRooms = new ArrayList<Room>();
        List<Reservation> reservations = new ArrayList<>();
        rooms.addAll(roomService.findByHotel(id));
        for(Room r:rooms){
            reservations.addAll(reservationDao.findAllReservationsOfRoom(r.getId()));
            if(reservations.isEmpty()){
                forRemoveRooms.add(r);
            }
            reservations.removeAll(rooms);

        }
        rooms.removeAll(forRemoveRooms);
        return Collections.unmodifiableList(rooms);

    }

}
