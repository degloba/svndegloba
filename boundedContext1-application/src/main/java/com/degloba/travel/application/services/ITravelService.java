package com.degloba.travel.application.services;

import com.degloba.travel.application.SearchCriteria;
import com.degloba.travel.domain.persistence.rdbms.jpa.Booking;
import com.degloba.travel.domain.persistence.rdbms.jpa.Hotel;
import com.degloba.travel.domain.persistence.rdbms.jpa.User;


import java.util.List;

/**
 * A service interface for retrieving hotels and bookings from a backing repository. Also supports the ability to cancel
 * a booking.
 */
public interface ITravelService {


    /**
     * @param username the user name
     * @return the user
     */
    User findUser(String username);

    User login(String u, String pw);

    /**
     * Find bookings made by the given user
     *
     * @param username the user's name
     * @return their bookings
     */
    List<Booking> findBookings(String username);

    Booking findBookingById(Long id);

    /**
     * Find hotels available for booking by some criteria.
     *
     * @param criteria the search criteria
     * @return a list of hotels meeting the criteria
     */
    List<Hotel> findHotels(SearchCriteria criteria);

    /**
     * Find hotels by their identifier.
     *
     * @param id the hotel id
     * @return the hotel
     */
    Hotel findHotelById(Long id);

    /**
     * Create a new, transient hotel booking instance for the given user.
     *
     * @param hotelId  the hotelId
     * @param userName the user name
     * @return the new transient booking instance
     */
    Booking createBooking(Long hotelId, String userName);

    /**
     * Cancel an existing booking.
     *
     * @param id the booking id
     */
    void cancelBooking(Long id);

    /**
     * persists the detached booking object
     *
     * @param bo booking to persist
     */
    void persistBooking(Booking bo);
}

