package cz.muni.cz.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.dao.CustomerDao;
import cz.muni.fi.pa165.bookingmanager.dao.HotelDao;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDao;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDao;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
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
    private Room room;
    private Room room2;
    private Hotel hotel;
    private Customer customer;
    private Customer customer2;
    private Date date1;
    private Date date2;
            
    
    /**
     * Create tested objects
     */
    @Before
    public void createTestData(){
        r1 = new Reservation();
        r2 = new Reservation();
        
        room = new Room();
        room.setName("Room1");
        room.setNumberOfBeds(3);
        room.setPrice(1500.00);
        
        room2 = new Room();
        room2.setName("Room2");
        room2.setNumberOfBeds(3);
        room2.setPrice(1500.00);
        
        hotel = new Hotel();
        hotel.setAddress("Botanicka 68a, Brno");
        hotel.setName("FIMU");
        hotel.setRooms(room);
        
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
        reservationDao.create(r2);
        
    }
        
    /**
     * Test reservation creation
     */
    @Test
    public void createTest() throws Exception{        
        assertNotNull(r1.getId());
    }
    
    /**
     * Test if reservations contains created reservations
     */
    @Test
    public void findAllTest() throws Exception{
        List<Reservation> found = reservationDao.findAll();
        Assert.assertEquals(found.size(), 2);
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
    public void updateTest() throws Exception{
        
        //Test data before change
        Assert.assertEquals(reservationDao.findById(r1.getId()).getStartOfReservation().getTime(),r1.getStartOfReservation().getTime());
        Assert.assertEquals(reservationDao.findById(r1.getId()).getCustomer(), r1.getCustomer());
        Assert.assertEquals(reservationDao.findById(r1.getId()).getRoom(), r1.getRoom());
        
        //Change data
        Date date;
        Calendar cal = Calendar.getInstance();
	cal.set(2015, 12, 26);
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
     * Test if start date of reservation is less than end date of reservation
     */
    @Test(expected = IllegalArgumentException.class)
    public void reservationDurationTest() throws Exception{
        r1.setStartOfReservation(date2);
        r1.setEndOfReservation(date1);
        reservationDao.create(r1);
    }
    
    /**
     * Test if room argument is correct
     */
    @Test(expected = IllegalArgumentException.class)
    public void reservationRoomArgumentTest() throws Exception{
        r1.setRoom(null);
        reservationDao.create(r1);
    }
    
    /**
     * Test if room argument is correct
     */
    @Test(expected = IllegalArgumentException.class)
    public void reservationCustomerArgumentTest() throws Exception{
        r1.setCustomer(null);
        reservationDao.create(r1);
    }
}
