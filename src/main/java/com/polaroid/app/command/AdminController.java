package com.polaroid.app.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.polaroid.app.admin.AdminService;

@Controller
public class AdminController {

	@Autowired
	@Qualifier("adminService")
	AdminService adminService;
	
	
	
}
