package com.ksourav.SpringStarter.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.ksourav.SpringStarter.models.Post;
import com.ksourav.SpringStarter.services.PostService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PostController {

    @Autowired
    private PostService postService ;
    
   

    @GetMapping("/post")
    public String getPost(Model model){

        List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);
        return "post_views/post";
    }

    

}
