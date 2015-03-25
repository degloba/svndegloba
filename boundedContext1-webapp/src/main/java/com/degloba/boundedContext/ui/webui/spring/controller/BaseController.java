package com.degloba.boundedContext.ui.webui.spring.controller;


import javax.inject.Inject;

import com.degloba.organisation.facade.OrganisationFacade;

/**
 * Created by yyang on 14-8-12.
 */
public class BaseController {

    @Inject
    protected OrganisationFacade facade;
}