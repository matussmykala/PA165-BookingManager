package cz.muni.fi.pa165.bookingmanager.entity;

import java.util.ArrayList;
import java.util.Collections;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;
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
    private List<Room> rooms = new ArrayList<>();


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
<<<<<<< HEAD

=======
    
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
        if (getAddress() != null ? !getAddress().equals(hotel.getAddress()) : hotel.getAddress() != null) return false;
        return !(getRooms() != null ? !getRooms().equals(hotel.getRooms()) : hotel.getRooms() != null);
    }
    
    /**
     * Generates specific hashcode by hotel name, address and rooms
     * 
     * @return hashcode of hotel
     */
>>>>>>> origin/master
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.address);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hotel other = (Hotel) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }
    
    
    
}
