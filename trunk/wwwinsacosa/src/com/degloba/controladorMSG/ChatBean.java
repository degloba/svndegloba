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

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;

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

import com.degloba.vo.InmobleForm;
import com.degloba.vo.ProvinciesForm;
import com.degloba.vo.UserForm;

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
