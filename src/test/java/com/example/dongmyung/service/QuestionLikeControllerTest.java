package com.example.dongmyung.service;

import com.example.dongmyung.like.QuestionLike;
import com.example.dongmyung.like.QuestionLikeRepository;
import com.example.dongmyung.like.QuestionLikeService;
import com.example.dongmyung.question.Question;
import com.example.dongmyung.question.QuestionService;
import com.example.dongmyung.user.SiteUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class QuestionLikeControllerTest {

    @Autowired
    QuestionLikeService questionLikeService;
    @Autowired
    QuestionLikeRepository questionLikeRepository;

    @Test
    public void 좋아요() throws Exception{

    }
}
