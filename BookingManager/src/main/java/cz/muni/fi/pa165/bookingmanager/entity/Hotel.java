package cz.muni.fi.pa165.bookingmanager.entity;

import java.util.Collections;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;


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
