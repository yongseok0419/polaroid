package com.polaroid.app.post;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.polaroid.app.command.PostDetailDto;
import com.polaroid.app.command.PostDto;
import com.polaroid.app.command.PostListDto;
import com.polaroid.app.command.UploadDto;
import com.polaroid.app.upload.UploadMapper;

import net.coobird.thumbnailator.Thumbnailator;

@Transactional(readOnly = true)
@Service("postService")
public class PostServiceImpl implements PostService {

	@Autowired
	PostMapper postMapper;
	@Autowired
	UploadMapper uploadMapper;

	@Value("${project.upload.path}")
	private String upload_filepath;

	// 폴더생성함수
	public String makeFolder() {
		String path = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
		File file = new File(upload_filepath + "\\" + path);
		if (file.exists() == false) {
			file.mkdirs(); // 파일생성
		}
		return path; // 경로
	}

	@Transactional
	@Override
	public boolean registerPost(PostDto postDto, List<MultipartFile> uploadFiles) {

		// 1. 폼데이터 인서트
		int post_id = postMapper.registerPost(postDto);

		// 1. 폼데이터 인서트
		// uploadMapper.registerUploadFile( uploadFiles);

		// 2. 파일업로드
		for (MultipartFile file : uploadFiles) {
			// 실제파일명 (브라우저별로 조금씩 다를수가있음)
			String origin = file.getOriginalFilename();
			// 저장할파일명(경로가 \\가 들어오는 경우 잘라서 처리)
			String filename = origin.substring(origin.lastIndexOf("\\") + 1);
			// 파일사이즈
			long size = file.getSize();
			// 랜덤이름
			String uuid = UUID.randomUUID().toString();
			// 날짜경로
			String path = makeFolder();
			// 업로드경로
			String saveName = upload_filepath + "\\" + path + "\\" + uuid + "_" + filename;
			// 썸네일경로
			String thumbnailName = upload_filepath + "\\" + path + "\\thumb_" + uuid + "_" + filename;

			System.out.println(filename);
			System.out.println(size);
			System.out.println(saveName);

			try {
				File saveFile = new File(saveName);
				file.transferTo(saveFile); // 파일업로드
				// 썸네일 생성 업로드
				Thumbnailator.createThumbnail(saveFile, new File(thumbnailName), 160, 160);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("업로드중 에러 발생");
			}

			// selectKey키방식

			// 3. 파일의경로를 DB인서트
			uploadMapper.registerUploadFile(UploadDto.builder().post_id(post_id).upload_filename(filename)
					.upload_filepath(path).upload_fileuuid(uuid).build());

		}
		return true;
	}

	/*
	 * @Override public List<UploadDto> getList(Criteria cri) {
	 * 
	 * return postMapper.getList(cri); }
	 */

//	@Override
//	public int getTotal(Criteria cri) {
//		
//		return postMapper.getTotal(cri);
//	}

//	@Override
//	public List<PostListDto> retrievePostList() { // 목록조회
//
//		return postMapper.selectPostList();
//	}
//
	@Override
	public List<PostListDto> retrieveMyPostList() { // 내 게시글 조회

		return postMapper.selectMyPostList();
	}
//
//	@Override
//	public List<PostListDto> retrieveLikePostList() {
//
//		return postMapper.selectLikePostList();
//	}
//
//	@Override
//	public PostDto retrivePostDetail(Integer post_id) {
//
//		return null;
//	}

	
//	 @Override public List<PostDetailDto> retriveDetailImg(int post_id) {
//	  
//	 return postMapper.retriveDetailImg(post_id); }
//	 
//
//	@Override
//	public boolean modifyPost(PostDto postDto) {
//
//		return postMapper.updatePost(postDto);
//	}
//
//	@Override
//	public boolean removePost() {
//
//		return false;
//	}
//
////	@Override
////	public List<PostListDto> getList(Criteria cri) {
////		// TODO Auto-generated method stub
////		return null;
////	}
//
//	@Override
//	public List<PostDetailDto> retriveDetailImg(Integer post_id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
