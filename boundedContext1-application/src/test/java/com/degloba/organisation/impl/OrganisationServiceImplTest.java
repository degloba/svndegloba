package com.degloba.organisation.impl;

import com.degloba.organisation.application.api.OrganisationService;
import com.degloba.organisation.application.impl.OrganisationApplicationImpl;
import com.degloba.organisation.impl.OrganisationServiceTest;


public class OrganisationServiceImplTest extends OrganisationServiceTest {

    @Override
    protected OrganisationService createInstance() {
        return new OrganisationApplicationImpl(repository);
    }
 
    
}
