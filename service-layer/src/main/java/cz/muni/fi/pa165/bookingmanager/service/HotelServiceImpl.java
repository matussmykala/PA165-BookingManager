/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.service;

import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.dao.HotelDao;
import cz.muni.fi.pa165.bookingmanager.service.facade.HotelFacadeImpl;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService{
    
    final static Logger log = LoggerFactory.getLogger(HotelFacadeImpl.class);
    
    @Autowired
    private HotelDao hotelDao;
    
    

    @Override
    public Hotel createHotel(Hotel hotel) {
        hotelDao.create(hotel);
        return hotel;
    }

    @Override
    public void deleteHotel(Hotel hotel) {
        hotelDao.delete(hotel);
    }

    @Override
    public Hotel findById(Long id) {
        return hotelDao.findById(id);
    }
    
    @Override
    public Hotel findByName(String name){
        return hotelDao.findByName(name);
    }

    @Override
    public List<Hotel> findAll() {
        return Collections.unmodifiableList(hotelDao.findAll());
    }

     
}
