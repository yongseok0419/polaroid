package com.polaroid.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.polaroid.app.command.MemberDto;
import com.polaroid.app.command.MemberReplyDto;
import com.polaroid.app.command.ReplyDto;
import com.polaroid.app.reply.ReplyService;

@Controller
public class ReplyController {

   @Autowired
   private ReplyService replyService;
   
   
   //댓글 조회
   @GetMapping(value = "/posts/{post_id}/replies")
   public @ResponseBody Map<String, Object> getReplyList(@PathVariable("post_id") Integer postId){
      
      List<MemberReplyDto> replyDtoList = replyService.retrieveReplyList(postId);
   
      Map<String, Object> map = new HashMap<>();
      
      map.put("replyList", replyDtoList);

      return map;   
   }
   
   //댓글 등록
   @PostMapping(value = "/posts/{post_id}/replies")
   public @ResponseBody Map<String, Object> regReply(@RequestBody ReplyDto replyDto,
                              @PathVariable("post_id") Integer postId, HttpSession session){
      
      replyDto.setPostId(postId);
      
      MemberDto member = (MemberDto)session.getAttribute("member");
      int memberId = member.getMemberId();      
      replyDto.setMemberId(memberId);
      
      replyService.registerReply(replyDto);
      
      List<MemberReplyDto> replyDtoList = replyService.retrieveReplyList(postId);
      
      Map<String, Object> map = new HashMap<>();      
      
      map.put("replyList", replyDtoList);

      return map;
      
   }
   
   //댓글 삭제
   @DeleteMapping(value = "/posts/{post_id}/replies/{reply_id}")
   public @ResponseBody Map<String, Object> delReply(@PathVariable("post_id") Integer postId,
                              @PathVariable("reply_id") Integer replyId){
      
      replyService.removeReply(replyId);
      
      List<MemberReplyDto> replyDtoList = replyService.retrieveReplyList(postId);
      
      Map<String, Object> map = new HashMap<>();      
      
      map.put("replyList", replyDtoList);

      return map;
   }
   
   //댓글 수정
   @PutMapping(value = "/posts/{post_id}/replies/{reply_id}")
   public @ResponseBody Map<String, Object> modReply(@RequestBody ReplyDto replyDto,
                              @PathVariable("post_id") Integer postId,
                              @PathVariable("reply_id") Integer replyId){
      
      replyDto.setReplyId(replyId);
      
      replyService.modifyReply(replyDto);
      
      List<MemberReplyDto> replyDtoList = replyService.retrieveReplyList(postId);
      
      Map<String, Object> map = new HashMap<>();      
      
      map.put("replyList", replyDtoList);

      return map;
   }
   
}