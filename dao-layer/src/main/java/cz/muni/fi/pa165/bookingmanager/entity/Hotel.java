package cz.muni.fi.pa165.bookingmanager.entity;

import java.util.ArrayList;
import java.util.Collections;
import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * Defines basic methods and attributes of hotel
 * 
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
@Entity
public class Hotel {

    /**
     * unique identification number
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * name of hotel
     */
    @NotNull
    private String name;

    /**
     * address of hotel
     */
    @NotNull
    private String address;

    /**
     * list of all rooms that are associated with this hotel
     */
    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms = new ArrayList<>();;


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
        return Collections.unmodifiableList(rooms);
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room){
        this.rooms.add(room);
    }
    
    /**
     * Check if 2 hotels are equal according to name, address and rooms
     * 
     * @param o object to be checked
     * @return  true    if objects are equals
     *          false   otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;

        Hotel hotel = (Hotel) o;

        if (getName() != null ? !getName().equals(hotel.getName()) : hotel.getName() != null) return false;
        return !(getAddress() != null ? !getAddress().equals(hotel.getAddress()) : hotel.getAddress() != null);
        
    }
    
    /**
     * Generates specific hashcode by hotel name, address and rooms
     * 
     * @return hashcode of hotel
     */
    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        
        return result;
    }
}
