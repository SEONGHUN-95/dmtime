package com.example.dongmyung.question;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {

	@NotBlank(message = "카테고리선택은 필수항목입니다.")
	private String category;

	@NotEmpty(message="제목을 써주세요.")
	@Size(max=200)
	private String subject;
	
	@NotEmpty(message="글 내용을 써주세요.")
	private String content;
}
