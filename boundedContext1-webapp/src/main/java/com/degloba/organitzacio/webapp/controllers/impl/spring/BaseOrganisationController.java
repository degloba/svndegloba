package com.degloba.organitzacio.webapp.controllers.impl.spring;


import javax.inject.Inject;

import com.degloba.organitzacio.facade.ui.IOrganitzacioFacade;


public class BaseOrganisationController {

    @Inject
    protected IOrganitzacioFacade facade;
}