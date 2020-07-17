package com.degloba.integration.impl.spring.services;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;


import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;


import org.springframework.integration.mail.MailHeaders;

import javax.annotation.PostConstruct;


import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @category Implementa els servei de notificacions utilitzant email.
 * La majoria de la feina és manipulat a través de Spring Integration
 */
@Component
public class EmailNotificationService implements INotificationService {

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
        /////IOUtils.closeQuietly(stringWriter);
        return stringWriter.toString();
    }


    @Autowired
    @Qualifier("errorMessageHandler")
    private MessageHandler messageHandler;


    @SuppressWarnings("unchecked")
    public void enviaCorreo(final Message<Object> inboundEmailFromMq) throws Exception {

        messageHandler.handleMessage(inboundEmailFromMq);

        Map<String, String> templates =  (Map<String, String>) inboundEmailFromMq.getPayload();

        final String to = inboundEmailFromMq.getHeaders().get(MailHeaders.TO, String.class);
        final String subject = inboundEmailFromMq.getHeaders().get(MailHeaders.SUBJECT, String.class);


        log.info("sending: {to: '" + to + "', subject: '" + subject + "'}");

        final String html = templates.get("html");
        final String txt = templates.get("txt");


        /*this.mailSender.send(new MimeMessagePreparator() {
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
        });*/
    }


	@Override
	public void enviaNotificacioConfirmacio(String userId, long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void enviaNotificacioRecordatori(String userId, long id) {
		// TODO Auto-generated method stub
		
	}


}
