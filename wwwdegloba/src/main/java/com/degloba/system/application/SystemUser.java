package com.degloba.system.application;

import com.degloba.canonicalmodel.publishedlanguage.AggregateId;

public class SystemUser {   
	
	public AggregateId getDomainUserId(){                
	return new AggregateId("1");//TODO introduce security integration        
	}
}