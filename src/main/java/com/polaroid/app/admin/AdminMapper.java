package com.polaroid.app.admin;

import org.apache.ibatis.annotations.Mapper;

import com.polaroid.app.command.MemberProfileDto;

@Mapper
public interface AdminMapper {

		//모든 회원 리스트 조회
		public MemberProfileDto selectMemberListAll();
		
	
}
