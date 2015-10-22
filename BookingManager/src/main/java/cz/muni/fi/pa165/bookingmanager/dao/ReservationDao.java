/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.dao;

/**
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
