/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.entity;

import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.NotNull;


/**
 *
 * @author ivet
 */

@Entity
public class Hotel {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String name;

    @NotNull
    private String address;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;

        Hotel hotel = (Hotel) o;

        if (getName() != null ? !getName().equals(hotel.getName()) : hotel.getName() != null) return false;
        if (getAddress() != null ? !getAddress().equals(hotel.getAddress()) : hotel.getAddress() != null) return false;
        return !(getRooms() != null ? !getRooms().equals(hotel.getRooms()) : hotel.getRooms() != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getRooms() != null ? getRooms().hashCode() : 0);
        return result;
    }
}
