package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entity.Reservation;

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
}
