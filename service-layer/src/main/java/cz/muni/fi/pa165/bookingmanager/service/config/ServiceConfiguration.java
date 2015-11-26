/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.service.config;

import cz.muni.fi.pa165.bookingmanager.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.bookingmanager.dto.CustomerDTO;
import cz.muni.fi.pa165.bookingmanager.dto.HotelCreateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.HotelDTO;
import cz.muni.fi.pa165.bookingmanager.dto.ReservationDTO;
import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingServiceImpl;
import cz.muni.fi.pa165.bookingmanager.service.ReservationServiceImpl;
import cz.muni.fi.pa165.bookingmanager.service.facade.HotelFacadeImpl;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 *
 * @author ivet
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
//@ComponentScan(basePackageClasses={ReservationServiceImpl.class, HotelFacadeImpl.class, BeanMappingServiceImpl.class})
@ComponentScan(basePackages = {"cz.muni.fi.pa165.bookingmanager.service"})
public class ServiceConfiguration {


	@Bean
	public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();
		dozer.addMapping(new DozerCustomConfig());
		return dozer;
	}

	public class DozerCustomConfig extends BeanMappingBuilder {
	    @Override
	    protected void configure() {
                mapping(Hotel.class, HotelDTO.class);
                mapping(Hotel.class, RoomDTO.class);
                //mapping(Room.class, RoomDTO.class);
                mapping(HotelCreateDTO.class,Hotel.class);
		mapping(Customer.class, CustomerDTO.class);
		mapping(Reservation.class, ReservationDTO.class);
		}
	}

}
