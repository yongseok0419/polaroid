package com.polaroid.app.profile;

import com.polaroid.app.command.ProfileDto;

public interface ProfileService {
	
	//프로필 수정
	public int modifyProfile(ProfileDto profileDto);
	
	//프로필 조회
	public ProfileDto retrieveProfileDetail(int profile_id);
	
}
