package cz.muni.fi.pa165.bookingmanager.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Defines basic method and attributes of hotel
 * 
 * @author Martin Cuchran <cuchy92@gmail.com>
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
    private List<Room> rooms = new ArrayList<>();;

    /**
     * Get hotel id
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set hotel id by id param
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get hotel name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set hotel name by name param
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get hotel address
     * 
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set hotel address by param address
     * 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get hotel rooms
     * 
     * @return list of hotel rooms
     */
    public List<Room> getRooms() {
        return Collections.unmodifiableList(rooms);
    }

    /**
     * Set hotel rooms by param rooms
     * 
     * @param rooms
     */
    public void setRooms(Room room) {
        this.rooms.add(room);
    }
    
    /**
     * Check if 2 hotels are equals by name and address
     *
     * @param o
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;

        Hotel hotel = (Hotel) o;

        if (!getName().equals(hotel.getName())) return false;
        return getAddress().equals(hotel.getAddress());

    }

    /**
     * Generates specific hashcode by hotel name and address
     *
     * @return hashcode of hotel
     */
    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getAddress().hashCode();
        return result;
    }
}
