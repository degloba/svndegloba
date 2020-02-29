package com.degloba.messaging.webapp.controllers.impl.spring;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;

// Spring Web
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.degloba.messaging.domain.persistence.nosql.impl.mongo.IFCMTokenRegisterRepository;



/**
 * Captura i registra (MongoDB) els tokens de dispositius Android
 * utilitzant
 */
//@WebServlet("/GCMBroadcast")
@Controller
@RequestMapping("/gcm/GCMBroadcast")
public class FCMBroadcastController {  
	
	@Inject
	private IFCMTokenRegisterRepository FCMTokenRegisterRepository; 
	
	
	// The SENDER_ID here is the "Browser Key" that was generated when I
	// created the API keys for my Google APIs project.
	private static final String SENDER_ID = "AIzaSyAd05vFrU0Sj-6YRjmybuMhMed5zJuYwq0";

		
	// This array will hold all the registration ids used to broadcast a message.
	// for this demo, it will only have the ANDROID_DEVICE id that was captured 
	// when we ran the Android client app through Eclipse.
	private List<String> androidTargets = new ArrayList<String>();
       
    
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String registerTokenFCMAndroid(@RequestParam("regID") String regID) {
    		   
    	androidTargets.add(regID);
    	
    	// We'll collect the "CollapseKey" and "Message" values from our JSP page
    			String collapseKey = "";
    			String userMessage = "";
    			
    			try {
    				userMessage = "missatge que enviarem al disp "; //request.getParameter("Message");
    				//collapseKey = request.getParameter("CollapseKey");
    			} catch (Exception e) {
    				e.printStackTrace();
    				//return;
    			}
    			
    	// Instance of com.android.gcm.server.Sender, that does the
    			// transmission of a Message to the Google Cloud Messaging service.
    			//Sender sender = new Sender(SENDER_ID);
    			
    			// This Message object will hold the data that is being transmitted
    			// to the Android client devices.  For this demo, it is a simple text
    			// string, but could certainly be a JSON object.
    			//Message message = new Message.Builder()
    			
    			// If multiple messages are sent using the same .collapseKey()
    			// the android target device, if it was offline during earlier message
    			// transmissions, will only receive the latest message for that key when
    			// it goes back on-line.
    			/////.collapseKey(collapseKey)
    			//.timeToLive(30)
    			//.delayWhileIdle(true)
    			//.addData("message", userMessage)
    			//.build();
    			
    			try {
    				// use this for multicast messages.  The second parameter
    				// of sender.send() will need to be an array of register ids.
    				//MulticastResult result = sender.send(message, androidTargets, 1);
    				
    				/*if (result.getResults() != null) {
    					int canonicalRegId = result.getCanonicalIds();
    					if (canonicalRegId != 0) {
    						
    					}
    				} else {
    					int error = result.getFailure();
    					System.out.println("Broadcast failure: " + error);
    				}*/
    				
    			} catch (Exception e) {
    				e.printStackTrace();
    			}

    			// We'll pass the CollapseKey and Message values back to index.jsp, only so
    			// we can display it in our form again.
    			/*request.setAttribute("CollapseKey", collapseKey);
    			request.setAttribute("Message", userMessage);*/
    	
    			
    			FCMTokenRegisterRepository.insertFCMTokenRegister(regID);
    	
    			return "Ok";
    	}


}

