package Application;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

import domini.IInsacosaClasseService;


public class InsacosaClasseApp 
		implements IInsacosaClasseApp  {
	
protected IInsacosaClasseService ds;

public InsacosaClasseApp(IInsacosaClasseService ds) {
	super();
	this.ds = ds;
}

public IInsacosaClasseService getDs() {
	return ds;
}

public void setDs(IInsacosaClasseService ds) {
	this.ds = ds;
}


public void deleteByKey(Key key)
{
	
}

public void update(com.google.appengine.api.datastore.Entity entitat) {
	// TODO Auto-generated method stub
	
}

public void create(com.google.appengine.api.datastore.Entity entitat) {
	// TODO Auto-generated method stub
	
}





}
