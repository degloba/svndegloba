package com.degloba.travel.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.degloba.travel.application.SearchCriteria;

import com.degloba.travel.application.services.ITravelService;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Reserves;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Hotel;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Hotels;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Usuari;

// Spring
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Provides RESTful endpoint for accessing this application's booking functionality.
 * <p/>
 * This can be used from zimbra, from Android, etc.
 * <p/>
 * todo support paging
 * <p/>
 * todo how can we secure this for remote clients?
 *
 * @author Josh Long
 */
@Controller
@RequestMapping(value = "/ws/", headers = HotelsRestController.acceptHeader)
public class HotelsRestController {

	static final String acceptHeader = "Accept=application/json, application/xml";

	@Autowired
	private ITravelService bookingService;


	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Usuari usuari( @PathVariable("id") String userId) {
		return this.bookingService.findUser(userId);
	}

	@RequestMapping(value = "/bookings/{user}", method = RequestMethod.GET)
	@ResponseBody
	public Reserves bookingsForUser( @PathVariable("user") String user) {
		return new Reserves(this.bookingService.findBookings(user));
	}

	//http://localhost:8080/ws/hotel/1
	@RequestMapping(value = "/hotel/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Hotel hotel(@PathVariable("id") long id) {
		return this.bookingService.findHotelById(id);
	}

	// todo whats the right way to handle this? currently its being handled using SPring Webflow on the web tier, the passwords are in the config. we need to make the config share the database jst like th rest of the service code, then make it so that REST clients can login as well.
	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	@ResponseBody
	public Usuari login(@RequestBody Usuari u ) {

		String usrname = u.getUsername();
		String pw = u.getPassword();
		return bookingService.login(usrname, pw);
	}

	//	http://localhost:8080/ws/bookings/josh/327680
	@RequestMapping(value = "/bookings/{user}/{bookingId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void cancelWithDelete(@PathVariable("user") String user,
															 @PathVariable("bookingId") Long bookingId) {

		bookingService.cancelBooking(bookingId);
	}

	// todo need to figure out how to setup the HttpHiddenMethod filter to work for regular AJAX clients
	// so that we can simply send the HTTP POST url to a DELETE method with an additional param in the JSON body or something
	@RequestMapping(value = "/bookings/delete/{user}/{bookingId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void cancelWithPost(@PathVariable("user") String user,
														 @PathVariable("bookingId") Long bookingId) {

		bookingService.cancelBooking(bookingId);
	}

	//http://localhost:8080/ws/hotels/search/?q=hilton&price=2000
	@RequestMapping(value = "/hotels/search/", method = RequestMethod.GET)
	@ResponseBody
	public Hotels search(@RequestParam("q") String query, @RequestParam("price") double maxPrice) {
		SearchCriteria searchCriteria = new SearchCriteria();
		///////////////searchCriteria.setMaximumPrice(maxPrice);
		searchCriteria.setSearchString(query);
		return new Hotels(bookingService.findHotels(searchCriteria));
	}

	/*
		POST:
		XML
		<booking total="700.00" smoking="false" nights="2"  creditCardName="Josh long" creditCardExpiryYear="5"
			creditCardExpiryMonth="1" creditCard="2422242224222422" checkoutDate="2011-09-31T00:00:00-07:00" checkinDate="2011-09-29T00:00:00-07:00" beds="1">
	<amenities/>
	<hotel  id="24" />
	<user username="josh"  />
	</booking>
	 or
	JSON:  /bookings/josh (specify content-type=application/json)
	{
			"user":{ "username":"josh" },
			"checkinDate":1333004400000,
			"hotel":{  "id":24 },
			"checkoutDate":1329980400000,
			"creditCard":"2422242224222422",
			"smoking":false,
			"beds":1,
			"creditCardName":"Josh long",
			"creditCardExpiryMonth":1,
			"creditCardExpiryYear":5
	}
		* */
	@RequestMapping(value = "/bookings/{user}", method = RequestMethod.POST)
	@ResponseBody
	public Reserva create(@RequestBody Reserva reserva) {
		String usr = reserva.getUser().getUsername();
		long hotelId = reserva.getHotel().getId();

		Reserva b = bookingService.createBooking(hotelId, usr);
		b.setCheckoutDate(reserva.getCheckoutDate());
		b.setCheckinDate(reserva.getCheckinDate());
		b.setAmenities(reserva.getAmenities());
		b.setBeds(reserva.getBeds());
		b.setSmoking(reserva.isSmoking());
		b.setCreditCard(reserva.getCreditCard());
		b.setCreditCardExpiryMonth(reserva.getCreditCardExpiryMonth());
		b.setCreditCardName(reserva.getCreditCardName());
		b.setCreditCardExpiryYear(reserva.getCreditCardExpiryYear());

		bookingService.persistBooking(b);

		return b;
	}
}
