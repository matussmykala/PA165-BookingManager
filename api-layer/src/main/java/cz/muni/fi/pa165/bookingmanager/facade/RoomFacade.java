
package cz.muni.fi.pa165.bookingmanager.facade;

import cz.muni.fi.pa165.bookingmanager.dto.RoomCreateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

/**
 * Room facade interface
 * 
 * @author Martin Cuchran
 */
public interface RoomFacade {
    /**
     * Create Room
     * 
     * @param roomCreateDTO - room which will be created
     * @return id of created room
     */
    public long createRoom(RoomDTO roomCreateDTO);
    
    /**
     * Change price of room
     * 
     * @param roomId - room id where price is changed
     * @param newPrice - new price
     */
    public void changeRoomPrice(Long roomId, BigDecimal newPrice);
    
    /**
     * Change number of beds in room
     * 
     * @param RoomId - Room id where number of beds is changed
     * @param newNumberOfBeds - new number of beds
     */
    public void changeRoomNumberOfBeds(Long RoomId, int newNumberOfBeds);
    
    /**
     * Remove room
     * 
     * @param RoomId - id of room which will be removed
     */
    public void deleteRoom(Long RoomId);
    
    /**
     * Get room by its id
     * 
     * @param RoomId - id of room which may be found
     * @return RoomDTO - required room
     */
    public RoomDTO getRoomById(Long RoomId);
    
    /**
     * Get all rooms
     * 
     * @return List<RoomDTO> - list of all rooms 
     */
    public List<RoomDTO> getAllRooms();
    
    /**
     * Get rooms with specific price
     * 
     * @price price of room
     * @return List<RoomDTO> - list of rooms with specific price
     */
    public List<RoomDTO> getRoomsByPrice(BigDecimal price);
    
    /**
     * Get rooms with specific numberof beds
     * 
     * @numberOfBeds number of beds
     * @return List<RoomDTO> - list of rooms with specific numberof beds
     */
    public List<RoomDTO> getRoomsByNumberOfBeds(int numberOfBeds);
    
}
