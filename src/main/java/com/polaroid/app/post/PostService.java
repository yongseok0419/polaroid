package com.polaroid.app.post;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.polaroid.app.command.PostDetailDto;
import com.polaroid.app.command.PostDto;
import com.polaroid.app.command.PostListDto;
import com.polaroid.app.command.UploadDto;

public interface PostService {
	

	
	public List<PostDto> retrieveMyPostList(int member_id); //내 게시글 조회 
	public int selectPostCount(int member_id); //내 게시글 수
	
	public List<PostListDto> retrieveLikePostList(); //좋아요 게시글 조회
	

	public PostDto retrivePostDetail(int post_id);// 상세조회
//	public List<PostDetailDto> retriveDetailImg(Integer post_id);//상세이미지 조회
	
	public boolean updatePost(PostDto postDto, List<MultipartFile> uploadFiles);//수정
	
	public boolean removePost(int post_id);// 게시글 전체 삭제 
	
	public PostDto retrievePostDetail(int post_id); //수정 전 데이터 불러오기
	
	public boolean registerPost(PostDto postDto, List<MultipartFile> uploadFiles);//등록
	
}
