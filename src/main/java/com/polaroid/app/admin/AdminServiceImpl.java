package com.polaroid.app.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polaroid.app.command.MemberProfileDto;

@Transactional(readOnly = true)
@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminMapper adminMapper;

	@Override
	public MemberProfileDto retrieveMemberListAll() {
		return null;
	}
	
}
