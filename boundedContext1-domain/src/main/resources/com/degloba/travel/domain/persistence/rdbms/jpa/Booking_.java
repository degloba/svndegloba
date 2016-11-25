package com.degloba.travel.domain.persistence.rdbms.jpa;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-11-25T19:25:36.044+0100")
@StaticMetamodel(Booking.class)
public class Booking_ {
	public static volatile SingularAttribute<Booking, Long> id;
	public static volatile SingularAttribute<Booking, Date> checkinDate;
	public static volatile SingularAttribute<Booking, Hotel> hotel;
	public static volatile SingularAttribute<Booking, User> user;
	public static volatile SingularAttribute<Booking, Date> checkoutDate;
	public static volatile SingularAttribute<Booking, String> creditCard;
	public static volatile SingularAttribute<Booking, Boolean> smoking;
	public static volatile SingularAttribute<Booking, Integer> beds;
	public static volatile SingularAttribute<Booking, String> creditCardName;
	public static volatile SingularAttribute<Booking, Integer> creditCardExpiryMonth;
	public static volatile SingularAttribute<Booking, Integer> creditCardExpiryYear;
}
