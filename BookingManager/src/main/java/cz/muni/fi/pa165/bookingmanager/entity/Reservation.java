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
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startOfReservation;

    /**
     * The ending date of a hotel room reservation.
     */
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
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
        if ((this.endOfReservation != null) && (startOfReservation != null) && !this.endOfReservation.after(startOfReservation)) {
            throw new IllegalArgumentException("StartOfReservation cannot be after EndOfReservation");
        }
        this.startOfReservation=startOfReservation;
    }

    public Date getEndOfReservation() {
        return endOfReservation;
    }

    public void setEndOfReservation(Date endOfReservation) {
        if ((endOfReservation != null) && (this.startOfReservation != null) && !endOfReservation.after(this.startOfReservation)) {
            throw new IllegalArgumentException("StartOfReservation cannot be after EndOfReservation");
        }
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
        if (!getEndOfReservation().equals(that.getEndOfReservation())) return false;
        if (getCustomer() != null ? !getCustomer().equals(that.getCustomer()) : that.getCustomer() != null)
            return false;
        return !(getRoom() != null ? !getRoom().equals(that.getRoom()) : that.getRoom() != null);

    }

    @Override
    public int hashCode() {
        int result = getStartOfReservation().hashCode();
        result = 31 * result + getEndOfReservation().hashCode();
        result = 31 * result + (getCustomer() != null ? getCustomer().hashCode() : 0);
        result = 31 * result + (getRoom() != null ? getRoom().hashCode() : 0);
        return result;
    }
}
