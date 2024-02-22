package com.ksourav.SpringStarter.Controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.ksourav.SpringStarter.models.Answer;
import com.ksourav.SpringStarter.models.Question;
import com.ksourav.SpringStarter.models.Test;
import com.ksourav.SpringStarter.models.TestPanel;
import com.ksourav.SpringStarter.services.QuestionService;
import com.ksourav.SpringStarter.services.TestService;
import com.ksourav.dto.CreateAnswerDto;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestPanelController {

  @Autowired
  private TestService testService;

  @Autowired
  private QuestionService questionService;

  @GetMapping("/testPanel/{testCode}")
  public String startTest(@PathVariable Long testCode, Model model) {
    Optional<Test> optionalTest = testService.getById(testCode);
    if (optionalTest.isPresent()) {
      Test test = optionalTest.get();
      model.addAttribute("test", test);
      // model.addAttribute("answerset", answerset);
      return "testPanel";

    } else {
      return "404";
    }
  }

  @PostMapping("/testPanel/{testCode}")
  public String start_Test(@ModelAttribute Test test, Model model) {

    return "redirect:/testPanel/{testCode}";

  }

}
