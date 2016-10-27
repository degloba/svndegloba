package com.degloba.organisation.ui.webui.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.degloba.organisation.domain.persistence.rdbms.jpa.Post;
import com.degloba.organisation.facade.PostDto;



@Controller
@RequestMapping("/post")
public class PostController extends BaseOrganisationController {

    @RequestMapping(value = "getpost/{postId}")
    public PostDto getPost(@PathVariable long postId) {
    	    	   	
    	/////////return facade.getPost(KeyFactory.createKey("Post",postId));
    	return null;
    	
    }
    
    @RequestMapping(value = "setpost/{postId}")
    public void setPost(@PathVariable long postId) {
    	Post post = new Post();
    	
    	facade.setPost(post);
    }
}
