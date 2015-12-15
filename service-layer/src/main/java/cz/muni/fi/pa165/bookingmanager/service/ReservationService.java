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
    /**
     * Creates the reservation.
     *
     * @param reservation   reservation to be created.
     */
    boolean createReservation(Reservation reservation);

    /**
     * Returns all the reservations.
     *
     * @return list of all reservations
     */
    List<Reservation> getAllReservations();

    /**
     * Returns reservations taken by the customer.
     *
     * @param customer    customer
     * @return  list of reservations taken by the customer
     */
    List<Reservation> getReservationsByCustomer(Customer customer);

    /**
     * Returns reservations taken in time interval <from, to>.
     *
     * @param from  beginning date
     * @param to    end date
     * @return      list of reservation taken in time interval
     */
    List<Reservation> getReservationsOfTime(Date from, Date to);

    /**
     * Returns the reservation with the id.
     *
     * @param id    id of the reservation
     * @return  reservation with the id
     */
    Reservation getReservationById(Long id);

    /**
     * Updates the reservation according to parameters.
     *
     * @param customer    customer id
     * @param room        room id
     * @param from        beggining of the reservation
     * @param to          end of the reservation
     */
    boolean updateReservation(Reservation reservation, Customer customer, Room room, Date from, Date to);

    /**
     * Cancel reservation with id.
     *
     * @param reservation    reservation
     */
    void cancelReservation(Reservation reservation);

    /**
     * Returns all reservations of customer which starts tomorrow and later.
     *
     * @param customer    customer
     * @return  list of future reservations of the customer
     */
    public List<Reservation> getFutureReservationsOfCustomer(Customer customer);

    /**
     * Returns all reservations which starts next month.
     *
     * @return list of reservation which starts next month
     */
    public List<Reservation> getNextMonthReservations();
}
