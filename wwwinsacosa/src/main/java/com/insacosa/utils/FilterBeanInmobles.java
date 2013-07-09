package com.insacosa.utils;


import com.google.appengine.api.datastore.Key;
import com.insacosa.interfaces.Inmoble_Impl;

import java.io.Serializable;   
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean; 
import javax.faces.bean.SessionScoped;   
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.richfaces.model.Filter;

import com.insacosa.entitats.Ciutats;
import com.insacosa.entitats.Provincies;

import com.insacosa.vo.CiutatsForm;
import com.insacosa.vo.InmobleForm;
import com.insacosa.vo.ProvinciesForm;
 
@ManagedBean
@SessionScoped
public class FilterBeanInmobles implements Serializable {     /**      *       */    
	
	private static final long serialVersionUID = -5680001353441022183L;     


private String nomFilter;
private int habitacionsFilter;
private int banysFilter;
// combos 
private String localitatFilter;
private String provinciaFilter;
private Key tipusFilter;

private int metresFilter;
private int preuFilter;

public String getNomFilter() {
	return nomFilter;
}
public void setNomFilter(String nomFilter) {
	this.nomFilter = nomFilter;
}
public String getLocalitatFilter() {
	return localitatFilter;
}
public void setLocalitatFilter(String localitatFilter) {
	this.localitatFilter = localitatFilter;
}
public String getProvinciaFilter() {
	return provinciaFilter;
}
public void setProvinciaFilter(String provinciaFilter) {
	this.provinciaFilter = provinciaFilter;
}
public Key getTipusFilter() {
	return tipusFilter;
}
public void setTipusFilter(Key tipusFilter) {
	this.tipusFilter = tipusFilter;
}  


/*
public Filter<?> getHabitacionsFilterImpl() {    
	
	return new Filter<InmobleForm>() {             
		public boolean accept(InmobleForm item) {                 
			int habitacions = getHabitacionsFilter();                 
			if (habitacions == 0 || habitacions >=item.getHabitacions())  {                    
				return true;                 
				}                 
			return false;             
	}         
};     
} 
*/
/*
public Filter<?> getBanysFilterImpl() {    
	
	return new Filter<InmobleForm>() {             
		public boolean accept(InmobleForm item) {                 
			int banys = getBanysFilter();                 
			if (banys == 0 || banys >=item.getBanys())  {                    
				return true;                 
				}                 
			return false;             
	}         
};     
} 
*/

 
public Filter<?> getMetresFilterImpl() {    
	
	return new Filter<InmobleForm>() {             
		public boolean accept(InmobleForm item) {                 
			int metres = getMetresFilter();                 
			if (metres == 0 || metres >=item.getMetres())  {                    
				return true;                 
				}                 
			return false;             
	}         
};     
} 


public Filter<?> getPreuFilterImpl() {    
	
	return new Filter<InmobleForm>() {             
		public boolean accept(InmobleForm item) {                 
			int preu = getPreuFilter();                 
			if (preu == 0 || preu >=item.getPreu())  {                    
				return true;                 
				}                 
			return false;             
	}         
};     
} 
/*
public Filter<?> getFilterTipus() {  
	
	return new Filter<InventoryItem>() {             
	public boolean accept(InventoryItem t) {                 
		String tipus = getTipusFilter();                 
		if (tipus == null || tipus.length() == 0 || tipus.equals(t.getTipus())) {                     
			return true;                 
		}                 
		return false;             
	}         
	};     
}
*/

public int getMetresFilter() {
	return metresFilter;
}
public int getHabitacionsFilter() {
	return habitacionsFilter;
}
public void setHabitacionsFilter(int habitacionsFilter) {
	this.habitacionsFilter = habitacionsFilter;
}
public int getBanysFilter() {
	return banysFilter;
}
public void setBanysFilter(int banysFilter) {
	this.banysFilter = banysFilter;
}
public void setMetresFilter(int metresFilter) {
	this.metresFilter = metresFilter;
}
public int getPreuFilter() {
	return preuFilter;
}
public void setPreuFilter(int preuFilter) {
	this.preuFilter = preuFilter;
}


/*
 * Al cambiar el valor de la provincia construim la combo de les ciutats 
 * de la provincia en questio
 */
public void cambiarProvincia(ValueChangeEvent event)
{
	
	FacesContext context = FacesContext.getCurrentInstance(); 
	CiutatsForm ciutatsForm = (CiutatsForm) context.getApplication().evaluateExpressionGet(context, "#{ciutats}", CiutatsForm.class);
	ProvinciesForm provinciesForm = (ProvinciesForm) context.getApplication().evaluateExpressionGet(context, "#{provincies}", ProvinciesForm.class);
	
	List<SelectItem> novesCiutats = ciutatsForm.getCiutats();
	novesCiutats.clear();
	
	Inmoble_Impl r = new Inmoble_Impl();
	
	 if (null != event.getNewValue()) 
	 {             		
		Provincies provincia = new Provincies();
		
		// Recuperem l'objecte Provincia que hem seleccionat
		provincia = r.provinciaPerKey((String)event.getNewValue());
		
		// Actualitzem les ciutats de la provincia seleccionada		
		Iterator<Ciutats> iter = r.ciutatsProvincia(provincia).iterator();
		while (iter.hasNext())
		{
			Ciutats ciutatHBM = (Ciutats)(iter.next());  
			
			SelectItem item = new SelectItem(ciutatHBM.getId(), ciutatHBM.getName() , "", false, false);
			                   
			// hem de modificar també el combi de ciutats del formulari d'entrada
			novesCiutats.add(item);
			
		}
		

		// Modifiquem la provincia  i localitat dels corresponents filtres 
		setLocalitatFilter((String) novesCiutats.get(0).getValue());
		setProvinciaFilter(provincia.getProvinciaKey());
		
		// actualitzem el necessari per actualitzar els combos de la ciutat i provincia del formulari d'entrada
		//inmobleForm.setProvincia(provincia.getId());
		///////inmobleForm.setProvinciaStr(provincia.getName());
		provinciesForm.setValorActual(provincia.getProvinciaKey());
		ciutatsForm.setValorActual((String) novesCiutats.get(0).getValue());
		 
      }
	
		
}



/*
 * Al cambiar el valor de la provincia construim la combo de les ciutats 
 * de la provincia en questio
 */
public void cambiarCiutat(ValueChangeEvent event)
{
	
	FacesContext context = FacesContext.getCurrentInstance(); 
	CiutatsForm ciutatsForm = (CiutatsForm) context.getApplication().evaluateExpressionGet(context, "#{ciutats}", CiutatsForm.class);
	
	Inmoble_Impl r = new Inmoble_Impl();
	
	 if (null != event.getNewValue()) 
	 {             		
		 ciutatsForm.setValorActual((String) event.getNewValue());
		 
      }
		
}



} 
