package cz.muni.fi.pa165.bookingmanager.service.facade;

import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.facade.RoomFacade;
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingService;
import cz.muni.fi.pa165.bookingmanager.service.RoomService;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of RoomFacade interface
 * 
 * @author Martin Cuchran
 */

@Service
@Transactional
public class RoomFacadeImpl implements RoomFacade{

    final static Logger log = LoggerFactory.getLogger(RoomFacadeImpl.class);

    @Inject
    private RoomService roomService;

    @Inject
    private BeanMappingService beanMappingService;

  
    
    
    
    @Override
    public void deleteRoom(Long RoomId) {
        if (RoomId == null) {
            throw new IllegalArgumentException("Room ID is null");
        }
        roomService.deleteRoom(roomService.findById(RoomId));
    }
    
    

    @Override
    public void createRoom(RoomDTO roomCreateDTO) {
        if (roomCreateDTO == null) {
            throw new IllegalArgumentException("roomCreateDTO is null");
        }

        Room mappedRoom = beanMappingService.mapTo(roomCreateDTO, Room.class);
        Room room = roomService.createRoom(mappedRoom);
        //return room.getId();
    }
    
    @Override
    public RoomDTO getRoomById(Long RoomId) {
        if (RoomId == null) {
            throw new IllegalArgumentException("Room ID is null");
        }
        Room room = roomService.findById(RoomId);
        
        if (room == null) {
            throw new IllegalArgumentException("Room does not exist");
        }
        return beanMappingService.mapTo(room, RoomDTO.class);
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        return beanMappingService.mapTo(roomService.findAll(), RoomDTO.class);
    }
    
    @Override
    public void changeRoomPrice(Long roomId, BigDecimal newPrice) {
        if(roomId == null){
            throw new IllegalArgumentException("roomId is null");
        }
        if(newPrice.compareTo(new BigDecimal("0.0"))== -1){
            throw new IllegalArgumentException("newPrice is less than 0");
        }
        roomService.changeRoomPrice(roomService.findById(roomId), newPrice);
    }

    @Override
    public void changeRoomNumberOfBeds(Long roomId, int newNumberOfBeds) {
        if(roomId == null){
            throw new IllegalArgumentException("roomId is null");
        }
        if(newNumberOfBeds < 0){
            throw new IllegalArgumentException("newNumberOfBeds is less than 0");
        }
        roomService.changeNumberOfBeds(roomService.findById(roomId), newNumberOfBeds);
    }

    @Override
    public List<RoomDTO> getRoomsByPrice(BigDecimal price) {
        if(price == null){
            throw new IllegalArgumentException("price is null");
        }
        return beanMappingService.mapTo(roomService.findByPrice(price), RoomDTO.class);
    }

    @Override
    public List<RoomDTO> getRoomsByNumberOfBeds(int numberOfBeds) {
        
        if(numberOfBeds < 0){
            throw new IllegalArgumentException("numberOfBeds is less than 0");
        }
        return beanMappingService.mapTo(roomService.findByNumberOfBeds(numberOfBeds), RoomDTO.class);
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    public void setBeanMappingService(BeanMappingService beanMappingService) {
        this.beanMappingService = beanMappingService;
    }
}
