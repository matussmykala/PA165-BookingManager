package cz.muni.fi.pa165.bookingmanager.service.facade;

import cz.muni.fi.pa165.bookingmanager.dto.CustomerDTO;
import cz.muni.fi.pa165.bookingmanager.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.facade.CustomerFacade;
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingService;
import cz.muni.fi.pa165.bookingmanager.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Implementation of CustomerFacade
 *
 * Created on 22.11.2015.
 *
 * @author Vladimir Caniga
 */
@Service
@Transactional
public class CustomerFacadeImpl implements CustomerFacade {

    @Inject
    private CustomerService customerService;

    @Inject
    private BeanMappingService beanMappingService;


    /**
     * Registers a new customer.
     *
     * @param customer            new customer
     * @param unencryptedPassword password of the new customer
     */
    @Override
    public void registerCustomer(CustomerDTO customer, String unencryptedPassword) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null.");
        }
        if (unencryptedPassword == null) {
            throw new IllegalArgumentException("unencryptedPassword is null.");
        }

        customerService.registerCustomer(beanMappingService.mapTo(customer, Customer.class),
                unencryptedPassword);
    }

    /**
     * Fetches all registered customers.
     *
     * @return all customers
     */
    @Override
    public Collection<CustomerDTO> getAllCustomers() {
        return beanMappingService.mapTo(customerService.getAllCustomers(), CustomerDTO.class);
    }

    /**
     * Finds a customer based on his ID.
     *
     * @param customerId ID of the customer
     * @return customer with specified ID
     */
    @Override
    public CustomerDTO findCustomerById(Long customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException("customerId is null");
        }

        Customer customer = customerService.findCustomerById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer with id " + customerId + " does not exist");
        }

        return beanMappingService.mapTo(customer, CustomerDTO.class);
    }
    
    @Override
    public CustomerDTO findCustomerByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email is null");
        }

        Customer customer = customerService.findCustomerByEmail(email);
        if (customer == null) {
            throw new IllegalArgumentException("Customer with email " + email + " does not exist");
        }

        return beanMappingService.mapTo(customer, CustomerDTO.class);
    }

    /**
     * Checks if the specified user has admin role.
     *
     * @param customer customer to be checked
     * @return true if given customer has admin role, false if not
     */
    @Override
    public boolean isAdmin(CustomerDTO customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }
        return customerService.isAdmin(beanMappingService.mapTo(customer, Customer.class));
    }

    /**
     * Updates an existing customer.
     *
     * @param customerDTO customer to be updated
     */
    public void updateCustomer(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            throw new IllegalArgumentException("customerDTO is null");
        }
        if (customerDTO.getId() == null) {
            throw new IllegalArgumentException("customerDTO does not have valid ID set");
        }

        Customer customer = customerService.findCustomerById(customerDTO.getId());
        if (customer == null) {
            throw new IllegalArgumentException("No such customer exists");
        }

        customerService.updateCustomer(customer);
    }

    /**
     * Deletes a customer with given ID
     *
     * @param customerId ID of a customer that will be deleted
     */
    public void deleteCustomer(Long customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException("customerId is null");
        }

        customerService.deleteCustomer(customerId);
    }

    /**
     * Returns a collection of all customers that have at least one reservation
     * in the system.
     *
     * @return customers with reservation
     */
    @Override
    public Collection<CustomerDTO> getCustomersWithReservation() {
        return beanMappingService.mapTo(customerService.getCustomersWithReservation(), CustomerDTO.class);
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setBeanMappingService(BeanMappingService beanMappingService) {
        this.beanMappingService = beanMappingService;
    }
    
    @Override
    public boolean authenticate(UserAuthenticateDTO u) {
        return customerService.authenticated(customerService.findCustomerById(u.getUserId()), u.getPassword());
    }
}
