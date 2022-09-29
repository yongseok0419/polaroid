package com.polaroid.app.post;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.polaroid.app.command.PostDetailDto;
import com.polaroid.app.command.PostDto;
import com.polaroid.app.command.PostListDto;


@Mapper
public interface PostMapper {
	public int registerPost(PostDto postDto); //등록
	//public boolean registerFile(UploadDto uploadDto); //파일등록
	
	
	
	public List<PostDto> selectPostList(); //전체 게시글 조회
	public List<PostDto> selectMyPostList(int member_id); //내 게시글 조회
	public List<PostListDto> selectLikePostList(); //좋아요조회

	public int selectPostCount(int member_id); //내 게시글 수
	 
	public PostDetailDto selectPostDetail(int post_id);// 상세조회
	//public List<UploadDto> retriveDetailImg(Integer post_id);//상세이미지 조회
	public boolean updatePost(PostDto postDto);//수정
	public boolean deletePost(int post_id);//삭제
}
