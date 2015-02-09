package com.degloba.utils;

import java.util.UUID;

public class NewGuid {
	
	public String newGuid()
	{
	
		// Creating a random UUID (Universally unique identifier).        
		//        
		UUID uuid = UUID.randomUUID();   
		
		return uuid.toString();         
   
		}
	} 


