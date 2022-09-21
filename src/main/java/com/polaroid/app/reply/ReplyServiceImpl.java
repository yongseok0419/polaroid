package com.polaroid.app.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polaroid.app.command.ReplyDto;

@Transactional(readOnly = true)
@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyMapper replyMapper;

	//댓글 등록
	@Transactional
	@Override
	public void registerReply(ReplyDto replyDto) {
		// TODO Auto-generated method stub
		
	}

	//댓글 삭제
	@Transactional
	@Override
	public int removeReply(int reply_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	//댓글 수정
	@Transactional
	@Override
	public int modifyReply(ReplyDto replyDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	//댓글 조회
	@Override
	public List<ReplyDto> retrieveReplyList(int post_id) {
		// TODO Auto-generated method stub
		return null;
	}

	//댓글 좋아요
	@Transactional
	@Override
	public void likeReply(int user_id, int reply_id) {
		// TODO Auto-generated method stub
		
	}
	
}
