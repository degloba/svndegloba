package com.degloba.integration.spring.config.services;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Spring AMQP
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
// Spring
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

//Spring Messaging 
import org.springframework.messaging.*;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

// Spring Integration
import org.springframework.integration.mail.MailHeaders;
import org.springframework.integration.transformer.Transformer;

// Spring Mail
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

// Velocity
//import org.springframework.ui.velocity.VelocityEngineFactoryBean;

//is used on a method that needs to be executed after dependency injection is done to perform any initialization
import javax.annotation.PostConstruct;

import javax.inject.Inject;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @category defineix la configuració d'integració (amqp/rabbit, messaging, mail,...) com un bean d'Spring
 * i a partir del fitxer de propietats application.properties
 */
@Configuration
@PropertySource("classpath:/META-INF/application.properties")
public class IntegracioConfiguration {


    @Inject
    private Environment environment;

    private String notificationQueueName;
    private String username;
    private String password;
    private String emailHost = "smtp.gmail.com";
    private int emailPort = 465;
    private String emailProtocol = "smtps";
    private String emailUsername;
    private String emailPassword;
    private String brokerUrl;

    @PostConstruct
    public void setup() throws Throwable {
        emailHost = environment.getProperty("notifications.email.host");
        emailPort = Integer.parseInt(environment.getProperty("notifications.email.port"));
        emailProtocol = environment.getProperty("notifications.email.protocol");
        emailPassword = environment.getProperty("notifications.email.password");
        emailUsername = environment.getProperty("notifications.email.username");
        brokerUrl = environment.getProperty("broker.url");
        username = environment.getProperty("broker.username");
        password = environment.getProperty("broker.password");
        notificationQueueName = environment.getProperty("amqp.notification.queue");
    }


  /*  @Bean
    public VelocityEngineFactoryBean velocityEngineFactoryBean() {
        return new VelocityEngineFactoryBean();
    }*/


    @Bean
    public Map<String, String> emailProperties() {
        Map<String, String> props = new HashMap<String, String>();
        props.put("mail.smtps.auth", true + "");
        props.put("mail.smtps.starttls.enable", true + "");
        props.put("mail.smtp.starttls.enable", true + "");
        return props;
    }

    @Bean
    public JavaMailSender javaMailSender() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(this.emailHost);
        javaMailSender.setPassword(this.emailPassword);
        javaMailSender.setUsername(this.emailUsername);
        javaMailSender.setPort(emailPort);
        javaMailSender.setProtocol(this.emailProtocol);

        Properties properties = new Properties();
        properties.putAll(emailProperties());
        javaMailSender.setJavaMailProperties(properties);

        return javaMailSender;
    }


  /*  @Bean
    public AmqpTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }*/


   /* @Bean
    public MessageConverter jsonMessageConverter() {
        return new JsonMessageConverter();
    }*/

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(this.brokerUrl);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(this.connectionFactory());
    }

    @Bean
    public Queue notificationQueue() {
        Queue q = new Queue(this.notificationQueueName);
        amqpAdmin().declareQueue();
        return q;
    }

    @Bean
    public DirectExchange notificationExchange() {
        DirectExchange directExchange = new DirectExchange(notificationQueueName);
        this.amqpAdmin().declareExchange(directExchange);
        return directExchange;
    }

    @Bean
    public Binding notificationBinding() {
        return BindingBuilder.bind(notificationQueue()).to(notificationExchange()).with(this.notificationQueueName);
    }

    private Log log = LogFactory.getLog(getClass());

    @Bean
    public MessageHandler errorMessageHandler() {
        MessageHandler messageHandler = new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                log.error("Inside the Error Handler. Something has Occurred");
                for (String k : message.getHeaders().keySet())
                    log.error(String.format("{'%s':'%s'}", k, message.getHeaders().get(k)));
                log.error(message.getPayload().toString());
                log.error("------------------------------------------------------------------");
            }
        };

        return messageHandler;
    }

    @Bean
    public Transformer transformerGatewayMessageToAmqpPayload() {
        return new Transformer() {
            @Override
            public Message<?> transform(Message<?> message) {
                MessageHeaders msgHeaders = message.getHeaders();

                Map<String, Object> body = (Map<String, Object>) message.getPayload();
                for(String headerName : new String []{ MailHeaders.TO,  MailHeaders.SUBJECT} )
                    body.put( headerName,  msgHeaders.get(headerName));

                return message;
            }
        };
    }
  

   
}
