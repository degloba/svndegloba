package com.degloba.usuaris.domain;

import org.springframework.data.annotation.Id; 
import org.springframework.data.mongodb.core.mapping.Document; 

/** 
 * * A simple POJO representing a GCM Token (Android devices) 
 * **/ 
@Document public class GCMTokenRegister { 
	 @Id private String GCMTokenRegisterId; 
	 
	 private String token; 

	 
	 public GCMTokenRegister(String token) { 
		 this.token = token; 
		 } 
	 
	 public String getGCMTokenRegisterId() { 
		 return GCMTokenRegisterId; 
		 } 
		 
		 public void setGCMTokenRegisterId(final String GCMTokenRegisterId) { 
			 this.GCMTokenRegisterId = GCMTokenRegisterId; 
		 } 
		 
		 public String getToken() { 
			 return token; 
		 } 
		 
		 public void setToken(final String token) { 
			 this.token = token; 
		 } 
		 		 		
		 
		 } 