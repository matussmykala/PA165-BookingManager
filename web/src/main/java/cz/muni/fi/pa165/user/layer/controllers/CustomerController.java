package cz.muni.fi.pa165.user.layer.controllers;

import cz.muni.fi.pa165.bookingmanager.dto.CustomerDTO;
import cz.muni.fi.pa165.bookingmanager.dto.ReservationDTO;
import cz.muni.fi.pa165.bookingmanager.facade.CustomerFacade;
import cz.muni.fi.pa165.bookingmanager.facade.ReservationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

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

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {
        model.addAttribute("customer", customerFacade.findCustomerById(id));
        model.addAttribute("reservations", reservationFacade.getReservationsByCustomer(id));
        return "customer/view";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("customer", customerFacade.findCustomerById(id));
        return "customer/edit";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable long id, @Valid @ModelAttribute("customer") CustomerDTO updatedCustomerDTO,
                         BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                         UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "hotel/edit/{id}";
        }

        CustomerDTO customer = customerFacade.findCustomerById(id);
        customer.setName(updatedCustomerDTO.getName());
        customer.setSurname(updatedCustomerDTO.getSurname());
        customer.setEmail(updatedCustomerDTO.getEmail());
        customer.setUsername(updatedCustomerDTO.getUsername());
        customer.setAdmin(updatedCustomerDTO.isAdmin());
        customerFacade.updateCustomer(customer);

        redirectAttributes.addFlashAttribute("alert_success", "Customer " + id + " was updated");
        return "redirect:" + uriBuilder.path("/customer/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        CustomerDTO customerDTO = customerFacade.findCustomerById(id);

        List<ReservationDTO> reservations = reservationFacade.getReservationsByCustomer(customerDTO.getId());
        if (!reservations.isEmpty()) {
            redirectAttributes.addFlashAttribute("alert_info", "Customer " + customerDTO.getName() + " " + customerDTO.getSurname()
                    + " cannot be deleted because there are still some reservations associated with him.");
            return "redirect:" + uriBuilder.path("/customer/list").toUriString();
        }

        customerFacade.deleteCustomer(id);
        redirectAttributes.addFlashAttribute("alert_success", "Customer " + customerDTO.getName()
                + " " + customerDTO.getSurname() + " was deleted from the system.");
        return "redirect:" + uriBuilder.path("/customer/list").toUriString();
    }
}
