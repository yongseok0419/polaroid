package com.polaroid.app.profile;

import org.apache.ibatis.annotations.Mapper;

import com.polaroid.app.command.MemberDto;
import com.polaroid.app.command.ProfileDto;

@Mapper
public interface ProfileMapper {

	//프로필 화면에 닉네임 수정
	public boolean updateMember(MemberDto memberDto);
	
	//프로필 테이블 수정
	public void insertProfileFile(ProfileDto profileDto);
	
}
