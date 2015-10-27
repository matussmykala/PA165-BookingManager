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
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created 26.10.2015
 * 
 * Test ReservationDao implementation
 * 
 * @author Martin Cuchran <cuchy92@gmail.com>
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
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
        
        r1.setCustomer(customer);
        r1.setRoom(room);
        r1.setStartOfReservation(new Date(2015,10,26));
        r1.setEndOfReservation(new Date(2015,10,29));  
        
        r2.setCustomer(customer);
        r2.setRoom(room);
        r2.setStartOfReservation(new Date(2015,11,26));
        r2.setEndOfReservation(new Date(2015,11,29)); 
        
        roomDao.create(room);
        hotelDao.create(hotel);
        customerDao.create(customer);       
        reservationDao.create(r1);
        reservationDao.create(r2);
        
    }
    
    @Test
    public void createTest() throws Exception{        
        assertNotNull(r1.getId());
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
        Assert.assertEquals(reservationDao.findById(r1.getId()), r1.getId());
}
    
    /**
     * Test if remove method removes reservation
     */
    @Test
    public void deleteTest(){
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
        Assert.assertEquals(reservationDao.findById(r1.getId()).getStartOfReservation(),r1.getStartOfReservation());
        r1.setStartOfReservation(date);
        reservationDao.update(r1);       
        Assert.assertEquals(reservationDao.findById(r1.getId()).getStartOfReservation(),date);
    }
   
}
