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
      for(int i=0;i< test.getQuestions().size();i++){
        // System.out.println(test.getQuestions().size());
  
        System.out.println("test "+ test.toSting() +" question "+ test.getQuestions().get(i));
        // questionService.save(test.getQuestions().get(i));
      }
      model.addAttribute("test", test);
      CreateAnswerDto createAnswerDto = new CreateAnswerDto();

      model.addAttribute("createAnswerDto", createAnswerDto);
      return "testPanel";

    } else {
      return "404";
    }
  }

  @PostMapping("/testPanel/{testCode}")
  public String start_Test(@ModelAttribute Test test, Model model) {

    Optional<Test> test1 = testService.getById(test.getTestCode());
    if(test1.isPresent()){
      Test test2 = test1.get();
      for(int i=0;i< test2.getQuestions().size();i++){
        System.out.println("test "+ test.toSting() +" question "+ test.getQuestions().get(i));
        test2.getQuestions().get(i).setCorrectAnswer(test.getQuestions().get(i).getCorrectAnswer());
        System.out.println("test "+ test2.toSting() +" question "+ test2.getQuestions().get(i));
        questionService.save(test2.getQuestions().get(i));
      }
    }

    return "redirect:/testPanel/{testCode}";

  }

}
