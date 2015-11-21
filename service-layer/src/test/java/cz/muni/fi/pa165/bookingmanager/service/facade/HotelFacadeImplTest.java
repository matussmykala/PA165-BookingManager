/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.service.facade;



import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import cz.muni.fi.pa165.bookingmanager.service.config.ServiceConfiguration;

/**
 *
 * @author ivet
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class HotelFacadeImplTest extends AbstractTransactionalTestNGSpringContextTests{
    
    public HotelFacadeImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
  
    /**
     * Test of createHotel method, of class HotelFacadeImpl.
     */
    @org.junit.Test
    public void testCreateHotel() {
     
    }

    /**
     * Test of updateHotel method, of class HotelFacadeImpl.
     */
    @org.junit.Test
    public void testUpdateHotel() {
    
    }

    /**
     * Test of deleteHotel method, of class HotelFacadeImpl.
     */
    @org.junit.Test
    public void testDeleteHotel() {
      
    }

    /**
     * Test of getHotelById method, of class HotelFacadeImpl.
     */
    @org.junit.Test
    public void testGetHotelById() {
  
    }

    /**
     * Test of getAllHotels method, of class HotelFacadeImpl.
     */
    @org.junit.Test
    public void testGetAllHotels() {
      
    }
    
}
