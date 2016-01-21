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
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import org.junit.Rule;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ExpectedException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created 26.10.2015
 *
 * Test ReservationDao implementation
 *
 * @author Martin Cuchran <cuchy92@gmail.com>
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ReservationDaoTest extends AbstractJUnit4SpringContextTests{

    @Inject
    public ReservationDao reservationDao;

    @Inject
    private RoomDao roomDao;

    @Inject
    private HotelDao hotelDao;

    @Inject
    private CustomerDao customerDao;

    private Reservation r1;
    private Reservation r2;
    private Reservation r3;
    private Room room;
    private Room room2;
    private Hotel hotel;
    private Customer customer;
    private Customer customer2;
    private Date date1;
    private Date date2;
    private Date date3;
    private Date date4;

    /**
     * Create tested objects
     */
    @Before
    public void createTestData(){
        r1 = new Reservation();
        r2 = new Reservation();
        r3 = new Reservation();

        room = new Room();
        room.setName("Room1");
        room.setNumberOfBeds(3);
        room.setPrice(new BigDecimal("15.0"));

        room2 = new Room();
        room2.setName("Room2");
        room2.setNumberOfBeds(3);
        room2.setPrice(new BigDecimal("20.0"));

        hotel = new Hotel();
        hotel.setAddress("Botanicka 68a, Brno");
        hotel.setName("FIMU");
        hotel.addRoom(room);

        customer = new Customer();
        customer.setEmail("cuchy92@gmail.com");
        customer.setIsAdmin(false);
        customer.setName("Martinn");
        customer.setPassword("12345");
        customer.setSurname("Cuchran");
        customer.setUsername("cuchy92");

        customer2 = new Customer();
        customer2.setEmail("cuchran@ics.muni.cz");
        customer2.setIsAdmin(false);
        customer2.setName("Peter");
        customer2.setPassword("123456");
        customer2.setSurname("Cuchran");
        customer2.setUsername("cuchy");

        Calendar cal1 = Calendar.getInstance();
	    cal1.set(2016, 10, 26);
	    date1 = cal1.getTime();

        Calendar cal2 = Calendar.getInstance();
        cal2.set(2016, 11, 29);
	    date2 = cal2.getTime();

        Calendar cal3 = Calendar.getInstance();
	    cal3.set(2016, 10, 27);
	    date3 = cal3.getTime();

        Calendar cal4 = Calendar.getInstance();
        cal4.set(2016, 11, 28);
	    date4 = cal4.getTime();

        r1.setCustomer(customer);
        r1.setRoom(room);
        r1.setStartOfReservation(date1);
        r1.setEndOfReservation(date2);

        r2.setCustomer(customer);
        r2.setRoom(room);
        r2.setStartOfReservation(date1);
        r2.setEndOfReservation(date2);

        r3.setCustomer(customer);
        r3.setRoom(room2);
        r3.setStartOfReservation(date3);
        r3.setEndOfReservation(date4);

        roomDao.create(room);
        roomDao.create(room2);
        hotelDao.create(hotel);
        customerDao.create(customer);
        reservationDao.create(r1);
        reservationDao.create(r2);
        reservationDao.create(r3);
    }

    /**
     * Test reservation creation
     */
    @Test
    public void testCreate(){
        assertNotNull(r1.getId());
    }

    /**
     * Test if reservations contains created reservations
     */
    @Test
    public void testFindAll(){
        List<Reservation> found = reservationDao.findAll();
        Assert.assertEquals(found.size(), 3);
    }

    /**
     * Test if findById method returns correct reservation
     */
    @Test
    public void testFindById(){
        Reservation found = reservationDao.findById(r1.getId());
        Assert.assertEquals(found.getCustomer(),customer);
        Assert.assertEquals(found.getEndOfReservation().getTime(),date2.getTime());
        Assert.assertEquals(found.getStartOfReservation().getTime(),date1.getTime());
        Assert.assertEquals(found.getRoom(),room);

    }

    /**
     * Test if delete method removes reservation
     */
    @Test
    public void testDelete(){

        //Test if Data exist
        Assert.assertNotNull(reservationDao.findById(r2.getId()));
        //Delete some data
        reservationDao.delete(r2);
        //Test if data not exist after delete
        Assert.assertNull(reservationDao.findById(r2.getId()));
    }

    /**
     * Test if update of reservation attributes is working
     */
    @Test
    public void testUpdate(){

        //Test data before change
        Assert.assertEquals(reservationDao.findById(r1.getId()).getStartOfReservation().getTime(),r1.getStartOfReservation().getTime());
        Assert.assertEquals(reservationDao.findById(r1.getId()).getCustomer(), r1.getCustomer());
        Assert.assertEquals(reservationDao.findById(r1.getId()).getRoom(), r1.getRoom());

        //Change data
        Date date;
        Calendar cal = Calendar.getInstance();
	    cal.set(2016, 9, 26);
	    date = cal.getTime();
        r1.setStartOfReservation(date);
        r1.setCustomer(customer2);
        r1.setRoom(room2);
        reservationDao.update(r1);

        //Test if data after change are correct
        Assert.assertEquals(reservationDao.findById(r1.getId()).getStartOfReservation().getTime(),date.getTime());
        Assert.assertEquals(reservationDao.findById(r1.getId()).getCustomer(), r1.getCustomer());
        Assert.assertEquals(reservationDao.findById(r1.getId()).getRoom(), r1.getRoom());
    }

    /**
     * Test null reservation creation
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithWrongAttribute() {
        reservationDao.create(null);
    }

    /**
     * Test find by null id
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFindByIdWithWrongAtributes() {
        reservationDao.findById(null);
    }

    /**
     * Test update by null id
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateWithWrongAtributes() {
        reservationDao.update(null);
    }

    /**
     * Test delete by null id
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteWithWrongAtributes() {
        reservationDao.delete(null);
    }

    /**
     * Test create reservation with StartOfReservation attribute greater than EndOfReservation attribute
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWrongStartOfReservationAttribute(){
        r1.setStartOfReservation(date2);
        reservationDao.create(r1);
    }

    /**
     * Test create reservation with EndOfReservation attribute greater than StartOfReservation attribute
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWrongEndOfReservationAttribute(){
        r1.setEndOfReservation(date1);
        reservationDao.create(r1);
    }
}
