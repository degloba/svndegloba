package com.degloba.missatgeria.application.services;

import com.degloba.messaging.domain.pojos.NotificationData;

/**
 * 
 * @author degloba
 * 
 * @category Servei de notificacions
 * 
 * @category https://www.baeldung.com/reactor-bus
 */
public interface INotificationService {

	void initiateNotification(NotificationData notificationData) 
		      throws InterruptedException;
}
