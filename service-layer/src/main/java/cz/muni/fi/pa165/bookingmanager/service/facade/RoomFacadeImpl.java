package cz.muni.fi.pa165.bookingmanager.service.facade;

import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.facade.RoomFacade;
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingService;
import cz.muni.fi.pa165.bookingmanager.service.RoomService;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
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
    public void updateRoom(RoomDTO roomDTO) {
        if (roomDTO == null) {
            throw new IllegalArgumentException("Room is null");
        }
        if (roomService.findById(roomDTO.getId()) == null) {
            throw new IllegalArgumentException("Room does not exist");
        }
        Room mappedRoom = beanMappingService.mapTo(roomDTO, Room.class);
        roomService.updateRoom(mappedRoom);

    }

    @Override
    public void deleteRoom(Long RoomId) {
        if (RoomId == null) {
            throw new IllegalArgumentException("Room ID is null");
        }
        if (roomService.findById(RoomId) == null) {
            throw new IllegalArgumentException("Room does not exist");
        }
        roomService.deleteRoom(roomService.findById(RoomId));
    }

    @Override
    public RoomDTO getRoomById(Long RoomId) {
        if (RoomId == null) {
            throw new IllegalArgumentException("Room ID is null");
        }
        if (roomService.findById(RoomId) == null) {
            throw new IllegalArgumentException("Room does not exist");
        }
        return beanMappingService.mapTo(roomService.findById(RoomId), RoomDTO.class);
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        return beanMappingService.mapTo(roomService.findAll(), RoomDTO.class);
    }

}
