package com.degloba.system.application;


public class SystemUser {   
	
	private Long clientId;

	SystemUser(Long clientId) {
		this.clientId = clientId;
	}

	/**
	 * 
	 * @return Domain model Client
	 */
	public Long getClientId(){
		return clientId;
	}

	public boolean isLoogedIn(){
		return clientId != null;
	}
}