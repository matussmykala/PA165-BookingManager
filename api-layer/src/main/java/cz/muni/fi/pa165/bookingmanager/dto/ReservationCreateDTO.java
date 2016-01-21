package cz.muni.fi.pa165.bookingmanager.dto;
import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * @author matus
 */
public class ReservationCreateDTO
{
    /**
     * Date and time when the reservation begins.
     */
    @NotNull
    private Date startOfReservation = new Date();

    /**
     * Date and time, when the reservation ends.
     */
    @NotNull
    private Date endOfReservation = new Date();

    /**
     * Customer who books the room.
     */
    private long customerId;

    /**
     * Booked room.
     */
    private long roomId;

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

    public long getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(long customerId)
    {
        this.customerId = customerId;
    }

    public long getRoomId()
    {
        return roomId;
    }

    public void setRoomId(long roomId)
    {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationCreateDTO that = (ReservationCreateDTO) o;

        if (customerId != that.customerId) return false;
        if (roomId != that.roomId) return false;
        if (!startOfReservation.equals(that.startOfReservation)) return false;
        return endOfReservation.equals(that.endOfReservation);

    }

    @Override
    public int hashCode()
    {
        int result = startOfReservation.hashCode();
        result = 31 * result + endOfReservation.hashCode();
        result = 31 * result + (int) (customerId ^ (customerId >>> 32));
        result = 31 * result + (int) (roomId ^ (roomId >>> 32));
        return result;
    }
}
