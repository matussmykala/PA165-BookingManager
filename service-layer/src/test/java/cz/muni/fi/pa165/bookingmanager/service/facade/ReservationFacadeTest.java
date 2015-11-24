package cz.muni.fi.pa165.bookingmanager.service.facade;
import java.util.Date;

import cz.muni.fi.pa165.bookingmanager.dao.CustomerDao;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDao;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDao;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.facade.ReservationFacade;
import cz.muni.fi.pa165.bookingmanager.service.CustomerService;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.testng.Assert.assertNull;

/**
 * @author matus
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@Transactional
public class ReservationFacadeTest extends AbstractTestNGSpringContextTests
{
    @Mock
    private ReservationService reservationService;
    //@Mock
    //private RoomService roomService;
    @Mock
    private CustomerService customerService;

    @Autowired
    @InjectMocks
    private ReservationFacade reservationFacade;

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
        /*
        Mockito.doAnswer(new Answer()
        {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable
            {
                reservation1.setId((long) 1);
                return null;
            }
        }).when(reservationService).create(any(Reservation.class));

        Mockito.doNothing().when(roomDao).create(any(Room.class));
        Mockito.doNothing().when(customerDao).create(any(Customer.class));
        */
        assertNull(reservation1.getId());
        Date from = new Date();
        Date to = new Date();
        reservationFacade.createReservation((long) 0, (long) 0, from, to);
        assertNotNull(reservation1.getId());
    }
}
