package com.example.dongmyung.like;

import com.example.dongmyung.question.Question;
import com.example.dongmyung.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Transactional
@Repository
public interface QuestionLikeRepository extends JpaRepository<QuestionLike, Long>{

    Optional<QuestionLike> findBySiteUserAndQuestion(SiteUser siteUser, Question question);
    void deleteBySiteUserAndQuestion(SiteUser siteUser, Question question);
}


