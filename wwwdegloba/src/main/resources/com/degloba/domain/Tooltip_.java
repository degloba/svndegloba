package com.degloba.domain;

import com.google.appengine.api.datastore.Key;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-26T18:41:13.661+0200")
@StaticMetamodel(Tooltip.class)
public class Tooltip_ {
	public static volatile SingularAttribute<Tooltip, Key> id;
	public static volatile SingularAttribute<Tooltip, Integer> tooltipid;
	public static volatile SingularAttribute<Tooltip, String> abrev;
	public static volatile SingularAttribute<Tooltip, String> per;
}
