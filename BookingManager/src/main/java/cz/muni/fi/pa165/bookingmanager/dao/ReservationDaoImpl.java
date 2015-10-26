package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import java.util.List;
import javax.persistence.EntityManager;
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
@Transactional
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
        return em.createQuery("SELECT r FROM Reservation r", Reservation.class).getResultList();
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
