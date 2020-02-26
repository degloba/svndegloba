package com.degloba.messaging.webapp.controllers.impl.spring;


import java.util.List;
import java.util.logging.Logger;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.degloba.messaging.domain.persistence.nosql.impl.mongo.api.spring.FCM.Topic;

/**
 *
 * @author degloba
 * 
 * @category Controlador Firebase Cloud Message
 *
 */
@Controller
public class FCMController {
	
	private final Logger logger = Logger.getLogger(getClass().getName());

	@RequestMapping(value = "/fcm")
	public String getTopics(Model model) {	
		
		// TODO Recuperar tots els topics
		List<Topic> topics;
		
		
		
		
        model.addAttribute("topics", topics);
       
		return "fcm/topics";
	}

}
