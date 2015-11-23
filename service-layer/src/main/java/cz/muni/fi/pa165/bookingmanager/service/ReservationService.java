package cz.muni.fi.pa165.bookingmanager.service;
import java.util.Date;
import java.util.List;

import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;

/**
 * @author matus
 */
public interface ReservationService
{
    void createReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsByCustomer(Customer customer);
    List<Reservation> getReservationsOfTime(Date from, Date to);
    Reservation getReservationById(Long id);
    void createReservation(Customer customer, Room room, Date from, Date to);
    void updateReservation(Reservation reservation, Customer customer, Room room, Date from, Date to);
    void cancelReservation(Reservation reservation);
}
