package cz.muni.fi.pa165.bookingmanager.service;

import cz.muni.fi.pa165.bookingmanager.dao.CustomerDao;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDao;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of CustomerService interface.
 *
 * Created on 22.11.2015.
 *
 * @author Vladimir Caniga
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private ReservationDao reservationDao;

    //    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    /**
     * Registers a new customer.
     *
     * @param customer            new customer
     * @param unencryptedPassword password of the new customer
     */
    @Override
    public void registerCustomer(Customer customer, String unencryptedPassword) {
//        customer.setPassword(unencryptedPassword);
        customer.setPassword(passwordEncoder.encode(unencryptedPassword));
        customerDao.create(customer);
    }

    /**
     * Checks if provided password equals customers hashed password stored in database.
     *
     * @param customer            customer that is authenticating
     * @param unencryptedPassword customer's password
     * @return true if the passwords match, false if not
     */
    @Override
    public boolean authenticateCustomer(Customer customer, String unencryptedPassword) {
        if (customer == null) {
            return false;
        }
//        return customer.getPassword().equals(sha256Hash(unencryptedPassword));
        return passwordEncoder.matches(unencryptedPassword, customer.getPassword());
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

    /**
     * Returns a collection of all customers that have at least one reservation
     * in the system.
     *
     * @return customers with reservation
     */
    @Override
    public Collection<Customer> getCustomersWithReservation() {
        List<Reservation> reservations = reservationDao.findAll();
        Set<Customer> customers = new HashSet<>();

        for (Reservation reservation : reservations) {
            customers.add(reservation.getCustomer());
        }

        return customers;
    }


    @Override
    public Customer findCustomerByEmail(String email) {
        return customerDao.findByEmail(email);
    }
}
