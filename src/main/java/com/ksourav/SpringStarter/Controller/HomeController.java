package com.ksourav.SpringStarter.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ksourav.SpringStarter.models.Test;
import com.ksourav.SpringStarter.services.TestService;


@Controller
public class HomeController {
    
    @Autowired
    private TestService testService;
   
    @GetMapping("/home")
    public String home(Model model){

        List<Test> tests = testService.getAll();
        model.addAttribute("tests", tests);        
        return "home_views/home";
    }

     @GetMapping("/about")
    public String about(Model model){
        return "home_views/about";
    }

     @GetMapping("/service")
    public String service(Model model){
        return "service";
    }

    @GetMapping("/editor")
    public String editor(Model model){
        return "editor";
    }


}

