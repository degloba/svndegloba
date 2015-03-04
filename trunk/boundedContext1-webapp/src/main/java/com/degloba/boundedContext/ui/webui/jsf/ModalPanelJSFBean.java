package com.degloba.boundedContext.ui.webui.jsf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;



// JSF
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

// CDI Java EE 6
import javax.inject.Inject;


// SPRING
import org.springframework.stereotype.Component;

// CQRS - client - read

// CQRS - client - write


// CQRS - server - write
import command.IGate;



import com.degloba.casino.modalpanel.Modalpanel;
//
import com.degloba.domain.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.modalpanel.application.api.commands.AddModalpanelCommand;
import com.degloba.modalpanel.readmodel.IModalpanelFinder;
import com.degloba.modalpanel.readmodel.ModalpanelDto;

//Application


/**
 * @author degloba
 *
 * JSF Bean
 * 
 */
@Component
@ManagedBean
@SessionScoped
public class ModalPanelJSFBean {
	
	private static final Logger log = Logger.getLogger(ModalPanelJSFBean.class.getName());
	
	// CQRS - read
    @Inject
    private IModalpanelFinder modalpanelFinder;
    
    // CQRS - write
    @Inject
    private IGate gate;
    
    
    // 
    //@Inject
    //private ICasinoApp<Long> casinoApp;
    

    	
	private  ArrayList<ModalpanelDto> items = new ArrayList<ModalpanelDto>();
	ModalpanelDto mp;

	FacesContext context;
	ResourceBundle bundle;


	public ModalPanelJSFBean() {
		super();

		context  = FacesContext.getCurrentInstance();
		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
		
	}


	public List<ModalpanelDto> getItems() {
		return omplirPanelModals();		
	}


	public void setItems(ArrayList<ModalpanelDto> items) {
		this.items = items;
	}


	private List<ModalpanelDto> omplirPanelModals() {
		
		List<ModalpanelDto> panells = new ArrayList<ModalpanelDto>(); 
		
		try {
			
			Modalpanel p = new Modalpanel();
			//p.setAggregateId(AggregateId.generate());
			
			List<Modalpanel> rs = modalpanelFinder.findAll();
			
			log.info("Modalpanel count : " + rs.size());
    	
			Iterator<Modalpanel> imp=rs.iterator();
	        while (imp.hasNext())
	  		{
	        	Modalpanel mp1 = (Modalpanel) imp.next();
	        	
	    		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
				
		  		mp = new ModalpanelDto();
		  		//mp.setId(mp1.getModalpanelid());
		  		//mp.setTitol(bundle.getString("titolPanelModal." + mp1.getModalpanelid().toString()));
		  		//mp.setDefinicio(bundle.getString("definicioPanelModal." + mp1.getModalpanelid().toString()));
				
				panells.add(mp);
	  		}
                
		}	catch (Exception ex) {
			    // handle any errors
		}
		
		// 1
		//this.casinoApp.addModalpanelById(AggregateId.generate());
		
		// 2
		Modalpanel modalpanel = new Modalpanel();
		//modalpanel.setAggregateId(AggregateId.generate());
		modalpanel.setDescripcio("hola");
		//this.casinoApp.addModalpanel(modalpanel);
		
		// 3
		gate.dispatch(new AddModalpanelCommand(AggregateId.generate()));
		
		
	
		
		return panells;
	}


	public IModalpanelFinder getModalpanelFinder() {
		return modalpanelFinder;
	}


	public void setModalpanelFinder(IModalpanelFinder modalpanelFinder) {
		this.modalpanelFinder = modalpanelFinder;
	}
	
	
}


