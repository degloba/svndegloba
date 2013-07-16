package Application;

import domini.IInsacosaClasseService;

public class InsacosaClasseApp implements IInsacosaClasseApp {
	
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



}
