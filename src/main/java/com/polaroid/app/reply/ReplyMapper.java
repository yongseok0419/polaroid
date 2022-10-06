package com.polaroid.app.reply;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.polaroid.app.command.MemberReplyDto;
import com.polaroid.app.command.ReplyDto;

@Mapper
public interface ReplyMapper {
	
	public List<MemberReplyDto> selectReplyList(int postId);
	public void insertReply(ReplyDto replyDto);
	public int deleteReply(int replyId);
	public void updateReply(ReplyDto replyDto);
	public int selectReplyCount(int postId);  //댓글 개수

}
