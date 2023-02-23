package com.example.dongmyung.answer;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;

import com.example.dongmyung.question.Question;
import com.example.dongmyung.user.SiteUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String content;

	@CreatedDate
	private LocalDateTime createDate;

	@ManyToOne(fetch = FetchType.LAZY)
	private Question question;

	@ManyToOne(fetch = FetchType.LAZY)
	private SiteUser author;
	private LocalDateTime modifyDate;
	
	@ManyToMany
	Set<SiteUser> voter;

}