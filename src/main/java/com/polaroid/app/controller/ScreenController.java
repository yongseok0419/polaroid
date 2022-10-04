package com.polaroid.app.controller;

import java.util.HashMap;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.polaroid.app.command.MemberDto;
import com.polaroid.app.command.MemberProfileDto;
import com.polaroid.app.command.PostDetailDto;
import com.polaroid.app.command.PostDto;
import com.polaroid.app.post.PostService;
import com.polaroid.app.profile.ProfileService;


@Controller
public class ScreenController {

	@Autowired
	@Qualifier("postService")
	PostService postService;
	
	@Autowired
	@Qualifier("profileService")
	ProfileService profileService;
	
	//관리자 메인화면
	@GetMapping("adminIndex")
	public String adminIndex() {
		return "adminIndex";
	}
	
	//관리자 정지화면
	@GetMapping("adminStop")
	public String adminStop() {
		return "adminStop";
	}
	
	//팔로우/팔로워 리스트 화면
	@GetMapping("follow")
	public String follow() {
		return "follow";
	}	
	
	//비밀번호 찾기 화면
	@GetMapping("forgotPwd")
	public String forgotPwd() {
		return "forgotPwd";
	}
	
	//메인 화면
	@GetMapping("/index")
	public String index(Model model, HttpSession session, MemberProfileDto memberProfileDto) {
		
		//메인 화면 회원 정보 조회
		MemberDto member = (MemberDto)session.getAttribute("member");
		

		memberProfileDto.setMemberId(member.getMemberId());
				
		MemberProfileDto mpd = profileService.retrieveMemberList(memberProfileDto);
				
		model.addAttribute("mpd", mpd);
		
		//내 게시글 조회

		List <PostDto> list = postService.retrieveMyPostList(member.getMemberId());
		
		int postCount = postService.selectPostCount(member.getMemberId());
		
		model.addAttribute("posts", list);
		model.addAttribute("postCount", postCount);
		
		//프로필 존재 유무
		int cnt = profileService.isProfile(member.getMemberId());
		
		model.addAttribute("isProfile", cnt);
		
		return "/index";
	}
	

	
	//회원가입 화면
	@GetMapping("join")
	public String Join() {
		return "join";
	}
	
	@GetMapping("listAll")
	   public String listAll(Model model) {
	      List <PostDto> list = postService.retrievePostList();
	      model.addAttribute("posts", list);
	      return "/listAll";
	   }
	
	//팔로우 게시글 화면
	@GetMapping("listFollow")
	public String listFollow() {
		return "listFollow";
	}
	
	//좋아요 게시글 화면
	@GetMapping("listLike")
	public String listLike() {
		return "listLike";
	}
	
	//로그인 화면
	@GetMapping("login")
	public String Login() {
		return "login";
	}
	
	//비밀번호 수정 화면
	@GetMapping("modifyPwd")
	public String modifyPwd() {
		return "modifyPwd";
	}
	
	//프로필 등록 화면
	@GetMapping("registProfile")
	public String registProfile() {
		return "registProfile";
	}
	
	//업로드 화면
	@GetMapping("upload")
	public String upload() {
		return "upload";
	}
	@GetMapping(value="/posts/{post_id}")
	public @ResponseBody Map<String, Object> selectPostDetail(@PathVariable("post_id") int post_id) {
		System.out.println("post_id" + post_id);
		PostDetailDto post = postService.retrivePostDetail(post_id);
		//List<UploadDto> uploads = postService.retrivePostDetail(post_id);
		Map<String, Object> map = new HashMap<>();
		//model.addAttribute("post", post);
		map.put("post", post);
		return map;
	}
	
	
	
	//게시글 수정 
	@GetMapping("updateForm")
	public String updateForm(Model model, @RequestParam(value = "post_id", defaultValue = "13") int post_id) {
		//post_id = 30;
		
		PostDto postDto = postService.modifyPostDetail(post_id); //

		model.addAttribute("postDto", postDto);
		
		return "update";
	}
}
