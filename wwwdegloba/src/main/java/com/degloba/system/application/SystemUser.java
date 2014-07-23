package com.degloba.system.application;

import domain.canonicalmodel.publishedlanguage.AggregateId;


public class SystemUser {   
	
	public AggregateId getDomainUserId(){                
	return new AggregateId("1");//TODO introduce security integration        
	}
}