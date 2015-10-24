package com.degloba.infrastructure.integration.services;

import org.springframework.integration.annotation.Gateway;
// Spring - Integration
import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.Payload;
import org.springframework.integration.mail.MailHeaders;

import java.util.Map;

public interface NotificationGateway {

    @Gateway(requestChannel = "enqueuedEmails")
    void sendNotification(
            @Header(MailHeaders.TO) String destinationAddresses,
            @Header(MailHeaders.SUBJECT) String subject,
            @Payload Map<String, String> body);

}
