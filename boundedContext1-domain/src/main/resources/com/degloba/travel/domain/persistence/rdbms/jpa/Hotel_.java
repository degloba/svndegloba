package com.degloba.travel.domain.persistence.rdbms.jpa;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-11-25T19:25:36.046+0100")
@StaticMetamodel(Hotel.class)
public class Hotel_ {
	public static volatile SingularAttribute<Hotel, Long> id;
	public static volatile SingularAttribute<Hotel, BigDecimal> price;
	public static volatile SetAttribute<Hotel, Booking> reservations;
	public static volatile SingularAttribute<Hotel, String> name;
	public static volatile SingularAttribute<Hotel, String> address;
	public static volatile SingularAttribute<Hotel, String> city;
	public static volatile SingularAttribute<Hotel, String> zip;
	public static volatile SingularAttribute<Hotel, String> state;
	public static volatile SingularAttribute<Hotel, String> country;
}
