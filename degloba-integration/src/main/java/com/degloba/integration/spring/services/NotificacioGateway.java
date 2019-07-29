package com.degloba.integration.spring.services;

// Spring - Integration
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.mail.MailHeaders;

// Spring Messaging
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

/**
 * 
 * @author degloba
 *
 * @category defineix un mètode per enviar mails com missatges en el context Spring
 */
public interface NotificacioGateway {

    @Gateway(requestChannel = "enqueuedEmails")
    void enviaNotificacio(
            @Header(MailHeaders.TO) String destinationAddresses,
            @Header(MailHeaders.SUBJECT) String subject,
            @Payload Map<String, String> body);

}
