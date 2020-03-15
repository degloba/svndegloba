package com.degloba.missatgeria.application.services;

import org.springframework.stereotype.Service;

import com.degloba.messaging.domain.pojos.NotificationData;

/**
 * 
 * @author degloba
 * 
 * @category https://www.baeldung.com/reactor-bus
 *
 */
@Service
public class NotificationServiceimpl implements INotificationService {
	
     
	    @Override
	    public void initiateNotification(NotificationData notificationData) 
	      throws InterruptedException {
	 
	      System.out.println("Notification service started for "
	        + "Notification ID: " + notificationData.getId());
	         
	      Thread.sleep(5000);
	         
	      System.out.println("Notification service ended for "
	        + "Notification ID: " + notificationData.getId());
	    }
}
