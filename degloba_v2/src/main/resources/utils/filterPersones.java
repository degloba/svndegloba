package utils;

  
import java.io.Serializable;   
import javax.faces.bean.ManagedBean; 
import javax.faces.bean.SessionScoped;   
 
@ManagedBean
@SessionScoped
public class filterPersones implements Serializable {     /**      *       */    
	
	private static final long serialVersionUID = -5680001353441022183L;     

// PFIS	
private String first_nameFilter;    
private String second_nameFilter;  

// PJUR
private String nameFilter;  

public String getFirst_nameFilter() {         
	return first_nameFilter;     }       

public void setFirst_nameFilter(String first_nameFilter) {         
	this.first_nameFilter = first_nameFilter;     
	}

public String getSecond_nameFilter() {
	return second_nameFilter;
}

public void setSecond_nameFilter(String second_nameFilter) {
	this.second_nameFilter = second_nameFilter;
}

public String getNameFilter() {
	return nameFilter;
}

public void setNameFilter(String nameFilter) {
	this.nameFilter = nameFilter;
} 




} 
