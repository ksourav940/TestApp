package com.ksourav.SpringStarter.Controller;

import java.security.Principal;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.ksourav.SpringStarter.models.Account;
import com.ksourav.SpringStarter.models.Answer;
import com.ksourav.SpringStarter.models.AttemptSheet;
import com.ksourav.SpringStarter.models.Question;
import com.ksourav.SpringStarter.models.Test;
import com.ksourav.SpringStarter.services.AccountService;
import com.ksourav.SpringStarter.services.QuestionService;
import com.ksourav.SpringStarter.services.TestPanelService;
import com.ksourav.SpringStarter.services.TestService;
import com.ksourav.dto.CreateAnswerDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TestPanelController {

  @Autowired
  private TestService testService;

  @Autowired
  private QuestionService questionService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private TestPanelService testPanelService;

  @GetMapping("/testPanel/{testCode}/question/{questionId}")
  @PreAuthorize("isAuthenticated()")
  public String startTest(@PathVariable Long testCode, @PathVariable Long questionId, Model model,
      Principal principal) {

    String authuser = "email";
    if (principal != null) {
      authuser = principal.getName();
    }

    Optional<Account> optionalAccount = accountService.findOneByEmail(authuser);

    if (optionalAccount.isPresent()) {
      Account account = optionalAccount.get();
      model.addAttribute("account", account);
      model.addAttribute("profilephoto", account.getPhoto());
      Optional<Test> optionalTest = testService.getById(testCode);

      if (optionalTest.isPresent()) {
        Test test = optionalTest.get();
        model.addAttribute("test", test);
        List<Question> questions = test.getQuestions();

        for (Question ques : questions) {
          if (ques.getQuestionId() == (questionId)) {

            System.out.println(ques);
            model.addAttribute("question", ques);
          }
        }
        Optional<AttemptSheet> optionalSheet = testPanelService.getAttemptSheetByQuestionId(questionId);
        if (optionalSheet.isPresent()) {
          AttemptSheet attemptSheet = optionalSheet.get();
          model.addAttribute("sheet", attemptSheet);
        } else {
          AttemptSheet attemptSheet = new AttemptSheet();
          model.addAttribute("sheet", attemptSheet);
        }

        return "test_views/testPanel";
      } else {
        return "404";
      }
    } else {
      return "redirect:/?error";
    }

  }

  @PostMapping("/testPanel/{testCode}")
  public String start_Test(@ModelAttribute AttemptSheet sheet, Model model) {

    if (sheet != null) {
      System.out.println(sheet);
      Long testCode = sheet.getTestCode();
      Long questionId = sheet.getQuestionId();
      Long userCode   = sheet.getUserCode();
      System.out.println(testCode);
      Optional<AttemptSheet> optionalSheet = testPanelService.getAttemptSheetByQuestionId(questionId);
      if (optionalSheet.isPresent()) {
        AttemptSheet addSheet = optionalSheet.get();
        addSheet.setTestCode(sheet.getTestCode());
        addSheet.setQuestionId(sheet.getQuestionId());
        addSheet.setUserCode(sheet.getUserCode());
        addSheet.setSelectedOption(sheet.getSelectedOption());
        testPanelService.save(addSheet);
      } else {
        AttemptSheet addSheet = new AttemptSheet();
        addSheet.setTestCode(sheet.getTestCode());
        addSheet.setQuestionId(sheet.getQuestionId());
        addSheet.setUserCode(sheet.getUserCode());
        addSheet.setSelectedOption(sheet.getSelectedOption());
        testPanelService.save(addSheet);
      }

      Optional<Test> optionalTest = testService.getById(testCode);
      Test test = optionalTest.get();
      List<Question> questionList = test.getQuestions();
      Question question1 = new Question();
      for (Question ques : questionList) {
        if (ques.getQuestionId() == (questionId)) {
          question1 = ques;
        }
      }
      int index = questionList.indexOf(question1);
      if (index >= 0 && index < questionList.size() - 1) {
        // Get the next object
        Question nextQuestion = questionList.get(index + 1);
        Long nextQuestionId = nextQuestion.getQuestionId();
        return "redirect:/testPanel/" + testCode + "/question/" + nextQuestionId;
      } else {
        Long firstQuestionId = questionList.get(0).getQuestionId();
        return "redirect:/testPanel/" + testCode + "/question/" + firstQuestionId;
      }
    } else {
      return "redirect:/?error";
    }
  }

  @GetMapping("/submit_test")
  public String submit_tests(Model model) {
      return "test_views/submit_test";
  }
  
  @PostMapping("/submit_test")
  public String submit_test(){

    return "redirect:/submit_test";
  }

}
