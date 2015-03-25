package com.degloba.boundedContext.ui.webui.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.degloba.organisation.facade.PostDto;

/**
 * Created by yyang on 14-8-12.
 */
@Controller
@RequestMapping("/post")
public class PostController extends BaseController {

    @RequestMapping(value = "{postId}")
    public PostDto getPost(@PathVariable long postId) {
        return facade.getPost(postId);
    }
}
