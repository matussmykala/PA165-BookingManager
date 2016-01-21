/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.cz.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.bookingmanager.dao.CustomerDao;
import cz.muni.fi.pa165.bookingmanager.dao.HotelDao;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDao;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDao;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created 27.10.2015
 *
 * Test RoomDaoTest implementation
 *
 * @author Iveta Jurcikova
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RoomDaoTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private HotelDao hotelDao;

    private Room room1;
    private Room room2;

    private Reservation reservation1;

    private Customer customer1;

    private Hotel hotel;

    private Date date1;
    private Date date2;

    @Before
    public void setUpClass() {

        //create customer for reservation
        customer1 = new Customer();

        customer1.setName("Marketa");
        customer1.setSurname("Harastova");
        customer1.setEmail("marketa.harastova@gmail.com");
        customer1.setIsAdmin(false);
        customer1.setUsername("market");
        customer1.setPassword("12345678");
        customerDao.create(customer1);

        // create reservation for room
        reservation1 = new Reservation();

        Calendar cal1 = Calendar.getInstance();
        cal1.set(2016, 10, 26);
        date1 = cal1.getTime();

        Calendar cal2 = Calendar.getInstance();
        cal2.set(2016, 11, 29);
        date2 = cal2.getTime();

        reservation1.setCustomer(customer1);
        reservation1.setStartOfReservation(date1);
        reservation1.setEndOfReservation(date2);
        reservationDao.create(reservation1);

        //createHotel
        hotel = new Hotel();
        hotel.setName("Voronez");
        hotel.setAddress("Brno");
        hotelDao.create(hotel);

        //createRooms
        room1 = new Room();
        room2 = new Room();

        room1.setName("57");
        room1.setNumberOfBeds(3);
        room1.setPrice(new BigDecimal("80.00"));
        room1.setHotel(hotel);
        room1.setReservation(reservation1);

        room2.setName("120");
        room2.setNumberOfBeds(1);
        room2.setPrice(new BigDecimal("150.00"));
        room2.setHotel(hotel);
        room2.setReservation(reservation1);

        roomDao.create(room1);
        roomDao.create(room2);
    }

    /**
     * Test of create method, of class RoomDaoImpl.
     */
    @Test
    public void testCreate() {
        Assert.assertNotNull(room1.getId());
    }

    /**
     * Test of findById method, of class RoomDaoImpl.
     */
    @Test
    public void testFindById() {
        Room found = roomDao.findById(room1.getId());
        Assert.assertEquals("57", found.getName());
        Assert.assertEquals(3, found.getNumberOfBeds());
        Assert.assertEquals(new BigDecimal("80.00"), found.getPrice());
        Assert.assertEquals(hotel, found.getHotel());
    }

    /**
     * Test of findAll method, of class RoomDaoImpl.
     */
    @Test
    public void testFindAll() {
        List<Room> found = roomDao.findAll();
        Assert.assertEquals(2, found.size());
    }

    /**
     * Test of update method, of class RoomDaoImpl.
     */
    @Test
    public void testUpdate() {
        room1.setName("63");
        room1.setNumberOfBeds(2);
        room1.setPrice(new BigDecimal("100.00"));
        room1.setHotel(hotel);
        room1.setReservation(reservation1);
        roomDao.update(room1);
        Room found = roomDao.findById(room1.getId());
        Assert.assertEquals("63", found.getName());
        Assert.assertEquals(2, found.getNumberOfBeds());
        Assert.assertEquals(new BigDecimal("100.00"), found.getPrice());
        Assert.assertEquals(hotel, found.getHotel());
    }

    /**
     * Test of delete method, of class RoomDaoImpl.
     */
    @Test
    public void testDelete() {
        roomDao.delete(roomDao.findById(room2.getId()));
        List<Room> found = roomDao.findAll();
        Assert.assertNull(roomDao.findById(room2.getId()));
        Assert.assertEquals(1, found.size());
    }

    /**
     * Test of create method with wrong attributes of Room
     */
    @Test
    public void testCreateWithWrongAtributes() {
        //try to create room with value null
        try {
            roomDao.create(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //ok
        }

        //try to create room with no name
        Room wrongRoom = new Room();
        wrongRoom.setName(null);
        wrongRoom.setNumberOfBeds(1);
        wrongRoom.setPrice(new BigDecimal("150.00"));
        wrongRoom.setHotel(hotel);
        wrongRoom.setReservation(reservation1);
        try {
            roomDao.create(wrongRoom);
            fail();
        } catch (ConstraintViolationException cx) {
            //ok
        }

        //try to create room with negative number of beds
        wrongRoom = new Room();
        wrongRoom.setName("007");
        wrongRoom.setNumberOfBeds(-1);
        wrongRoom.setPrice(new BigDecimal("150.00"));
        wrongRoom.setHotel(hotel);
        wrongRoom.setReservation(reservation1);
        try {
            roomDao.create(wrongRoom);
            fail();
        } catch (ConstraintViolationException cx) {
            //ok
        }

        //try to create room with negative price
        wrongRoom = new Room();
        wrongRoom.setName("007");
        wrongRoom.setNumberOfBeds(1);
        wrongRoom.setPrice(new BigDecimal("-1"));
        wrongRoom.setHotel(hotel);
        wrongRoom.setReservation(reservation1);
        try {
            roomDao.create(wrongRoom);
            fail();
        } catch (ConstraintViolationException cx) {
            //ok
        }
    }

    /**
     * Test of update method with wrong attributes of Room
     */
    @Test
    public void testUpdateWithWrongAtributes() {
        //try to create room with value null
        try {
            roomDao.update(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //ok
        }

        //try to update room with no name
        Room wrongRoom = new Room();
        wrongRoom.setName(null);
        wrongRoom.setNumberOfBeds(1);
        wrongRoom.setPrice(new BigDecimal("150.00"));
        wrongRoom.setHotel(hotel);
        wrongRoom.setReservation(reservation1);
        try {
            roomDao.update(wrongRoom);
            fail();
        } catch (ConstraintViolationException cx) {
            //ok
        }

        //try to update room with negative number of beds
        wrongRoom = new Room();
        wrongRoom.setName("007");
        wrongRoom.setNumberOfBeds(-1);
        wrongRoom.setPrice(new BigDecimal("150.00"));
        wrongRoom.setHotel(hotel);
        wrongRoom.setReservation(reservation1);
        try {
            roomDao.update(wrongRoom);
            fail();
        } catch (ConstraintViolationException cx) {
            //ok
        }

        //try to update room with negative price
        wrongRoom = new Room();
        wrongRoom.setName("007");
        wrongRoom.setNumberOfBeds(1);
        wrongRoom.setPrice(new BigDecimal("-1"));
        wrongRoom.setHotel(hotel);
        wrongRoom.setReservation(reservation1);
        try {
            roomDao.update(wrongRoom);
            fail();
        } catch (ConstraintViolationException cx) {
            //ok
        }
    }

    /**
     * Test of findById method with wrong attributes of Room
     */
    @Test
    public void testFindByIdWithWrongAtributes() {
        try {
            roomDao.findById(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //ok            throw new IllegalArgumentException("EndOfReservation cannot be before today.");

        }
    }

    /**
     * Test of delete method with wrong attributes of Room
     */
    @Test
    public void testDeleteWithWrongAtributes() {
        try {
            roomDao.delete(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //ok
        }
    }
}
