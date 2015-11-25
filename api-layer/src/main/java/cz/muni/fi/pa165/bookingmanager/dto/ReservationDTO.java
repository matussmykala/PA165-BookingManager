package cz.muni.fi.pa165.bookingmanager.dto;
import java.util.Date;

/**
 * Data Transfer Object for Reservation entity.
 * @author matus
 */
public class ReservationDTO
{
    /**
     * Id.
     */
    private Long id = (long) 0;

    /**
     * Date and time when the reservation begins.
     */
    private Date startOfReservation = new Date();

    /**
     * Date and time, when the reservation ends.
     */
    private Date endOfReservation = new Date();

    /**
     * Customer who books the room.
     */
    private CustomerDTO customer;

    /**
     * Booked room.
     */
    private RoomDTO room;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Date getStartOfReservation()
    {
        return startOfReservation;
    }

    public void setStartOfReservation(Date startOfReservation)
    {
        this.startOfReservation = startOfReservation;
    }

    public Date getEndOfReservation()
    {
        return endOfReservation;
    }

    public void setEndOfReservation(Date endOfReservation)
    {
        this.endOfReservation = endOfReservation;
    }

    public CustomerDTO getCustomer()
    {
        return customer;
    }

    public void setCustomer(CustomerDTO customer)
    {
        this.customer = customer;
    }

    public RoomDTO getRoom()
    {
        return room;
    }

    public void setRoom(RoomDTO room)
    {
        this.room = room;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationDTO that = (ReservationDTO) o;

        if (!id.equals(that.id)) return false;
        if (!startOfReservation.equals(that.startOfReservation)) return false;
        if (!endOfReservation.equals(that.endOfReservation)) return false;
        if (!customer.equals(that.customer)) return false;
        return room.equals(that.room);
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + startOfReservation.hashCode();
        result = 31 * result + endOfReservation.hashCode();
        result = 31 * result + customer.hashCode();
        result = 31 * result + room.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return "ReservationDTO{" +
                "id=" + id +
                ", startOfReservation=" + startOfReservation +
                ", endOfReservation=" + endOfReservation +
                ", customer=" + customer +
                ", room=" + room +
                '}';
    }
}
