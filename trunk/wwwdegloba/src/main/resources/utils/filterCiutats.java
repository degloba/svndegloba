package utils;

  
import java.io.Serializable;   
import javax.faces.bean.ManagedBean; 
import javax.faces.bean.SessionScoped;   
   

@ManagedBean
@SessionScoped
public class filterCiutats implements Serializable {     /**      *       */    
	
	private static final long serialVersionUID = -5680001353441022183L;     

private String nameFilter;    
 

public String getNameFilter() {         
	return nameFilter;     }       

public void setNameFilter(String nameFilter) {         
	this.nameFilter = nameFilter;     
	}


} 
