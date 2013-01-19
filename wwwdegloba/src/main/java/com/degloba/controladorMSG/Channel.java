package com.degloba.controladorMSG;


import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 * @author Nick Belaevski
 * 
 */
public class Channel implements Serializable {

    private static final long serialVersionUID = 6798558262812940593L;

    private String name;

    private boolean rendered = true;
    
    public Channel(String name) {
        super();
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isRendered() {
        return rendered;
    }
    
    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }
    
    public void processChannelStateChange(ValueChangeEvent event) {
        setRendered(Boolean.TRUE.equals(event.getNewValue()));
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ChatBean chatBean = (ChatBean) externalContext.getSessionMap().get("chatBean");
        
        chatBean.handleStateChange(this);
    }
}
