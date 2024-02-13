package com.ksourav.SpringStarter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksourav.SpringStarter.models.Qoption;
import com.ksourav.SpringStarter.models.Question;
import com.ksourav.SpringStarter.models.Test;
import com.ksourav.SpringStarter.repository.QoptionRepository;
import com.ksourav.SpringStarter.repository.QuestionRepository;
import com.ksourav.SpringStarter.repository.TestRepository;

@Service
public class AdminService {
  
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QoptionRepository qoptionRepository;

    public Optional<Test> getById(long id){
        return testRepository.findById(id);
    }

    public Optional<Question> getQuestionById(long id){
        return questionRepository.findById(id);
    }



     public List<Test> getAll(){
        return testRepository.findAll();
    }

     public void delete(Test test){
        testRepository.delete(test);
     }

     public Test save(Test test){
      
        return  testRepository.save(test);
     }

     public Question saveQuestion(Question question){
      
        return  questionRepository.save(question);
     }

     public void saveQoption(Qoption _qoption) {
        if(_qoption.equals(null)){
            throw new UnsupportedOperationException("Unimplemented method 'saveQoption'");
        }else{
             qoptionRepository.save(_qoption);
        }
       

    }

    public void updateTest(Long id) {
        // throw new UnsupportedOperationException("Unimplemented method 'updateTest'");
            Test test = testRepository.getById(id);

          
        
    }

    
}
