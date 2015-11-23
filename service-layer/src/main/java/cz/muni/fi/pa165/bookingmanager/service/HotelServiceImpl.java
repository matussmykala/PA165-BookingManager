/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.service;

import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.dao.HotelDao;
import java.util.ArrayList;
import java.util.Collections;
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
    
    @Override
    public Hotel createHotel(Hotel hotel) throws DataAccessException{
        try{
            hotelDao.create(hotel);
        }catch(DataAccessException dae){};
        return hotel;
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

     
}
