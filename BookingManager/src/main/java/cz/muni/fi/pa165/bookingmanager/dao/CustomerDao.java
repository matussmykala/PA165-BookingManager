/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entity.Customer;

import java.util.List;

/**
 *
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
public interface CustomerDao {
    
    /**
     * Create entry for customer
     * 
     * @param customer
     */
    public void create(Customer customer);
    
    /**
     * Find customer by given id
     * 
     * @param id
     * @return
     */
    public Customer findById(Long id);
    
    /**
     * Find customer by given name
     * 
     * @param name
     * @return
     */
    public Customer findByName(String name);
    
    /**
     * Return list of all customers
     * 
     * @return
     */
    public List<Customer> findAll();
    
    /**
     * Update customer entry
     * 
     * @param customer
     */
    public void update(Customer customer);
    
    /**
     * Delete customer entry
     * 
     * @param customer
     */
    public void delete(Customer customer);
    
}
