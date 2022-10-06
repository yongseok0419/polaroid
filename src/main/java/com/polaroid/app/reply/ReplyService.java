package com.polaroid.app.reply;

import java.util.List;

import com.polaroid.app.command.MemberReplyDto;
import com.polaroid.app.command.ReplyDto;

public interface ReplyService {

	//댓글 등록
	public void registerReply(ReplyDto replyDto);
	
	//댓글 삭제
	public int removeReply(int reply_id);
	
	//댓글 수정
	public int modifyReply(ReplyDto replyDto);
	
	//댓글 조회
	public List<MemberReplyDto> retrieveReplyList(int post_id);
	
	//댓글 좋아요
	public void likeReply(int user_id, int reply_id);

	//댓글 개수
	public int retrieveReplyCount(int postId);
	
}
