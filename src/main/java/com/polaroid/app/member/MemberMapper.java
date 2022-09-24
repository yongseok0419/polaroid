package com.polaroid.app.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.polaroid.app.command.LoginDto;
import com.polaroid.app.command.MemberDto;

@Mapper
public interface MemberMapper {
	
	//이메일 인증
	public int insertAuthentication(Map<String, Object> map);
	
	//이메일 인증번호 일치여부
	public int selectAuthentication(Integer memberAuthentication);
	
	//이메일 중복 체크
	public int selectEmail(String memberEmail);
	
	//닉네임 중복 체크
	public int selectNick(String memberNick);
	
	//전화번호 중복 체크
	public int selectPhone(String memberPhone);
	
	//회원가입
	public int insertMember(MemberDto memberDto);
	
	//로그인
	public MemberDto selectMember(MemberDto memberDto);
	
}
