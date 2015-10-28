/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.entity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
<<<<<<< HEAD
import org.hibernate.annotations.Check;
=======
>>>>>>> origin/master

/**
 *
 * @author ivet
 */
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @NotNull
    private String name;

=======
    /**
     * room name
     */
    @NotNull
    private String name;

    /**
     * number of beds in the room
     */
>>>>>>> origin/master
    @NotNull
    @Min(1)
    private int numberOfBeds;

<<<<<<< HEAD
    @NotNull
    @Min(0)
    private BigDecimal price;
    
    private Currency currency;

=======
    /**
     * price of this room (value)
     */
    @NotNull
    @Min(0)
    private BigDecimal price;

    /**
     * currency of the price
     */
    @NotNull
    private Currency currency;

    /**
     * The associated hotel to this room.
     */
>>>>>>> origin/master
    @ManyToOne
    private Hotel hotel;
    
    @OneToMany(mappedBy="room")
    private Set<Reservation> reservations = new HashSet<Reservation>();

    /**
     * The associated reservations to this room.
     */
    @OneToMany(mappedBy="room")
    private Set<Reservation> reservations = new HashSet<Reservation>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    
    public void setReservation(Reservation r){
        reservations.add(r);
    }
    
    public Set<Reservation> getReservations(){
        return Collections.unmodifiableSet(reservations);
    }

<<<<<<< HEAD
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

=======
    public void setReservation(Reservation r){
        reservations.add(r);
    }

    public Set<Reservation> getReservations(){
        return Collections.unmodifiableSet(reservations);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

>>>>>>> origin/master
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.hotel);
        return hash;
<<<<<<< HEAD
=======
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.hotel, other.hotel)) {
            return false;
        }
        return true;
>>>>>>> origin/master
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.hotel, other.hotel)) {
            return false;
        }
        return true;
    }

   
}
