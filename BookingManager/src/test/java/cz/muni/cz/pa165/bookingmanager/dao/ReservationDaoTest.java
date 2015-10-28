package cz.muni.cz.pa165.bookingmanager.dao;

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
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
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
    private Room room;
    private Hotel hotel;
    private Customer customer;
    private Date date1;
    private Date date2;
            
    
    /**
     * Create tested objects
     */
    @Before
    public void createTestData(){
        r1 = new Reservation();
        r2 = new Reservation();

        Currency czk = Currency.getInstance("CZK");

        room = new Room();
        room.setName("Room1");
        room.setNumberOfBeds(3);
        room.setPrice(new BigDecimal("1500.00"));
        room.setCurrency(czk);
        
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

        Calendar cal1 = Calendar.getInstance();
	    cal1.set(2015, 10, 26);
	    date1 = cal1.getTime();
        
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2015, 11, 29);
	    date2 = cal2.getTime();
        
        r1.setCustomer(customer);
        r1.setRoom(room);
        r1.setStartOfReservation(date1);
        r1.setEndOfReservation(date2);  
        
        r2.setCustomer(customer);
        r2.setRoom(room);
        r2.setStartOfReservation(date1);
        r2.setEndOfReservation(date2); 
        
        roomDao.create(room);
        hotelDao.create(hotel);
        customerDao.create(customer);       
        reservationDao.create(r1);
    }
    
    /**
     * Clear reservations in DB
     */
    @After
    public void clearTestData(){
        reservationDao.delete(r1);
    }
    
    /**
     * Test reservation creation
     */
    @Test
    public void createTest() throws Exception{        
        assertNotNull(r1.getId());
    }
    
    /**
     * Test if reservations contains reservations after creation
     */
    @Test
    public void findAllTest() throws Exception{
        reservationDao.create(r2);
        List<Reservation> found = reservationDao.findAll();
        Assert.assertEquals(found.size(), 2);
        reservationDao.delete(r2);
    }
    
    /**
     * Test if findById method returns correct reservation
     */
    @Test
    public void findByIdTest() throws Exception{
        Reservation found = reservationDao.findById(r1.getId());
        Assert.assertEquals(found.getCustomer(),customer);
        Assert.assertEquals(found.getEndOfReservation().getTime(),date2.getTime());
        Assert.assertEquals(found.getStartOfReservation().getTime(),date1.getTime());
        Assert.assertEquals(found.getRoom(),room);
    }
    
    /**
     * Test if remove method removes reservation
     */
    @Test
    public void deleteTest() throws Exception{
        reservationDao.create(r2);
        Assert.assertNotNull(reservationDao.findById(r2.getId()));
        reservationDao.delete(r2);
        Assert.assertNull(reservationDao.findById(r2.getId()));
    }
    
    /**
     * Test if update of reservation attribute is working
     */
    @Test
    public void updateTest() throws Exception{
        Date date;
        Calendar cal = Calendar.getInstance();
	    cal.set(2015, 12, 26);
	    date = cal.getTime();
        Assert.assertEquals(reservationDao.findById(r1.getId()).getStartOfReservation().getTime(),r1.getStartOfReservation().getTime());
        r1.setStartOfReservation(date);
        reservationDao.update(r1);       
        Assert.assertEquals(reservationDao.findById(r1.getId()).getStartOfReservation().getTime(),date.getTime());
    }
}
