package com.example.dongmyung.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.dongmyung.DataNotFoundException;
import com.example.dongmyung.answer.Answer;
import com.example.dongmyung.user.SiteUser;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private static final int PAGE_POST_COUNT = 10;

    public Page<Question> getQuestionByAuthor(int page, SiteUser siteUser){
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "createDate"));
        return this.questionRepository.findByAuthor(siteUser, pageable);
    }
    public Question getQuestion(Long id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            Question q = question.get();
            this.questionRepository.save(q);
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
    /* Views Counting */
    @Transactional
    public int updateView(Long id) {
        return this.questionRepository.updateView(id);
    }

    public Page<Question> getList(int page, String kw, String category, String sort) {
        Pageable pageable = PageRequest.of(page, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, sort));
        return this.questionRepository.findAllByKeywordAndCategory(kw, category, pageable);
    }
    public Page<Question> getList(int page, String kw, String sort) {
        Pageable pageable = PageRequest.of(page, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, sort));
        return this.questionRepository.findAllByKeyword(kw,pageable);
    }

    public void create(String subject, String content, SiteUser author, String category) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setAuthor(author);
        q.setCategory(category);
        this.questionRepository.save(q);
    }

    public void create(String subject, String content) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }

    public void modify(Question question, String subject, String content, String category) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        question.setCategory(category);
        this.questionRepository.save(question);
    }

    public void delete(Question question) {
        this.questionRepository.delete(question);
    }

    private Specification<Question> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true); // 중복을 제거
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"), // 내용
                        cb.like(u1.get("username"), "%" + kw + "%"), // 질문 작성자
                        cb.like(a.get("content"), "%" + kw + "%"), // 답변 내용
                        cb.like(u2.get("username"), "%" + kw + "%")); // 답변 작성자
            }
        };
    }

}
