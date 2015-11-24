package cz.muni.fi.pa165.bookingmanager.service.facade;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cz.muni.fi.pa165.bookingmanager.dao.CustomerDao;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDao;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDao;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.service.ReservationService;
import cz.muni.fi.pa165.bookingmanager.service.config.ServiceConfiguration;

import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;

/**
 * @author matus
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ReservationServiceTest extends AbstractTestNGSpringContextTests
{
    @Mock
    private ReservationDao reservationDao;
    @Mock
    private RoomDao roomDao;
    @Mock
    private CustomerDao customerDao;

    @Autowired
    @InjectMocks
    private ReservationService reservationService;

    private Reservation reservation1;
    private Reservation reservation2;

    @BeforeMethod
    public void createReservations(){
        reservation1 = new Reservation();
        reservation1 = new Reservation();
    }

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createReservationTest(){
        Mockito.doAnswer(new Answer()
        {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable
            {
                reservation1.setId((long) 1);
                return null;
            }
        }).when(reservationDao).create(any(Reservation.class));

        Mockito.doNothing().when(roomDao).create(any(Room.class));
        Mockito.doNothing().when(customerDao).create(any(Customer.class));

        assertNull(reservation1.getId());
        reservationService.createReservation(reservation1);
        assertNotNull(reservation1.getId());
    }

    @Test
    public void getAllReservationsTest(){
        List<Reservation> list = new ArrayList<>();
        when(reservationDao.findAll()).thenReturn(list);
        Assert.assertEquals(0, reservationService.getAllReservations().size());
        list.add(reservation1);
        Assert.assertEquals(1, reservationService.getAllReservations().size());
    }

    @Test
    public void getReservationsByCustomerTest(){

    }

    @Test
    public void getReservationByIdTest(){

    }

    @Test
    public void createReservation2Test(){

    }

    @Test
    public void updateReservationTest(){

    }

    @Test
    public void cancelReservationTest(){

    }
}