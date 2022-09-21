package com.polaroid.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.polaroid.app.reply.ReplyService;

@Controller
public class ReplyController {

	@Autowired
	@Qualifier("replyService")
	ReplyService replyService;
	
}
