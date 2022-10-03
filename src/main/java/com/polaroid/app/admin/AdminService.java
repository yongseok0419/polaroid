package com.polaroid.app.admin;

import com.polaroid.app.command.MemberProfileDto;

public interface AdminService {

	//모든 회원 리스트 조회
	public MemberProfileDto retrieveMemberListAll();
	
}
