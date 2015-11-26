package cz.muni.fi.pa165.bookingmanager.service.facade;


import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingService;
import cz.muni.fi.pa165.bookingmanager.service.CustomerService;
import cz.muni.fi.pa165.bookingmanager.service.RoomService;
import cz.muni.fi.pa165.bookingmanager.service.RoomService;
import cz.muni.fi.pa165.bookingmanager.service.config.ServiceConfiguration;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Test of  RoomFacadeImpl
 * 
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class RoomFacadeImplTest extends AbstractJUnit4SpringContextTests{
    

    @Mock
    private RoomService roomService;   
   
    @Autowired
    private BeanMappingService beanMappingService;

    //ugly peace of code, but wasn't able to do better way
    //----------------------------------------------------
    private RoomFacadeImpl roomFacade = new RoomFacadeImpl();
    //----------------------------------------------------

    private Room room1;
    private Room room2;

    @Before
    public void createRooms(){
        MockitoAnnotations.initMocks(this);

        //ugly peace of code, but wasn't able to do better way
        //----------------------------------------------------
        roomFacade.setRoomService(roomService);
        roomFacade.setBeanMappingService(beanMappingService);
        //----------------------------------------------------

        room1 = new Room();
        room2 = new Room();

        room1.setId((long) 2);
        room1.setHotel(new Hotel());
        room1.setName("Room1");
        room1.setNumberOfBeds(1);
        room1.setPrice(new BigDecimal("25.0"));
        //room1.setRoom(new Room());


        room2.setId((long) 2);
        room2.setHotel(new Hotel());
        room2.setName("Room2");
        room2.setNumberOfBeds(1);
        room2.setPrice(new BigDecimal("35.0"));
        //room2.setRoom(new Room());


    }
    
    @Test
    public void createRoomTest() {

        doAnswer(invocationOnMock -> {
            room1.setId((long) 1);
            return null;
        }).when(roomService).createRoom(any(Room.class));

        assertNotEquals(room1.getId(), new Long("1"));

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room1.getId());
        roomDTO.setName(room1.getName());
        roomDTO.setNumberOfBeds(room1.getNumberOfBeds());
        roomDTO.setPrice(room1.getPrice());

        roomFacade.createRoom(roomDTO);
        verify(roomService).createRoom(any(Room.class));

        assertEquals(room1.getId(), new Long("1"));
    }  
    
    @Test
    public void deleteRoomTest(){
        when(roomService.findById(any(Long.class))).thenReturn(room1);
        doNothing().when(roomService).deleteRoom(any(Room.class));
        roomFacade.deleteRoom((long) 1);
        verify(roomService).deleteRoom(any(Room.class));
    }
    
    @Test
    public void getRoomByIdTest(){
        when(roomService.findById(any(Long.class))).thenReturn(room1);
        RoomDTO dtoRoom = roomFacade.getRoomById((long) 1);

        assertEquals(dtoRoom.getName() , room1.getName());
        assertEquals(dtoRoom.getNumberOfBeds(), room1.getNumberOfBeds());
        assertEquals(dtoRoom.getPrice(), room1.getPrice());

        assertEquals(dtoRoom.getName() , room2.getName());
        assertEquals(dtoRoom.getNumberOfBeds(), room2.getNumberOfBeds());
        assertEquals(dtoRoom.getPrice(), room2.getPrice());

        verify(roomService).findById(any(Long.class));
    }
    
    @Test
    public void getAllRoomsTest(){
        List<Room> list = new ArrayList<>();

        list.add(room1);
        list.add(room2);
        when(roomService.findAll()).thenReturn(list);
        List<RoomDTO> dtoList = roomFacade.getAllRooms();
        assertEquals(dtoList.size(), 2);

        assertNotNull(dtoList.get(0));
        assertNotNull(dtoList.get(0).getName());
        assertNotNull(dtoList.get(0).getNumberOfBeds());

        assertEquals(dtoList.get(0).getName(), room1.getName());
        assertEquals(dtoList.get(0).getNumberOfBeds(), room1.getNumberOfBeds());


        assertEquals(dtoList.get(0).getName(), room2.getName());
        assertEquals(dtoList.get(0).getNumberOfBeds(), room2.getNumberOfBeds());

        verify(roomService).findAll();
    }
    
    @Test
    public void getRoomsByNumberOfBedsTest(){
        List<Room> list = new ArrayList<>();
        list.add(room1);
        list.add(room2);
        when(roomService.findByNumberOfBeds(anyInt())).thenReturn(list);
        roomFacade.getRoomsByNumberOfBeds(1);
        verify(roomService).findByNumberOfBeds(anyInt());
    }
    
    @Test
    public void getRoomsByPriceTest(){
        List<Room> list = new ArrayList<>();
        list.add(room1);
        list.add(room2);
        when(roomService.findByPrice(any(BigDecimal.class))).thenReturn(list);
        roomFacade.getRoomsByPrice(new BigDecimal("35.0"));
        verify(roomService).findByPrice(any(BigDecimal.class));
    }
}