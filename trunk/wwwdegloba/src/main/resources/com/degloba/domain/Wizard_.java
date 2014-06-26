package com.degloba.domain;

import com.google.appengine.api.datastore.Key;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-26T18:41:13.668+0200")
@StaticMetamodel(Wizard.class)
public class Wizard_ {
	public static volatile SingularAttribute<Wizard, Key> id;
	public static volatile SingularAttribute<Wizard, Integer> wizardid;
	public static volatile SingularAttribute<Wizard, Integer> idanterior;
	public static volatile SingularAttribute<Wizard, String> abrev;
	public static volatile SingularAttribute<Wizard, String> jsp;
	public static volatile SingularAttribute<Wizard, String> jspgrafic;
	public static volatile SingularAttribute<Wizard, Boolean> esroot;
	public static volatile SingularAttribute<Wizard, Integer> idarbre;
	public static volatile SingularAttribute<Wizard, String> descripcio;
	public static volatile SingularAttribute<Wizard, Integer> idw;
}
