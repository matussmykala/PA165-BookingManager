package cz.muni.fi.pa165.bookingmanager.service.facade;

import cz.muni.fi.pa165.bookingmanager.dao.CustomerDao;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.service.CustomerService;
import cz.muni.fi.pa165.bookingmanager.service.config.ServiceConfiguration;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created on 25.11.2015.
 *
 * @author Vladimir Caniga
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CustomerServiceTest extends AbstractJUnit4SpringContextTests {

    @Mock
    private CustomerDao customerDao;

    @Autowired
    @InjectMocks
    private CustomerService customerService;

    private Customer customer1;
    private Customer customer2;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        customer1 = new Customer();
        customer1.setName("Janko");
        customer1.setSurname("Hrasko");
        customer1.setEmail("j.hrasko@email.com");
        customer1.setUsername("jhrasko");
        customer1.setIsAdmin(true);

        customer2 = new Customer();
        customer2.setName("Emanuel");
        customer2.setName("Bacigala");
        customer2.setEmail("e.bacigala@email.com");
        customer2.setUsername("ebacigala");
        customer2.setIsAdmin(false);
    }

    @Test
    public void registerCustomerTest() {
        doNothing().when(customerDao).create(any(Customer.class));

        customerService.registerCustomer(customer1, "password");
        verify(customerDao).create(customer1);
    }

    @Test
    public void getAllCustomersTest() {
        List<Customer> customers = new ArrayList<>();
        when(customerDao.findAll()).thenReturn(customers);

        customerService.getAllCustomers();
        verify(customerDao).findAll();
    }

    @Test
    public void findCustomerByIdTest() {
        when(customerDao.findById(any(Long.class))).thenReturn(customer1);
        customerService.findCustomerById(1L);

        verify(customerDao).findById(1L);
    }

    @Test
    public void isAdminTest() {
        when(customerDao.findById(any(Long.class))).thenReturn(customer1);

        Assert.assertTrue(customerService.isAdmin(customer1));

        when(customerDao.findById(any(Long.class))).thenReturn(customer2);

        Assert.assertFalse(customerService.isAdmin(customer2));
    }

    @Test
    public void updateCustomerTest() {
        doNothing().when(customerDao).update(any(Customer.class));

        customerService.updateCustomer(customer1);
        verify(customerDao).update(customer1);
    }

    @Test
    public void deleteCustomerTest() {
        doNothing().when(customerDao).delete(any(Customer.class));
        when(customerDao.findById(any(Long.class))).thenReturn(customer1);

        customerService.deleteCustomer(1L);
        verify(customerDao).findById(1L);
        verify(customerDao).delete(customer1);
    }

    @Test
    public void isAdminNullCustomerTest() {
        expectedException.expect(IllegalArgumentException.class);
        customerService.isAdmin(null);
    }

    @Test
    public void isAdminNullIdTest() {
        customer1.setId(null);
        expectedException.expect(IllegalArgumentException.class);
        customerService.isAdmin(customer1);
    }
}
