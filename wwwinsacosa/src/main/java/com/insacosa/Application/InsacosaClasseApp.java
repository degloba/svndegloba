package com.insacosa.Application;

import com.insacosa.Domini.IInsacosaClasseService;

public class InsacosaClasseApp implements IInsacosaClasseApp {
	
private IInsacosaClasseService ds;

public InsacosaClasseApp(IInsacosaClasseService ds) {
	super();
	this.ds = ds;
}



}
