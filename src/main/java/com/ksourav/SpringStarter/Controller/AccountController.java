package com.ksourav.SpringStarter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ksourav.SpringStarter.models.Account;
import com.ksourav.SpringStarter.services.AccountService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class AccountController {

@Autowired
private AccountService accountService;

    @GetMapping("/register") 
     public String registerUser(Model model){
        Account account = new Account();
        model.addAttribute("account", account); 
       return "register";
     }

     @PostMapping("/register")
     public String register_user(@ModelAttribute Account account) {
         accountService.save(account);
         return "redirect:/home";
     }

     @GetMapping("/login")
     public String login(Model model) {
         return "login";
     }

     @GetMapping("/logout")
     public String logout(Model model) {
         return "logout";
     }

      @GetMapping("/profile")
     public String profile(Model model) {
         return "profile";
     }

     @GetMapping("/sample")
     public String sample(Model model) {
         return "sample";
     } 
}
