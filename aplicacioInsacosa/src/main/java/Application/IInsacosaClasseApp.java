package Application;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;


public interface IInsacosaClasseApp {
	
	public void deleteByKey(Key key);
	public void update(Entity entitat);
	public void create(Entity entitat);

}
