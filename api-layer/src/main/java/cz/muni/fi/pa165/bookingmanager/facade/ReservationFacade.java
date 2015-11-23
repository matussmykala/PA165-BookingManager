package cz.muni.fi.pa165.bookingmanager.facade;
import java.util.Date;
import java.util.List;

import cz.muni.fi.pa165.bookingmanager.dto.ReservationDTO;

/**
 * @author matus
 */
public interface ReservationFacade
{
    public List<ReservationDTO> getAllReservations();
    public List<ReservationDTO> getReservationsOfTime(Date from, Date to);
    public List<ReservationDTO> getReservationsByCustomer(Long customerId);
    public ReservationDTO getReservationById(Long id);
    public void createReservation(Long customerId, Long roomId, Date from, Date to);
    public void updateReservation(Long id, Long customerId, Long roomId, Date from, Date to);
    public void cancelReservation(Long id);
}
