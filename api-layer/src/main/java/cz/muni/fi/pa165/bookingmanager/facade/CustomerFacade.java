package cz.muni.fi.pa165.bookingmanager.facade;

import cz.muni.fi.pa165.bookingmanager.dto.CustomerDTO;
import cz.muni.fi.pa165.bookingmanager.dto.UserAuthenticateDTO;

import java.util.Collection;

/**
 * Customer facade interface that specifies possible operations
 * with CustomerDTOs.
 *
 * Created on 22.11.2015.
 *
 * @author Vladimir Caniga
 */
public interface CustomerFacade {

    /**
     * Registers a new customer.
     *
     * @param customer            new customer
     * @param unencryptedPassword password of the new customer
     */
    void registerCustomer(CustomerDTO customer, String unencryptedPassword);

    /**
     * Fetches all registered customers.
     *
     * @return all customers
     */
    Collection<CustomerDTO> getAllCustomers();

    /**
     * Finds a customer based on his ID.
     *
     * @param customerId ID of the customer
     * @return customer with specified ID
     */
    CustomerDTO findCustomerById(Long customerId);

    /**
     * Checks if the specified user has admin role.
     *
     * @param customer customer to be checked
     * @return true if given customer has admin role, false if not
     */
    boolean isAdmin(CustomerDTO customer);

    /**
     * Updates an existing customer.
     *
     * @param customerDTO customer to be updated
     */
    void updateCustomer(CustomerDTO customerDTO);

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
    Collection<CustomerDTO> getCustomersWithReservation();
    
    /**
     * If user is authenticated returns true else false
     *
     * @return boolean
     */
    boolean authenticate(UserAuthenticateDTO u);
    
    CustomerDTO findCustomerByEmail(String email);
}
