
package cz.muni.fi.pa165.bookingmanager.service;

import cz.muni.fi.pa165.bookingmanager.dao.RoomDao;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * Implementation of RoomService interface
 * 
 * @author Martin Cuchran
 */
@Service
public class RoomServiceImpl implements RoomService{
    final static Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);
    
    @Inject
    private RoomDao roomDao;
    
    @Override
    public Room createRoom(Room room) throws DataAccessException{
        try{
            roomDao.create(room);
        }catch(DataAccessException ex){};
        return room;
    }
    
    @Override
    public void updateRoom(Room room) throws DataAccessException{
        try{
            roomDao.update(room);
        }catch(DataAccessException ex){};
    }

    @Override
    public void deleteRoom(Room room) throws DataAccessException{
        try{
            roomDao.delete(room);
        }catch(DataAccessException ex){};
    }

    @Override
    public Room findById(Long id) throws DataAccessException {
        Room room = null;
        try{
            room = roomDao.findById(id);
        }catch(DataAccessException ex){};
        return room;
    }

    @Override
    public List<Room> findAll() throws DataAccessException {
        List<Room> rooms = new ArrayList<>();
        try{
            rooms.addAll(roomDao.findAll());
        }catch(DataAccessException ex){};
        return Collections.unmodifiableList(rooms);
    }

    @Override
    public void changeRoomPrice(Room room, BigDecimal price) throws DataAccessException {
        room.setPrice(price);
        try{
            roomDao.update(room);
        }catch(DataAccessException ex){};        
    }
    
    @Override
    public void changeNumberOfBeds(Room room, int numberOfBeds) throws DataAccessException {
        room.setNumberOfBeds(numberOfBeds);        
        try{
            roomDao.update(room);
        }catch(DataAccessException ex){};        
    }    

    @Override
    public List<Room> findByPrice(BigDecimal price) throws DataAccessException {
        List<Room> rooms = new ArrayList<>();
        try{
            rooms.addAll(roomDao.findRoomByPrice(price));
        }catch(DataAccessException ex){};
        return Collections.unmodifiableList(rooms);
    }

    @Override
    public List<Room> findByNumberOfBeds(int numberOfBeds) throws DataAccessException {
        List<Room> rooms = new ArrayList<>();
        try{
            rooms.addAll(roomDao.findRoomByNumberOfBeds(numberOfBeds));
        }catch(DataAccessException ex){};
        return Collections.unmodifiableList(rooms);
    }
}
