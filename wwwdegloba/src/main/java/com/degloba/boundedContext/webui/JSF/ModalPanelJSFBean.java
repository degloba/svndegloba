package com.degloba.boundedContext.webui.JSF;

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

import com.degloba.boundedContext.application.api.commands.AddModalpanelCommand;
import com.degloba.boundedContext.application.api.service.ModalpanelService;

// Entitat Domini
import com.degloba.boundedContext.domain.modalpanel.Modalpanel;
import com.degloba.boundedContext.readmodel.modalpanel.ModalpanelDto;

// Finder (CQRS)
import com.degloba.boundedContext.readmodel.modalpanel.IModalpanelFinder;

import command.IGate;
import domain.canonicalmodel.publishedlanguage.AggregateId;


@Component
@ManagedBean
@SessionScoped
public class ModalPanelJSFBean {
	
	private static final Logger log = Logger.getLogger(ModalPanelJSFBean.class.getName());
	
    @Inject
    private IModalpanelFinder modalpanelFinder;
    
    @Inject
    private ModalpanelService<Long> modalpanelsService;
    
    @Inject
    private IGate gate;
    	
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
		
		
		////modalpanelsService.addModalpanel(() 1);
		
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


