package com.degloba.domain;

import com.google.appengine.api.datastore.Key;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-26T18:41:13.631+0200")
@StaticMetamodel(Framework.class)
public class Framework_ {
	public static volatile SingularAttribute<Framework, Key> id;
	public static volatile SingularAttribute<Framework, String> nom;
	public static volatile SingularAttribute<Framework, String> tecnologia;
	public static volatile SingularAttribute<Framework, String> icon;
	public static volatile SingularAttribute<Framework, String> descripcio;
	public static volatile SingularAttribute<Framework, String> url;
	public static volatile SingularAttribute<Framework, Key> idTipus;
	public static volatile SingularAttribute<Framework, TipusFramework> tipusframework;
}
