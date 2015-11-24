
package cz.muni.fi.pa165.bookingmanager.facade;

import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import java.util.List;

/**
 *
 * @author Martin Cuchran
 */
public interface RoomFacade {
    /**
     * Create Room
     * 
     * @param room - room which will be created
     * @return id of created room
     */
    //public Long createRoom(RoomCreateDTO room);
    
    /**
     * Update Room
     * 
     * @param roomDTO - room which will be updated
     */
    public void updateRoom(RoomDTO roomDTO);
    
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
     * @return List<RoomDTO> - list of all rooms which are in system
     */
    public List<RoomDTO> getAllRooms();
}
