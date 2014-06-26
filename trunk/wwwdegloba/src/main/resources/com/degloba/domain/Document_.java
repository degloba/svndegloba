package com.degloba.domain;

import com.google.appengine.api.datastore.Key;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-26T18:41:13.624+0200")
@StaticMetamodel(Document.class)
public class Document_ {
	public static volatile SingularAttribute<Document, Key> id;
	public static volatile SingularAttribute<Document, String> abrev;
	public static volatile SingularAttribute<Document, String> entorn;
	public static volatile SingularAttribute<Document, String> descripcio;
	public static volatile SingularAttribute<Document, String> tecnologia;
	public static volatile SingularAttribute<Document, Integer> ordre;
	public static volatile SingularAttribute<Document, Integer> idanterior;
	public static volatile SingularAttribute<Document, Boolean> esroot;
	public static volatile SingularAttribute<Document, Integer> idarbre;
}
