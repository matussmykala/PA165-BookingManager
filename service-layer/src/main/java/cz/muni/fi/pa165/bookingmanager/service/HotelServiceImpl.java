/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.service;

import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.dao.HotelDao;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDao;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDao;
import cz.muni.fi.pa165.bookingmanager.dto.ReservationDTO;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService{
    
    final static Logger log = LoggerFactory.getLogger(HotelServiceImpl.class);
    
    @Autowired
    private HotelDao hotelDao;
    
    @Autowired
    private RoomService roomService;
    
    @Autowired
    private ReservationService reservationService;
    
    @Override
    public void createHotel(Hotel hotel) throws DataAccessException{
        try{
            hotelDao.create(hotel);
        }catch(DataAccessException dae){};
    }
    
    @Override
    public void updateHotel(Hotel hotel) throws DataAccessException{
        try{
            hotelDao.update(hotel);
        }catch(DataAccessException dae){};
    }

    @Override
    public void deleteHotel(Hotel hotel) throws DataAccessException{
        try{
            hotelDao.delete(hotel);
        }catch(DataAccessException dae){};
    }

    @Override
    public Hotel findById(Long id) throws DataAccessException {
        Hotel hotel = null;
        try{
            hotel = hotelDao.findById(id);
        }catch(DataAccessException dae){};
        return hotel;
    }
    
    @Override
    public Hotel findByName(String name) throws DataAccessException{
        Hotel hotel = null;
        try{
            hotel = hotelDao.findByName(name);
        }catch(DataAccessException dae){};
        return hotel;
    }

    @Override
    public List<Hotel> findAll() throws DataAccessException {
        List<Hotel> hotels = new ArrayList<>();
        try{
            hotels.addAll(hotelDao.findAll());
        }catch(DataAccessException dae){};
        return Collections.unmodifiableList(hotels);
    }

    @Override
    public List<Hotel> findByAdress(String address) {
        List<Hotel> hotels = new ArrayList<>();
        try{
            hotels.addAll(hotelDao.findByAdress(address));
        }catch(DataAccessException dae){};
        return Collections.unmodifiableList(hotels);
    }

    @Override
    public List<Room> findFreeRoomInRange(Hotel hotel,Date start, Date end) {
          List<Reservation> reservationsInTime = new ArrayList<>();
          List<Room> bookedRooms = new ArrayList<>();
          List<Room> roomsOfHotel = new ArrayList<>();
          List<Room> reservedRoomsOutOfHotel = new ArrayList<>();
          List<Room> reservedRoomsOfHotel = new ArrayList<>();
          List<Room> freeRoomsOfHotel = new ArrayList<>();
          
          //we get all reservation in cercain time
          reservationsInTime.addAll(reservationService.getReservationsOfTime(start, end));
          
          //we get all reserved rooms
          for (Reservation reservation : reservationsInTime) {
              bookedRooms.add(reservation.getRoom());
          }
          //we get all rooms in hotel
          roomsOfHotel.addAll(roomService.findAllRoomsByHotel(hotel));
          
          //we get all reservedRoom which arent in hotel
          reservedRoomsOutOfHotel.addAll(bookedRooms);
          reservedRoomsOutOfHotel.removeAll(roomsOfHotel);
          
          //we get all reservedRoom in hotel
          reservedRoomsOfHotel.addAll(bookedRooms);
          reservedRoomsOfHotel.removeAll(reservedRoomsOutOfHotel);
          
          //we get all free rooms in hotel
          freeRoomsOfHotel.addAll(roomsOfHotel);
          freeRoomsOfHotel.removeAll(reservedRoomsOfHotel);
          
          return Collections.unmodifiableList(freeRoomsOfHotel);
          
         
         
       
    }

     
}
