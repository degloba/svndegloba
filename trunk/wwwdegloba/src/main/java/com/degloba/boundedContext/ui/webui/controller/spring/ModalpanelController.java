package com.degloba.boundedContext.ui.webui.controller.spring;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.degloba.boundedContext.ui.webui.controller.BaseController;

/**
 * Created by yyang on 14-8-12.
 */
@Controller
@RequestMapping("/post")
public class ModalpanelController extends BaseController {

/*    @RequestMapping(value = "{modalpanelId}")
    public ModalpanelDto ModalpanelDto(@PathVariable long modalpanelId) {
        return facade.getPost(modalpanelId);
    }*/
}
