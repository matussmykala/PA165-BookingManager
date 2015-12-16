package cz.muni.fi.pa165.user.layer.controllers;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controller which manage authentication redirects and login form
 * 
 * @author Martin Cuchran <cuchy92@gmail.com>
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    final static Logger log = LoggerFactory.getLogger(AuthController.class);

    /**
     * Show empty login form
     *
     * @return JSP page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loadLogin(){       
        return "auth/login";
    }

    /**
     * Redirects to homepage with success alert
     *
     * @return JSP page
     */
    @RequestMapping(value = "/login-success", method = RequestMethod.GET)
    public String loginSuccess(UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        
        redirectAttributes.addFlashAttribute("alert_info", "Login successful");
        
        return "redirect:"+ uriBuilder.path("").toUriString();
    }
    
    /**
     * Redirects to login jsp with info alert after unathorized access
     *
     * @return JSP page
     */
    @RequestMapping(value = "/login-required", method = RequestMethod.GET)
    public String loadLoginFailed(UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("alert_info", "This page is authorized, please log in");
        return "redirect:" + uriBuilder.path("/auth/login").toUriString();
    }

    /**
     * Redirects to login jsp with error alert after incorrect credential
     *
     * @return JSP page
     */
    @RequestMapping(value = "/login-error", method = RequestMethod.GET)
    public String loginError(UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("alert_danger", "Username or password incorrect");
        return "redirect:" + uriBuilder.path("/auth/login").toUriString();
    }

    /**
     * Redirects to login jsp with warning alert after nonexisting  username lookup
     *
     * @return JSP page
     */
    @RequestMapping(value = "/login-nouser", method = RequestMethod.GET)
    public String loginNoUser(UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("alert_warning", "Username does not exist");
        return "redirect:" + uriBuilder.path("/auth/login").toUriString();
    }

    /**
     * Redirects to access-denied jsp with info alert after unathorized access to administrator's pages
     *
     * @return JSP page
     */
    @RequestMapping(value = "/login-noadmin", method = RequestMethod.GET)
    public String loginNoAdmin(UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("alert_info", "You are not administrator");
        return "access-denied";
    }
    
    /**
     * Redirects to login jsp with info alert after logout
     *
     * @return JSP page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ServletRequest r, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        
        HttpServletRequest request = (HttpServletRequest) r;
        HttpSession session = request.getSession();
        
        session.setAttribute("authenticatedUser", null);
        request.setAttribute("authenticatedUser", null);
        redirectAttributes.addFlashAttribute("alert_info", "You were logged out");
        return "redirect:" + uriBuilder.path("").toUriString();
    }
}