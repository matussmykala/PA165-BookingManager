package cz.muni.cz.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.bookingmanager.dao.HotelDao;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDao;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import static org.junit.Assert.*;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test for basic CRUD operations on Hotel entities using HotelDaoImpl.
 * <p/>
 * Created on 26.10.2015.
 *
 * @author Vladimir Caniga
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class HotelDaoTest extends AbstractJUnit4SpringContextTests {
    /*

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private HotelDao hotelDao;

    private Hotel hotel1;
    private Hotel hotel2;
    private Hotel hotel3;

    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;

    @Before
    public void setUp() throws Exception {
        hotel1 = new Hotel();
        hotel1.setName("Kempinski");
        hotel1.setAddress("Strbske Pleso");

        hotel2 = new Hotel();
        hotel2.setName("Devin");
        hotel2.setAddress("Bratislava");

        hotel3 = new Hotel();
        hotel3.setName("Jasna");
        hotel3.setAddress("Demanovska Dolina");

        Currency eur = Currency.getInstance("EUR");

        room1 = new Room();
        room1.setName("001");
        room1.setNumberOfBeds(2);
        room1.setPrice(new BigDecimal("100.0"));
        room1.setCurrency(eur);

        room2 = new Room();
        room2.setName("002");
        room2.setNumberOfBeds(3);
        room2.setPrice(new BigDecimal("150.0"));
        room2.setCurrency(eur);

        room3 = new Room();
        room3.setName("010");
        room3.setNumberOfBeds(2);
        room3.setPrice(new BigDecimal("75.0"));
        room3.setCurrency(eur);
        room3.setHotel(hotel2);

        room4 = new Room();
        room4.setName("123");
        room4.setNumberOfBeds(5);
        room4.setPrice(new BigDecimal("340"));
        room4.setCurrency(eur);
    }

    @Test
    public void testCreate() throws Exception {
        hotelDao.create(hotel1);
        assertNotNull(hotel1.getId());
    }

    @Test
    public void testFindById() throws Exception {
        hotel2.addRoom(room1);
        hotelDao.create(hotel2);
        room1.setHotel(hotel2);
        roomDao.create(room1);
        Hotel hotel = hotelDao.findById(hotel2.getId());

        assertNotNull(hotel);
        assertEquals("Devin", hotel.getName());
        assertEquals("Bratislava", hotel.getAddress());
        assertEquals(1, hotel.getRooms().size());
        assertEquals(room1, hotel.getRooms().get(0));
    }

    @Test
    public void testFindByName() throws Exception {
        hotel1.addRoom(room2);
        hotelDao.create(hotel1);
        room2.setHotel(hotel1);
        roomDao.create(room2);
        Hotel hotel = hotelDao.findByName("Kempinski");

        assertNotNull(hotel);
        assertEquals("Kempinski", hotel.getName());
        assertEquals("Strbske Pleso", hotel.getAddress());
        assertEquals(1, hotel.getRooms().size());
        assertEquals(room2, hotel.getRooms().get(0));
    }

    @Test
    public void testFindAll() throws Exception {
        hotelDao.create(hotel1);
        hotelDao.create(hotel2);
        hotelDao.create(hotel3);
        List<Hotel> hotels = hotelDao.findAll();

        assertNotNull(hotels);
        assertEquals(3, hotels.size());
        assertTrue(hotels.contains(hotel1));
        assertTrue(hotels.contains(hotel2));
        assertTrue(hotels.contains(hotel3));
    }

    @Test
    public void testUpdate() throws Exception {
        hotel3.addRoom(room3);
        hotelDao.create(hotel3);
        room3.setHotel(hotel3);
        roomDao.create(room3);
        hotel3.setName("NewName");
        hotelDao.update(hotel3);
        Hotel hotel = hotelDao.findById(hotel3.getId());

        assertNotNull(hotel);
        assertEquals("NewName", hotel.getName());
        assertEquals("Demanovska Dolina", hotel.getAddress());
        assertEquals(1, hotel.getRooms().size());
        assertEquals(room3, hotel.getRooms().get(0));
    }

    @Test
    public void testDelete() throws Exception {
        hotelDao.create(hotel1);
        Hotel hotel = hotelDao.findById(hotel1.getId());
        assertNotNull(hotel);

        hotelDao.delete(hotel);
        hotel = hotelDao.findById(hotel1.getId());
        assertNull(hotel);
    }
    */
}