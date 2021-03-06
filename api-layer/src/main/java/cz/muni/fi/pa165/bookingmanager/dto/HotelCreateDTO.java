package cz.muni.fi.pa165.bookingmanager.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ivet
 */
public class HotelCreateDTO {

    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    @NotNull
    @Size(min = 1, max = 60)
    private String address;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * HashCode use parameters name and address
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.address);
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
        final HotelCreateDTO other = (HotelCreateDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }
}
