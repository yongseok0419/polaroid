package com.polaroid.app.member;

import java.util.List;
import java.util.Map;

import com.polaroid.app.command.MemberDto;
import com.polaroid.app.command.StateMemberDto;

public interface MemberService {
	
	//이메일 인증
	public int registerAuthentication(Map<String, Object> map);
	
	//이메일 인증번호 일치 여부
	public int checkAuthentication(Integer memberAuthentication);
	
	//이메일 중복 체크
	public int checkEmail(String memberEmail);
	
	//닉네임 중복 체크
	public int checkNick(String memberNick);
	
	//전화번호 중복 체크
	public int checkPhone(String memberPhone);
	
	//회원가입
	public int registerMember(MemberDto memberDto);
	
	//로그인
	public int findMember(String memberEmail, String memberPwd);
	
	//회원 상태 변경
	public int modifyStateMember(StateMemberDto stateMemberDto);
	
	//회원 상태 조회
	public List<MemberDto> retrieveStateMember(int memberStatuscode);
	
	//회원 탈퇴
	public int removeMember(int memberId);
	
	//회원 비밀번호 변경
	public int modifyPwd(MemberDto memberDto);
	
	//회원 비밀번호 변경폼
	public int modifyForm(String memberEmail);
	
	//비밀번호 변경 시 DB에 회원이 있는지 유무
	public int retrieveMemberEmail(String toAddress);
	
}
