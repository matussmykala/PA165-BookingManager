package cz.muni.fi.pa165.bookingmanager.service;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author matus
 */
@Service
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
        return reservationDao.findReservationsOfCustomer(customer);
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
    public void updateReservation(Reservation reservation, Customer customer, Room room, Date from, Date to)
    {
        if (reservation.getCustomer() != null && reservation.getCustomer() != customer) {
            reservation.getCustomer().getReservations().remove(reservation);
        }
        if (!customer.getReservations().contains(reservation)){
            customer.getReservations().add(reservation);
        }
        reservation.setCustomer(customer);
        if (reservation.getRoom() != null && reservation.getRoom() != room){
            reservation.getRoom().getReservations().remove(reservation);
        }
        if (!room.getReservations().contains(reservation)){
            room.setReservation(reservation);
        }
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

    @Override
    public List<Reservation> getFutureReservationsOfCustomer(Customer customer)
    {
        List<Reservation> reservations = reservationDao.findReservationsOfCustomer(customer);
        List<Reservation> retList = new ArrayList<>();
        Date now = new Date();
        for (Reservation r : reservations){
            if (r.getStartOfReservation().after(now)){
                retList.add(r);
            }
        }
        return retList;
    }
    
   

    @Override
    public List<Reservation> getNextMonthReservations()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date nextMonthFirstDay = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date nextMonthLastDay = calendar.getTime();

        return reservationDao.findReservationsOfTime(nextMonthFirstDay, nextMonthLastDay);
    }

    @Override
    public List<Reservation> getAllReservationsOfRoom(Long id) {
        
        return reservationDao.findAllReservationsOfRoom(id);
    }    
  
}
