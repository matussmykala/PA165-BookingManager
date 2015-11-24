package cz.muni.fi.pa165.bookingmanager.service.facade;
import java.util.Date;
import java.util.List;

import cz.muni.fi.pa165.bookingmanager.dto.ReservationDTO;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.facade.ReservationFacade;
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingService;
import cz.muni.fi.pa165.bookingmanager.service.CustomerService;
import cz.muni.fi.pa165.bookingmanager.service.ReservationService;

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
    //@Autowired
    //private RoomService roomService;
    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public List<ReservationDTO> getAllReservations()
    {
        List<Reservation> reservations = reservationService.getAllReservations();
        return beanMappingService.mapTo(reservations, ReservationDTO.class);
    }

    @Override
    public List<ReservationDTO> getReservationsOfTime(Date from, Date to)
    {
        List<Reservation> reservations = reservationService.getReservationsOfTime(from, to);
        return beanMappingService.mapTo(reservations, ReservationDTO.class);
    }

    @Override
    public List<ReservationDTO> getReservationsByCustomer(Long customerId)
    {
        //Customer customer = customerService.getCustomerById(customerId);
        //List<Reservation> reservations = reservationService.getReservationsByCustomer(customer);

        //return beanMappingService.mapTo(reservations, ReservationDTO.class);
        return null;
    }

    @Override
    public ReservationDTO getReservationById(Long id)
    {
        Reservation reservation = reservationService.getReservationById(id);
        return beanMappingService.mapTo(reservation, ReservationDTO.class);
    }

    @Override
    public void createReservation(Long customerId, Long roomId, Date from, Date to)
    {
        //Customer customer = customerService.getCustomerById(customerId);
        //Room room = roomService.getRoomById(roomId);
        //reservationService.createReservation(customer, room, from, to);
    }

    @Override
    public void updateReservation(Long id, Long customerId, Long roomId, Date from, Date to)
    {
        //Customer customer = customerService.getCustomerById(customerId);
        //Room room = roomService.getRoomById(roomId);
        //Reservation reservation = reservationService.getReservationById(id);
        //reservationService.updateReservation(reservation, customer, room, from, to);
    }

    @Override
    public void cancelReservation(Long id)
    {
        Reservation reservation = reservationService.getReservationById(id);
        reservationService.cancelReservation(reservation);
    }
}
