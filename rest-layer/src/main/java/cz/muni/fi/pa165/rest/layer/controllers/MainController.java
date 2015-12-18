package cz.muni.fi.pa165.rest.layer.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    final static Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {

        Map<String, String> resourcesMap = new HashMap<>();
        resourcesMap.put("rooms_uri", "/room");
        return Collections.unmodifiableMap(resourcesMap);
    }
}
