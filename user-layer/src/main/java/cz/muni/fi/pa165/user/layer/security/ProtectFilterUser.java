package cz.muni.fi.pa165.user.layer.security;

import cz.muni.fi.pa165.bookingmanager.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.bookingmanager.dto.CustomerDTO;
import cz.muni.fi.pa165.bookingmanager.facade.CustomerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 * Protects administrative part of application.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@WebFilter(urlPatterns = {"/room/new/*", "/room/edit/*", "/room/delete/*", 
                            "/hotel/new/*", "/hotel/edit/*", "/hotel/delete/*", 
                            "/customer/new/*", "/customer/edit/*", "/customer/delete/*",
                            "/reservation/edit/*", "/reservation/delete/*"})
public class ProtectFilterUser implements Filter {

    final static Logger log = LoggerFactory.getLogger(ProtectFilterUser.class);


    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;
            
            CustomerFacade userFacade = WebApplicationContextUtils.getWebApplicationContext(r.getServletContext()).getBean(CustomerFacade.class);
  
            HttpSession session = request.getSession();
                
            CustomerDTO auth = (CustomerDTO) session.getAttribute("authenticatedUser");
            CustomerDTO tmp = new CustomerDTO();
            try{
                tmp = userFacade.findCustomerByEmail(auth.getEmail());
            }catch (NullPointerException e){
                tmp = null;
            }
            
            
            if(tmp==null){
                
                response.sendRedirect(request.getContextPath()+"/auth/login-required");
                return;
            }   
                


                if(auth==null) {
                    response.sendRedirect(request.getContextPath()+"/auth/login-error");
                    return;
                }

                if (!userFacade.isAdmin(auth)) {
                    log.info("user not admin {}", auth);
                    response.sendRedirect(request.getContextPath()+"/auth/login-noadmin");
                    return;
                }
                
            

            chain.doFilter(request, response);
    }


    private String[] parseAuthHeader(String auth) {
        return new String(DatatypeConverter.parseBase64Binary(auth.split(" ")[1])).split(":", 2);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    private String sha256Hash(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(password.getBytes(StandardCharsets.UTF_8));
        String str = new BigInteger(1, digest.digest()).toString(16);
        return str;
    }
}