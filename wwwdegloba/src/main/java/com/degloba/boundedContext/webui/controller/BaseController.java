package com.degloba.boundedContext.webui.controller;



import javax.inject.Inject;

import com.degloba.boundedContext.readmodel.modalpanel.ModalpanelFacade;

/**
 * Created by yyang on 14-8-12.
 */
public class BaseController {

    @Inject
    protected ModalpanelFacade facade;
}
