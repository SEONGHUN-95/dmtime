package com.example.dongmyung.like;

import com.example.dongmyung.question.Question;
import com.example.dongmyung.question.QuestionService;
import com.example.dongmyung.user.SiteUser;
import com.example.dongmyung.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.Set;

@RequiredArgsConstructor
@Controller
public class QuestionLikeController {
    private final QuestionService questionService;
    private final UserService userService;
    private final QuestionLikeService questionLikeService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("question/like/{id}")
    public String questionLike(Principal principal, @PathVariable("id") Long id) {
        Question question = this.questionService.getQuestion(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.questionLikeService.questionlike(siteUser, question);
        return String.format("redirect:/question/detail/%s", id);
    }
}
