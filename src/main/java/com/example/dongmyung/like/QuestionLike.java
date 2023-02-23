package com.example.dongmyung.like;

import com.example.dongmyung.question.Question;
import com.example.dongmyung.user.SiteUser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private SiteUser siteUser;

    public static QuestionLike createQuestionLike(SiteUser siteUser, Question question)
    {
        QuestionLike questionLike = new QuestionLike();
        questionLike.setQuestion(question);
        questionLike.setSiteUser(siteUser);
        return questionLike;
    }

}
