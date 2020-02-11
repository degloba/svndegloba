package com.degloba.domain.messaging.persistence.nosql.impl.mongo.api.spring.FCM;

import org.springframework.data.annotation.Id; 
import org.springframework.data.mongodb.core.mapping.Document; 

/** 
 *
 * @category Entitat/Document (MongoDB) : GCM Token (Android devices) 
 * 
 * Firebase Cloud Message
 * 
 * @author degloba
 *
 **/ 
@Document public class FCMTokenRegister { 
	 @Id private String FCMTokenRegisterId; 
	 
	 private String token; 

	 
	 public FCMTokenRegister(String token) { 
		 this.token = token; 
		 } 
	 
	 public String getFCMTokenRegisterId() { 
		 return FCMTokenRegisterId; 
		 } 
		 
		 public void setFCMTokenRegisterId(final String FCMTokenRegisterId) { 
			 this.FCMTokenRegisterId = FCMTokenRegisterId; 
		 } 
		 
		 public String getToken() { 
			 return token; 
		 } 
		 
		 public void setToken(final String token) { 
			 this.token = token; 
		 } 
		 		 		
		 
		 } 