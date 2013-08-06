package com.insacosa.application.services;


import ddd.domain.BaseEntity;

public abstract class BaseApplicationService {
	
/*	private CommonService commonService;
    
    private void setCommonService(CommonService commonService){
         this.commonService = commonService;
    }*/
	
	public Boolean a;
	
	
	// OBSERVACIO : String guid
	// 	Identifica una entitat del Domini, i no està lligada
	//  a cap tecnologia/infraestructura que hi hagi per sota (ex. Google/Key, SQLServer/Guid,...)
	//  En el cas que l'entitat del Domini estigui associat al DataStore de Google, el "guid" serà la Key
	//  Per a qualsevol altre dataSource diferent al DataStore de Google (ex. : SQLServer, fitxer Xml,...)
	//  el guid serà la "clau" que identifiqui de forma unica l'entitat dins del dataSource
	
	public void deleteClasseAppByGuid(String guid) {
	}
	
	public BaseEntity getClasseAppByGuid(String guid) {
		return null;
		
	}
	
	public void createClasseApp() {
		
	}


}
