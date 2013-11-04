package com.insacosa.dragdrop;


import java.io.Serializable;

import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.google.common.collect.Lists;

import com.insacosa.vo.InmobleForm;

import com.insacosa.domain.*;


@ManagedBean(name = "dragDropBeanCaract")
@ViewScoped
public class DragDropBeanCaract extends DragDropBean<Caracteristiques> implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InmobleForm inmobleForm;
	
	
	@Override
	protected Object getId(Caracteristiques t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveCaracteristica(Caracteristiques caracteristica)
	{
	     getSource().remove(caracteristica);
	     getTarget().add(caracteristica);
	      	
	     inmobleForm.getValorsCaracts().put(caracteristica.getCaracteristicaKey(), true);
	     
	}
	
	@Override
    public void initList(List<Caracteristiques> llista) {
		
		 //HAURIEM DE POSAR LA CARACTERISTICA SELECCIONADA EN EL HASH
	     FacesContext context = FacesContext.getCurrentInstance(); // 
	     inmobleForm = (InmobleForm) context.getApplication().evaluateExpressionGet(context, "#{inmobleForm}", InmobleForm.class);
	  
		 setSource(Lists.newArrayList());
	     setTarget(Lists.newArrayList());
	        
	     Iterator iter = llista.iterator();
	        while (iter.hasNext())
	        {
	        	getSource().add((Caracteristiques) iter.next());
	        }
			
	    
	    // esborrem els valors de les caracteristiques (NOMES LES BOOLEANES!!!!)    
		inmobleForm.getValorsCaracts().clear();
    	
    }

    
}
