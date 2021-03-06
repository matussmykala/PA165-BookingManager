package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entity.Customer;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created 26.10.2015
 * 
 * Implementation of CustomerDao interface
 * 
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
@Repository
public class CustomerDaoImpl implements CustomerDao{

    @PersistenceContext
    EntityManager em;

    /**
     * Create entry for customer
     *
     * @param customer
     */
    @Override
    public void create(Customer customer) {
        em.persist(customer);
    }

    /**
     * Looking for customer with id specified in param id
     * 
     * @param id
     * @return customer
     */
    @Override
    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }
    
    /**
     * Looking for customers with name specified in param name
     * 
     * @param name
     * @return list of customers
     */
    @Override
    public List<Customer> findByName(String name) {
        return Collections.unmodifiableList(em.createQuery("SELECT c FROM Customer c WHERE c.name = :name ",Customer.class)
                .setParameter("name", name).getResultList());
    }
    
    /**
     * Returns all customers 
     * 
     * @return list of all customers
     */
    @Override
    public List<Customer> findAll() {
        return Collections.unmodifiableList(em.createQuery("SELECT c FROM Customer c ",Customer.class).getResultList());
    }
    
    /**
     * Update customer specified in param customer
     * 
     * @param customer
     */
    @Override
    public void update(Customer customer) {
        em.merge(customer);
    }

    /**
     * Delete customer specified in param customer
     * 
     * @param customer
     */
    @Override
    public void delete(Customer customer) {
        em.remove(customer);
    }

    @Override
    public Customer findByEmail(String email) {
        try {
            return em.createQuery("SELECT c FROM Customer c WHERE c.email = :email ",Customer.class)
                    .setParameter("email", email).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

}
