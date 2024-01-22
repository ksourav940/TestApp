package com.ksourav.SpringStarter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ksourav.SpringStarter.models.Test;
import com.ksourav.SpringStarter.services.TestService;


@Controller
public class AdminController {

   @Autowired
 private TestService testService;
 
     @GetMapping("/admin") 
     public String admin(Model model){
      Test test = new Test();
      model.addAttribute("test", test);
       return "admin";
     }

     @PostMapping("/admin")
     public String  addTest(@ModelAttribute Test test) {
         testService.save(test);
         return "redirect:/admin";
     }

      @DeleteMapping("/admin")
     public String  removeTest(@ModelAttribute Test test) {
        testService.delete(test);
         return "redirect:/admin";
     }

}
