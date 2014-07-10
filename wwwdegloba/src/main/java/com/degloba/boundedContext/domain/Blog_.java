package com.degloba.boundedContext.domain;

import com.google.appengine.api.datastore.Key;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-08T18:28:07.283+0200")
@StaticMetamodel(Blog.class)
public class Blog_ {
	public static volatile SingularAttribute<Blog, Key> id;
	public static volatile SingularAttribute<Blog, String> titol;
	public static volatile SingularAttribute<Blog, String> descripcio;
}
