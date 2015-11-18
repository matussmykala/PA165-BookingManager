/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.service.facade;
import cz.muni.fi.pa165.bookingmanager.dto.HotelCreateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.HotelDTO;
import cz.muni.fi.pa165.bookingmanager.facade.HotelFacade;
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingService;
import cz.muni.fi.pa165.bookingmanager.service.HotelService;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ivet
 */

@Service
@Transactional
public class HotelFacadeImpl implements HotelFacade{

    final static Logger log = LoggerFactory.getLogger(HotelFacadeImpl.class);
    
    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public Long createHotel(HotelCreateDTO hotelCreateDTO) {
        if(hotelCreateDTO==null) {
            throw new IllegalArgumentException("hotelCreateDTO is null");
        }
        if(hotelCreateDTO.getId()!=null){
            throw new IllegalArgumentException("hotelCreateDTO already exists");
        }
        Hotel hotel = new Hotel();
        hotel.setName(hotelCreateDTO.getName());
        hotel.setAddress(hotelCreateDTO.getAdress());
        hotelService.createHotel(hotel);
        return hotel.getId();
    }

    @Override
    public void deleteHotel(Long HotelId) {
        if(HotelId==null){
            throw new IllegalArgumentException("HotelId is null");
        }
        if(hotelService.findById(HotelId)==null){
            throw new IllegalArgumentException("Hotel does not exist");
        }
        hotelService.deleteHotel(hotelService.findById(HotelId));
    }

    @Override
    public HotelDTO getHotelById(Long HotelId) {
        if(HotelId==null){
            throw new IllegalArgumentException("HotelId is null");
        }
        if(hotelService.findById(HotelId)==null){
            throw new IllegalArgumentException("Hotel does not exist");
        } 
       return beanMappingService.mapTo(hotelService.findById(HotelId), HotelDTO.class);
    }

    @Override
    public List<HotelDTO> getAllHotels() {
       return beanMappingService.mapTo(hotelService.findAll(), HotelDTO.class);
    }

 }
