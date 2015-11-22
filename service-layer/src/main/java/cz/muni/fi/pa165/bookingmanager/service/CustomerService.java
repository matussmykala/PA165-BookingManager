package cz.muni.fi.pa165.bookingmanager.service;

import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created on 22.11.2015.
 *
 * @author Vladimir Caniga
 */
@Service
public interface CustomerService {

    /**
     * Registers a new customer.
     *
     * @param customer            new customer
     * @param unencryptedPassword password of the new customer
     */
    void registerCustomer(Customer customer, String unencryptedPassword);

    /**
     * Fetches all registered customers.
     *
     * @return all customers
     */
    Collection<Customer> getAllCustomers();
}
