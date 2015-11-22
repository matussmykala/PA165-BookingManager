package cz.muni.fi.pa165.bookingmanager.facade;

import cz.muni.fi.pa165.bookingmanager.dto.CustomerDTO;

import java.util.Collection;

/**
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
}
