package com.degloba.travel.integration.spring;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Velocity
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

// Spring - Integration
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.integration.mail.MailHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;


// Domain
import com.degloba.travel.domain.Booking;
import com.degloba.travel.domain.User;


// Spring
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implements notifications using email. Most of the work is handled through Spring Integration
 */
@Component
public class EmailNotificationService implements NotificationService {

/*    @Value("classpath:/templates/confirmation-html.vm")
    private Resource htmlConfirmation;*/

    private Log log = LogFactory.getLog(getClass());

   
    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${notifications.confirmation.subject}")
    private String confirmationSubject;

    @Value("classpath:/templates/confirmation-txt.vm")
    private Resource textConfirmation;

    @Value("${notifications.email.from}")
    private String emailFrom;

    private Map<Resource, String> cachedTemplates = new ConcurrentHashMap<Resource, String>();

/*    @PostConstruct
    public void start() throws Exception {
        // read the templates in as strings and cache the results
        cachedTemplates.put(this.textConfirmation, readTemplate(textConfirmation));
        cachedTemplates.put(this.htmlConfirmation, readTemplate(htmlConfirmation));
    }*/

    public String mergeTemplate(Map<String, Object> model, String template) throws Exception {
        VelocityContext context = new VelocityContext();
        for (String k : model.keySet())
            context.put(k, model.get(k));
        StringWriter stringWriter = new StringWriter();
        this.velocityEngine.evaluate(context, stringWriter, "notifications", template);
        IOUtils.closeQuietly(stringWriter);
        return stringWriter.toString();
    }


    @Autowired
    @Qualifier("errorMessageHandler")
    private MessageHandler messageHandler;


    @SuppressWarnings("unchecked")
    public void sendEmail(final Message<Object> inboundEmailFromMq) throws Exception {

        messageHandler.handleMessage(inboundEmailFromMq);

        Map<String, String> templates =  (Map<String, String>) inboundEmailFromMq.getPayload();

        final String to = inboundEmailFromMq.getHeaders().get(MailHeaders.TO, String.class);
        final String subject = inboundEmailFromMq.getHeaders().get(MailHeaders.SUBJECT, String.class);


        log.info("sending: {to: '" + to + "', subject: '" + subject + "'}");

        final String html = templates.get("html");
        final String txt = templates.get("txt");


        this.mailSender.send(new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mesg) throws Exception {
                mesg.setFrom(new InternetAddress(emailFrom));

                InternetAddress toAddress = new InternetAddress(to);
                mesg.addRecipient(javax.mail.Message.RecipientType.TO, toAddress);
                mesg.setSubject(subject);

                Multipart mp = new MimeMultipart("alternative");

                BodyPart textPart = new MimeBodyPart();
                textPart.setContent(txt, "text/plain; charset=\"us-ascii\""); // sets type to "text/plain"
                textPart.setHeader("Content-Transfer-Encoding", "7bit");

                BodyPart htmlBodyPart = new MimeBodyPart();
                htmlBodyPart.setContent(html, "text/html; charset=\"us-ascii\"");
                htmlBodyPart.setHeader("Content-Transfer-Encoding", "7bit");

                mp.addBodyPart(textPart);
                mp.addBodyPart(htmlBodyPart);

                mesg.setContent(mp);
            }
        });
    }

    @Override
    public void sendReminderNotification(String userId, long bookingId) {
        // todo
    }
  

    @Override
	public void sendConfirmationNotification(String userId, long id) {
		// TODO Auto-generated method stub
		
	}
}
