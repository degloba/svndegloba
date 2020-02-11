package com.degloba.ecommerce.system.application;

import java.util.UUID;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class SystemUser {

	public Key getDomainUserId(){
		return KeyFactory.stringToKey( UUID.randomUUID().toString());
		//return new Key("1");//TODO introduce security integration
	}
}
