package com.example.dongmyung.like;

import com.example.dongmyung.question.Question;
import com.example.dongmyung.question.QuestionRepository;
import com.example.dongmyung.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static com.example.dongmyung.like.QuestionLike.createQuestionLike;


@RequiredArgsConstructor
@Service
public class QuestionLikeService {
    private final QuestionLikeRepository questionLikeRepository;

    public void questionlike(SiteUser siteUser, Question question){
        boolean isNotAlreadyLike = isNotAlreadyLike(siteUser, question);
        if(isNotAlreadyLike){
            QuestionLike ql = createQuestionLike(siteUser, question);
            System.out.println("좋아요");
            question.setLike_count(question.getLike_count()+1);
            this.questionLikeRepository.save(ql);
        }
        else
        {
            System.out.println("좋아요 취소");
            question.setLike_count(question.getLike_count()-1);
            this.questionLikeRepository.deleteBySiteUserAndQuestion(siteUser, question);
        }
    }
    private boolean isNotAlreadyLike(SiteUser siteUser, Question question) {
        return questionLikeRepository.findBySiteUserAndQuestion(siteUser,question).isEmpty();
    }
}
