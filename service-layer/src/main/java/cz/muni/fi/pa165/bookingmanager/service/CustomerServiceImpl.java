package cz.muni.fi.pa165.bookingmanager.service;

import cz.muni.fi.pa165.bookingmanager.dao.CustomerDao;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created on 22.11.2015.
 *
 * @author Vladimir Caniga
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    /**
     * Registers a new customer.
     *
     * @param customer            new customer
     * @param unencryptedPassword password of the new customer
     */
    @Override
    public void registerCustomer(Customer customer, String unencryptedPassword) {
        customerDao.create(customer);
    }

    /**
     * Fetches all registered customers.
     *
     * @return all customers
     */
    @Override
    public Collection<Customer> getAllCustomers() {
        return customerDao.findAll();
    }

    /**
     * Finds a customer based on his ID.
     *
     * @param customerId ID of the customer
     * @return customer with specified ID
     */
    @Override
    public Customer findCustomerById(Long customerId) {
        return customerDao.findById(customerId);
    }

    /**
     * Checks if the specified user has admin role.
     *
     * @param customer customer to be checked
     * @return true if given customer has admin role, false if not
     */
    @Override
    public boolean isAdmin(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }

        Customer customer1 = customerDao.findById(customer.getId());
        if (customer1 == null) {
            throw new IllegalArgumentException("Such customer does not exist");
        }

        return customer1.isAdmin();
    }

    /**
     * Updates an existing customer.
     *
     * @param customer customer to be updated
     */
    @Override
    public void updateCustomer(Customer customer) {
        customerDao.update(customer);
    }

    /**
     * Deletes a customer with given ID
     *
     * @param customerId ID of a customer that will be deleted
     */
    @Override
    public void deleteCustomer(Long customerId) {
        customerDao.delete(customerDao.findById(customerId));
    }
}
