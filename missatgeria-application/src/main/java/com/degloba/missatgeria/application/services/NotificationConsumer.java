package com.degloba.missatgeria.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.degloba.messaging.domain.pojos.NotificationData;

import reactor.bus.Event;
import java.util.function.Consumer;  // https://stackoverflow.com/questions/41708207/upgrading-io-projectreactor-version-from-2-0-x-to-3-0-4-using-spring-framework

@Service
public class NotificationConsumer implements Consumer<Event<NotificationData>> {
 
    @Autowired
    private INotificationService notificationService;
     
    @Override
    public void accept(Event<NotificationData> notificationDataEvent) {
        NotificationData notificationData = notificationDataEvent.getData();
         
        try {
            notificationService.initiateNotification(notificationData);
        } catch (InterruptedException e) {
            // ignore        
        }   
    }
}