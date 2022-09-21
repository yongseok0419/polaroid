package com.polaroid.app.follow;

import java.util.List;

import com.polaroid.app.command.FollowDto;

public interface FollowService {

	//팔로우 등록
	public int registerFollow1(FollowDto followDto);
	
	//팔로우 조회
	public List<FollowDto> retrieveFollow1(int member_id);
	
	//팔로우 삭제
	public List<FollowDto> removeFollow1(int follow_id);
	
	//팔로워 등록
	public int registerFollow2(FollowDto followDto);
	
	//팔로워 조회
	public List<FollowDto> retrieveFollow2(int member_id);
	
	//팔로워 삭제
	public List<FollowDto> removeFollow2(int follow_id);
	
}
