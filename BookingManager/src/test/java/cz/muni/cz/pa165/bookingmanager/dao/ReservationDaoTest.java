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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
    
    @PersistenceContext
    public EntityManager em;
    
    @Inject
    public ReservationDao reservationDao;
    
    @Inject
    public RoomDao roomDao;
    
    @Inject 
    public HotelDao hotelDao;
    
    @Inject 
    public CustomerDao customerDao;
    
    private Reservation r1;
    private Reservation r2;
    
    /**
     * Create testing objects
     */
    @Before
    public void createReservations(){
        r1 = new Reservation();
        r2 = new Reservation();
        
        Room room = new Room();
        room.setName("Room1");
        room.setNumberOfBeds(3);
        room.setPrice(1500.00);
        room.setId(Long.decode("1"));
        
        List<Room> rooms = new ArrayList<Room>();
        rooms.add(room);
        
        Hotel hotel = new Hotel();
        hotel.setAddress("Botanicka 68a, Brno");
        hotel.setName("FIMU");
        hotel.setRooms(rooms);
        hotel.setId(Long.decode("1"));
        
        Customer customer = new Customer();
        customer.setEmail("cuchy92@gmail.com");
        customer.setIsAdmin(false);
        customer.setName("Martinn");
        customer.setPassword("12345");
        customer.setSurname("Cuchran");
        customer.setUsername("cuchy92");
        customer.setId(Long.decode("1"));
        
        r1.setCustomer(customer);
        r1.setId(Long.decode("1"));
        r1.setRoom(room);
        r1.setStartOfReservation(new Date(2015,10,26));
        r1.setEndOfReservation(new Date(2015,10,29));  
        
        r2.setCustomer(customer);
        r2.setId(Long.decode("2"));
        r2.setRoom(room);
        r2.setStartOfReservation(new Date(2015,11,26));
        r2.setEndOfReservation(new Date(2015,11,29)); 
        
        reservationDao.create(r1);
        reservationDao.create(r2);
        
        hotelDao.create(hotel);
        roomDao.create(room);
        customerDao.create(customer);
               
              
    }
    
    /**
     * Test if reservations contains reservations after creation
     */
    @Test
    public void findAllTest(){
        List<Reservation> found = reservationDao.findAll();
        Assert.assertEquals(found.size(), 2);
    }
    
    /**
     * Test if findById method returns correct reservation
     */
    @Test
    public void findByIdTest(){
        Assert.assertEquals(reservationDao.findById(Long.decode("1")), r1.getId());
    }
    
    /**
     * Test if remove method removes reservation
     */
    @Test
    public void removeTest(){
        Assert.assertNotNull(reservationDao.findById(r2.getId()));
        reservationDao.delete(r2);
        Assert.assertNull(reservationDao.findById(r2.getId()));
    }
    
    /**
     * Test if update of reservation attribute is working
     */
    @Test
    public void updateTest(){
        Date date = new Date(2015, 12, 26);
        Assert.assertEquals(reservationDao.findById(r1.getId()).getStartOfReservation(),date);
        r1.setStartOfReservation(new Date(2015, 12, 26));
        reservationDao.update(r1);       
        Assert.assertEquals(reservationDao.findById(r1.getId()).getStartOfReservation(),date);
    }
   
}
