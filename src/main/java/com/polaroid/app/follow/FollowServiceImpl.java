package com.polaroid.app.follow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polaroid.app.command.FollowDto;

@Transactional(readOnly = true)
@Service("followService")
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowMapper followMapper;
	
	//팔로우 등록
	@Transactional
	@Override
	public int registerFollow1(FollowDto followDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	//팔로우 조회
	@Override
	public List<FollowDto> retrieveFollow1(int member_id) {
		// TODO Auto-generated method stub
		return null;
	}

	//팔로우 삭제
	@Transactional
	@Override
	public List<FollowDto> removeFollow1(int follow_id) {
		// TODO Auto-generated method stub
		return null;
	}

	//팔로워 등록
	@Transactional
	@Override
	public int registerFollow2(FollowDto followDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	//팔로워 조회
	@Override
	public List<FollowDto> retrieveFollow2(int member_id) {
		// TODO Auto-generated method stub
		return null;
	}

	//팔로워 삭제
	@Transactional
	@Override
	public List<FollowDto> removeFollow2(int follow_id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
