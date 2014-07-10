package com.degloba.boundedContext.domain;

import com.google.appengine.api.datastore.Key;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-08T18:28:07.319+0200")
@StaticMetamodel(TipusFramework.class)
public class TipusFramework_ {
	public static volatile SingularAttribute<TipusFramework, Key> id;
	public static volatile SingularAttribute<TipusFramework, String> nom;
	public static volatile CollectionAttribute<TipusFramework, Framework> frameworks;
}
