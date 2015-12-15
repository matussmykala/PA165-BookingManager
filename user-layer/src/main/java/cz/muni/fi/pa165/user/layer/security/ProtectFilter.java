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
import javax.servlet.http.Cookie;

/**
 * Protects administrative part of application.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@WebFilter(urlPatterns = {"/auth/check", "/room/*", "/customer/*", "/reservation/*", "/hotel/*"})
public class ProtectFilter implements Filter {

    final static Logger log = LoggerFactory.getLogger(ProtectFilter.class);


    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;
        
        String logname = "";
        String password = "";
        String auth = "false";
        
        auth = request.getHeader("auth");
        
        if(auth.equals("false")){
        
        if(logname.equals("")){
            logname = request.getParameter("username");
            password = request.getParameter("password");
        }
        
        CustomerFacade userFacade = WebApplicationContextUtils.getWebApplicationContext(r.getServletContext()).getBean(CustomerFacade.class);
        
        CustomerDTO matchingUser = new CustomerDTO();

        matchingUser = userFacade.findCustomerByEmail(logname);
        
        if(matchingUser==null) {
            log.info("no user with id {}", logname);
            response401(response);
            return;
        }
        UserAuthenticateDTO userAuthenticateDTO = new UserAuthenticateDTO();
        userAuthenticateDTO.setUserId(matchingUser.getId());
        userAuthenticateDTO.setPassword(password);
        if (!userFacade.isAdmin(matchingUser)) {
            log.info("user not admin {}", matchingUser);
            response401(response);
            return;
        }
        if (!userFacade.authenticate(userAuthenticateDTO)) {
            log.info("wrong credentials: user={} password={}", logname, password);
            response401(response);
            return;
        }
        
        response.setHeader("auth", "true");
        request.setAttribute("authenticatedUser", matchingUser);
        }else{
            response.setHeader("auth", "true");
        }
        chain.doFilter(request, response);
    }


    private String[] parseAuthHeader(String auth) {
        return new String(DatatypeConverter.parseBase64Binary(auth.split(" ")[1])).split(":", 2);
    }

    private void response401(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "Basic realm=\"type email and password\"");
        response.getWriter().println("<html><body><h1>401 Unauthorized</h1> Go away ...</body></html>");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
