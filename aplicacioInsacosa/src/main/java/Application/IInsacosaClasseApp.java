package Application;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;


public interface IInsacosaClasseApp  {
	
	public void deleteClasseAppByKey(Key key);
	public <E> E getClasseAppByKey(Key key);
	
	public <E> void updateClasseApp(E entitat);
	public <E> void createClasseApp(E entitat);
	public <E> void saveClasseApp(E entitat);

}
