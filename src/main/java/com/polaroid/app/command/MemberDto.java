package com.polaroid.app.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDto {
	
	private int memberId;						//번호
	private String memberEmail;				//이메일
	private String memberNick;				//닉네임
	private String memberName;				//이름
	private String memberPhone;			//전화번호
	private String memberPwd;				//비밀번호
	
}
