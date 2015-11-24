package cz.muni.fi.pa165.bookingmanager.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Martin Cuchran
 */
public class RoomDTO {
    
    private Long id;    
    /**
     * Name of room
     */
    private String name;
    
    /**
     * number of beds in the room
     */
    private int numberOfBeds;   
    
    /**
     * Price of room
     */
    private BigDecimal price;
    
    /**
     * Currency of room price
     */
    private Currency currency;
    
    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }   

    /**
     *
     * @return
     */
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     *
     * @param numberOfBeds
     */
    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    /**
     *
     * @return
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     *
     * @return
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + this.numberOfBeds;
        hash = 11 * hash + Objects.hashCode(this.price);
        hash = 11 * hash + Objects.hashCode(this.currency);
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
        final RoomDTO other = (RoomDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.numberOfBeds != other.numberOfBeds) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        return true;
    }
    
    

    
}
