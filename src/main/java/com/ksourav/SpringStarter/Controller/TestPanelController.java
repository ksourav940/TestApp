package com.ksourav.SpringStarter.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ksourav.SpringStarter.models.Test;
import com.ksourav.SpringStarter.services.TestService;

@Controller
public class TestPanelController {

    @Autowired
    private TestService testService;

    @GetMapping("/testPanel/{testCode}") 
public String startTest(@PathVariable Long testCode,Model model){
  Optional<Test> optionalTest = testService.getById(testCode); 
  if(optionalTest.isPresent()){
    Test test = optionalTest.get();
    model.addAttribute("test", test);
    return "testPanel";

  }else{
    return "404";
  }
}
    
}
