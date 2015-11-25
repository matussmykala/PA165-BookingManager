package cz.muni.fi.pa165.bookingmanager.service;

import cz.muni.fi.pa165.bookingmanager.dao.RoomDao;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test of RoomService class
 * 
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class RoomServiceImplTest{
    
    @Mock
    private RoomDao roomDao;
        
    @Autowired
    @InjectMocks
    private RoomServiceImpl roomService;
    
      
    private Room room1;
    private Room room2;
    
    @Before
    public void createRoom(){
        
        MockitoAnnotations.initMocks(this);
        room1 = new Room();
        room2 = new Room();

        room1.setName("57");
        room1.setNumberOfBeds(3);
        room1.setPrice(new BigDecimal("80.00"));

        room2.setName("120");
        room2.setNumberOfBeds(1);
        room2.setPrice(new BigDecimal("150.00"));
    }
    
    @Test
    public void createRoomTest(){
        doNothing().when(roomDao).create(any(Room.class));
        roomService.createRoom(room1);
        verify(roomDao).create(room1);
    } 
    
    @Test
    public void findbyIdTest(){
        when(roomDao.findById(any(Long.class))).thenReturn(room1);
        roomService.findById((long) 0);
        verify(roomDao).findById(anyLong());
    }
    
    @Test
    public void updateRoomTest(){
        doNothing().when(roomDao).update(any(Room.class));
        room1.setName("Zmenena izba");        
        roomService.updateRoom(room1);
        verify(roomDao).update(any(Room.class));
    }
    
    @Test
    public void deleteRoomTest(){
        doNothing().when(roomDao).delete(any(Room.class));
        roomService.deleteRoom(room1);
        verify(roomDao).delete(any(Room.class));
    }
    
    @Test
    public void findAllTest(){
        List<Room> list = new ArrayList<>();
        when(roomDao.findAll()).thenReturn(list);
        roomService.findAll();
        verify(roomDao).findAll();
    }
    /*
    @Test
    public void changeRoomPriceTest(){
        doNothing().when(roomDao).update(any(Room.class));
        room1.setName("Zmenena izba");        
        roomService.updateRoom(room1);
        verify(roomDao).update(any(Room.class));
    }
    */
}
