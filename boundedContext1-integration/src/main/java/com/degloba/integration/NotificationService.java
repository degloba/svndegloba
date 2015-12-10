package com.degloba.integration;


/**
 * Handles sending notifications
 */
public interface NotificationService {

    void sendConfirmationNotification(String userId, long id);

    void sendReminderNotification(String userId, long id);

}
