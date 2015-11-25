package cz.muni.fi.pa165.bookingmanager.service.facade;

import cz.muni.fi.pa165.bookingmanager.dto.RoomCreateDTO;
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingService;
import cz.muni.fi.pa165.bookingmanager.service.RoomService;
import java.math.BigDecimal;
import java.util.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test of  RoomFacadeImpl
 * 
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class RoomFacadeImplTest {

    @Mock
    private RoomService roomService;

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private RoomFacadeImpl roomFacade;

    /**
     * Test of createRoom method, of class RoomFacadeImpl.
     */
    @Test
    public void testCreateRoom() {
        /*RoomCreateDTO roomDTO = new RoomCreateDTO();
        roomDTO.setName("Room with ocean view");
        roomDTO.setCurrency(Currency.getInstance("EUR"));
        roomDTO.setPrice(new BigDecimal("25.0"));
        roomDTO.setNumberOfBeds(2);
        roomFacade.createRoom(roomDTO);*/
    }    
}