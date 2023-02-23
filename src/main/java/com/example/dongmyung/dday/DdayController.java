package com.example.dongmyung.dday;

import com.example.dongmyung.question.Question;
import com.example.dongmyung.question.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
public class DdayController {

    @GetMapping("/")
    public String root(Model model) {
        LocalDate curDate = LocalDate.now();
        LocalDate targetDate = LocalDate.of(2022,9,05);
        LocalDate endDate = LocalDate.of(2023,5,16);
        Long doneDays = ChronoUnit.DAYS.between(targetDate, curDate);
        Long lastDays = ChronoUnit.DAYS.between(curDate, endDate);
        int percent = Math.round((doneDays*100)/252);
        model.addAttribute("percent", percent);
        model.addAttribute("doneDays", doneDays);
        return "index";
    }

}
