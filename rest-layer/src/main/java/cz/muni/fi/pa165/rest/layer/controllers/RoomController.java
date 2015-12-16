/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * REST Controller for Products
 *
 * @author brossi
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    
    @Inject
    private RoomFacade roomFacade;

    /**
     * Get list of Products curl -i -X GET
     * http://localhost:8080/pa165/rest/room
     *
     * @return ProductDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<RoomDTO> getRooms() {
        return roomFacade.getAllRooms();
    }

    /**
     *
     * Get Product by identifier id curl -i -X GET
     * http://localhost:8080/pa165/rest/room/1
     *
     * @param id identifier for a product
     * @return ProductDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final RoomDTO getRoom(@PathVariable("id") long id) throws Exception {
        RoomDTO roomDTO;
        try{
            roomDTO = roomFacade.getRoomById(id);
        }catch(Exception ex){
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
     * http://localhost:8080/pa165/rest/room/1
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
     * Create a new product by POST method
     * curl -X POST -i -H "Content-Type: application/json" --data 
     * '{"name":"test","description":"test","color":"UNDEFINED","price":"200",
     * "currency":"CZK", "categoryId":"1"}' 
     * http://localhost:8080/pa165/rest/room/create
     * 
     * @param room roomCreateDTO with required fields for creation
     * @return the created product RoomDTO
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

   
   