package cz.muni.fi.pa165.bookingmanager.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Room DTO implementation
 *
 * @author Martin Cuchran
 */
public class RoomDTO {

    /**
     * id of room
     */
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
     * Hotel which room belongs to
     */
    private HotelDTO hotel = new HotelDTO();

    public HotelDTO getHotel() {
        return hotel;
    }

    public void setHotel(HotelDTO hotel) {
        this.hotel = hotel;
    }

    public Set<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationDTO> reservations) {
        this.reservations = reservations;
    }

    /**
     * Reservations where room is used
     */
    private Set<ReservationDTO> reservations = new HashSet<ReservationDTO>();

    /**
     *  Gets id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets numberofBeds
     *
     * @return numberOfBeds
     */
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * Sets numberOfBeds
     *
     * @param numberOfBeds
     */
    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    /**
     * Gets price
     *
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price
     *
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets currency
     *
     * @return currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Sets currency
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
        //hash = 11 * hash + Objects.hashCode(this.currency);
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

    @Override
    public String toString() {
        return "RoomDTO{" + "id=" + id + ", name=" + name + ", numberOfBeds=" + numberOfBeds + ", price=" + price + ", currency=" + currency + '}';
    }
}
