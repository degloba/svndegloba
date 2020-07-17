package com.degloba.integration.impl.spring.services;


/**
 * @category Servei de notificacions
 */
public interface INotificationService {

    void enviaNotificacioConfirmacio(String userId, long id);

    void enviaNotificacioRecordatori(String userId, long id);

}
