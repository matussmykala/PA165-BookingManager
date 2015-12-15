package cz.muni.fi.pa165.bookingmanager.service;

import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Interface that specifies all possible operations with Customer
 * entities on service layer.
 *
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

    /**
     * Finds a customer based on his ID.
     *
     * @param customerId ID of the customer
     * @return customer with specified ID
     */
    Customer findCustomerById(Long customerId);

    /**
     * Checks if the specified user has admin role.
     *
     * @param customer customer to be checked
     * @return true if given customer has admin role, false if not
     */
    boolean isAdmin(Customer customer);

    /**
     * Updates an existing customer.
     *
     * @param customer customer to be updated
     */
    void updateCustomer(Customer customer);

    /**
     * Deletes a customer with given ID
     *
     * @param customerId ID of a customer that will be deleted
     */
    void deleteCustomer(Long customerId);

    /**
     * Returns a collection of all customers that have at least one reservation
     * in the system.
     *
     * @return customers with reservation
     */
    Collection<Customer> getCustomersWithReservation();
    
    boolean authenticated(Customer customer, String password);
    
    Customer findCustomerByEmail(String email);
}
