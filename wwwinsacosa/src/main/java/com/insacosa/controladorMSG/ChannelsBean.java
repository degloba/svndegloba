package com.insacosa.controladorMSG;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author Nick Belaevski
 * 
 */
@SessionScoped
@ManagedBean
public class ChannelsBean implements Serializable {

    private static final long serialVersionUID = 2916670958555985564L;

    private List<Channel> channels = new ArrayList<Channel>();
    
    public ChannelsBean() {
        channels.add(new Channel("LLUISsolicitudsInmobles"));
        channels.add(new Channel("programming"));
        channels.add(new Channel("hardware"));
        channels.add(new Channel("os"));
    }
    
    public List<Channel> getChannels() {
        return channels;
    }
}
