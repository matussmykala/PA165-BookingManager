package cz.muni.fi.pa165.bookingmanager.service.facade;

import cz.muni.fi.pa165.bookingmanager.dto.RoomCreateDTO;
import cz.muni.fi.pa165.bookingmanager.facade.RoomFacade;
import cz.muni.fi.pa165.bookingmanager.service.RoomService;
import cz.muni.fi.pa165.bookingmanager.service.config.ServiceConfiguration;
import java.math.BigDecimal;
import java.util.Currency;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test of  RoomFacadeImpl
 * 
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=ServiceConfiguration.class)
public class RoomFacadeImplTest extends AbstractTransactionalTestNGSpringContextTests{
    @Mock
    private RoomService roomService;

    @Autowired
    @InjectMocks
    private RoomFacade roomFacade;
    
    
    
    @BeforeMethod
    public void setUpClass() {
         MockitoAnnotations.initMocks(this);
    }
  
  
    /**
     * Test of createRoom method, of class RoomFacadeImpl.
     */
    @Test
    public void testCreateRoom() {
        RoomCreateDTO roomDTO = new RoomCreateDTO();
        roomDTO.setName("Room with ocean view");
        roomDTO.setCurrency(Currency.getInstance("EUR"));
        roomDTO.setPrice(new BigDecimal("25.0"));
        roomDTO.setNumberOfBeds(2);
        roomFacade.createRoom(roomDTO);
    }
}
