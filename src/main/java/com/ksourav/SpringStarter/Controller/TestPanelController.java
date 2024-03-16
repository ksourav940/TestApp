package com.ksourav.SpringStarter.Controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.ksourav.SpringStarter.models.Account;
import com.ksourav.SpringStarter.models.Answer;
import com.ksourav.SpringStarter.models.Test;
import com.ksourav.SpringStarter.services.AccountService;
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

  @Autowired
  private AccountService accountService;

  @GetMapping("/testPanel/{testCode}")
  @PreAuthorize("isAuthenticated()")
  public String startTest(@PathVariable Long testCode, Model model, Principal principal) {
    
    String authuser = "email";
    if(principal != null){
      authuser = principal.getName();
    }

    Optional<Account> optionalAccount = accountService.findOneByEmail(authuser);
    if(optionalAccount.isPresent()){
      Account account = optionalAccount.get();
      model.addAttribute("account", account);
      model.addAttribute("profilephoto", account.getPhoto());
      Optional<Test> optionalTest = testService.getById(testCode);

      if (optionalTest.isPresent()) {
        Test test = optionalTest.get();
        model.addAttribute("test", test);
        return "test_views/testPanel";
      } else {
        return "404";
      }
    }else{
      return "redirect:/?error";
    }

   
  }

  @PostMapping("/testPanel/{testCode}")
  public String start_Test(@ModelAttribute Test test, Model model) {

    // Optional<Test> test1 = testService.getById(test.getTestCode());
    // if(test1.isPresent()){
    //   Test test2 = test1.get();
    //   for(int i=0;i< test2.getQuestions().size();i++){
    //     System.out.println("test "+ test.toSting() +" question "+ test.getQuestions().get(i));
    //     test2.getQuestions().get(i).setCorrectAnswer(test.getQuestions().get(i).getCorrectAnswer());
    //     System.out.println("test "+ test2.toSting() +" question "+ test2.getQuestions().get(i));
    //     questionService.save(test2.getQuestions().get(i));
    //   }
    // }

    return "redirect:/test_views/testPanel/{testCode}";

  }

}
