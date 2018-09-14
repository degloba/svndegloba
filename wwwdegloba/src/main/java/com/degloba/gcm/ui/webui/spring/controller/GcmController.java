package com.degloba.gcm.ui.webui.spring.controller;


import java.util.List;
import java.util.logging.Logger;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.degloba.gcm.domain.persistence.nosql.mongo.spring.Topic;

/**
 *
 * @author degloba
 *
 */
@Controller
public class GcmController {
	
	private final Logger logger = Logger.getLogger(getClass().getName());

	@RequestMapping(value = "/gcm")
	public String landing(Model model) {	
		
		// TODO Recuperar tots els topics
		List<Topic> topics;
        /////model.addAttribute("topics", topics);
       
		return "gcm/topics";
	}

}
