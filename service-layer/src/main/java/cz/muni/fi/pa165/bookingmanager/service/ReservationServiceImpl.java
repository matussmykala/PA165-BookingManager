package cz.muni.fi.pa165.bookingmanager.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.muni.fi.pa165.bookingmanager.dao.CustomerDao;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDao;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDao;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author matus
 */
public class ReservationServiceImpl implements ReservationService
{
    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private RoomDao roomDao;

    @Override
    public void createReservation(Reservation reservation)
    {
        customerDao.create(reservation.getCustomer());
        roomDao.create(reservation.getRoom());
        reservationDao.create(reservation);
    }

    @Override
    public List<Reservation> getAllReservations()
    {
        return reservationDao.findAll();
    }

    @Override
    public List<Reservation> getReservationsByCustomer(Customer customer)
    {
        List<Reservation> reservations = new ArrayList<>();
        for(Reservation reservation : reservationDao.findReservationsOfCustomer(customer)){
            reservations.add(reservation);
        }
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsOfTime(Date from, Date to)
    {
        List<Reservation> reservations = new ArrayList<>();
        for(Reservation reservation : reservationDao.findReservationsOfTime(from, to)){
            reservations.add(reservation);
        }
        return reservations;
    }

    @Override
    public Reservation getReservationById(Long id)
    {
        return reservationDao.findById(id);
    }

    @Override
    public void createReservation(Customer customer, Room room, Date from, Date to)
    {
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setRoom(room);
        reservation.setStartOfReservation(from);
        reservation.setEndOfReservation(to);
        customerDao.create(customer);
        roomDao.create(room);
        reservationDao.create(reservation);
    }

    @Override
    public void updateReservation(Reservation reservation, Customer customer, Room room, Date from, Date to)
    {
        reservation.setCustomer(customer);
        reservation.setRoom(room);
        reservation.setStartOfReservation(from);
        reservation.setEndOfReservation(to);
        reservationDao.update(reservation);
    }

    @Override
    public void cancelReservation(Reservation reservation)
    {
        reservationDao.delete(reservation);
    }
}
