package com.polaroid.app.member;

import java.util.List;

import com.polaroid.app.command.MemberDto;
import com.polaroid.app.command.StateMemberDto;

public interface MemberService {
	
	//회원가입
	public int registerMember(MemberDto memberDto);
	
	//이메일 중복 검사
	public int checkEmail(String member_email);
	
	//닉네임 중복 검사
	public int checkNick(String member_nick);
	
	//로그인
	public int findMember(String member_email, String member_pwd);
	
	//회원 상태 변경
	public int modifyStateMember(StateMemberDto stateMemberDto);
	
	//회원 상태 조회
	public List<MemberDto> retrieveStateMember(int member_statuscode);
	
	//회원 탈퇴
	public int removeMember(int member_id);
	
	
}
