package com.degloba.hotels.webapp.webflux;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class HotelRepository {
    static Map<String,Hotel> hotelData;

    static Map<String,String> employeeAccessData;

    static
    {
    	hotelData = new HashMap<>();
    	hotelData.put("1",new Hotel("1","Employee 1"));
    	hotelData.put("2",new Hotel("2","Employee 2"));
    	hotelData.put("3",new Hotel("3","Employee 3"));
    	hotelData.put("4",new Hotel("4","Employee 4"));
    	hotelData.put("5",new Hotel("5","Employee 5"));
    	hotelData.put("6",new Hotel("6","Employee 6"));
    	hotelData.put("7",new Hotel("7","Employee 7"));
    	hotelData.put("8",new Hotel("8","Employee 8"));
    	hotelData.put("9",new Hotel("9","Employee 9"));
    	hotelData.put("10",new Hotel("10","Employee 10"));
        
        employeeAccessData=new HashMap<>();
        employeeAccessData.put("1", "Employee 1 Access Key");
        employeeAccessData.put("2", "Employee 2 Access Key");
        employeeAccessData.put("3", "Employee 3 Access Key");
        employeeAccessData.put("4", "Employee 4 Access Key");
        employeeAccessData.put("5", "Employee 5 Access Key");
        employeeAccessData.put("6", "Employee 6 Access Key");
        employeeAccessData.put("7", "Employee 7 Access Key");
        employeeAccessData.put("8", "Employee 8 Access Key");
        employeeAccessData.put("9", "Employee 9 Access Key");
        employeeAccessData.put("10", "Employee 10 Access Key");
    }
    
    public Mono<Hotel> findHotelById(String id)
    {
        return Mono.just(hotelData.get(id));
    }
    
    public Flux<Hotel> findAllHotels()
    {
        return Flux.fromIterable(hotelData.values());
    }
    
    public Mono<Hotel> updateEmployee(Hotel hotel)
    {
    	Hotel existingHotel=hotelData.get(hotel.getId());
        if(existingHotel!=null)
        {
        	existingHotel.setName(hotel.getName());
        }
        return Mono.just(existingHotel);
    }
}
