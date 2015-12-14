package cz.muni.fi.pa165.bookingmanager.facade;
import java.util.Date;
import java.util.List;

import cz.muni.fi.pa165.bookingmanager.dto.ReservationDTO;
import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;

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
     * Creates the reservation according to reservationDto parameters..
     *
     * @param reservation    reservation to create
     */
    public void createReservation(ReservationDTO reservation);

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

    /**
     * Returns all reservations of customer with customerId which starts tomorrow and later.
     *
     * @param customerId    customer id
     * @return  list of future reservations of the customer
     */
    public List<ReservationDTO> getFutureReservationsOfCustomer(Long customerId);

    /**
     * Returns all reservations which starts next month.
     *
     * @return list of reservation which starts next month
     */
    public List<ReservationDTO> getNextMonthReservations();
    
     /**
     * Returns all reservations of room 
     *
     * @param room    room
     * @return  list of all of room
     */
    List<ReservationDTO> getAllReservationsOfRoom(Long id);
}
