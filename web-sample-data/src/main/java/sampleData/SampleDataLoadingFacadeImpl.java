package sampleData;
import cz.muni.fi.pa165.bookingmanager.entity.Customer;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.entity.Reservation;
import cz.muni.fi.pa165.bookingmanager.entity.Room;
import cz.muni.fi.pa165.bookingmanager.service.CustomerService;
import cz.muni.fi.pa165.bookingmanager.service.HotelService;
import cz.muni.fi.pa165.bookingmanager.service.ReservationService;
import cz.muni.fi.pa165.bookingmanager.service.RoomService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author matus
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade
{
    
    @Autowired
    HotelService hotelService;
    Date date;
    @Autowired
    RoomService roomService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    CustomerService customerService;

    @Override
    public void loadData() throws IOException {
        this.Add();
    }
    
    public void Add(){
      
        this.createDate();
        Hotel voronez = hotel("Voronez","Brno","Luxusny hotel v Brne",date);
        Hotel park = hotel("Park Hotel","Praha","Pekny hotel v Prahe", date);
        this.createDate();
        Hotel arkadia = hotel("Arkadia","Banovce","Drahy maly hotel kde nikto nechodi", date);
        Hotel ira = hotel("Ira hotel", "Praha", "Miesto kde sa vzdy zmesti vela ludi", date);
        this.createDate();
        Hotel domSportu = hotel("Dom Sportu", "Bratislava", "Hotel sportovcov", date);
        Hotel ohlaHotel = hotel("Ohla Hotel", "Bratislava" , "Nejaky hotel", date);
        Hotel transylvania = hotel("Transylvania", "Brno", "Hotel priseriek", date);
        
        Room room1 = room("Izba-1",1,new BigDecimal("25.1"), voronez );
        Room room2 = room("Izba-2",2,new BigDecimal("25.2"), ira );
        Room room3 = room("Izba-3",3,new BigDecimal("25.3"), arkadia );
        Room room4 = room("Izba-4",4,new BigDecimal("25.4"), ira );
        
        Customer customer1 = customer("janko", "hrasko", "hrasok", "hraska@seznam.cz", "pass1", Boolean.FALSE);
        Customer customer2 = customer("fero", "hrasko", "fero", "hraska2@seznam.cz", "pass2", Boolean.FALSE);
        Customer customer3 = customer("zuzka", "adamkova", "adamek", "adamkova@seznam.cz", "pass3", Boolean.TRUE);
        Customer customer4 = customer("anicka", "kubova", "kuba", "kubova@seznam.cz", "pass4", Boolean.TRUE);
        
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, 11, 1);
        Date startDate1 = calendar.getTime();
        calendar.set(2015, 12, 1);
        Date endDate1 = calendar.getTime();
        calendar.set(2014, 11, 1);
        Date startDate2 = calendar.getTime();
        calendar.set(2014, 12, 1);
        Date endDate2 = calendar.getTime();
        
        Reservation reservation1 = reservation(startDate1, endDate1, customer1, room4);
        Reservation reservation2 = reservation(startDate2, endDate2, customer2, room3);
        
   }
    
    private Date createDate(){
        Random rand = new Random();
        int  d = rand.nextInt(30) + 1;
        int  m = rand.nextInt(12) + 1;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, m);
        calendar.set(Calendar.DATE, d);
        this.date = calendar.getTime();
        return calendar.getTime();
        
    }
   
    private Hotel hotel(String name, String address, String description, Date date) {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setDescription(description);
      //  hotel.setImage(readImage(imageFile));
      //  hotel.setImageMimeType(mimeType);
        hotel.setLastUpdateDay(date);
        hotelService.createHotel(hotel);
        return hotel;
    }
    
    private Room room(String name,int numberOfBeds, BigDecimal price, Hotel hotel){
        Room room = new Room();
        room.setName(name);
        room.setNumberOfBeds(numberOfBeds);
        room.setPrice(price);
        room.setHotel(hotel); 
        roomService.createRoom(room);
        return room;
    }
    
    private Reservation reservation(Date startReservation, Date endReservation, Customer customer, Room room){
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setStartOfReservation(startReservation);
        reservation.setEndOfReservation(endReservation);
        reservation.setRoom(room);
        reservationService.createReservation(reservation);
        return reservation;
    }
    
    private Customer customer(String name, String Surname, String username, String email, String password, Boolean isAdmin){
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setName(name);
        customer.setPassword(password);
        customer.setSurname(Surname);
        customer.setPassword(password);
        customer.isAdmin();
        customerService.registerCustomer(customer, password);
        return  customer;
    }
}
