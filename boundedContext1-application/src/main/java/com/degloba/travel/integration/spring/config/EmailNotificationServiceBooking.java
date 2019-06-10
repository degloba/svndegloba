package com.degloba.travel.integration.spring.config;

///////import org.apache.commons.io.IOUtils;
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
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

//Spring - Messaging
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.integration.mail.MailHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.degloba.travel.integration.spring.EmailNotificationService;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Usuari;
import com.degloba.integration.spring.services.NotificationGateway;

import com.degloba.travel.application.services.ITravelService;

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
 * @category Implementa les notificacions de {@link Reserva} utilitzant email. 
 * La majoria del treball és gestionat per Spring Integration
 */
@Component
public class EmailNotificationServiceBooking extends EmailNotificationService {

    @Value("classpath:/templates/confirmation-html.vm")
    private Resource htmlConfirmation;

    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private ITravelService bookingService;

    @Autowired
    private NotificationGateway notificationGateway;

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

    @PostConstruct
    public void start() throws Exception {
        // read the templates in as strings and cache the results
        //////cachedTemplates.put(this.textConfirmation, readTemplate(textConfirmation));
        //////cachedTemplates.put(this.htmlConfirmation, readTemplate(htmlConfirmation));
    }

    private String mergeTemplate(Usuari usuari, Reserva reserva, String tplBody) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", usuari.getName());
        model.put("email", usuari.getEmail());
        model.put("bookingId", reserva.getId());
        model.put("bookingCheckin", reserva.getCheckinDate());
        model.put("hotelName", reserva.getHotel().getName());
        model.put("bookingCheckout", reserva.getCheckoutDate());
        return mergeTemplate(model, tplBody);
    }

    public String mergeTemplate(Map<String, Object> model, String template) throws Exception {
        VelocityContext context = new VelocityContext();
        for (String k : model.keySet())
            context.put(k, model.get(k));
        StringWriter stringWriter = new StringWriter();
        this.velocityEngine.evaluate(context, stringWriter, "notifications", template);
       // IOUtils.closeQuietly(stringWriter);
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
    public void sendConfirmationNotification(String userId, long bookingId) {
        Usuari usuari = bookingService.findUser(userId);
        Reserva reserva = bookingService.findBookingById(bookingId);

        try {
            String html = mergeTemplate(usuari, reserva, cachedTemplates.get(htmlConfirmation));
            String txt = mergeTemplate(usuari, reserva, cachedTemplates.get(textConfirmation));
            Map<String, String> m = new HashMap<String, String>();
            m.put("html", html);
            m.put("txt", txt);

            notificationGateway.sendNotification(usuari.getEmail(), this.confirmationSubject, m);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   /* private String readTemplate(Resource resource) {
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            Assert.notNull(inputStream, "the inputStream shouldn't be null");
            return IOUtils.toString(inputStream);
        } catch (IOException e) {
            log.error("couldn't read in the body of the HTML template ", e);
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
        }
    }*/
}
