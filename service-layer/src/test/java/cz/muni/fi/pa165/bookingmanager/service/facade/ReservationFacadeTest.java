package cz.muni.fi.pa165.bookingmanager.service.facade;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cz.muni.fi.pa165.bookingmanager.dao.CustomerDao;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDao;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDao;
import cz.muni.fi.pa165.bookingmanager.dto.CustomerDTO;
import cz.muni.fi.pa165.bookingmanager.dto.ReservationCreateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.ReservationDTO;
import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.facade.ReservationFacade;
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingService;
import cz.muni.fi.pa165.bookingmanager.service.CustomerService;
import cz.muni.fi.pa165.bookingmanager.service.ReservationService;
import cz.muni.fi.pa165.bookingmanager.service.RoomService;
import cz.muni.fi.pa165.bookingmanager.service.config.ServiceConfiguration;

import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author matus
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ReservationFacadeTest extends AbstractJUnit4SpringContextTests
{
    @Mock
    private ReservationService reservationService;
    @Mock
    private RoomService roomService;
    @Mock
    private CustomerService customerService;
    @Autowired
    private BeanMappingService beanMappingService;

    private ReservationFacadeImpl reservationFacade = new ReservationFacadeImpl();

    private Reservation reservation1;
    private Reservation reservation2;
    private Date nextMonthFirstDay;
    private Date nextMonthLastDay;

    @Before
    public void createReservations(){
        MockitoAnnotations.initMocks(this);

        reservationFacade.setReservationService(reservationService);
        reservationFacade.setCustomerService(customerService);
        reservationFacade.setRoomService(roomService);
        reservationFacade.setBeanMappingService(beanMappingService);

        reservation1 = new Reservation();
        reservation2 = new Reservation();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        nextMonthFirstDay = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        nextMonthLastDay = calendar.getTime();

        reservation1.setId((long) 2);
        reservation1.setCustomer(new Customer());
        reservation1.setRoom(new Room());
        reservation1.setStartOfReservation(nextMonthFirstDay);
        reservation1.setEndOfReservation(nextMonthLastDay);

        reservation2.setId((long) 2);
        reservation2.setCustomer(new Customer());
        reservation2.setRoom(new Room());
        reservation2.setStartOfReservation(nextMonthFirstDay);
        reservation2.setEndOfReservation(nextMonthLastDay);
    }


    @Test
    public void createReservationTest(){
        doAnswer(invocationOnMock -> {
            reservation1.setId((long) 1);
            return null;
        }).when(reservationService).createReservation(any(Reservation.class));

        assertNotEquals(reservation1.getId(), new Long("1"));

        ReservationCreateDTO reservationDTO = new ReservationCreateDTO();
        //reservationDTO.setId(reservation1.getId());
        reservationDTO.setStartOfReservation(reservation1.getStartOfReservation());
        reservationDTO.setEndOfReservation(reservation1.getEndOfReservation());
        reservationDTO.setCustomerId((long) 1);
        reservationDTO.setRoomId((long) 1);

        reservationFacade.createReservation(reservationDTO);
        verify(reservationService).createReservation(any(Reservation.class));

        assertEquals(reservation1.getId(), new Long("1"));
    }

    @Test
    public void getAllReservationsTest(){
        List<Reservation> list = new ArrayList<>();

        list.add(reservation1);
        list.add(reservation2);
        when(reservationService.getAllReservations()).thenReturn(list);
        List<ReservationDTO> dtoList = reservationFacade.getAllReservations();
        assertEquals(dtoList.size(), 2);

        assertNotNull(dtoList.get(0));
        assertNotNull(dtoList.get(0).getCustomer());
        assertNotNull(dtoList.get(0).getCustomer().getEmail());

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

    @Test
    public void getReservationsByCustomerTest(){
        List<Reservation> list = new ArrayList<>();
        list.add(reservation1);
        list.add(reservation2);
        when(reservationService.getReservationsByCustomer(any(Customer.class))).thenReturn(list);
        List<ReservationDTO> dtoList = reservationFacade.getReservationsByCustomer((long) 1);
        assertEquals(dtoList.size(), 2);

        assertEquals(dtoList.get(0).getCustomer().getEmail(), reservation1.getCustomer().getEmail());
        assertEquals(dtoList.get(0).getRoom().getName(), reservation1.getRoom().getName());
        assertEquals(dtoList.get(0).getStartOfReservation(), reservation1.getStartOfReservation());
        assertEquals(dtoList.get(0).getEndOfReservation(), reservation1.getEndOfReservation());

        assertEquals(dtoList.get(1).getCustomer().getEmail(), reservation2.getCustomer().getEmail());
        assertEquals(dtoList.get(1).getRoom().getName(), reservation2.getRoom().getName());
        assertEquals(dtoList.get(1).getStartOfReservation(), reservation2.getStartOfReservation());
        assertEquals(dtoList.get(1).getEndOfReservation(), reservation2.getEndOfReservation());

        verify(reservationService).getReservationsByCustomer(any(Customer.class));
    }

    @Test
    public void getReservationByIdTest(){
        when(reservationService.getReservationById(any(Long.class))).thenReturn(reservation1);
        ReservationDTO dtoReservation = reservationFacade.getReservationById((long) 1);

        assertEquals(dtoReservation.getCustomer().getEmail(), reservation1.getCustomer().getEmail());
        assertEquals(dtoReservation.getRoom().getName(), reservation1.getRoom().getName());
        assertEquals(dtoReservation.getStartOfReservation(), reservation1.getStartOfReservation());
        assertEquals(dtoReservation.getEndOfReservation(), reservation1.getEndOfReservation());

        assertEquals(dtoReservation.getCustomer().getEmail(), reservation2.getCustomer().getEmail());
        assertEquals(dtoReservation.getRoom().getName(), reservation2.getRoom().getName());
        assertEquals(dtoReservation.getStartOfReservation(), reservation2.getStartOfReservation());
        assertEquals(dtoReservation.getEndOfReservation(), reservation2.getEndOfReservation());

        verify(reservationService).getReservationById(any(Long.class));
    }

    @Test
    public void updateReservationTest(){
        doNothing().when(reservationService).updateReservation(any(Reservation.class), any(Customer.class),
                any(Room.class), any(Date.class), any(Date.class));
        reservationFacade.updateReservation((long) 1, (long) 1, (long) 1, nextMonthFirstDay, nextMonthLastDay);
        verify(reservationService).updateReservation(any(Reservation.class), any(Customer.class),
                any(Room.class), any(Date.class), any(Date.class));
    }

    @Test
    public void cancelReservation(){
        when(reservationService.getReservationById(any(Long.class))).thenReturn(reservation1);
        doNothing().when(reservationService).cancelReservation(any(Reservation.class));
        reservationFacade.cancelReservation((long) 1);
        verify(reservationService).cancelReservation(any(Reservation.class));
    }

    @Test
    public void getFutureReservationsOfCustomer(){
        List<Reservation> list = new ArrayList<>();
        list.add(reservation1);
        list.add(reservation2);
        when(reservationService.getFutureReservationsOfCustomer(any(Customer.class))).thenReturn(list);
        reservationFacade.getFutureReservationsOfCustomer((long) 1);
        verify(reservationService).getFutureReservationsOfCustomer(any(Customer.class));
    }

    @Test
    public void getNextMonthReservationsTest(){
        List<Reservation> list = new ArrayList<>();
        list.add(reservation1);
        list.add(reservation2);
        when(reservationService.getNextMonthReservations()).thenReturn(list);
        reservationFacade.getNextMonthReservations();
        verify(reservationService).getNextMonthReservations();
    }
}
