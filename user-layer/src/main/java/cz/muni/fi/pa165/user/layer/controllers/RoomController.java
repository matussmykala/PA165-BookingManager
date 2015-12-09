package cz.muni.fi.pa165.user.layer.controllers;


import cz.muni.fi.pa165.bookingmanager.dto.RoomDTO;
import cz.muni.fi.pa165.bookingmanager.facade.RoomFacade;
import cz.muni.fi.pa165.bookingmanager.service.facade.RoomFacadeImpl;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
@RequestMapping("/room")
public class RoomController {

    final static Logger log = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomFacade roomFacade;// = new RoomFacadeImpl();

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("rooms", roomFacade.getAllRooms());
        return "room/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        RoomDTO room = roomFacade.getRoomById(id);
        roomFacade.deleteRoom(id);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Room \"" + room.getName() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/room/list").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("room", roomFacade.getRoomById(id));
        return "room/view";
    }

}
