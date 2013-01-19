

import java.util.HashMap;
import java.util.Map;

	
public class JavaBeatRichfacesBean { 
		
		private String name;    
		private Integer empId;    
		private String status;
		
		public Integer getEmpId() {        
			return empId;    
		}
	
	public void setEmpId(Integer empId) {        
		this.empId = empId;    
		}   
	
	public String getStatus() {        
		return status;    
		}  
	
	public void setStatus(String status) {        
		this.status = status;    
		}  
	
	public String getName() {        
		return name;    
		}    
	
	public void setName(String name) {        
		this.name = name;    
		}    
	
	public void update(){        
			Map<Integer,String> map = new HashMap<Integer,String>();        
			map.put(1, "Employee One");        
			map.put(2, "Employee Two");        
			map.put(3, "Employee Three");        
			map.put(4, "Employee Four");        
			this.name = map.get(this.empId);   
			
			if (this.name == null || this.name.trim().length() == 0)        
			{            
				this.status = "No Matching Found";        
			}    
	}

}

