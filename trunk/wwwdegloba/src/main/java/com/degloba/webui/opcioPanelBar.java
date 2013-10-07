package com.degloba.webui;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;

import org.richfaces.event.ItemChangeEvent;
import org.richfaces.event.ItemChangeListener;
import org.richfaces.component.UIAccordionItem;

public class opcioPanelBar {
	
	private static HashMap<String,Boolean> visibles=new HashMap<String,Boolean>();
	private String item;
	
	

	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}


	public opcioPanelBar() {
		// TODO Auto-generated constructor stub

		inicialitzar();
		setItem("document");
	}
	
	

	public void inicialitzar ()
	{
		
		visibles.put("wizard", true);
	}
	
	public void reset ()
	{
		int seguent;
		
		Iterator it = visibles.entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			e.setValue(false);
		}
	}

	public HashMap<String,Boolean> getVisibles() {
		return visibles;
	}

	public void setVisibles(HashMap<String,Boolean> visibles) {
		this.visibles = visibles;
	}



	
    public void itemChangeActionListener(ItemChangeEvent event) {
		//int quinaOpcio=Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("opcio"));
		
		reset();
		
		//visibles.set(quinaOpcio, true);
    	setItem(event.getNewItemName());  
    	visibles.put(event.getNewItemName(), true);
    }

	

}
