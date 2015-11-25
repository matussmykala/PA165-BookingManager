package cz.muni.fi.pa165.bookingmanager.service.facade;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cz.muni.fi.pa165.bookingmanager.dao.CustomerDao;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDao;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDao;
import cz.muni.fi.pa165.bookingmanager.dto.ReservationDTO;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.facade.ReservationFacade;
import cz.muni.fi.pa165.bookingmanager.service.CustomerService;
import cz.muni.fi.pa165.bookingmanager.service.ReservationService;
import cz.muni.fi.pa165.bookingmanager.service.RoomService;
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
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * @author matus
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ReservationFacadeTest extends AbstractTestNGSpringContextTests
{
    @Mock
    private ReservationService reservationService;
    @Mock
    private RoomService roomService;
    @Mock
    private CustomerService customerService;

    //ugly peace of code, but wasn't able to do better way
    //----------------------------------------------------
    //@Autowired
    //@InjectMocks
    private ReservationFacadeImpl reservationFacade;
    //----------------------------------------------------

    private Reservation reservation1;
    private Reservation reservation2;
    private Date nextMonthFirstDay;
    private Date nextMonthLastDay;

    @BeforeMethod
    public void createReservations(){
        MockitoAnnotations.initMocks(this);

        //ugly peace of code, but wasn't able to do better way
        //----------------------------------------------------
        reservationFacade = new ReservationFacadeImpl();
        reservationFacade.setReservationService(reservationService);
        //----------------------------------------------------

        reservation1 = new Reservation();
        reservation2 = new Reservation();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        nextMonthFirstDay = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        nextMonthLastDay = calendar.getTime();
    }


    @Test
    public void createReservationTest(){

        doAnswer(new Answer()
        {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable
            {
                reservation1.setId((long) 1);
                return null;
            }
        }).when(reservationService).createReservation(any(Customer.class), any(Room.class), any(Date.class), any(Date.class));

        assertNull(reservation1.getId());

        reservationFacade.createReservation((long) 0, (long) 0, nextMonthFirstDay, nextMonthLastDay);
        verify(reservationService).createReservation(any(Customer.class), any(Room.class),
                any(Date.class), any(Date.class));

        assertNotNull(reservation1.getId());
    }

    public void getAllReservationsTest(){
        List<Reservation> list = new ArrayList<>();
        list.add(reservation1);
        list.add(reservation2);
        when(reservationService.getAllReservations()).thenReturn(list);
        List<ReservationDTO> dtoList = reservationFacade.getAllReservations();
        assertEquals(dtoList.size(), 2);

        assertEquals(dtoList.get(0).getCustomer().getEmail(), reservation1.getCustomer().getEmail());
        assertEquals(dtoList.get(0).getRoom().getName(), reservation1.getRoom().getName());
        assertEquals(dtoList.get(0).getStartOfReservation(), reservation1.getStartOfReservation());
        assertEquals(dtoList.get(0).getEndOfReservation(), reservation1.getEndOfReservation());

        assertEquals(dtoList.get(1).getCustomer().getEmail(), reservation2.getCustomer().getEmail());
        assertEquals(dtoList.get(1).getRoom().getName(), reservation2.getRoom().getName());
        assertEquals(dtoList.get(1).getStartOfReservation(), reservation2.getStartOfReservation());
        assertEquals(dtoList.get(1).getEndOfReservation(), reservation2.getEndOfReservation());

        verify(reservationService).getAllReservations();
    }

    public void getReservationsByCustomerTest(){

    }

    public void getReservationByIdTest(){

    }

    public void getReservationById(){

    }

    public void updateReservationTest(){

    }

    public void cancelReservation(){

    }

    public void getFutureReservationsOfCustomer(){

    }

    public void getNextMonthReservationsTest(){

    }
}
