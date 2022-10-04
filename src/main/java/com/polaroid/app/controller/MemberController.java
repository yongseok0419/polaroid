package com.polaroid.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.polaroid.app.command.MemberDto;
import com.polaroid.app.command.SendMailHelper;
import com.polaroid.app.member.MemberService;

@Controller
public class MemberController {

		@Autowired
		@Qualifier("memberService")
		MemberService memberService;
		
		@Autowired
		SendMailHelper sendMailHelper;
		
		@Value("${spring.mail.username}")
		private String fromAddress;
		
		//이메일 인증
		@ResponseBody
		@PostMapping("/authForm")
		public String authForm(@RequestBody MemberDto memberDto) throws Exception {
			//인증번호 난수 생성
			Random random = new Random();
			
			// 111111~ 999999 범위의 숫자를 얻기 위해서 nextInt(888888) + 111111를 사용
			int authCode = random.nextInt(888888) + 111111;
			
			//이메일 인증
			System.out.println("fromaddress : " + fromAddress);
			
			HashMap<String, Object> map = new HashMap<String, Object>(); 
			map.put("authCode", authCode); 			
			String body = "인증번호 : " + authCode;
			
			String[] toAddress = {memberDto.getMemberEmail()};
			System.out.println("toAddress : " + toAddress);
			String subject = "polaroid에서 요청하신 인증번호를 발송해드립니다";
			sendMailHelper.sendMail(fromAddress, toAddress, subject, body);
			
			//DB에 인증번호 저장 (이메일, 인증번호)
			Map<String, Object> map1 = new HashMap<>();
			map1.put("authEmail", memberDto.getMemberEmail());
			map1.put("authCode", authCode);		
			
			int cnt = memberService.registerAuthentication(map1);
			System.out.println("cnt : " + cnt);
			
			return String.valueOf(cnt);
		}
		
		// 비밀번호 변경 링크 메일전송
        @ResponseBody
        @PostMapping("/forgotPwd")
        public String forgotPwd(@RequestBody Map<String, String> map) throws Exception {

           String memberEmail =  map.get("memberEmail");
           
           int cnt = memberService.retrieveMemberEmail(memberEmail);

           if (cnt == 1) {

              String body = "<a href='http://localhost:8282/modifyPwdForm?memberEmail=" + memberEmail + "'>비밀번호 변경 링크</a>";
              String[] toAddress = { memberEmail };
              String subject = "더욱 간편하게 polaroid에 다시 로그인해보세요";

              sendMailHelper.sendMail(fromAddress, toAddress, subject, body);
              return "1";

           } else {
              return "0";
           }
        }
        
    	// 비밀번호 변경 화면
        @GetMapping("/modifyPwdForm")
        public String modifyPwdForm(@RequestParam(value = "memberEmail") String memberEmail) throws Exception {

           return "/modifyPwd";
        }
        
        // 비밀번호 변경
        @ResponseBody
        @PostMapping("/changePwd")
        public String changePwd(@RequestBody HashMap<String, Object> map) throws Exception {
           
           String memberPwd  = (String)map.get("memberPwd");      
           String memberEmail = ((ArrayList<String>)map.get("memberEmail")).get(0);   
           
           System.out.println("memberEmail : " + memberEmail);
           System.out.println("memberPwd : " + memberPwd);
           
           MemberDto memberDto = new MemberDto();
           memberDto.setMemberEmail(memberEmail);
           memberDto.setMemberPwd(memberPwd);
           
           int result = memberService.modifyPwd(memberDto);

           if (result == 1) {
              
              return "1";
           } else {
              return "0";
           }
        }

		//인증번호 일치 여부
		@ResponseBody
		@PostMapping("/checkAuthForm")
		public String joinForm(@RequestBody Map<String, Integer> map) throws Exception {
			
			//DB에 있는 인증번호 일치 여부
			int cnt = memberService.checkAuthentication(map.get("memberAuthentication"));
			
			return String.valueOf(cnt);
		}
		
		//이메일 중복 체크
		@ResponseBody
		@PostMapping("/checkEmail")
		public String checkEmail(@RequestBody Map<String, String> map) throws Exception {
			
			int cnt = memberService.checkEmail(map.get("memberEmail"));
			
			return String.valueOf(cnt);
		}
		
		//닉네임 중복 체크
		@ResponseBody
		@PostMapping("/checkNick")
		public String checkNick(@RequestBody Map<String, String> map) throws Exception {
					
			int cnt = memberService.checkNick(map.get("memberNick"));
					
			return String.valueOf(cnt);
		}
				
		//전화번호 중복 체크
		@ResponseBody
		@PostMapping("/checkPhone")
		public String checkPhone(@RequestBody Map<String, String> map) throws Exception {
					
			int cnt = memberService.checkPhone(map.get("memberPhone"));
					
			return String.valueOf(cnt);
		}		
		
		//회원가입
		@PostMapping("/joinForm")
		public String joinForm(MemberDto memberDto) throws Exception {
			
			memberService.registerMember(memberDto);
			
			return "redirect:/login";
		}
		
		//로그인
		@ResponseBody
		@PostMapping("/loginForm")
		public String loginForm(@RequestBody MemberDto memberDto, HttpSession session) {

			MemberDto login = memberService.findMember(memberDto);
			
			if(login == null) {
				return "0";
			} else if(login.getMemberStatusCode().equals("1")) {	//탈퇴한 회원인 경우
				return "2";
		    } else if(memberDto.getMemberEmail().startsWith("admin")){
		    	session.setAttribute("member", login);
		    	session.setMaxInactiveInterval(30*60);
				return "3";
		    } else {
				session.setAttribute("member", login);
				session.setMaxInactiveInterval(30*60);
				return "1";
			}
			
		}
		
		//로그아웃
		@GetMapping("/logoutForm")
		public String logoutForm(HttpSession session) {
			
			session.invalidate();
			
			return "redirect:/login";
		}
		
		//회원탈퇴
		@ResponseBody
		@PostMapping("/withdrawalForm")
		public String withdrawal(@RequestBody MemberDto memberDto) {
			
			//비밀번호 체크해서 맞으면 회원탈퇴
			int cnt = memberService.modifyMember(memberDto);
			System.out.println("cnt : " + cnt);
			
			return String.valueOf(cnt);
		}
		
}
