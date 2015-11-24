
package cz.muni.fi.pa165.bookingmanager.service;

import cz.muni.fi.pa165.bookingmanager.dao.RoomDao;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Cuchran
 */
@Service
public class RoomServiceImpl implements RoomService{
    final static Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);
    
    @Autowired
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
    public List<Room> findAllRoomsByPrice(BigDecimal price, Currency currency) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Room> findAllRoomsByNumberOfBeds(int numberOfBeds) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Room> findAllRoomsByHotel(Hotel hotel) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
