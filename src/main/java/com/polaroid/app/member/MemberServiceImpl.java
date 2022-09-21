package com.polaroid.app.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polaroid.app.command.MemberDto;
import com.polaroid.app.command.StateMemberDto;

@Transactional(readOnly = true)
@Service("memberService")
public class MemberServiceImpl implements MemberService {

		@Autowired
		MemberMapper memberMapper;
		
		//회원가입
		@Transactional
		@Override
		public int registerMember(MemberDto MemberDto) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		//이메일 중복 검사
		@Override
		public int checkEmail(String member_email) {
			// TODO Auto-generated method stub
			return 0;
		}

		//닉네임 중복 검사
		@Override
		public int checkNick(String member_nick) {
			// TODO Auto-generated method stub
			return 0;
		}

		//로그인
		@Override
		public int findMember(String member_email, String member_pwd) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		//회원 상태 변경
		@Transactional
		@Override
		public int modifyStateMember(StateMemberDto stateMemberDto) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		//회원 상태 조회
		@Override
		public List<MemberDto> retrieveStateMember(int member_statuscode) {
			// TODO Auto-generated method stub
			return null;
		}

		//회원 탈퇴
		@Transactional
		@Override
		public int removeMember(int member_id) {
			// TODO Auto-generated method stub
			return 0;
		}

}
