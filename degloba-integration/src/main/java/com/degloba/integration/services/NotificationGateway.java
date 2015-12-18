package com.degloba.integration.services;

// Spring - Integration
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.mail.MailHeaders;

// Spring Messaging
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

public interface NotificationGateway {

    @Gateway(requestChannel = "enqueuedEmails")
    void sendNotification(
            @Header(MailHeaders.TO) String destinationAddresses,
            @Header(MailHeaders.SUBJECT) String subject,
            @Payload Map<String, String> body);

}
