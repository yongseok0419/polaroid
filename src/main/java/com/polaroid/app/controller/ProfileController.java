package com.polaroid.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.polaroid.app.profile.ProfileService;

@Controller
public class ProfileController {

	@Autowired
	@Qualifier("profileService")
	ProfileService profileService;
	
}
