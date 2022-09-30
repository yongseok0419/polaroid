package com.polaroid.app.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.polaroid.app.command.MemberDto;
import com.polaroid.app.command.PostDetailDto;
import com.polaroid.app.command.PostDto;
import com.polaroid.app.command.PostListDto;
import com.polaroid.app.post.PostService;

@Controller
public class PostController {
	
	@Autowired
	@Qualifier("postService")
	PostService postService;
	
	// 전체 게시글 조회
	@GetMapping("postListAll")
	public String postListAll() {
		return "/listAll";
	}
		
	@GetMapping("postLikeList")
	public String postLikeList(Model model) {
		List <PostListDto> list = postService.retrieveLikePostList();
		model.addAttribute("list", list);
		return "/listLike";
		
	}
	
	@GetMapping("postUpload")
	//@RequestMapping("/upload")
	public String postUpload() {
		return "/upload";
	}
	

	@PostMapping("/registerPost")
	public String registerPost(@Valid PostDto postDto, Errors errors, Model model,
			@RequestParam("file") List<MultipartFile> uploadFiles, HttpSession session) {

		if (errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			for (FieldError err : list) {
				if (err.isBindingFailure()) {
					model.addAttribute("valid" + err.getField(), "빈칸이 있습니다.");
				} else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			model.addAttribute("postDto", postDto);
			// model.addAttribute("uploadFiles", uploadFiles);
			return "/upload";
		}

		// 공백데이터 제거
		uploadFiles = uploadFiles.stream().filter((f) -> !f.isEmpty()).collect(Collectors.toList());
		// 이미지 파일검증
		for (MultipartFile f : uploadFiles) {
			if (f.getContentType().contains("image") == false) { // 이미지가 아닌경우
				// 다시 등록화면으로
				model.addAttribute("vo", postDto);
				model.addAttribute("valid_files", "이미지형식만 등록가능합니다");
				return "/upload";
			}
		}

		// 게시글 등록
		MemberDto member= (MemberDto)session.getAttribute("member");
		int member_id = member.getMemberId();
		postDto.setMember_id(member_id);
		
		boolean result = postService.registerPost(postDto, uploadFiles); // 상품데이터, 이미지데이터

		// model.addAttribute("vo", postDto);
		// postService.registerPost(postDto,uploadFiles);
		return "redirect:/index";

	}
	
	//게시글 상세조회
	@GetMapping("/posts/{post_id}")
	public @ResponseBody Map<String, Object> PostDetail(@PathVariable("post_id") int post_id) {
		
		PostDetailDto post = postService.retrivePostDetail(post_id);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("post", post);
		
		return map;
	}
	
	
	
	//게시글 수정
//	@PostMapping("/updatePost")
//	public String updatePost (PostDto postDto, RedirectAttributes RA) {
//		boolean result = postService.modifyPost(postDto);
//		//메시지처리(리다이렉트 시 1회성 메시지를 보내는 방법)
//		if(result) {
//			RA.addFlashAttribute("msg", "정상 수정 되었습니다");
//		} else {
//			RA.addFlashAttribute("msg", "정보 수정에 실패했습니다");
//		}
//
//		return "redirect:/index";
//	}
	
	
	
	   //게시글 수정
	   @PostMapping("/updatePost")
	   public String updatePost(@Valid PostDto postDto, Errors errors, Model model,
	         @RequestParam("file") List<MultipartFile> uploadFiles, HttpSession session) {

	      if (errors.hasErrors()) {
	         List<FieldError> list = errors.getFieldErrors();
	         for (FieldError err : list) {
	            if (err.isBindingFailure()) {
	               model.addAttribute("valid" + err.getField(), "빈칸이 있습니다.");
	            } else {
	               model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
	            }
	         }
	         model.addAttribute("postDto", postDto);
	         // model.addAttribute("uploadFiles", uploadFiles);
	         return "/update";
	      }
	      
	      //System.out.println("uploadFiles size1 : " + uploadFiles.size());
	      //System.out.println("uploadFiles empty : " + uploadFiles.get(0).isEmpty());
	      
	      // 공백데이터 제거
	      uploadFiles = uploadFiles.stream().filter((f) -> !f.isEmpty()).collect(Collectors.toList());
	      
	      if (uploadFiles.size() != 0) {
	         //기존 업로드 이미지 제거
	         //새로운 업로드 이미지 
	         MemberDto member = (MemberDto) session.getAttribute("member");
	         int member_id = member.getMemberId();
	         postDto.setMember_id(member_id);
	         boolean result = postService.modifyPost(postDto, uploadFiles); // 게시글 데이터, 이미지데이터
	         
	      } 
	      
	      
	      // 이미지 파일검증
//	      for (MultipartFile f : uploadFiles) {
//	         if (f.getContentType().contains("image") == false) { // 이미지가 아닌경우
//	            // 다시 등록화면으로
//	            model.addAttribute("vo", postDto);
//	            model.addAttribute("valid_files", "이미지형식만 등록가능합니다");
//	            return "/update";
//	         }
//	      }

//	      //메시지처리(리다이렉트 시 1회성 메시지를 보내는 방법)
//	            if(result) {
//	               RA.addFlashAttribute("msg", "정상 수정 되었습니다");
//	            } else {
//	               RA.addFlashAttribute("msg", "정보 수정에 실패했습니다");
//	            }
//	            

	      
	      

	      return "redirect:/index";
	   }
	
	
	
	
	//게시글 삭제
	@PostMapping("/deletePost")
	public String deletePost(@RequestParam("post_id") int post_id,
							 RedirectAttributes RA) {
		boolean result = postService.removePost(post_id);
		
		if(result) {
			RA.addFlashAttribute("msg", "삭제 성공하였습니다");
		} else {
			RA.addFlashAttribute("msg", "삭제 실패하였습니다");
		}
		
		return "redirect:/index";
	}
	

}