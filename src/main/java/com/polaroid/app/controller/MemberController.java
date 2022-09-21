package com.polaroid.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.polaroid.app.member.MemberService;

@Controller
public class MemberController {

		@Autowired
		@Qualifier("memberService")
		MemberService memberService;
		
}
