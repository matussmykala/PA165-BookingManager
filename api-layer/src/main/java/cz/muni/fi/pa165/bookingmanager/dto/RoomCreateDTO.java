package cz.muni.fi.pa165.bookingmanager.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * Implements RoomCreateDTO
 * 
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
public class RoomCreateDTO {
   
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
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + this.numberOfBeds;
        hash = 61 * hash + Objects.hashCode(this.price);
        hash = 61 * hash + Objects.hashCode(this.currency);
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
        final RoomCreateDTO other = (RoomCreateDTO) obj;
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

    @Override
    public String toString() {
        return "RoomCreateDTO{" + "name=" + name + ", numberOfBeds=" + numberOfBeds + ", price=" + price + ", currency=" + currency + '}';
    }        
}
