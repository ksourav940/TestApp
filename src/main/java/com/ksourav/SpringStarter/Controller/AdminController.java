package com.ksourav.SpringStarter.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ksourav.SpringStarter.models.Qoption;
import com.ksourav.SpringStarter.models.Question;
import com.ksourav.SpringStarter.models.Test;
import com.ksourav.SpringStarter.services.AdminService;

@Controller
public class AdminController {

  @Autowired
  private AdminService adminService;

  @GetMapping("/admin")
  public String admin(Model model) {
    Test test = new Test();
    model.addAttribute("test", test);
    model.addAttribute("listoftest", adminService.getAll());
    return "admin_views/admin";
  }

  @PostMapping("/admin")
  public String addTesta(@ModelAttribute Test _test) {
    if (_test.getTestName() != "") {
      adminService.save(_test);
      return "redirect:/admin";
    } else {
      return "redirect:/admin";
    }
  }

  @GetMapping("/admin/{testCode}/addQuestion")
  public String addTest(@PathVariable Long testCode, Model model) {
    Optional<Test> optionalTest = adminService.getById(testCode);

    if (optionalTest.isPresent()) {
      //System.out.println("inside addquestion");
      Question question = new Question();
      model.addAttribute("question", question);
      Test showtest = optionalTest.get();
      model.addAttribute("showtest", showtest);
      return "admin_views/addQuestion";

    } else {
      return "404";
    }
  }

  @PostMapping("/admin/{testCode}/addQuestion")
  public String addQuestion(@PathVariable Long testCode, @ModelAttribute Question _question) {
    System.out.println("hellow world" + testCode);
    if (_question.getQuestionName() != "") {
      // _question.setTest(adminService.getById(testCode).get());
      adminService.saveQuestion(_question);
      return "redirect:/admin/{testCode}/addQuestion";
    } else {
      return "redirect:/admin/{testCode}/addQuestion";
    }
  }

  // @GetMapping("/admin/{testCode}/addQuestion/{questionId}/addOption")
  // public String addOption(@PathVariable Long questionId, Model model) {
  //   Optional<Question> optionalQuestiion = adminService.getQuestionById(questionId);

  //   if (optionalQuestiion.isPresent()) {
     
  //     Qoption qoption = new Qoption();
  //     model.addAttribute("qoption",qoption);
  //     Question showquestion = optionalQuestiion.get();
  //     model.addAttribute("showquestion", showquestion);
  //     return "admin_views/addOption";

  //   } else {
  //     return "404";
  //   }
  // }

  // @PostMapping("/admin/{testCode}/addQuestion/{questionId}/addOption")
  // public String addOption(@PathVariable Long questionId, @ModelAttribute Qoption _qoption) {
  // //  System.out.println("hellow world" + testCode);
  //   if (_qoption.getOptionName() != "") {
  //     _qoption.setQuestion(adminService.getQuestionById(questionId).get());
  //     adminService.saveQoption(_qoption);
  //     return "redirect:/admin/{testCode}/addQuestion/{questionId}/addOption";
  //   } else {
  //     return "redirect:/admin/{testCode}/addQuestion/{questionId}/addOption";
  //   }
  // }

  @DeleteMapping("/admin")
  public String removeTest(@ModelAttribute Test test) {
    adminService.delete(test);
    return "redirect:/admin";
  }

}
