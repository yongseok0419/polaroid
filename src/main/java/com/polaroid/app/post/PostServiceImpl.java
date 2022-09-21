package com.polaroid.app.post;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polaroid.app.command.PostDetailDto;
import com.polaroid.app.command.PostDto;
import com.polaroid.app.command.PostListDto;
import com.polaroid.app.upload.UploadMapper;

@Transactional(readOnly = true)
@Service("postService")
public class PostServiceImpl implements PostService {

		@Autowired
		PostMapper postMapper;
		
		@Autowired
		UploadMapper uploadMapper;
		
		//게시글 등록
		@Transactional
		@Override
		public void registerPost() {
			// TODO Auto-generated method stub
			
		}

		//게시글 수정
		@Transactional
		@Override
		public int modifyPost(PostDto postDto) {
			// TODO Auto-generated method stub
			return 0;
		}

		//게시글 목록조회
		@Override
		public List<PostListDto> retrievePostList() {
			// TODO Auto-generated method stub
			return null;
		}

		//게시글 좋아요 목록조회
		@Override
		public List<PostListDto> retrieveLikePostList(int member_id) {
			// TODO Auto-generated method stub
			return null;
		}

		//게시글 상세조회
		@Override
		public PostDetailDto retrievePostDetail(int post_id) {
			// TODO Auto-generated method stub
			return null;
		}

		//게시글 삭제
		@Transactional
		@Override
		public int removePost(int post_id) {
			// TODO Auto-generated method stub
			return 0;
		}

		//게시글 검색
		@Override
		public List<PostDto> searchPostList(HashMap<String, String> map) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
}
