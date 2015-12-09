package cz.muni.fi.pa165.user.layer.controllers;

import cz.muni.fi.pa165.bookingmanager.facade.CustomerFacade;
import cz.muni.fi.pa165.bookingmanager.facade.ReservationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 09.12.2015.
 *
 * @author Vladimir Caniga
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    final static Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerFacade customerFacade;

    @Autowired
    private ReservationFacade reservationFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("customers", customerFacade.getAllCustomers());
        return "customer/list";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {
        model.addAttribute("customer", customerFacade.findCustomerById(id));
        model.addAttribute("reservations", reservationFacade.getReservationsByCustomer(id));
        return "customer/detail";
    }
}
