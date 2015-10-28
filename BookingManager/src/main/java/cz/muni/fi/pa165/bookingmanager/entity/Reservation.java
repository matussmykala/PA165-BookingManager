package cz.muni.fi.pa165.bookingmanager.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Simple entity representing hotel room reservation.
 * <p/>
 * Created on 23.10.2015.
 *
 * @author Vladimir Caniga
 */
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The starting date of a hotel room reservation.
     */
    @NotNull
    //@Temporal(TemporalType.TIMESTAMP)
    private Date startOfReservation;

    /**
     * The ending date of a hotel room reservation.
     */
    @NotNull
    //@Temporal(TemporalType.TIMESTAMP)
    private Date endOfReservation;

    /**
     * The associated customer to this reservation.
     */
    @ManyToOne
    private Customer customer;

    /**
     * The associated room to this reservation.
     */
    @ManyToOne
    private Room room;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        
    }

    public Date getStartOfReservation() {
        return startOfReservation;
    }

    public void setStartOfReservation(Date startOfReservation){
        this.startOfReservation=startOfReservation;
    }

    public Date getEndOfReservation() {
        return endOfReservation;
    }

    public void setEndOfReservation(Date endOfReservation) {
        this.endOfReservation=endOfReservation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;

        Reservation that = (Reservation) o;

        if (!getStartOfReservation().equals(that.getStartOfReservation())) return false;
        if (!getCustomer().equals(that.getCustomer())) return false;
        if (!getRoom().equals(that.getRoom())) return false;
        return getEndOfReservation().equals(that.getEndOfReservation());
    }

    @Override
    public int hashCode() {
        int result = getStartOfReservation().hashCode();
        result = 31 * result + getEndOfReservation().hashCode();
        return result;
    }
}
