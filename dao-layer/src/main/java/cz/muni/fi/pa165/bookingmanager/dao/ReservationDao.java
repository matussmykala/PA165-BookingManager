package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;

import java.util.Date;
import java.util.List;

/**
 * Created 26.10.2015
 *
 * Interface with CRUD operations for Reservation
 *
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
public interface ReservationDao {
    /**
     * Create entry for reservation
     *
     * @param reservation
     */
    public void create(Reservation reservation);

    /**
     * Find reservation by given id
     *
     * @param id
     * @return
     */
    public Reservation findById(Long id);

    /**
     * Return list of all reservations
     *
     * @return
     */
    public List<Reservation> findAll();

    /**
     * Return list of reservations of the customer.
     *
     * @param customer
     * @return
     */
    public List<Reservation> findReservationsOfCustomer(Customer customer);

    /**
     * Return list of reservations of time interval.
     *
     * @param from
     * @param to
     * @return
     */
    public List<Reservation> findReservationsOfTime(Date from, Date to);

    /**
     * Update reservation entry
     *
     * @param reservation
     */
    public void update(Reservation reservation);

    /**
     * Delete reservation entry
     *
     * @param reservation
     */
    public void delete(Reservation reservation);

    /**
     * Find reservations of room in range of time
     * @param id
     * @param from
     * @param to
     * @return
     */
    public List<Reservation> findReservationOfRoom(Long id, Date from, Date to);

     /**
     * Find all reservations of room
     * @param id
     * @return
     */
    public List<Reservation> findAllReservationsOfRoom(Long id);
}
