package com.insacosa.system.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SystemContext {      
	
	@Bean        
	@Scope("prototype")        
	public SystemUser getSystemUser(){                
		return new SystemUser();//TODO introduce security integration        
		}
}

