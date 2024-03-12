package com.ksourav.SpringStarter.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import com.ksourav.SpringStarter.models.Account;
import com.ksourav.SpringStarter.services.AccountService;
import com.ksourav.SpringStarter.util.constants.AppUtil;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Value("${spring.mvc.static-path-pattern}")
    private String photo_prefix;

    @GetMapping("/register")
    public String registerUser(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "account_views/register";
    }

    @PostMapping("/register")
    public String register_user(@ModelAttribute Account account, BindingResult result) {
        if (result.hasErrors()) {
            return "account_views/register";
        }
        accountService.save(account);
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "account_views/login";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "account_views/logout";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model, Principal principal) {
        String authUser = "email";
        if (principal != null) {
            authUser = principal.getName();
        }
        Optional<Account> optionalAccount = accountService.findOneByEmail(authUser);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            model.addAttribute("account", account);
            model.addAttribute("photo", account.getPhoto());
            return "account_views/profile";
        } else {
            return "redirect:/?error";
        }
    }

    @PostMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String post_profile(@ModelAttribute Account account, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "account_views/profile";
        }
        String authUser = "email";
        if (principal != null) {
            authUser = principal.getName();
        }
        Optional<Account> optionalAccount = accountService.findOneByEmail(authUser);
        if (optionalAccount.isPresent()) {
            Account account_by_id = accountService.findById(account.getId()).get();
            account_by_id.setFirstname(account.getFirstname());
            account_by_id.setLastname(account.getLastname());
            account_by_id.setPassword(account.getPassword());
            account_by_id.setAge(account.getAge());
            account_by_id.setDate_of_birth(account.getDate_of_birth());
            account_by_id.setGender(account.getGender());
            accountService.save(account_by_id);
            SecurityContextHolder.clearContext();
            return "redirect:/home";
        } else {
            return "redirect:/?error";
        }

    }

    @PostMapping("/update_photo")
    @PreAuthorize("isAuthenticated")
    public String update_photo(@RequestParam("file") MultipartFile file,
            RedirectAttributes attributes, Principal principal) {
        if (file.isEmpty()) {
            attributes.addFlashAttribute("error", "No file uploaded");
            return "redirect:/profile";
        } else {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                int length = 10;
                boolean useLetters = true;
                boolean useNumbers = true;
                String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
                String final_photo_name = generatedString + fileName;
                String absolute_fileLocation = AppUtil.get_upload_path(final_photo_name);
                Path path = Paths.get(absolute_fileLocation);

                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                attributes.addFlashAttribute("message","you successfully Uploaded");

                String authUser = "email";
                if (principal != null) {
                    authUser = principal.getName();
                }
                Optional<Account> optionalAcccount = accountService.findOneByEmail(authUser);
                if (optionalAcccount.isPresent()) {
                    Account account = optionalAcccount.get();
                    Account account_by_id = accountService.findById(account.getId()).get();
                    String relative_fileLocation = photo_prefix.replace("**", "uploads/" + final_photo_name);
                    account_by_id.setPhoto(relative_fileLocation);
                    accountService.save(account_by_id);
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                return "redirect:/profile";

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return "redirect:/profile?error";
    }

    @GetMapping("/sample")
    public String sample(Model model) {
        return "sample";
    }
}
