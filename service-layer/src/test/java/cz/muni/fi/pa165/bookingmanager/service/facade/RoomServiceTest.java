package cz.muni.fi.pa165.bookingmanager.service.facade;

import cz.muni.fi.pa165.bookingmanager.dao.ReservationDao;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDao;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.service.ReservationService;
import cz.muni.fi.pa165.bookingmanager.service.RoomService;
import cz.muni.fi.pa165.bookingmanager.service.config.ServiceConfiguration;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Test of Room Service layer
 *
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class RoomServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

    @Mock
    private RoomDao roomDao;

    @Mock
    private ReservationDao reservationDao;

    @Autowired
    @InjectMocks
    private RoomService roomService;

    @Autowired
    @InjectMocks
    private ReservationService reservationService;

    private Room room1;
    private Room room2;
    private Room room3;
    private Reservation reservation1;
    private Reservation reservation2;
    private Hotel hotel1;
    private Hotel hotel2;
    private Date nextMonthFirstDay;
    private Date nextMonthLastDay;

    /**
     * Prepare example objects
     */
    @Before
    public void createRoom(){

        MockitoAnnotations.initMocks(this);
        room1 = new Room();
        room2 = new Room();
        room3 = new Room();

        room1.setId((long) 1);
        room1.setName("57");
        room1.setNumberOfBeds(3);
        room1.setPrice(new BigDecimal("80.00"));
        room1.setHotel(hotel1);

        room2.setId((long) 2);
        room2.setName("120");
        room2.setNumberOfBeds(1);
        room2.setPrice(new BigDecimal("150.00"));
        room2.setHotel(hotel2);

        room3.setId((long) 3);
        room3.setName("58");
        room3.setNumberOfBeds(3);
        room3.setPrice(new BigDecimal("80.00"));

        hotel1 = new Hotel();
        hotel2 = new Hotel();

        hotel1.setName("Hotel 1");
        hotel1.setAddress("Hotel address 1");

        hotel2.setName("Hotel 2");
        hotel2.setAddress("Hotel address 2");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        nextMonthFirstDay = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        nextMonthLastDay = calendar.getTime();

        reservation1 = new Reservation();
        reservation2 = new Reservation();

        reservation1.setStartOfReservation(nextMonthFirstDay);
        reservation1.setEndOfReservation(nextMonthLastDay);
        reservation1.setRoom(room1);


        reservation2.setStartOfReservation(nextMonthFirstDay);
        reservation2.setEndOfReservation(nextMonthLastDay);
        reservation2.setRoom(room2);
    }

    /**
     * Test of creating room
     */
    @Test
    public void createRoomTest(){

        doAnswer(invocationOnMock -> {
            room1.setId((long) 1);
            return null;
        }).when(roomDao).create(any(Room.class));
        assertNotEquals(room1.getId(), new Long("2"));
        roomService.createRoom(room1);
        verify(roomDao).create(any(Room.class));
        assertEquals(room1.getId(), new Long("1"));
    }

    /**
     * Test of finding room by id
     */
    @Test
    public void findbyIdTest(){
        when(roomDao.findById(any(Long.class))).thenReturn(room1);
        assertEquals(roomService.findById((long) 0),room1);
        verify(roomDao).findById(anyLong());
    }

    /**
     * Test of updating room
     */
    @Test
    public void updateRoomTest(){
        String roomName = "Zmenenea izba";
        doAnswer(invocationOnMock -> {
            room1.setName(roomName);;
            return null;
        }).when(roomDao).update(any(Room.class));
        assertNotEquals(room1.getName(), roomName);
        roomService.updateRoom(room1);
        assertEquals(room1.getName(), roomName);
        verify(roomDao).update(any(Room.class));
    }

    /**
     * Test of deleting room
     */
    @Test
    public void deleteRoomTest(){
        doAnswer(invocationOnMock -> {
            room1=null;
            return null;
        }).when(roomDao).delete(any(Room.class));
        assertNotEquals(room1, null);
        roomService.deleteRoom(room1);
        verify(roomDao).delete(any(Room.class));
        assertEquals(room1, null);
    }

    /**
     * Test of finding all rooms
     */
    @Test
    public void findAllTest(){
        List<Room> list = new ArrayList<>();
        list.add(room1);
        list.add(room2);
        list.add(room3);
        when(roomDao.findAll()).thenReturn(list);
        assertEquals((roomService.findAll()).size(), 3);
        verify(roomDao).findAll();
    }

    /**
     * Test of finding room by price
     */
    @Test
    public void findByPriceTest(){
        List<Room> list = new ArrayList<>();
        list.add(room1);
        list.add(room3);
        BigDecimal price = new BigDecimal("25.0");
        when(roomDao.findRoomByPrice(any(BigDecimal.class))).thenReturn(list);
        assertEquals((roomService.findByPrice(price)).size(), 2);
        verify(roomDao).findRoomByPrice(any(BigDecimal.class));
    }

    /**
     * Test of finfing rooms by number of beds
     */
    @Test
    public void findByNumberOfBedsTest(){
        List<Room> list = new ArrayList<>();
        list.add(room1);
        list.add(room3);
        int numberOfBeds = 3;
        when(roomDao.findRoomByNumberOfBeds(anyInt())).thenReturn(list);
        assertEquals((roomService.findByNumberOfBeds(numberOfBeds)).size(), 2);
        verify(roomDao).findRoomByNumberOfBeds(anyInt());
    }

    /**
     * Test of changing price of room
     */
    @Test
    public void changeRoomPriceTest(){

        BigDecimal price = new BigDecimal("99.0");

        doAnswer(invocationOnMock -> {
            room1.setPrice(price);
            return null;
        }).when(roomDao).update(any(Room.class));
        assertNotEquals(room1.getPrice(), price);
        roomService.changeRoomPrice(room1, price);
        verify(roomDao).update(any(Room.class));
        assertEquals(room1.getPrice(), price);
    }

    /**
     * Test of changing number of beds in room
     */
    @Test
    public void changeNumberOfBedsTest(){

        int numberOfBeds = 34;

        doAnswer(invocationOnMock -> {
            room1.setNumberOfBeds(numberOfBeds);
            return null;
        }).when(roomDao).update(any(Room.class));
        assertNotEquals(room1.getNumberOfBeds(), numberOfBeds);
        roomService.changeNumberOfBeds(room1, numberOfBeds);
        verify(roomDao).update(any(Room.class));
        assertEquals(room1.getNumberOfBeds(), numberOfBeds);
    }

    /**
     * Test of finding rooms by hotel
     */
    @Test
    public void findByHotelTest(){
        List<Room> list = new ArrayList<>();
        list.add(room1);
        Hotel hotel = new Hotel();
        when(roomDao.findByHotel(any(Hotel.class))).thenReturn(list);
        assertEquals((roomService.findByHotel(hotel)).size(), 1);
        verify(roomDao).findByHotel(any(Hotel.class));
    }

    /**
     * Test of finding reserved rooms at specific time
     */
    @Test
    public void findReservedRoomsAtSpecificTimeTest(){

        List<Reservation> list = new ArrayList<>();
        list.add(reservation1);
        list.add(reservation2);

        when(reservationDao.findReservationsOfTime(any(Date.class),any(Date.class))).thenReturn(list);
        List<Room> reservedRooms = roomService.findReservedRoomsAtSpecificTime(nextMonthFirstDay, nextMonthLastDay);

        verify(reservationDao).findReservationsOfTime(any(Date.class),any(Date.class));
        assertTrue(reservedRooms.contains(room1));
        assertTrue(reservedRooms.contains(room2));

    }

    /**
     * Test of finding free rooms at specific time
     */
    @Test
    public void findFreeRoomsAtSpecificTimeTest(){
        List<Reservation> list = new ArrayList<>();
        list.add(reservation1);
        list.add(reservation2);

        when(reservationDao.findReservationsOfTime(any(Date.class),any(Date.class))).thenReturn(list);
        List<Room> reservedRooms = roomService.findFreeRoomsAtSpecificTime(nextMonthFirstDay, nextMonthLastDay);

        verify(reservationDao).findReservationsOfTime(any(Date.class),any(Date.class));
        assertFalse(reservedRooms.contains(room1));
        assertFalse(reservedRooms.contains(room2));
    }
}
