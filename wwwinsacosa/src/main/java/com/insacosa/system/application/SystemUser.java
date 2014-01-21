package com.insacosa.system.application;

import com.insacosa.canonicalmodel.publishedlanguage.AggregateId;

public class SystemUser {   
	
	public AggregateId getDomainUserId(){                
	return new AggregateId("1");//TODO introduce security integration        
	}
}