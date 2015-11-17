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
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ivet
 */
public class HotelFacadeImpl implements HotelFacade{

    final static Logger log = LoggerFactory.getLogger(HotelFacadeImpl.class);
    
    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public Long createHotel(HotelCreateDTO hotelCreateDTO) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelCreateDTO.getName());
        hotel.setAddress(hotelCreateDTO.getAdress());
        hotelService.createHotel(hotel);
        return hotel.getId();
    }

    @Override
    public void deleteHotel(Long HotelId) {
        hotelService.deleteHotel(hotelService.findById(HotelId));
    }

    @Override
    public HotelDTO getHotelById(Long id) {
       return beanMappingService.mapTo(hotelService.findById(id), HotelDTO.class);
    }

    @Override
    public List<HotelDTO> getAllHotels() {
       return beanMappingService.mapTo(hotelService.findAll(), HotelDTO.class);
    }

 }
