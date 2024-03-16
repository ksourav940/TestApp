package com.ksourav.SpringStarter.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ksourav.SpringStarter.models.Account;
import com.ksourav.SpringStarter.models.Authority;
import com.ksourav.SpringStarter.models.Question;
import com.ksourav.SpringStarter.models.Test;
import com.ksourav.SpringStarter.services.AccountService;
import com.ksourav.SpringStarter.services.AuthorityService;
import com.ksourav.SpringStarter.services.QuestionService;
import com.ksourav.SpringStarter.services.TestService;
import com.ksourav.SpringStarter.util.constants.Privillages;
import com.ksourav.SpringStarter.util.constants.Roles;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private TestService testService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private QuestionService questionService;



    @Override
    public void run(String... args) throws Exception {

        for (Privillages auth : Privillages.values()) {
            Authority authority = new Authority();
            authority.setId(auth.getId());
            authority.setName(auth.getPrivillages());
            authorityService.save(authority);
        }

        Account account01 = new Account();
        Account account02 = new Account();
        Account account03 = new Account();
        Account account04 = new Account();

        account01.setEmail("user01@user.com");
        account01.setPassword("password");
        account01.setFirstname("user01");
        account01.setLastname("sir");

        account02.setEmail("admin01@admin.com");
        account02.setPassword("password");
        account02.setFirstname("admin01");
        account02.setLastname("ji");
        account02.setRole(Roles.ADMIN.getRole());

        account03.setEmail("editor@editor.com");
        account03.setPassword("password");
        account03.setFirstname("user01");
        account03.setLastname("sir");
        account03.setRole(Roles.EDITOR.getRole());

        account04.setEmail("super_user01@gmail.com");
        account04.setPassword("password");
        account04.setFirstname("user01");
        account04.setLastname("sir");
        account04.setRole(Roles.EDITOR.getRole());

        Set<Authority> authorities = new HashSet<Authority>();
        authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
        authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);
        account04.setAuthorities(authorities);
        account01.setAuthorities(authorities);
        account02.setAuthorities(authorities);
        account03.setAuthorities(authorities);

        accountService.save(account01);
        accountService.save(account02);
        accountService.save(account03);
        accountService.save(account04);

        List<Test> tests = testService.getAll();
        if (tests.size() == 0) {
            Test test01 = new Test();
            test01.setTestName("Java");
            test01.setTestDecs("Java test for beginer");
            test01.setNoOfQuestion(10);
            test01.setTestFullMarks(10);
            test01.setTimeDuration("10");
            

            testService.save(test01);

            Test test02 = new Test();
            test02.setTestName("Python");
            test02.setTestDecs("Java test for beginer");
            test02.setNoOfQuestion(10);
            test02.setTestFullMarks(10);
            test02.setTimeDuration("10");

            testService.save(test02);

        }

    }

}
