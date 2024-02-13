package com.ksourav.SpringStarter.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ksourav.SpringStarter.models.Test;
import com.ksourav.SpringStarter.services.TestService;



@Controller
public class TestController {

    
 @Autowired
 private TestService testService;

@GetMapping("/test")
public String gettest(Model model) {

      List<Test> tests = testService.getAll();
        model.addAttribute("tests", tests);
        return "test";
}

@GetMapping("/test/{testCode}")
public String getTestById(@PathVariable Long testCode, Model model) {
  Optional<Test> optionalTest = testService.getById(testCode); 

  if(optionalTest.isPresent()){
    Test test = optionalTest.get();
    model.addAttribute("test", test);
    return "test_views/test";

  }else{
    return "404";
  }
    
}

// @GetMapping("/testPanel/{testCode}") 
// public String startTest(@PathVariable Long testCode,Model model){
//   Optional<Test> optionalTest = testService.getById(testCode); 
//   if(optionalTest.isPresent()){
//     Test test = optionalTest.get();
//     model.addAttribute("test", test);
//     return "testPanel";

//   }else{
//     return "404";
//   }
// }

//  @PostMapping("/testPanel/{testCode}")
//  public String start_test(@PathVariable Long testCode,Model model) { 
//      return "redirect:/testPanel";
//  }


   
}
