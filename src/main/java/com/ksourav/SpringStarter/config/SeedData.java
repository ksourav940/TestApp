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
            test01.setAccount(account01);

            testService.save(test01);

            Test test02 = new Test();
            test02.setTestName("Python");
            test02.setAccount(account02);

            testService.save(test02);

            List<Question> questions = questionService.getAll();
            if (questions.size() == 0) {

                Question question01 = new Question();
                question01.setQuestionName("first question?");
                question01.setOptionA("first option FOR FIRST");
                question01.setOptionB("Second option FOR FIRST");
                question01.setOptionC("third option");
                question01.setOptionD("forth option");
                question01.setCorrectAnswer("forth option");
                question01.setTest(test01);
                questionService.save(question01);

                Question question02 = new Question();
                question02.setQuestionName("Second question?");
                question02.setOptionA("first option FOR SECOND");
                question02.setOptionB("Second option FOR SECOND");
                question02.setOptionC("third option");
                question02.setOptionD("forth option");
                question02.setCorrectAnswer("forth option");

                question02.setTest(test01);
                questionService.save(question02);

                // Question question03 = new Question();
                // question03.setQuestionName("third question");
                // question03.setTest(test01);
                // questionService.save(question03);

                // Question question04 = new Question();
                // question04.setQuestionName("Forth question");
                // question04.setTest(test01);
                // questionService.save(question04);

                Question question05 = new Question();
                question05.setQuestionName("first question");
                question05.setOptionA("first option");
                question05.setOptionB("Second option");
                question05.setOptionC("third option");
                question05.setOptionD("forth option");
                question05.setCorrectAnswer("forth option");
                question05.setTest(test02);
                questionService.save(question05);

                Question question06 = new Question();
                question06.setQuestionName("Second question");
                question06.setOptionA("first option");
                question06.setOptionB("Second option");
                question06.setOptionC("third option");
                question06.setOptionD("forth option");
                question06.setCorrectAnswer("forth option");
                question06.setTest(test02);
                questionService.save(question06);

                // Question question07 = new Question();
                // question07.setQuestionName("third question");
                // question07.setTest(test02);
                // questionService.save(question07);

                // Question question08 = new Question();
                // question08.setQuestionName("Forth question");
                // question08.setTest(test02);
                // questionService.save(question08);

                // Qoption qoptionA = new Qoption();
                // qoptionA.setOptionNumber(1);
                // qoptionA.setOptionName("optionA");
                // qoptionA.setQuestion(question01);
                // qoptionService.save(qoptionA);

                // Qoption qoptionB = new Qoption();
                // qoptionB.setOptionNumber(2);
                // qoptionB.setOptionName("optionB");
                // qoptionB.setQuestion(question01);
                // qoptionService.save(qoptionB);

                // Qoption qoptionC = new Qoption();
                // qoptionC.setOptionNumber(3);
                // qoptionC.setOptionName("optionC");
                // qoptionC.setQuestion(question01);
                // qoptionService.save(qoptionC);

                // Qoption qoptionD = new Qoption();
                // qoptionD.setOptionNumber(4);
                // qoptionD.setOptionName("optionD");
                // qoptionD.setQuestion(question01);
                // qoptionService.save(qoptionD);

                // Qoption qoptionE = new Qoption();
                // qoptionA.setOptionNumber(5);
                // qoptionA.setOptionName("optionA");
                // qoptionA.setQuestion(question02);
                // qoptionService.save(qoptionA);

                // Qoption qoptionF = new Qoption();
                // qoptionB.setOptionNumber(6);
                // qoptionB.setOptionName("optionB");
                // qoptionB.setQuestion(question02);
                // qoptionService.save(qoptionB);

                // Qoption qoptionG = new Qoption();
                // qoptionC.setOptionNumber(7);
                // qoptionC.setOptionName("optionC");
                // qoptionC.setQuestion(question02);
                // qoptionService.save(qoptionC);

                // Qoption qoptionH = new Qoption();
                // qoptionD.setOptionNumber(8);
                // qoptionD.setOptionName("optionD");
                // qoptionD.setQuestion(question02);
                // qoptionService.save(qoptionD);

            }

            // throw new UnsupportedOperationException("Unimplemented method 'run'");
        }

    }

}
