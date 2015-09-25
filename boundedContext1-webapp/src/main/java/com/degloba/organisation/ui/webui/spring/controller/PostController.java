package com.degloba.organisation.ui.webui.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.degloba.organisation.domain.Post;
import com.degloba.organisation.facade.PostDto;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


@Controller
@RequestMapping("/post")
public class PostController extends BaseController {

    @RequestMapping(value = "{postId}")
    public PostDto getPost(@PathVariable long postId) {
    	    	   	
    	return facade.getPost(KeyFactory.createKey(Post.class.getSimpleName(), "1"));
    }
}
