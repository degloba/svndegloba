package com.degloba.dragdrop;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

/*
 * Classe Abstracta
 */
public abstract class DragDropBean<T> implements Serializable {

    private static final long serialVersionUID = 1416925735640720492L;
   
    private List<T> source;

    private List<T> target;

    public Collection<T> getSource() {
        return source;
    }

    public Collection<T> getTarget() {
    	 return target;
    }

   
    public void setSource(ArrayList<Object> arrayList) {
		this.source = (List<T>) arrayList;
	}

    
	public void setTarget(ArrayList<Object> arrayList) {
		this.target = (List<T>) arrayList;
	}

	public void moveCaracteristica(T caracteristica) {
        getSource().remove(caracteristica);
        getTarget().add(caracteristica);
   }

    public void reset(List<T> novaLlista) {
        initList(novaLlista);
    }

  
	public void initList(List<T> novaLlista) {
		
        setSource(Lists.newArrayList());
        setTarget(Lists.newArrayList());
        
        Iterator iter = novaLlista.iterator();
        while (iter.hasNext())
        {
           	getSource().add((T) iter.next());
        }
	}

	
	 protected abstract Object getId(T t);

    
}
