package cz.muni.fi.pa165.rest.layer.controllers;

import cz.muni.fi.pa165.rest.layer.ApiUris;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    
    final static Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {

        Map<String,String> resourcesMap = new HashMap<>();
        
        resourcesMap.put("rooms_uri", ApiUris.ROOT_URI_ROOMS);
        resourcesMap.put("reservations_uri", ApiUris.ROOT_URI_RESERVATIONS);
        resourcesMap.put("customers_uri", ApiUris.ROOT_URI_CUSTOMERS);
        resourcesMap.put("hotels_uri", ApiUris.ROOT_URI_HOTELS);
        
        return Collections.unmodifiableMap(resourcesMap);
        
    }
}
