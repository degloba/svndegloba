package utils;



import java.io.Serializable; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean; 
import javax.faces.bean.ViewScoped;   
import javax.faces.context.FacesContext;

import model.capa.prov;

import org.richfaces.component.SortOrder; 


@ManagedBean
@ViewScoped
public class provsSortingBean implements Serializable {       
	/**      
	 * *       
	 * */    
	private static final long serialVersionUID = -6237417487105926855L;     
     
	private SortOrder provsOrder = SortOrder.ascending; 
  	
	public void sortByProvs() {   

		// cal reconstruir la llista en funcio de l'ordre ascending/descending
		FacesContext context = FacesContext.getCurrentInstance(); 
		prov bean = (prov) context.getApplication().evaluateExpressionGet(context, "#{prov}", prov.class); 
		
		List<prov> llista = bean.getLlista();  // llista on es guarden les provincies
		List<prov> llista2 = new ArrayList<prov>();
		
		for (int i= llista.size(); i>0; i--)
		{
			llista2.add(llista.get(i-1));
		}
		
		bean.setLlista(llista2);
		
		
		if (provsOrder.equals(SortOrder.ascending)) 
		{             
			setProvsOrder(SortOrder.descending);         
		} 
		else {             
				setProvsOrder(SortOrder.ascending);         
			}    
	}    
	
      
	
	public SortOrder getProvsOrder() {         
		return provsOrder;     }       
	
	public void setProvsOrder(SortOrder provsOrder) {         
		this.provsOrder = provsOrder;     }       
	
} 

