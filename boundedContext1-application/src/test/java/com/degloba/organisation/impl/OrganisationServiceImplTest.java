package com.degloba.organisation.impl;


import com.degloba.organisation.application.services.IOrganitzacioService;
import com.degloba.organisation.application.services.OrganisationApplicationImpl;
import com.degloba.organisation.impl.OrganisationServiceTest;


public class OrganisationServiceImplTest extends OrganisationServiceTest {

    @Override
    protected IOrganitzacioService createInstance() {
        return new OrganisationApplicationImpl(repository);
    }
 
    
}
