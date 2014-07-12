package com.degloba.boundedContext.webui.JSF;


import java.util.HashMap;
import java.util.Iterator;

import java.util.Map;

import org.richfaces.event.ItemChangeEvent;

public class opcioPanelBar {
	
	private HashMap<String,Boolean> visibles=new HashMap<String,Boolean>();
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
		
		Iterator<?> it = visibles.entrySet().iterator();
		
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
