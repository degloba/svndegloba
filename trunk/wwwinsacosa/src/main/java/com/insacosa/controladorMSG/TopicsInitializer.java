package com.insacosa.controladorMSG;

import java.text.MessageFormat;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;
import javax.servlet.http.HttpServletRequest;

import org.richfaces.application.push.EventAbortedException;
import org.richfaces.application.push.Session;
import org.richfaces.application.push.SessionPreSubscriptionEvent;
import org.richfaces.application.push.SessionSubscriptionEvent;
import org.richfaces.application.push.SessionTopicListener;
import org.richfaces.application.push.SessionUnsubscriptionEvent;
import org.richfaces.application.push.Topic;
import org.richfaces.application.push.TopicKey;
import org.richfaces.application.push.TopicsContext;
import org.richfaces.application.push.impl.DefaultMessageDataSerializer;

/**
 * @author Nick Belaevski
 * 
 */
public class TopicsInitializer implements SystemEventListener {

    public void processEvent(SystemEvent event) throws AbortProcessingException {
    	
    	HornetQInitializer h = new HornetQInitializer();
    	
    	try {
			h.startHornetQ();  //UTILITZEM EL HORNETQ COM A PROVEIDOR JMS
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
        TopicsContext topicsContext = TopicsContext.lookup();
        
        Topic topic = topicsContext.getOrCreateTopic(new TopicKey("inmobles"));
        
        topic.setMessageDataSerializer(DefaultMessageDataSerializer.instance());
        
        topic.addTopicListener(new SessionTopicListener() {
            
            public void processUnsubscriptionEvent(SessionUnsubscriptionEvent event) throws EventAbortedException {
                TopicKey topicKey = event.getTopicKey();
                Session session = event.getSession();
                System.out.println(MessageFormat.format("Session {0} disconnected from {1}", session.getId(), topicKey.getTopicAddress()));
            }
            
            public void processSubscriptionEvent(SessionSubscriptionEvent event) throws EventAbortedException {
                TopicKey topicKey = event.getTopicKey();
                Session session = event.getSession();
                
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpServletRequest hsr = (HttpServletRequest) facesContext.getExternalContext().getRequest();
                
                System.out.println(MessageFormat.format("Session {0} connected to {1} from {2}", session.getId(), 
                    topicKey.getTopicAddress(), hsr.getRemoteAddr()));
            }
            
            public void processPreSubscriptionEvent(SessionPreSubscriptionEvent event) throws EventAbortedException {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                ChatBean chatBean = (ChatBean) externalContext.getSessionMap().get("chatBean");
                ///////////if (chatBean == null || "badname".equals(chatBean.getUserName())) {
                    //////////throw new EventAbortedException("User name has not passed validation");
                //////////}
            }
        });
    }

    public boolean isListenerForSource(Object source) {
        return true;
    }

}
