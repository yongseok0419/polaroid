package com.polaroid.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.polaroid.app.post.PostService;

@Controller
public class PostController {

	@Autowired
	@Qualifier("postService")
	PostService postService;
	
}
