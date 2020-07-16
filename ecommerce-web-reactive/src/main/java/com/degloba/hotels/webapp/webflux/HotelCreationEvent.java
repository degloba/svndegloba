package com.degloba.hotels.webapp.webflux;

/**
 * https://github.com/eugenp/tutorials/blob/master/spring-5-reactive-security/src/main/java/com/baeldung/webflux/EmployeeCreationEvent.java 
 */
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class HotelCreationEvent {
    private String hotelId;
    private String creationTime;
    
    public HotelCreationEvent(String hotelId, String creationTime) {
        super();
        this.hotelId = hotelId;
        this.creationTime = creationTime;
    }
}
