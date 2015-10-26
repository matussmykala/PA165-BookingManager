package cz.muni.fi.pa165.bookingmanager.entity;

import javax.persistence.*;
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

    private String name;

    private String address;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

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
        return rooms;
    }

    /**
     * Set hotel rooms by param rooms
     * 
     * @param rooms
     */
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    
    /**
     * Check if 2 hotels are equals by name, address and rooms
     * 
     * @param hotel
     * @return true or false
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
    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getRooms() != null ? getRooms().hashCode() : 0);
        return result;
    }
}
