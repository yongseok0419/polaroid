package com.polaroid.app.post;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.polaroid.app.command.PostDto;
import com.polaroid.app.command.PostListDto;
import com.polaroid.app.command.UploadDto;


@Mapper
public interface PostMapper {
	public int registerPost(PostDto postDto); //등록
	//public boolean registerFile(UploadDto uploadDto); //파일등록
	
	
	
	//public List<PostListDto> selectPostList(); //조회
	public List<PostListDto> selectMyPostList(); //내 게시글 조회
	public List<PostListDto> selectLikePostList(); //좋아요조회
	// public List<UploadDto> getList(Criteria cri); //페이징 조회
	// public int getTotal(Criteria cri); //전체게시글수
	
	public PostDto retrivePostDetail(Integer post_id);// 상세조회
	public List<UploadDto> retriveDetailImg(Integer post_id);//상세이미지 조회
	public boolean updatePost(PostDto postDto);//수정
	public boolean removePost();//삭제
}
