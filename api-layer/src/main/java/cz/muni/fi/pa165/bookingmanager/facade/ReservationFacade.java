package cz.muni.fi.pa165.bookingmanager.facade;
import java.util.Date;
import java.util.List;

import cz.muni.fi.pa165.bookingmanager.dto.ReservationDTO;

/**
 * Facade layer interface.
 *
 * @author matus
 */
public interface ReservationFacade
{

    /**
     * Returns all the reservations.
     *
     * @return list of all reservations
     */
    public List<ReservationDTO> getAllReservations();

    /**
     * Returns reservations taken in time interval <from, to>.
     *
     * @param from  beginning date
     * @param to    end date
     * @return      list of reservation taken in time interval
     */
    public List<ReservationDTO> getReservationsOfTime(Date from, Date to);

    /**
     * Returns reservations taken by the customer.
     *
     * @param customerId    id of the customer
     * @return  list of reservations taken by the customer
     */
    public List<ReservationDTO> getReservationsByCustomer(Long customerId);

    /**
     * Returns the reservation with the id.
     *
     * @param id    id of the reservation
     * @return  reservation with the id
     */
    public ReservationDTO getReservationById(Long id);

    /**
     * Books the room with roomId for the customer with customerId for time period <from, to>.
     *
     * @param customerId    customer id
     * @param roomId        room id
     * @param from          beggining of the reservation
     * @param to            end of the reservation
     */
    public void createReservation(Long customerId, Long roomId, Date from, Date to);

    /**
     * Updates the reservation with id according to parameters.
     *
     * @param id            reservation id
     * @param customerId    customer id
     * @param roomId        room id
     * @param from          beggining of the reservation
     * @param to            end of the reservation
     */
    public void updateReservation(Long id, Long customerId, Long roomId, Date from, Date to);

    /**
     * Cancel reservation with id.
     *
     * @param id    reservation id
     */
    public void cancelReservation(Long id);
}
