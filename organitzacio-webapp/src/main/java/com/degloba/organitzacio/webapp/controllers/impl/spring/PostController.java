package com.degloba.organitzacio.webapp.controllers.impl.spring;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Post;
import com.degloba.organitzacio.facade.dtos.PostDto;



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
    	PostDto postDto = new PostDto();
    	
    	facade.setPost(postDto);
    }
}
