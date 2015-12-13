package cz.muni.fi.pa165.bookingmanager.service.facade;

import cz.muni.fi.pa165.bookingmanager.dto.HotelCreateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.HotelDTO;
import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import cz.muni.fi.pa165.bookingmanager.facade.HotelFacade;
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingService;
import cz.muni.fi.pa165.bookingmanager.service.HotelService;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ivet
 */
@Service
@Transactional
public class HotelFacadeImpl implements HotelFacade{

    @Inject
    private HotelService hotelService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public Long createHotel(HotelCreateDTO hotelCreateDTO) {
        if (hotelCreateDTO == null) {
            throw new IllegalArgumentException("hotelCreateDTO is null");
        }
        Hotel mappedHotel = beanMappingService.mapTo(hotelCreateDTO, Hotel.class);
        //set Date to entity
        Date now = new Date();
        mappedHotel.setLastUpdateDay(now);
        hotelService.createHotel(mappedHotel);
        return mappedHotel.getId();
    }

    @Override
    public void updateHotel(HotelDTO hotelDTO) {
        if (hotelDTO == null) {
            throw new IllegalArgumentException("HotelId is null");
        }
        if (hotelService.findById(hotelDTO.getId()) == null) {
           throw new IllegalArgumentException("Hotel does not exist");
        }
        Hotel mappedHotel = beanMappingService.mapTo(hotelDTO, Hotel.class);
        //set Date to entity
        Date now = new Date();
        mappedHotel.setLastUpdateDay(now);
        hotelService.updateHotel(mappedHotel);
    }

    @Override
    public void deleteHotel(Long hotelId) {
        if (hotelId == null) {
            throw new IllegalArgumentException("HotelId is null");
        }
       if (hotelService.findById(hotelId) == null) {
            throw new IllegalArgumentException("Hotel does not exist");
       }
        hotelService.deleteHotel(hotelService.findById(hotelId));
    }

    @Override
    public HotelDTO getHotelById(Long hotelId) {
        if (hotelId == null) {
            throw new IllegalArgumentException("HotelId is null");
        }
        if (hotelService.findById(hotelId) == null) {
            throw new IllegalArgumentException("Hotel does not exist");
        }
        return beanMappingService.mapTo(hotelService.findById(hotelId), HotelDTO.class);
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        return beanMappingService.mapTo(hotelService.findAll(), HotelDTO.class);
    }

    @Override
    public HotelDTO findByName(String name) {
        if(name==null){
            throw new IllegalArgumentException("Name is null");
        }
            return beanMappingService.mapTo(hotelService.findByName(name), HotelDTO.class);
    }

    @Override
    public List<HotelDTO> findByAddress(String address) {
            if(address==null){
            throw new IllegalArgumentException("Address is null");
        }
            return beanMappingService.mapTo(hotelService.findByAdress(address), HotelDTO.class);
    }

    @Override
    public List<RoomDTO> findFreeRoomInRange(HotelDTO hotelDTO, Date start, Date end) {
        if (hotelDTO == null) {
            throw new IllegalArgumentException("HotelId is null");
        }
        if (hotelDTO.getId() == null) {
            throw new IllegalArgumentException("Hotel does not exist");
        }
        Hotel hotel = beanMappingService.mapTo(hotelDTO,Hotel.class);

        return beanMappingService.mapTo(hotelService.findFreeRoomInRange(hotel, start, end), RoomDTO.class);
    }

    @Override
    public List<RoomDTO> findBookedRoomInRange(HotelDTO hotelDTO, Date start, Date end) {
        if (hotelDTO == null) {
            throw new IllegalArgumentException("HotelId is null");
        }
        if (hotelDTO.getId() == null) {
            throw new IllegalArgumentException("Hotel does not exist");
        }
        Hotel hotel = beanMappingService.mapTo(hotelDTO,Hotel.class);

        return beanMappingService.mapTo(hotelService.findBookedRoomInRange(hotel, start, end), RoomDTO.class);
    }
    
   
    public HotelFacadeImpl(HotelService hotelService, BeanMappingService beanMappingService){
        this.hotelService=hotelService;
        this.beanMappingService=beanMappingService;
    }
    public HotelFacadeImpl(){

    }

    
  }
