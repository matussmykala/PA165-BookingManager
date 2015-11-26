/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.service.facade;


import cz.muni.fi.pa165.bookingmanager.dto.HotelCreateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.HotelDTO;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingService;
import cz.muni.fi.pa165.bookingmanager.service.HotelService;
import cz.muni.fi.pa165.bookingmanager.service.config.ServiceConfiguration;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import javax.validation.ConstraintViolationException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 *
 * @author ivet
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class HotelFacadeImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Mock
    private HotelService hotelService;

    @Autowired
    BeanMappingService beanMappingService;

    //private HotelFacadeImpl hotelFacade = new HotelFacadeImpl();
    private HotelFacadeImpl hotelFacade;

    private Hotel hotel1;
    private Hotel hotel2;

    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;

    private HotelCreateDTO hotelCreateDTO1;
    private HotelCreateDTO hotelCreateDTO2;

    private Calendar calendar;
    private Date date;

    private HotelDTO hotelDTO1;
    private HotelDTO hotelDTO2;

    @Before
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);

        hotelFacade = new HotelFacadeImpl(hotelService, beanMappingService);

        Currency eur = Currency.getInstance("EUR");

        room1 = new Room();
        room1.setName("001");
        room1.setNumberOfBeds(2);
        room1.setPrice(new BigDecimal("100.0"));
        room1.setCurrency(eur);
        room1.setHotel(hotel1);

        room2 = new Room();
        room2.setName("002");
        room2.setNumberOfBeds(3);
        room2.setPrice(new BigDecimal("150.0"));
        room2.setCurrency(eur);
        room2.setHotel(hotel1);

        room3 = new Room();
        room3.setName("010");
        room3.setNumberOfBeds(2);
        room3.setPrice(new BigDecimal("75.0"));
        room3.setCurrency(eur);
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

        hotelCreateDTO1 = new HotelCreateDTO();
        hotelCreateDTO1.setName(hotel1.getName());
        hotelCreateDTO1.setAddress(hotel1.getAddress());

        hotelCreateDTO2 = new HotelCreateDTO();
        hotelCreateDTO2.setName(hotel2.getName());
        hotelCreateDTO2.setAddress(hotel2.getAddress());

        hotelDTO1 = new HotelDTO();
        hotelDTO1.setId((long) 10);
        hotelDTO1.setName(hotel1.getName());
        hotelDTO1.setAddress(hotel1.getAddress());

        hotelDTO2 = new HotelDTO();
        hotelDTO2.setId((long) 11);
        hotelDTO2.setName(hotel2.getName());
        hotelDTO2.setAddress(hotel2.getAddress());

        //Mock for Create on Service layer
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                hotel1.setId((long) 10);
                return null;
            }
        }).when(hotelService).createHotel(hotel1);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                hotel2.setId((long) 11);
                return null;
            }
        }).when(hotelService).createHotel(hotel2);

        //Mock for FindById on Service layer
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return hotel1;
            }
        }).when(hotelService).findById(10l);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return hotel2;
            }
        }).when(hotelService).findById(11l);

        Long id1 = hotelFacade.createHotel(hotelCreateDTO1);
        Long id2 = hotelFacade.createHotel(hotelCreateDTO2);

    }

    /**
     * Test of createHotel method, of class HotelFacadeImpl.
     */
    @Test
    public void testCreateHotel() {

        Hotel mappedHotel = beanMappingService.mapTo(hotelCreateDTO1, Hotel.class);
        verify(hotelService).createHotel(mappedHotel);

        assertEquals(hotel1.getId(), new Long("10"));

    }

    /**
     * Test of updateHotel method, of class HotelFacadeImpl.
     */
    @Test
    public void testUpdateHotel() {

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                hotel1.setName("Hotel Dom Sportu");
                hotel1.setAddress("Bratislava");
                return null;
            }
        }).when(hotelService).updateHotel(any(Hotel.class));

        hotelDTO1.setName("Hotel Dom Sportu");
        hotelDTO1.setAddress("Bratislava");
        hotelFacade.updateHotel(hotelDTO1);
        Hotel mappedHotel = beanMappingService.mapTo(hotelDTO1, Hotel.class);
        verify(hotelService).updateHotel(mappedHotel);

        assertNotNull(hotel1);
        assertEquals(hotel1.getId(), new Long("10"));
        assertEquals("Hotel Dom Sportu", hotel1.getName());
        assertEquals("Bratislava", hotel1.getAddress());

        assertNotNull(hotel2);
        assertEquals(hotel2.getId(), new Long("11"));
        assertEquals("Park Hotel", hotel2.getName());
        assertEquals("Praha", hotel2.getAddress());

    }

    /**
     * Test of deleteHotel method, of class HotelFacadeImpl.
     */
    @Test
    public void testDeleteHotel() {

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                hotel1 = null;
                return null;
            }
        }).when(hotelService).deleteHotel(any(Hotel.class));

        assertNotNull(hotel1);
        assertNotNull(hotel2);
        assertNotNull(hotelDTO1);
        assertNotNull(hotelDTO2);
        Long id = hotel1.getId();
        hotelFacade.deleteHotel(id);

        assertNull(hotel1);
        assertNotNull(hotel2);

    }

    /**
     * Test of getHotelById method, of class HotelFacadeImpl.
     */
    @Test
    public void testGetHotelById() {

        HotelDTO found = hotelFacade.getHotelById(new Long(10));
        assertEquals(found.getId(), new Long(10));

    }

    /**
     * Test of getAllHotels method, of class HotelFacadeImpl.
     */
    @Test
    public void testGetAllHotels() {
        List<Hotel> list = new ArrayList<>();

        list.add(hotel1);
        list.add(hotel2);
        when(hotelService.findAll()).thenReturn(list);
        List<HotelDTO> dtoList = hotelFacade.getAllHotels();
        assertEquals(dtoList.size(), 2);
    }

    @Test
    public void testCreateInvalidName() {
        hotelCreateDTO1.setName(null);
        try {
            hotelFacade.createHotel(hotelCreateDTO1);
        } catch (ConstraintViolationException cx) {
            //ok
        }
    }

    @Test
    public void testCreateInvalidAddress() {
        hotelCreateDTO1.setAddress(null);
        try {
            hotelFacade.createHotel(hotelCreateDTO1);
        } catch (ConstraintViolationException cx) {
            //ok
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidArgument() {
        hotelFacade.createHotel(null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByIdInvalidArgument() {
        hotelFacade.getHotelById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateInvalidArgument() {
        hotelFacade.updateHotel(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteInvalidArgument() {
        hotelFacade.deleteHotel(null);
    }
}
