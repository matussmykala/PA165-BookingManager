package cz.muni.fi.pa165.bookingmanager.dto;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author ivet
 */
public class HotelDTO {

    private Long id;

    /**
     * Name of hotel
     */
    private String name;

    /**
     * Address of hotel
     */
    private String address;

    /**
     * Short description about hotel
     */
    private String description;

    /**
     * Date of creating or last updating hotel
     */
    private Date lastUpdateDay;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastUpdateDay() {
        return lastUpdateDay;
    }

    public void setLastUpdateDay(Date lastUpdateDay) {
        this.lastUpdateDay = lastUpdateDay;
    }

    /**
     * HashCode use parameters name and address
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.address);
        return hash;
    }

    /**
     * Two hotels are equal if they have same name and address
     *
     * @param obj to compare
     * @return true if two hotels are same or false if not or if there is some
     * mistake
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HotelDTO other = (HotelDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }
}
