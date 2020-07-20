package com.degloba.hotels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hotel {
	
    private String id;
    private String nom;
    private String email;
    private String contactNumber;
    private String tipusHotel;
    
}
