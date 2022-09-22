package com.polaroid.app.post;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.polaroid.app.command.PostDetailDto;
import com.polaroid.app.command.PostDto;
import com.polaroid.app.command.PostListDto;

public interface PostService {
	
//	public List<PostListDto> retrievePostList(); //조회
//	
	public List<PostListDto> retrieveMyPostList(); //내 게시글 조회
//	
//	//public List<PostListDto> getList(Criteria cri); //페이징 조회
//	
//	//public int getTotal(Criteria cri); //전체게시글수
//	
//	public List<PostListDto> retrieveLikePostList(); //좋아요조회
//	
//	public PostDto retrivePostDetail(Integer post_id);// 상세조회
//	public List<PostDetailDto> retriveDetailImg(Integer post_id);//상세이미지 조회
//	
//	public boolean modifyPost(PostDto postDto);//수정
//	
//	public boolean removePost();//삭제
	
	
	
	//public boolean registerPost(PostDto postDto, List<MultipartFile> uploadFiles);//등록
	//public boolean registerUploadFile(UploadDto upload_file); //파일등록
	public boolean registerPost(PostDto postDto, List<MultipartFile> uploadFiles);//등록
	
	
}
