package cz.muni.fi.pa165.rest.layer.controllers;

import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import cz.muni.fi.pa165.bookingmanager.facade.RoomFacade;
import cz.muni.fi.pa165.rest.layer.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.rest.layer.exceptions.ResourceNotFoundException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

/**
 * REST Controller for Room
 *
 * @author ivet
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    @Inject
    private RoomFacade roomFacade;

    /**
     * Get list of Products curl -i -X GET http://localhost:8080/pa165/rest/room
     *
     * @return RoomDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<RoomDTO> getRooms() {
        return roomFacade.getAllRooms();
    }

    /**
     *
     * Get Product by identifier id curl -i -X GET
     * http://localhost:8080/pa165/rest/room/{id}
     *
     * @param id identifier for a product
     * @return RoomtDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final RoomDTO getRoom(@PathVariable("id") long id) throws Exception {
        RoomDTO roomDTO;
        try {
            roomDTO = roomFacade.getRoomById(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
        if (roomDTO != null) {
            return roomDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Delete one product by id curl -i -X DELETE
     * http://localhost:8080/pa165/rest/room/{id}
     *
     * @param id identifier for product
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteRoom(@PathVariable("id") long id) throws Exception {
        try {
            roomFacade.deleteRoom(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Create a new product by POST method curl -X POST -i -H "Content-Type:
     * application/json" --data
     * '{"name":"TEST","numberOfBeds":2,"price":25.20,"hotel":{"id":2,
     * "name":"Park Hotel","address":"Praha","description":"Pekny hotel v
     * Prahe", "lastUpdateDay":"2016-07-27 00:00"},"reservations":[]}'
     * http://localhost:8080/pa165/rest/room/create
     *
     * @param roomDTO roomCreateDTO with required fields for creation
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final void createRoom(@RequestBody RoomDTO roomDTO) throws Exception {

        try {
            roomFacade.createRoom(roomDTO);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }
}

