package cz.muni.fi.pa165.bookingmanager.service;

import cz.muni.fi.pa165.bookingmanager.dao.RoomDao;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.service.config.ServiceConfiguration;
import java.math.BigDecimal;
import java.util.Currency;
import javax.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.testng.annotations.BeforeMethod;

/**
 * Test of RoomService class
 * 
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class RoomServiceImplTest extends AbstractJUnit4SpringContextTests {
    
    @Mock
    private RoomDao roomDAO;
        
    @Inject
    @InjectMocks
    private RoomService roomService;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private Room testRoom;
    
    @BeforeMethod
    public void prepareTestRoom(){
    	testRoom = new Room();
        testRoom.setName("Room with ocean view");
        testRoom.setCurrency(Currency.getInstance("EUR"));
        testRoom.setPrice(new BigDecimal("25.0"));
        testRoom.setNumberOfBeds(2);        
    }
    
    @Test
    public void changeRoomNumberOfBeds(){
        Room createdRoom = roomService.createRoom(testRoom);
        Assert.assertTrue(createdRoom.equals(testRoom));
    }   
    
    @Test
    public void testFindRoombyId() {
        try {
            roomService.findById(null);
            fail("No IllegalArgumentException for null id");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        
        roomService.createRoom(testRoom);
        when(roomService.findById(testRoom.getId())).thenReturn(testRoom);
    }
}
