package com.degloba.travel.integration.spring;


/**
 * Handles sending notifications
 */
public interface NotificationService {

    void sendConfirmationNotification(String userId, long id);

    void sendReminderNotification(String userId, long id);

}
