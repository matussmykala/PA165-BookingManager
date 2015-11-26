/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.service.facade;

import cz.muni.fi.pa165.bookingmanager.dao.HotelDao;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.service.HotelService;
import cz.muni.fi.pa165.bookingmanager.service.RoomService;
import cz.muni.fi.pa165.bookingmanager.service.config.ServiceConfiguration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author ivet
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class HotelServiceTest extends AbstractJUnit4SpringContextTests{
    
    @Mock
    private HotelDao hotelDao;
    
      
    @Autowired
    @InjectMocks
    private HotelService hotelService;
    
    @Mock
    private RoomService roomService;

    private Hotel hotel1;
    private Hotel hotel2;

    private Room room1;
    private Room room2;
    private Room room3;
    
  
    
    Date date1;
    Date date2;
    


    
    @Before
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);

        room1 = new Room();
        room1.setName("001");
        room1.setNumberOfBeds(2);
        room1.setHotel(hotel1);

        room2 = new Room();
        room2.setName("002");
        room2.setNumberOfBeds(3);
        room2.setHotel(hotel1);

        room3 = new Room();
        room3.setName("010");
        room3.setNumberOfBeds(2);
        room3.setHotel(hotel1);

        hotel1 = new Hotel();
        hotel1.setName("Voronez");
        hotel1.setAddress("Brno");
        hotel1.addRoom(room1);
        hotel1.addRoom(room2);
        hotel1.addRoom(room3);

        hotel2 = new Hotel();
        hotel2.setName("Park Hotel");
        hotel2.setAddress("Praha");
        hotel2.setRooms(null);
        
           

      
        
        
        
        

    }
    
    @Test
    public void testCreateHotel(){
        doNothing().when(hotelDao).create(any(Hotel.class));
        
        hotelService.createHotel(hotel1);
        verify(hotelDao).create(hotel1);
        
    }
    
    @Test
    public void testUpdateHotel(){
        doNothing().when(hotelDao).update(any(Hotel.class));
        
        hotel1.setName("Hotel Dom Sportu");
        hotel1.setAddress("Bratislava");
        
        hotelService.updateHotel(hotel1);
        verify(hotelDao).update(hotel1);
    }
    
    @Test
    public void testDeleteHotel(){
        doNothing().when(hotelDao).delete(any(Hotel.class));
        
        hotelService.deleteHotel(hotel1);
        verify(hotelDao).delete(hotel1);
        
    }
    
    @Test
    public void testGetHotelById(){
        Long id = hotel1.getId();
        when(hotelDao.findById(any(Long.class))).thenReturn(hotel1);
        
        hotelService.findById(id);
        verify(hotelDao).findById(id);
        
    }
    
    @Test
    public void testGetAllHotels() {
        List<Hotel> list = new ArrayList<>();

        list.add(hotel1);
        list.add(hotel2);
        when(hotelDao.findAll()).thenReturn(list);
        List<Hotel> dtoList = hotelService.findAll();
        assertEquals(dtoList.size(), 2);
    }
    
    @Test
    public void testGetHotelByName(){
        Hotel hotel;
        when(hotelDao.findByName("Voronez")).thenReturn(hotel1);
        hotel = hotelService.findByName("Voronez");
        assertEquals(hotel1.getName(),"Voronez");
    }
    
    @Test
    public void testGetHotelByAdress(){
        List<Hotel> result = new ArrayList<>();
        result.add(hotel1);
        when(hotelDao.findByAdress("Brno")).thenReturn(result);
        List<Hotel> dtoList = hotelService.findByAdress("Brno");
        assertEquals(dtoList.size(), 1);
    }
    
    @Test
    public void testFindFreeRoomInRange(){
        List<Room> rooms = new ArrayList<>();
        List<Room> reservedRooms = new ArrayList<>();
        List<Room> freeRoomsInHotel = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        
        reservedRooms.add(room1);
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 5);
        date1 = calendar.getTime();
        calendar.set(Calendar.DATE, 25);
        date2 = calendar.getTime();
        
        when(roomService.findByHotel(hotel1)).thenReturn(rooms);
        when(roomService.findReservedRoomsAtSpecificTime(date1, date2)).thenReturn(reservedRooms);
        
        freeRoomsInHotel = hotelService.findFreeRoomInRange(hotel1, date1, date2);
        assertEquals(freeRoomsInHotel.size(), 2);
         
    
    }
    
    
    @Test
    public void testFindReservedRoomInRange(){
        List<Room> rooms = new ArrayList<>();
        List<Room> freeRooms = new ArrayList<>();
        List<Room> reservedRoomsInHotel = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        
        freeRooms.add(room1);
        freeRooms.add(room2);
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 5);
        date1 = calendar.getTime();
        calendar.set(Calendar.DATE, 25);
        date2 = calendar.getTime();
        
        when(roomService.findByHotel(hotel1)).thenReturn(rooms);
        when(roomService.findFreeRoomsAtSpecificTime(date1, date2)).thenReturn(freeRooms);
        
        reservedRoomsInHotel = hotelService.findBookedRoomInRange(hotel1, date1, date2);
        assertEquals(reservedRoomsInHotel.size(), 1);
         
    
    }
    
    
}