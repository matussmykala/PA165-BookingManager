package cz.muni.fi.pa165.user.layer.controllers;

import cz.muni.fi.pa165.user.layer.security.ProtectFilter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    
    final static Logger log = LoggerFactory.getLogger(ProtectFilter.class);
    /*    
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String checkLogin(@RequestParam String username,
                             @RequestParam String password,
                             UriComponentsBuilder uriBuilder,
                             RedirectAttributes redirectAttributes,
                             ServletRequest r, 
                             ServletResponse s, 
                             FilterChain chain) {  
        
        return "pa165";
    }    
    */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loadLogin(){
        return "auth/login";
    }
}
