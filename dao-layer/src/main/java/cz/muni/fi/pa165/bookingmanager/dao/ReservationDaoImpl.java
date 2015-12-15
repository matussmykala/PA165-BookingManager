package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 26.10.2015
 *
 * Implements basic operations of ReservationDao interface
 *
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
@Repository
public class ReservationDaoImpl implements ReservationDao{

    @PersistenceContext
    EntityManager em;

    /**
     * Create entry for reservation
     *
     * @param reservation
     */
    @Override
    public void create(Reservation reservation) {
        em.persist(reservation);
    }

    /**
     * Looking for reservation by id
     *
     * @param id
     * @return reservation
     */
    @Override
    public Reservation findById(Long id) {
        return em.find(Reservation.class, id);
    }

    /**
     * Returns all reservations
     *
     * @return list of reservation
     */
    @Override
    public List<Reservation> findAll() {
        return Collections.unmodifiableList(
                em.createQuery("SELECT r FROM Reservation r", Reservation.class).getResultList());
    }

    @Override
    public List<Reservation> findReservationsOfCustomer(Customer customer)
    {
        TypedQuery<Reservation> query = em.createQuery(
                "SELECT r FROM Reservation r WHERE r.customer = :customerid",
                Reservation.class);
        query.setParameter("customerid", customer);
        return Collections.unmodifiableList(query.getResultList());
    }
    
    @Override
    public List<Reservation> findAllReservationsOfRoom(Long id){
        TypedQuery<Reservation> query = em.createQuery("SELECT r FROM Reservation r WHERE r.room.id = :roomId",Reservation.class);
        query.setParameter("roomId",id);
        return Collections.unmodifiableList(query.getResultList());
    }

    @Override
    public List<Reservation> findReservationsOfTime(Date from, Date to)
    {
        TypedQuery<Reservation> query = em.createQuery(
                "SELECT r FROM Reservation r WHERE r.startOfReservation >= :startDate",
                Reservation.class);
        query.setParameter("startDate", from);
        //query.setParameter("endDate", to);
        return Collections.unmodifiableList(query.getResultList());
    }
    
    @Override
    public List<Reservation> findReservationOfRoom(Long id, Date from, Date to){
        {
        TypedQuery<Reservation> query = em.createQuery(
                "SELECT r FROM Reservation r WHERE r.room.id=:id and r.startOfReservation >= :startDate AND r.endOfReservation <= :endDate",
                Reservation.class);
        query.setParameter("id",id);
        query.setParameter("startDate", from);
        query.setParameter("endDate", to);
        return Collections.unmodifiableList(query.getResultList());
    }
    }
    
      /**
     * Update specified reservation
     *
     * @param reservation
     */
    @Override
    public void update(Reservation reservation) {
        em.merge(reservation);
    }

    /**
     * Delete specified reservation
     *
     * @param reservation
     */
    @Override
    public void delete(Reservation reservation) {
        em.remove(reservation);
    }
}
