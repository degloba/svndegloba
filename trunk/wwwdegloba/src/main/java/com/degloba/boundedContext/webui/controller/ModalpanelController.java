package com.degloba.boundedContext.webui.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.degloba.boundedContext.readmodel.modalpanel.ModalpanelDto;

/**
 * Created by yyang on 14-8-12.
 */
@Controller
@RequestMapping("/post")
public class ModalpanelController extends BaseController {

    @RequestMapping(value = "{modalpanelId}")
    public ModalpanelDto ModalpanelDto(@PathVariable long modalpanelId) {
        return facade.getPost(modalpanelId);
    }
}
