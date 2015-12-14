package cz.muni.fi.pa165.bookingmanager.service.facade;
import java.util.ArrayList;
import java.util.Calendar;
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

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author matus
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ReservationServiceTest extends AbstractJUnit4SpringContextTests
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

    @Before
    public void createReservations(){
        MockitoAnnotations.initMocks(this);

        reservation1 = new Reservation();
        reservation2 = new Reservation();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date nextMonthFirstDay = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date nextMonthLastDay = calendar.getTime();

        reservation1.setStartOfReservation(nextMonthFirstDay);
        reservation2.setStartOfReservation(nextMonthFirstDay);
        reservation1.setEndOfReservation(nextMonthLastDay);
        reservation2.setEndOfReservation(nextMonthLastDay);
    }

    @Test
    public void createReservationTest(){
        /*
        doNothing().when(roomDao).create(any(Room.class));
        doNothing().when(customerDao).create(any(Customer.class));
        doNothing().when(reservationDao).create(any(Reservation.class));

        reservationService.createReservation(reservation1);
        verify(reservationDao).create(reservation1);
        */
    }

    @Test
    public void getAllReservationsTest(){
        List<Reservation> list = new ArrayList<>();
        when(reservationDao.findAll()).thenReturn(list);
        reservationService.getAllReservations();
        verify(reservationDao).findAll();
    }

    @Test
    public void getReservationsByCustomerTest(){
        List<Reservation> list = new ArrayList<>();
        when(reservationDao.findReservationsOfCustomer(any(Customer.class))).thenReturn(list);
        Customer customer = new Customer();
        reservationService.getReservationsByCustomer(customer);
        verify(reservationDao).findReservationsOfCustomer(customer);
    }

    @Test
    public void getReservationByIdTest(){
        when(reservationDao.findById(any(Long.class))).thenReturn(reservation1);
        reservationService.getReservationById((long) 0);
        verify(reservationDao).findById(anyLong());
    }

    @Test
    public void updateReservationTest(){
        doNothing().when(reservationDao).update(any(Reservation.class));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date nextMonthFirstDay = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date nextMonthLastDay = calendar.getTime();

        reservationService.updateReservation(reservation1, new Customer(), new Room(), nextMonthFirstDay, nextMonthLastDay);
        verify(reservationDao).update(any(Reservation.class));
    }

    @Test
    public void cancelReservationTest(){
        doNothing().when(reservationDao).delete(any(Reservation.class));
        reservationService.cancelReservation(reservation1);
        verify(reservationDao).delete(any(Reservation.class));
    }

    @Test
    public void getReservationsOfTimeTest(){
        List<Reservation> list = new ArrayList<>();
        list.add(reservation1);
        list.add(reservation2);

        when(reservationDao.findReservationsOfTime(any(Date.class), any(Date.class))).thenReturn(list);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date nextMonthFirstDay = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date nextMonthLastDay = calendar.getTime();

        reservationService.getReservationsOfTime(nextMonthFirstDay, nextMonthLastDay);
        verify(reservationDao).findReservationsOfTime(any(Date.class), any(Date.class));
    }

    @Test
    public void getNextMonthReservationsTest(){
        List<Reservation> list = new ArrayList<>();
        list.add(reservation1);
        list.add(reservation2);

        when(reservationDao.findReservationsOfTime(any(Date.class), any(Date.class))).thenReturn(list);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date nextMonthFirstDay = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date nextMonthLastDay = calendar.getTime();

        reservationService.getReservationsOfTime(nextMonthFirstDay, nextMonthLastDay);
        verify(reservationDao).findReservationsOfTime(any(Date.class), any(Date.class));
    }

    @Test
    public void getFutureReservationsOfCustomerTest(){
        Customer customer = new Customer();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date thisMonthFirstDay = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        Date nextMonthFirstDay = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date nextMonthLastDay = calendar.getTime();

        reservation1.setCustomer(customer);
        reservation1.setStartOfReservation(nextMonthFirstDay);
        reservation1.setEndOfReservation(nextMonthLastDay);

        reservation2.setCustomer(customer);
        reservation2.setStartOfReservation(thisMonthFirstDay);
        reservation2.setEndOfReservation(nextMonthLastDay);

        List<Reservation> list = new ArrayList<>();
        list.add(reservation1);
        list.add(reservation2);
        when(reservationDao.findReservationsOfCustomer(any(Customer.class))).thenReturn(list);

        list = reservationService.getFutureReservationsOfCustomer(customer);
        verify(reservationDao).findReservationsOfCustomer(any(Customer.class));

        assertTrue(list.contains(reservation1));
        assertTrue(!list.contains(reservation2));
    }
}
