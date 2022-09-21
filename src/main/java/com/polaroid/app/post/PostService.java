package com.polaroid.app.post;

import java.util.HashMap;
import java.util.List;

import com.polaroid.app.command.PostDetailDto;
import com.polaroid.app.command.PostDto;
import com.polaroid.app.command.PostListDto;

public interface PostService {
	
	//게시글 등록
	public void registerPost();

	//게시글 수정
	public int modifyPost(PostDto postDto);
	
	//게시글 목록조회
	public List<PostListDto> retrievePostList();
	
	//게시글 좋아요 목록조회
	public List<PostListDto> retrieveLikePostList(int member_id);
	
	//게시글 상세조회
	public PostDetailDto retrievePostDetail(int post_id);
	
	//게시글 삭제
	public int removePost(int post_id);
	
	//게시글 검색
	public List<PostDto> searchPostList(HashMap<String, String> map);
	
}
