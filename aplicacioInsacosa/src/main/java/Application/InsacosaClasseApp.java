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


public void deleteClasseAppByKey(Key key)
{
	
}

public <E> void updateClasseApp(E entitat) {
	// TODO Auto-generated method stub
	
}

public <E> void createClasseApp(E entitat) {
	// TODO Auto-generated method stub
	
}

public <E> void saveClasseApp(E entitat) {
	// TODO Auto-generated method stub	
}

public <E> E getClasseAppByKey(Key key) {
	return null;
	// TODO Auto-generated method stub
	
}



}
