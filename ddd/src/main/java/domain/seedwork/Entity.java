package domain.seedwork;

import java.io.Serializable;
import java.util.UUID;

/*
 * NOTA : tal i com està fet a Althaia
 */
public abstract class Entity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer _requestedHashCode;
	
	private Long _id;		
	//private UUID _id;
	
    public  Boolean IsTransient() {
    	return this._id == null;
    }
    
    
    public int GetHashCode()
	{
    	if (!IsTransient())
			{
				if (_requestedHashCode !=  null)
					_requestedHashCode = this.getId().hashCode() ^ 31;
				return _requestedHashCode;
			}
		else
			return super.hashCode();
	}
    
    
    public boolean equals(Object obj) {
    	
    	if (obj == null || (!(obj instanceof Entity)))
    		return false;
    	
    	Entity item = (Entity)obj;
    	
    	if (item.IsTransient() || this.IsTransient())
    		return false;
    	else
    		return item.getId()==this.getId();
        
    }


// getters - setters

	public Long getId() {
		return _id;
	}


	public void setId(Long _id) {
		this._id = _id;
	}


	public boolean existed() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean notExisted() {
		// TODO Auto-generated method stub
		return false;
	}

   
    
}
