package com.degloba.organisation.ui.webui.spring.controller;


import javax.inject.Inject;

import com.degloba.organisation.facade.OrganisationFacade;


public class BaseOrganisationController {

    @Inject
    protected OrganisationFacade facade;
}