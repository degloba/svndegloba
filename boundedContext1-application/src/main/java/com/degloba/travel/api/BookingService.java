package com.degloba.travel.api;

import java.util.List;

import com.degloba.travel.Booking;
import com.degloba.travel.Hotel;
import com.degloba.travel.impl.SearchCriteria;
import com.degloba.travel.User;
import com.google.appengine.api.datastore.Key;



/**
 * A service interface for retrieving hotels and bookings from a backing repository. Also supports the ability to cancel
 * a booking.
 */
public interface BookingService {

    /**
     * Find bookings made by the given user
     * @param username the user's name
     * @return their bookings
     */
    public List<Booking> findBookings(String username);

    /**
     * Find hotels available for booking by some criteria.
     * @param criteria the search criteria
     * @param firstResult the index of the first result to return
     * @param sortBy the field to sort by
     * @param ascending true if the sorting should be in ascending order, false for descending
     * @return a list of hotels meeting the criteria
     */
    public List<Hotel> findHotels(SearchCriteria criteria, int firstResult, String sortBy, boolean ascending);
    
    public List<Hotel> findHotels(SearchCriteria criteria);

    /**
     * Find hotels by their identifier.
     * @param id the hotel id
     * @return the hotel
     */
    public Hotel findHotelById(Long id);

    /**
     * Create a new, transient hotel booking instance for the given user.
     * @param hotelId the hotelId
     * @param userName the user name
     * @return the new transient booking instance
     */
    public Booking createBooking(Long hotelId, String userName);

    /**
     * Persist the booking to the database
     * @param booking the booking
     */
    public void persistBooking(Booking booking);

    /**
     * Cancel an existing booking.
     * @param id the booking id
     */
    public void cancelBooking(Booking booking);
    
    public void cancelBooking(Long booking);

    /**
     * Return the total number of hotels for the given criteria.
     * @param criteria the criteria to use
     * @return the number of matching hotels
     */
    int getNumberOfHotels(SearchCriteria criteria);

    User findUser(String username);

	public User login(String usrname, String pw);
}
