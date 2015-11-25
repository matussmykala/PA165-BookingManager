/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.service.facade;


import cz.muni.fi.pa165.bookingmanager.dto.HotelCreateDTO;
import cz.muni.fi.pa165.bookingmanager.facade.HotelFacade;
import cz.muni.fi.pa165.bookingmanager.service.HotelService;
import cz.muni.fi.pa165.bookingmanager.service.config.ServiceConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 *
 * @author ivet
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class HotelFacadeImplTest extends AbstractTransactionalJUnit4SpringContextTests {
    
    @Mock
    private HotelService hotelService;

    @Autowired
    @InjectMocks
    private HotelFacade hotelFacade;


    @Before
    public void setUpClass() {
         MockitoAnnotations.initMocks(this);
    }
  
  
    /**
     * Test of createHotel method, of class HotelFacadeImpl.
     */
    @Test
    public void testCreateHotel() {
        HotelCreateDTO hotelDTO = new HotelCreateDTO();
        hotelDTO.setName("Park Hotel");
        hotelDTO.setAddress("Praha");
        hotelFacade.createHotel(hotelDTO);
    }

    /**
     * Test of updateHotel method, of class HotelFacadeImpl.
     */
    @Test
    public void testUpdateHotel() {
    
    }

    /**
     * Test of deleteHotel method, of class HotelFacadeImpl.
     */
    @Test
    public void testDeleteHotel() {
      
    }

    /**
     * Test of getHotelById method, of class HotelFacadeImpl.
     */
    @Test
    public void testGetHotelById() {
  
    }

    /**
     * Test of getAllHotels method, of class HotelFacadeImpl.
     */
    @Test
    public void testGetAllHotels() {
      
    }
    
}
