package com.degloba.organisation.impl;


import com.degloba.missatgeria.application.services.INotificationService;
import com.degloba.missatgeria.application.services.NotificationServiceimpl;
import com.degloba.organisation.impl.OrganisationServiceTest;


public class OrganisationServiceImplTest extends OrganisationServiceTest {

    @Override
    protected INotificationService createInstance() {
        return new NotificationServiceimpl(repository);
    }
 
    
}
