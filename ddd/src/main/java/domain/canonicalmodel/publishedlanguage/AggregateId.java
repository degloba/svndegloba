package domain.canonicalmodel.publishedlanguage;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.Validate;

@SuppressWarnings("serial")
@Embeddable
public class AggregateId implements Serializable {
	
    private String aggregateId; 
    //private String idValue;
    
    public AggregateId(String aggregateId) {                
    	Validate.notNull(aggregateId);                
    	this.aggregateId = aggregateId;        
    	//this.idValue = idValue;
    	}        
    
    public AggregateId() {        
    		
    	}          
    
    public String getId() {
		return aggregateId;
	}
    
    public static AggregateId generate(){                
    		return new AggregateId(UUID.randomUUID().toString());        
    		}      
    
     
    @Override        
    public int hashCode() {                
    		return aggregateId.hashCode();        
    		}        
    	
    @Override        
    public boolean equals(Object obj) {                
    		if (this == obj)                        
    			return true;                
    		if (obj == null)                        
    			return false;                
    		if (getClass() != obj.getClass())                        
    			return false;                
    		AggregateId other = (AggregateId) obj;                
    		if (aggregateId == null) {                        
    			if (other.aggregateId != null)                                
    				return false;                
    			} else if (!aggregateId.equals(other.aggregateId))                        
    				return false;                
    		return true;        
    		}        
    
    @Override        
    public String toString() {                
    		return aggregateId;        
    }

	/*public String getIdValue() {
		return idValue;
	}*/



}
