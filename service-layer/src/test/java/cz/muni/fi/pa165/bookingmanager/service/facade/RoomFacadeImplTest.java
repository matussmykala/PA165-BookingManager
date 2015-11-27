package cz.muni.fi.pa165.bookingmanager.service.facade;

import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingService;
import cz.muni.fi.pa165.bookingmanager.service.RoomService;
import cz.muni.fi.pa165.bookingmanager.service.config.ServiceConfiguration;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Test of  implementation of Room Facade layer
 *
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class RoomFacadeImplTest extends AbstractJUnit4SpringContextTests{

    @Mock
    RoomService roomService;

    @Autowired
    BeanMappingService beanMappingService;

    RoomFacadeImpl roomFacade;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Room room1;
    private Room room2;

    private RoomDTO roomDTO;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        roomFacade = new RoomFacadeImpl();
        roomFacade.setRoomService(roomService);
        roomFacade.setBeanMappingService(beanMappingService);

        room1 = new Room();
        room2 = new Room();

        room1.setId((long) 1);
        room1.setHotel(new Hotel());
        room1.setName("Room1");
        room1.setNumberOfBeds(2);
        room1.setPrice(new BigDecimal("25.0"));

        room2.setId((long) 2);
        room2.setHotel(new Hotel());
        room2.setName("Room2");
        room2.setNumberOfBeds(3);
        room2.setPrice(new BigDecimal("35.0"));

        roomDTO = new RoomDTO();
        roomDTO.setId((long) 2);
        roomDTO.setName("Room1");
        roomDTO.setNumberOfBeds(2);
        roomDTO.setPrice(new BigDecimal("25.0"));
    }

    /**
     * Tests creation of room on facade layer
     */
    @Test
    public void createRoomTest() {
        doAnswer(invocationOnMock -> {
            room1.setId((long) 1);
            return null;
        }).when(roomService).createRoom(any(Room.class));
        assertNotEquals(room1.getId(), new Long("2"));
        roomFacade.createRoom(roomDTO);
        verify(roomService).createRoom(any(Room.class));
        assertEquals(room1.getId(), new Long("1"));
    }

    /**
     * Tests delete of room on facade layer
     */
    @Test
    public void deleteRoomTest(){
        doAnswer(invocationOnMock -> {
            room2=null;
            return null;
        }).when(roomService).deleteRoom(any(Room.class));
        assertNotEquals(room2,null);
        roomFacade.deleteRoom((long) 2);
        verify(roomService).deleteRoom(any(Room.class));
        assertEquals(room2,null);
    }

    /**
     * Tests obtaining of room by id on facade layer
     */
    @Test
    public void getRoomByIdTest(){
        when(roomService.findById(any(Long.class))).thenReturn(room1);
        RoomDTO dtoRoom = roomFacade.getRoomById((long) 1);
        assertEquals(dtoRoom.getName() , room1.getName());
        assertEquals(dtoRoom.getNumberOfBeds(), room1.getNumberOfBeds());
        assertEquals(dtoRoom.getPrice(), room1.getPrice());
        verify(roomService).findById(any(Long.class));
    }

    /**
     * Tests obtaining of all rooms on facade layer
     */
    @Test
    public void getAllRoomsTest(){
        List<Room> list = new ArrayList<>();
        list.add(room1);
        list.add(room2);
        when(roomService.findAll()).thenReturn(list);
        List<RoomDTO> dtoList = roomFacade.getAllRooms();
        assertEquals(dtoList.size(), 2);
        verify(roomService).findAll();
    }

    /**
     * Tests obtaining of rooms by number of beds on facade layer
     */
    @Test
    public void getRoomsByNumberOfBedsTest(){
        List<Room> list = new ArrayList<>();
        list.add(room1);
        when(roomService.findByNumberOfBeds(anyInt())).thenReturn(list);
        assertEquals((roomFacade.getRoomsByNumberOfBeds(1)).size(),1);
        verify(roomService).findByNumberOfBeds(anyInt());
    }

    /**
     * Tests obtaining of rooms by price on facade layer
     */
    @Test
    public void getRoomsByPriceTest(){
        List<Room> list = new ArrayList<>();
        list.add(room2);
        when(roomService.findByPrice(any(BigDecimal.class))).thenReturn(list);
        assertEquals((roomFacade.getRoomsByPrice(new BigDecimal("35.0"))).size(),1);
        verify(roomService).findByPrice(any(BigDecimal.class));
    }

    /**
     * Tests room number of beds change on facade layer
     */
    @Test
    public void changeRoomNumberOfBedsTest(){
        int numberOfBeds = 5;
        doAnswer(invocationOnMock -> {
            room1.setNumberOfBeds(numberOfBeds);
            return null;
        }).when(roomService).changeNumberOfBeds(any(Room.class), anyInt());
        assertNotEquals(room1.getNumberOfBeds(), numberOfBeds);
        roomFacade.changeRoomNumberOfBeds((long)1, numberOfBeds);
        assertEquals(room1.getNumberOfBeds(), numberOfBeds);
        verify(roomService).changeNumberOfBeds(any(Room.class), anyInt());
    }

    /**
     * Tests room price change on facade layer
     */
    @Test
    public void changeRoomPriceTest(){
        BigDecimal price = new BigDecimal("99.0");
        doAnswer(invocationOnMock -> {
            room1.setPrice(price);
            return null;
        }).when(roomService).changeRoomPrice(any(Room.class), any(BigDecimal.class));
        assertNotEquals(room1.getPrice(), price);
        roomFacade.changeRoomPrice((long)1, price);
        assertEquals(room1.getPrice(), price);
        verify(roomService).changeRoomPrice(any(Room.class), any(BigDecimal.class));
    }
}
