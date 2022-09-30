package com.polaroid.app.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDetailDto {
	private int member_id;
	 private String member_nick;
	 	 
	 private Integer post_id;
	 private String post_title;
	 private String post_content;
	 private LocalDateTime post_regdate;
	 private Integer postlike_id;
	 
	 private Integer upload_id;
	 private String upload_filename;
	 private String upload_filepath;
	 private String upload_fileuuid;
	 private String upload_type;
	 
	 private Integer reply_id;
	 private Integer replytag_id;
	 private Integer replylike_id;
	 private String reply_content;
	 private LocalDateTime reply_regdate;
	 
	 private Integer posthashtag_id;
	 private Integer hashtag_id;

	 private Integer postmembertag_id;

}
