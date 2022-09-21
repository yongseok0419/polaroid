package com.polaroid.app.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaroid.app.command.ProfileDto;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	ProfileMapper profileMapper;
	
	//프로필 수정
	@Override
	public int modifyProfile(ProfileDto profileDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	//프로필 조회
	@Override
	public ProfileDto retrieveProfileDetail(int profile_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
