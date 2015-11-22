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
}
