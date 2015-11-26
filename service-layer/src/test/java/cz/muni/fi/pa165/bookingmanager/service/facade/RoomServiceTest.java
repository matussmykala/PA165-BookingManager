package cz.muni.fi.pa165.bookingmanager.service.facade;

import cz.muni.fi.pa165.bookingmanager.dao.RoomDao;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.service.RoomServiceImpl;
import cz.muni.fi.pa165.bookingmanager.service.config.ServiceConfiguration;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Test of RoomService class
 * 
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class RoomServiceTest{
    
    @Mock
    private RoomDao roomDao;
        
    @Autowired
    @InjectMocks
    private RoomServiceImpl roomServiceImpl;
    
      
    private Room room1;
    private Room room2;
    private Room room3;
    
    @Before
    public void createRoom(){
        
        MockitoAnnotations.initMocks(this);
        room1 = new Room();
        room2 = new Room();
        room3 = new Room();

        room1.setName("57");
        room1.setNumberOfBeds(3);
        room1.setPrice(new BigDecimal("80.00"));

        room2.setName("120");
        room2.setNumberOfBeds(1);
        room2.setPrice(new BigDecimal("150.00"));
        
        room3.setName("58");
        room3.setNumberOfBeds(3);
        room3.setPrice(new BigDecimal("80.00"));
    }
    
    @Test
    public void createRoomTest(){
        doNothing().when(roomDao).create(any(Room.class));
        roomServiceImpl.createRoom(room1);
        verify(roomDao).create(room1);
    } 
    
    @Test
    public void findbyIdTest(){
        when(roomDao.findById(any(Long.class))).thenReturn(room1);
        roomServiceImpl.findById((long) 0);
        verify(roomDao).findById(anyLong());
    }
    
    @Test
    public void updateRoomTest(){
        doNothing().when(roomDao).update(any(Room.class));
        room1.setName("Zmenena izba");        
        roomServiceImpl.updateRoom(room1);
        verify(roomDao).update(any(Room.class));
    }
    
    @Test
    public void deleteRoomTest(){
        doAnswer(invocationOnMock -> {
            room1=null;
            return null;
        }).when(roomDao).delete(any(Room.class));
        assertNotEquals(room1, null);
        roomServiceImpl.deleteRoom(room1);
        verify(roomDao).delete(any(Room.class));
        assertEquals(room1, null);
    }
    
    @Test
    public void findAllTest(){
        List<Room> list = new ArrayList<>();
        list.add(room1);
        list.add(room2);
        list.add(room3);
        when(roomDao.findAll()).thenReturn(list);
        assertEquals((roomServiceImpl.findAll()).size(), 3);
        verify(roomDao).findAll();
    }
    
    @Test
    public void findByPriceTest(){
        List<Room> list = new ArrayList<>();
        list.add(room1);
        list.add(room3);
        BigDecimal price = new BigDecimal("25.0");
        when(roomDao.findRoomByPrice(any(BigDecimal.class))).thenReturn(list);
        assertEquals((roomServiceImpl.findByPrice(price)).size(), 2);
        verify(roomDao).findRoomByPrice(any(BigDecimal.class));
    }
    
    @Test
    public void findByNumberOfBedsTest(){
        List<Room> list = new ArrayList<>();
        list.add(room1);
        list.add(room3);
        int numberOfBeds = 3;
        when(roomDao.findRoomByNumberOfBeds(anyInt())).thenReturn(list);
        assertEquals((roomServiceImpl.findByNumberOfBeds(numberOfBeds)).size(), 2);
        verify(roomDao).findRoomByNumberOfBeds(anyInt());
    }
    
    
    @Test
    public void changeRoomPriceTest(){

        BigDecimal price = new BigDecimal("99.0");

        doAnswer(invocationOnMock -> {
            room1.setPrice(price);
            return null;
        }).when(roomDao).update(any(Room.class));
        assertNotEquals(room1.getPrice(), price);
        roomServiceImpl.changeRoomPrice(room1, price);
        verify(roomDao).update(any(Room.class));
        assertEquals(room1.getPrice(), price);

        
    }  
    
    @Test
    public void changeNumberOfBedsTest(){

        int numberOfBeds = 34;

        doAnswer(invocationOnMock -> {
            room1.setNumberOfBeds(numberOfBeds);
            return null;
        }).when(roomDao).update(any(Room.class));
        assertNotEquals(room1.getNumberOfBeds(), numberOfBeds);
        roomServiceImpl.changeNumberOfBeds(room1,numberOfBeds);
        verify(roomDao).update(any(Room.class));
        assertEquals(room1.getNumberOfBeds(), numberOfBeds);
        
    }   
}
