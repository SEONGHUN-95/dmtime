package com.example.dongmyung.question;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.dongmyung.answer.Answer;
import com.example.dongmyung.like.QuestionLike;
import com.example.dongmyung.user.SiteUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200)
	private String subject;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDateTime createDate;

	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;

	@ManyToOne(fetch = FetchType.LAZY)
	private SiteUser author;

	private LocalDateTime modifyDate;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	Set<QuestionLike> questionlikes = new HashSet<>();

	private String category;

	@Column(columnDefinition = "integer default 0", nullable = false)
	private int views;

	@Column(columnDefinition = "integer default 0", nullable = false)
	private int like_count;

}