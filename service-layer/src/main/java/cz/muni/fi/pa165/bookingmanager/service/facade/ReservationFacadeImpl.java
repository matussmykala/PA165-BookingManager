package cz.muni.fi.pa165.bookingmanager.service.facade;
import java.util.Date;
import java.util.List;

import cz.muni.fi.pa165.bookingmanager.dto.ReservationCreateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.ReservationDTO;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.facade.ReservationFacade;
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingService;
import cz.muni.fi.pa165.bookingmanager.service.CustomerService;
import cz.muni.fi.pa165.bookingmanager.service.ReservationService;
import cz.muni.fi.pa165.bookingmanager.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author matus
 */
@Service
@Transactional
public class ReservationFacadeImpl implements ReservationFacade
{
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private BeanMappingService beanMappingService;

    public void setBeanMappingService(BeanMappingService beanMappingService)
    {
        this.beanMappingService = beanMappingService;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public void setRoomService(RoomService roomService)
    {
        this.roomService = roomService;
    }

    public void setReservationService(ReservationService reservationService)
    {
        this.reservationService = reservationService;
    }

    @Override
    public List<ReservationDTO> getAllReservations()
    {
        List<Reservation> reservations = reservationService.getAllReservations();
        return beanMappingService.mapTo(reservations, ReservationDTO.class);
    }

    @Override
    public List<ReservationDTO> getReservationsByCustomer(Long customerId)
    {
        Customer customer = customerService.findCustomerById(customerId);
        List<Reservation> reservations = reservationService.getReservationsByCustomer(customer);
        return beanMappingService.mapTo(reservations, ReservationDTO.class);
    }

    @Override
    public ReservationDTO getReservationById(Long id)
    {
        Reservation reservation = reservationService.getReservationById(id);
        return beanMappingService.mapTo(reservation, ReservationDTO.class);
    }

    @Override
    public boolean createReservation(ReservationCreateDTO reservation)
    {
        //Reservation r = beanMappingService.mapTo(reservation, Reservation.class);
        Reservation r = new Reservation();
        r.setStartOfReservation(reservation.getStartOfReservation());
        r.setEndOfReservation(reservation.getEndOfReservation());
        r.setRoom(roomService.findById(reservation.getRoomId()));
        r.setCustomer(customerService.findCustomerById(reservation.getCustomerId()));
        return reservationService.createReservation(r);
    }

    @Override
    public void updateReservation(Long id, Long customerId, Long roomId, Date from, Date to)
    {
        Customer customer = customerService.findCustomerById(customerId);
        Room room = roomService.findById(roomId);
        Reservation reservation = reservationService.getReservationById(id);
        reservationService.updateReservation(reservation, customer, room, from, to);
    }

    @Override
    public void cancelReservation(Long id)
    {
        Reservation reservation = reservationService.getReservationById(id);
        reservationService.cancelReservation(reservation);
    }

    @Override
    public List<ReservationDTO> getFutureReservationsOfCustomer(Long customerId)
    {
        Customer customer = customerService.findCustomerById(customerId);
        return beanMappingService.mapTo(
                reservationService.getFutureReservationsOfCustomer(customer), ReservationDTO.class
        );
    }

    @Override
    public List<ReservationDTO> getNextMonthReservations()
    {
        return beanMappingService.mapTo(
                reservationService.getNextMonthReservations(), ReservationDTO.class
        );
    }
}
