package com.polaroid.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.polaroid.app.command.MemberDto;
import com.polaroid.app.command.ProfileDto;
import com.polaroid.app.profile.ProfileService;

@Controller
public class ProfileController {

	@Autowired
	@Qualifier("profileService")
	ProfileService profileService;
	
	@PostMapping("/profileForm")
	public String profileForm(ProfileDto profileDto, MemberDto memberDto,
									 @RequestParam("upload") MultipartFile upload,
									 Model model, HttpSession session){
		System.out.println("call : " + profileDto);
		
		System.out.println(upload.getContentType());
		
		//이미지 파일 검증
			if(upload.getContentType().contains("image") == false) {		//이미지가 아닌경우
				//다시 등록화면으로
				model.addAttribute("profileDto", profileDto);
				model.addAttribute("valid_files", "이미지형식만 등록가능합니다.");
				return "/profile";
			}
		
		//프로필 등록(수정)
		
		MemberDto member = (MemberDto)session.getAttribute("member");
		System.out.println(member.toString());
		
		profileDto.setMemberId(member.getMemberId());
		
		
		boolean result = profileService.modifyProfile(profileDto, memberDto, upload);
		
		return "redirect:/index";
	}
	
}
