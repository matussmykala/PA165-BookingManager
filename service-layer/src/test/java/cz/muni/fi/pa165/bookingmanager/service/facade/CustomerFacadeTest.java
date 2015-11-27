package cz.muni.fi.pa165.bookingmanager.service.facade;

import cz.muni.fi.pa165.bookingmanager.dto.CustomerDTO;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingService;
import cz.muni.fi.pa165.bookingmanager.service.CustomerService;
import cz.muni.fi.pa165.bookingmanager.service.config.ServiceConfiguration;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Tests for CustomerFacade class.
 *
 * Created on 25.11.2015.
 *
 * @author Vladimir Caniga
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CustomerFacadeTest extends AbstractJUnit4SpringContextTests {

    @Mock
    CustomerService customerService;

    @Autowired
    BeanMappingService beanMappingService;

    CustomerFacadeImpl customerFacade;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Customer customer1;
    private Customer customer2;

    private CustomerDTO customer1DTO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        customerFacade = new CustomerFacadeImpl();
        customerFacade.setBeanMappingService(beanMappingService);
        customerFacade.setCustomerService(customerService);

        customer1 = new Customer();
        customer1.setName("Janko");
        customer1.setSurname("Hrasko");
        customer1.setEmail("j.hrasko@email.com");
        customer1.setUsername("jhrasko");
        customer1.setIsAdmin(true);
        customer1.setId(1L);

        customer2 = new Customer();
        customer2.setName("Emanuel");
        customer2.setName("Bacigala");
        customer2.setEmail("e.bacigala@email.com");
        customer2.setUsername("ebacigala");
        customer2.setIsAdmin(false);
        customer2.setId(2L);

        customer1DTO = new CustomerDTO();
        customer1DTO.setName("Janko");
        customer1DTO.setSurname("Hrasko");
        customer1DTO.setEmail("j.hrasko@email.com");
        customer1DTO.setUsername("jhrasko");
        customer1DTO.setId(1L);
    }

    @Test
    public void registerCustomerTest() {
        doNothing().when(customerService).registerCustomer(any(Customer.class), any(String.class));

        customerFacade.registerCustomer(customer1DTO, "password");
        verify(customerService).registerCustomer(any(Customer.class), eq("password"));
    }

    @Test
    public void registerCustomerNullCustomerTest() {
        expectedException.expect(IllegalArgumentException.class);
        customerFacade.registerCustomer(null, "password");
    }

    @Test
    public void registerCustomerNullPasswordTest() {
        expectedException.expect(IllegalArgumentException.class);
        customerFacade.registerCustomer(customer1DTO, null);
    }

    @Test
    public void getAllCustomersTest() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        when(customerService.getAllCustomers()).thenReturn(customers);

        Collection<CustomerDTO> result = customerFacade.getAllCustomers();
        verify(customerService).getAllCustomers();

        assertTrue(result.size() == 1);
        CustomerDTO resultDTO = result.stream().findFirst().get();

        assertEquals("Janko", resultDTO.getName());
        assertEquals("Hrasko", resultDTO.getSurname());
        assertEquals("j.hrasko@email.com", resultDTO.getEmail());
        assertEquals("jhrasko", resultDTO.getUsername());
    }

    @Test
    public void findCustomerByIdTest() {
        when(customerService.findCustomerById(any(Long.class))).thenReturn(customer1);

        CustomerDTO resultDTO = customerFacade.findCustomerById(1L);
        verify(customerService).findCustomerById(1L);

        assertEquals("Janko", resultDTO.getName());
        assertEquals("Hrasko", resultDTO.getSurname());
        assertEquals("j.hrasko@email.com", resultDTO.getEmail());
        assertEquals("jhrasko", resultDTO.getUsername());
    }

    @Test
    public void findCustomerByIdNullIdTest() {
        expectedException.expect(IllegalArgumentException.class);
        customerFacade.findCustomerById(null);
    }

    @Test
    public void isAdminTest() {
        when(customerService.isAdmin(any(Customer.class))).thenReturn(true);

        boolean result = customerFacade.isAdmin(customer1DTO);
        verify(customerService).isAdmin(any(Customer.class));
        assertTrue(result);

        when(customerService.isAdmin(any(Customer.class))).thenReturn(false);

        result = customerFacade.isAdmin(customer1DTO);
        verify(customerService, times(2)).isAdmin(any(Customer.class));
        assertFalse(result);
    }

    @Test
    public void isAdminNullCustomerTest() {
        expectedException.expect(IllegalArgumentException.class);
        customerFacade.isAdmin(null);
    }

    @Test
    public void updateCustomerTest() {
        doNothing().when(customerService).updateCustomer(any(Customer.class));
        when(customerService.findCustomerById(any(Long.class))).thenReturn(customer1);

        customerFacade.updateCustomer(customer1DTO);
        verify(customerService).updateCustomer(customer1);
        verify(customerService).findCustomerById(any(long.class));
    }

    @Test
    public void updateCustomerNullCustomerTest() {
        expectedException.expect(IllegalArgumentException.class);
        customerFacade.updateCustomer(null);
    }

    @Test
    public void deleteCustomerTest() {
        doNothing().when(customerService).deleteCustomer(any(Long.class));

        customerFacade.deleteCustomer(1L);
        verify(customerService).deleteCustomer(1L);
    }

    @Test
    public void deleteCustomerNullIdTest() {
        expectedException.expect(IllegalArgumentException.class);
        customerFacade.deleteCustomer(null);
    }

    @Test
    public void getCustomersWithReservation() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        when(customerService.getCustomersWithReservation()).thenReturn(customers);

        Collection<CustomerDTO> customerDTOs = customerFacade.getCustomersWithReservation();
        verify(customerService).getCustomersWithReservation();
        assertEquals(1, customerDTOs.size());
        CustomerDTO resultDTO = customerDTOs.stream().findFirst().get();

        assertEquals("Janko", resultDTO.getName());
        assertEquals("Hrasko", resultDTO.getSurname());
        assertEquals("j.hrasko@email.com", resultDTO.getEmail());
        assertEquals("jhrasko", resultDTO.getUsername());
    }
}
