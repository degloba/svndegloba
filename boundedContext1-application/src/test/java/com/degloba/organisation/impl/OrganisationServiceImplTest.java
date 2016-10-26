package com.degloba.organisation.impl;


import com.degloba.organisation.application.services.IOrganisationService;
import com.degloba.organisation.application.services.OrganisationApplicationImpl;
import com.degloba.organisation.impl.OrganisationServiceTest;


public class OrganisationServiceImplTest extends OrganisationServiceTest {

    @Override
    protected IOrganisationService createInstance() {
        return new OrganisationApplicationImpl(repository);
    }
 
    
}
