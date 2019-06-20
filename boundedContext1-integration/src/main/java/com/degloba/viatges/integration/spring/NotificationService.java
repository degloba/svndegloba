package com.degloba.viatges.integration.spring;


/**
 * Maneja l'enviament de notificacions
 */
public interface NotificationService {

    void sendConfirmationNotification(String userId, long id);

    void sendReminderNotification(String userId, long id);

}
