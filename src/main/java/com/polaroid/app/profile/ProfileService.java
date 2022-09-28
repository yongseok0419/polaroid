package com.polaroid.app.profile;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.polaroid.app.command.MemberDto;
import com.polaroid.app.command.ProfileDto;

public interface ProfileService {
	
	//프로필 등록(수정)
	public boolean modifyProfile(ProfileDto profileDto, MemberDto memberDto, MultipartFile upload);
	
	//프로필 조회
	public ProfileDto retrieveProfileDetail(int profile_id);
	
}
