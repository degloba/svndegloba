package com.degloba.webapp.organitzacio.controllers.impl.spring;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.degloba.infrastructure.facade.impl.organisation.PostDto;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Post;



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
