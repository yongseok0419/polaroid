package com.polaroid.app.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDto {
	private Integer replyId;
	private String replyContent;
	private LocalDateTime replyRegdate;
	private Integer postId;

}
