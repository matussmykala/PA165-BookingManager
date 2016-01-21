package cz.muni.cz.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.bookingmanager.dao.CustomerDao;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by matus on 28.10.2015.
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CustomerDaoTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private CustomerDao customerDao;

    private Customer customer1;
    private Customer customer2;
    private Customer customer3;

    @Before
    public void before(){
        customer1 = new Customer();
        customer1.setName("customerName");
        customer1.setPassword("customerPassword");
        customer1.setEmail("customer@mail.com");
        customer1.setIsAdmin(false);
        customer1.setSurname("customerSurname");
        customer1.setUsername("customerUsername");

        customer2 = new Customer();
        customer2.setName("customer2Name");
        customer2.setPassword("customer2Password");
        customer2.setEmail("customer2@mail.com");
        customer2.setIsAdmin(false);
        customer2.setSurname("customer2Surname");
        customer2.setUsername("customer2Username");

        customer3 = new Customer();
        customer3.setName("customer2Name");
        customer3.setPassword("customer2Password");
        customer3.setEmail("customer2@mail.com");
        customer3.setIsAdmin(false);
        customer3.setSurname("customer2Surname");
        customer3.setUsername("customer2Username");

        customerDao.create(customer1);
        customerDao.create(customer2);
    }

    @Test
    public void createTest(){
        assertNotNull(customer1.getId());
        assertNotNull(customer2.getId());
        assertNull(customer3.getId());
    }
/*
    @Test
    public void findByIdTest(){
        Customer found = customerDao.findById(customer1.getId());
        assertNotNull(found);
        assertEquals(customer1, found);
    }

    @Test
    public void findByNameTest(){
        List<Customer> found = customerDao.findByName(customer1.getName());
        assertTrue(found.size() == 1);
        assertTrue(found.contains(customer1));

        customer2.setName(customer1.getName());
        customerDao.update(customer2);
        found = customerDao.findByName(customer1.getName());
        assertTrue(found.size() == 2);
        assertTrue(found.contains(customer1));
        assertTrue(found.contains(customer2));
    }

    @Test
    public void findAllTest(){
        List<Customer> found = customerDao.findAll();
        assertTrue(found.size() == 2);
        assertTrue(found.contains(customer1));
        assertTrue(found.contains(customer2));

        customerDao.create(customer3);
        found = customerDao.findAll();
        assertTrue(found.size() == 3);
        assertTrue(found.contains(customer3));
    }

    @Test
    public void updateTest(){
        customer1.setName("newName");
        customer1.setUsername("newUsername");
        customer1.setPassword("newPassword");
        customer1.setSurname("newSurname");
        customerDao.update(customer1);

        Customer found = customerDao.findById(customer1.getId());
        assertEquals("newName", found.getName());
        assertEquals("newUsername", found.getUsername());
        assertEquals("newPassword", found.getPassword());
        assertEquals("newSurname", found.getSurname());
    }

    @Test
    public void deleteTest(){
        List<Customer> found = customerDao.findAll();
        int size = found.size();

        customerDao.delete(customer1);
        found = customerDao.findAll();
        assertTrue(found.size() + 1 == size);

        size = found.size();
        customerDao.delete(customer2);
        found = customerDao.findAll();
        assertTrue(found.size() + 1 == size);
    }
    */
}
