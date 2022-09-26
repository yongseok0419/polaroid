package com.polaroid.app.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polaroid.app.command.LoginDto;
import com.polaroid.app.command.MemberDto;
import com.polaroid.app.command.StateMemberDto;

@Transactional(readOnly = true)
@Service("memberService")
public class MemberServiceImpl implements MemberService {

		@Autowired
		MemberMapper memberMapper;
		
		//이메일 인증
		@Transactional
		@Override
		public int registerAuthentication(Map<String, Object> map) {
			return memberMapper.insertAuthentication(map);
		}
		
		//이메일 인증번호 일치 여부
		@Override
		public int checkAuthentication(Integer memberAuthentication) {
			return memberMapper.selectAuthentication(memberAuthentication);
		}
		
		//이메일 중복 체크
		@Override
		public int checkEmail(String memberEmail) {
			return memberMapper.selectEmail(memberEmail);
		}

		//닉네임 중복 체크
		@Override
		public int checkNick(String memberNick) {
			return memberMapper.selectNick(memberNick);
		}
		
		//전화번호 중복 체크
		@Override
		public int checkPhone(String memberPhone) {
			return memberMapper.selectPhone(memberPhone);
		}
		
		//회원가입
		@Transactional
		@Override
		public int registerMember(MemberDto MemberDto) {
			return memberMapper.insertMember(MemberDto);
		}

		//로그인
		@Override
		public MemberDto findMember(MemberDto memberDto) {
			return memberMapper.selectMember(memberDto);
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
		public int removeMember(MemberDto memberDto) {
			return memberMapper.deleteMember(memberDto);
		}

}
