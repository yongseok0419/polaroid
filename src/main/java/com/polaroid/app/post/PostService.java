package com.polaroid.app.post;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.polaroid.app.command.PostDetailDto;
import com.polaroid.app.command.PostDto;
import com.polaroid.app.command.PostLikeDto;
import com.polaroid.app.command.PostListDto;

public interface PostService {
	

	public List<PostDto> retrievePostList(); //전체 게시글 조회

	public List<PostDto> retrieveMyPostList(int member_id); //내 게시글 조회 
	public int selectPostCount(int member_id); //내 게시글 수

	public PostDetailDto retrivePostDetail(int post_id);// 상세조회
	
	public boolean updatePost(PostDto postDto, List<MultipartFile> uploadFiles);//수정
	
	public boolean removePost(int post_id);// 게시글 전체 삭제 
	
	public PostDto modifyPostDetail(int post_id); //수정 전 데이터 불러오기
	
	public boolean registerPost(PostDto postDto, List<MultipartFile> uploadFiles);//등록
	
	public List<PostDto> searchPostList(String keyword);	//게시글 검색
	
	
	public int postLike(PostLikeDto postLike);				//게시글 좋아요 누르기
	public int postFindLike(PostLikeDto postLike);	//좋아요 눌렀는지 확인 여부
	public int postLikeCount(int post_id);					//게시글 좋아요 개수
	public int postRemoveLike(PostLikeDto deletePostLike);	//좋아요 취소
	
}
