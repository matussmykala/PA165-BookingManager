/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.service.facade;


import cz.muni.fi.pa165.bookingmanager.dto.HotelCreateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.HotelDTO;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.facade.HotelFacade;
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingService;
import cz.muni.fi.pa165.bookingmanager.service.HotelService;
import cz.muni.fi.pa165.bookingmanager.service.config.ServiceConfiguration;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author ivet
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class HotelFacadeImplTest extends AbstractTransactionalTestNGSpringContextTests{

    @Mock
    private HotelService hotelService;

    @Autowired
    @InjectMocks
    private HotelFacade hotelFacade;

    @Autowired
    BeanMappingService beanMappingService;

    @BeforeMethod
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
        Mockito.verify(hotelService).createHotel(beanMappingService.mapTo(hotelDTO,Hotel.class));
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
