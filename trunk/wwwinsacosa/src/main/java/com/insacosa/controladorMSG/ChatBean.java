package com.insacosa.controladorMSG;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.richfaces.application.push.MessageException;
import org.richfaces.application.push.TopicKey;
import org.richfaces.application.push.TopicsContext;
import org.richfaces.log.LogFactory;
import org.richfaces.log.Logger;

import com.insacosa.vo.InmobleForm;

/**
 * @author Nick Belaevski
 * 
 */
@ManagedBean(name="chatBean")
@SessionScoped
public class ChatBean implements Serializable {

    private static final long serialVersionUID = -6377543444437645751L;

    private static final Logger LOGGER = LogFactory.getLogger(ChatBean.class);
    
    private String userName;
    
    private String message;
    
    private boolean chatJoined;
    
    private transient TopicsContext topicsContext;
    
    private String subchannel;
    
    @ManagedProperty("#{channelsBean}")
    private ChannelsBean channelsBean;
    
    @PostConstruct
    public void init() {
        topicsContext = TopicsContext.lookup();
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    private void publishStateChangeMessage(String canal, String action) {
        try {
        	topicsContext = TopicsContext.lookup();
        	
        	FacesContext context = FacesContext.getCurrentInstance();
        	InmobleForm inmobleForm = (InmobleForm) context.getApplication().evaluateExpressionGet(context, "#{inmobleForm}", InmobleForm.class);
        	//UserForm comprador = (UserForm) context.getApplication().evaluateExpressionGet(context, "#{userForm}", UserForm.class);
      		
        	// ho enviem a la cua de l'usuari del venedor
            topicsContext.publish(new TopicKey("inmobles", inmobleForm.getVenedor() + canal), "solicitud de inmueble por comprador ");
        } catch (MessageException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    
    /*
     * Metode cridat des de la capa UI que provoca l'enviament d'un missatge de solicitud d'inmoble
     */
    public void solicitudInmoble() {
        publishStateChangeMessage("solicitudsInmobles", "joined");
    }
    
    
    /*
     * Metode cridat des de la capa UI que provoca l'enviament d'un missatge de baixa d'inmoble
     */
    public void baixaInmoble() {
        publishStateChangeMessage("baixaInmoble", "joined");
    }
    
    
    
    public void handleStateChange(Channel channel) {
        String action;
        if (channel.isRendered()) {
            action = "joined";
        } else {
            action = "left";
        }

        publishStateChangeMessage(channel.getName(), action);
    }

    /*
     * Metode que publica un missatge a un topic
     */
    public void say() {
        try {
            topicsContext.publish(new TopicKey("inmobles", subchannel), "hola");
        } catch (MessageException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    public void setTopicsContext(TopicsContext topicsContext) {
        this.topicsContext = topicsContext;
    }
    
    /**
     * @return the subchannel
     */
    public String getSubchannel() {
        return subchannel;
    }
    
    /**
     * @param subchannel the subchannel to set
     */
    public void setSubchannel(String subchannel) {
        this.subchannel = subchannel;
    }

    /**
     * @param channelsBean the channelsBean to set
     */
    public void setChannelsBean(ChannelsBean channelsBean) {
        this.channelsBean = channelsBean;
    }

}
