package com.degloba.travel.domain.persistence.rdbms.jpa;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-11-25T19:25:36.047+0100")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Boolean> enabled;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, String> password;
}
