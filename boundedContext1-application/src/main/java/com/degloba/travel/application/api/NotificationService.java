package com.degloba.travel.application.api;


/**
 * Handles sending notifications
 */
public interface NotificationService {


    void sendConfirmationNotification(String userId, long bookingId);


    void sendReminderNotification(String userId, long bookingId);

}
