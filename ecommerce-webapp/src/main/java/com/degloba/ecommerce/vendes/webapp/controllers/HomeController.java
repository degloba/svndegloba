package com.degloba.ecommerce.vendes.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * https://github.com/eugenp/tutorials/blob/master/spring-rest-query-language/pom.xml
 * 
 * @author degloba
 *
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    public String index() {
        return "homepage";
    }

}
