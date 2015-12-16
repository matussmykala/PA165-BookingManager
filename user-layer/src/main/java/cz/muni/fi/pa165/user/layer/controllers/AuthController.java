package cz.muni.fi.pa165.user.layer.controllers;

import cz.muni.fi.pa165.user.layer.security.ProtectFilter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    final static Logger log = LoggerFactory.getLogger(AuthController.class);
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

    @RequestMapping(value = "/login-required", method = RequestMethod.GET)
    public String loadLoginFailed(UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("alert_info", "This page is authorized, please log in");
        return "redirect:" + uriBuilder.path("/auth/login").toUriString();
    }

    @RequestMapping(value = "/login-error", method = RequestMethod.GET)
    public String loginError(UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("alert_danger", "Username or password incorrect");
        return "redirect:" + uriBuilder.path("/auth/login").toUriString();
    }

    @RequestMapping(value = "/login-nouser", method = RequestMethod.GET)
    public String loginNoUser(UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("alert_warning", "Username does not exist");
        return "redirect:" + uriBuilder.path("/auth/login").toUriString();
    }

    @RequestMapping(value = "/login-noadmin", method = RequestMethod.GET)
    public String loginNoAdmin(UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("alert_info", "You are not administrator");
        return "redirect:" + uriBuilder.path("/auth/login").toUriString();
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ServletRequest r, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        
        HttpServletRequest request = (HttpServletRequest) r;
        HttpSession session = request.getSession();
        
        session.setAttribute("authenticatedUser", null);
        request.setAttribute("authenticatedUser", null);
        
        redirectAttributes.addFlashAttribute("alert_info", "You were logged out");
        return "redirect:" + uriBuilder.path("/auth/login").toUriString();
    }
}