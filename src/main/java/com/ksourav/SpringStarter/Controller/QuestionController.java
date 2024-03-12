package com.ksourav.SpringStarter.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class QuestionController {
    
    @GetMapping("/test/questions")
public String gettest(Model model) {
        return "question";
}

}
