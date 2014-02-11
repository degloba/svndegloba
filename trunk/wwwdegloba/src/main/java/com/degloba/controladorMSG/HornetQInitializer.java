/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.degloba.controladorMSG;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PostConstructApplicationEvent;
import javax.faces.event.PreDestroyApplicationEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;


import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.core.config.Configuration;
import org.hornetq.core.config.impl.ConfigurationImpl;

import org.hornetq.core.remoting.impl.invm.InVMAcceptorFactory;
import org.hornetq.core.remoting.impl.invm.InVMConnectorFactory;
import org.hornetq.core.server.HornetQServer;
import org.hornetq.core.server.HornetQServers;
import org.hornetq.jms.server.JMSServerManager;
import org.hornetq.jms.server.config.ConnectionFactoryConfiguration;
import org.hornetq.jms.server.config.impl.ConnectionFactoryConfigurationImpl;
import org.hornetq.jms.server.impl.JMSServerManagerImpl;
import org.richfaces.application.ServiceTracker;
import org.richfaces.application.push.PushContextFactory;


// per la versio 2.2.7 de HornetQ
import javax.jms.Connection;
import javax.jms.ConnectionFactory;

import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;



/**
 * @author Nick Belaevski
 * 
 */
public class HornetQInitializer implements SystemEventListener {

    private JMSServerManager serverManager;

    public void processEvent(SystemEvent event) throws AbortProcessingException {
        if (event instanceof PostConstructApplicationEvent) {
            try {
                startHornetQ();
            } catch (Exception e) {
                throw new AbortProcessingException(e);
            }

            //force push context initialization so that its PreDestroyApplicationevent listener is added before HornetQ stopper
            ServiceTracker.getService(PushContextFactory.class).getPushContext();
            
       Application application = FacesContext.getCurrentInstance().getApplication();
      application.subscribeToEvent(PreDestroyApplicationEvent.class, this);
        } else {
            try {
                stopHornetQ();
            } catch (Exception e) {
                throw new AbortProcessingException(e);
            }
        }
    }

    /**
     * @throws Exception 
     * 
     */
    private void stopHornetQ() throws Exception {
        serverManager.stop();
        serverManager = null;
    }

    /**
     * @throws Exception 
     * 
     */
    public void startHornetQ() throws Exception {
    	
    	/* opcio d'acces a la configuracio del HornetQ Server mitjan�ant fitxer de configuracio 
    	 FileConfiguration configuration = new FileConfiguration();
         configuration.setConfigurationUrl("hornetq-configuration.xml");
         configuration.start();
    	 */
    	
    	
    	/**************************************************/
    	/* opcio 1 : HornetQ 2.1.2 FUNCIONA*/
        // Step 2. Create the Configuration, and set the properties accordingly
        Configuration configuration = null;
        configuration = new ConfigurationImpl();
        configuration.setPersistenceEnabled(false);
        configuration.setSecurityEnabled(false);

/////////TransportConfiguration transpConf = new TransportConfiguration(NettyAcceptorFactory.class.getName());
        TransportConfiguration transpConf = new TransportConfiguration(InVMAcceptorFactory.class.getName());

        HashSet<TransportConfiguration> setTransp = new HashSet<TransportConfiguration>();
        setTransp.add(transpConf);

        configuration.setAcceptorConfigurations(setTransp);

        // Step 3. Create and start the server
        HornetQServer server = HornetQServers.newHornetQServer(configuration);

        serverManager = new JMSServerManagerImpl(server, "hornetq-jms.xml");
        
        
        /* opcio d'acces a la configuracio del HornetQ Server mitjan�ant fitxer de configuracio 
    	JMSServerManager serverManager = new JMSServerManagerImpl(server, "hornetq-jms.xml");*/

        //if you want to use JNDI, simple inject a context here or don't call this method and make sure the JNDI parameters are set.
        //InitialContext context = new InitialContext(props);
        //serverManager.setContext(context);  
        serverManager.start();
        
               
////////ConnectionFactoryConfiguration connectionFactoryConfiguration = new ConnectionFactoryConfigurationImpl("ConnectionFactory", new TransportConfiguration(NettyConnectorFactory.class.getName()), (String) null);
        ConnectionFactoryConfiguration connectionFactoryConfiguration = new ConnectionFactoryConfigurationImpl("InVMConnectionFactory", new TransportConfiguration(InVMConnectorFactory.class.getName()), (String) null);
        connectionFactoryConfiguration.setUseGlobalPools(false);
        
        // S'ASSIGNA EL JNDI NAME AMB EL DEL WEB.XML QUE ES EL QUE UTILITZA EL RICHFACES PUSH
////////serverManager.createConnectionFactory(false, connectionFactoryConfiguration, "ConnectionFactory");
////////connectionFactoryConfiguration.setClientID("ConnectionFactory");
        
        serverManager.createConnectionFactory(false, connectionFactoryConfiguration, "InVMConnectionFactory");
        connectionFactoryConfiguration.setClientID("InVMConnectionFactory");
        
        /*************************************************/
             
        
   /*    InitialContext ic = new InitialContext();
   ConnectionFactory cf2 = (ConnectionFactory) ic.lookup("InVMConnectionFactory");
     */   
        
        /******* CLIENT JMS *******/   
        Connection connection = null;

     // Step 1. Directly instantiate the JMS Queue object.
        Queue queue = HornetQJMSClient.createQueue("exampleQueue");
         

        // Step 2. Instantiate the TransportConfiguration object which contains the knowledge of what transport to use,
        // The server port etc.

        Map<String, Integer> connectionParams = new HashMap<String, Integer>();
        connectionParams.put("port", 5445); // cas Netty

////////TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getName(),
////////                  connectionParams);

        TransportConfiguration transportConfiguration = new TransportConfiguration(InVMConnectorFactory.class.getName(),
                null);

        
        try
        {

        
	        // Step 3 Directly instantiate the JMS ConnectionFactory object using that TransportConfiguration
	        ConnectionFactory cf = HornetQJMSClient.createConnectionFactory(transportConfiguration);
	
	        // Step 4.Create a JMS Connection
	        connection = cf.createConnection();
	
	        // Step 5. Create a JMS Session
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	
	        // Step 6. Create a JMS Message Producer
	        // Crea una Queue en el costat del Servidor
	        serverManager.createQueue(false, "exampleQueue", null, false, "exampleQueue");
	        // utilitzem la queue definida en la part del client
	        MessageProducer producer = session.createProducer(queue);
	
	        // Step 7. Create a Text Message
	        TextMessage message = session.createTextMessage("This is a text message");
	
	        System.out.println("Sent message: " + message.getText());
	
	        // Step 8. Send the Message
	        producer.send(message);
	        
	
	        // Step 9. Create a JMS Message Consumer
	        MessageConsumer messageConsumer = session.createConsumer(queue);
	
	        // Step 10. Start the Connection
	        connection.start();
	
	        // Step 11. Receive the message
	        TextMessage messageReceived = (TextMessage)messageConsumer.receive(5000);
	
	        System.out.println("Received message: " + messageReceived.getText());
	      
	        // HornetQ create Topic/Queue.Richfaces/Push NOMES UTILITZA TOPICS !!!!
	        serverManager.createTopic(false, "inmobles", "/topic/inmobles");
	        serverManager.createTopic(false, "info", "/topic/info");
	        
        }
        
        finally
        {
           if (connection != null)
           {
              connection.close();
           }
        }

      
        
    }

    public boolean isListenerForSource(Object source) {
        return true;
    }

}
