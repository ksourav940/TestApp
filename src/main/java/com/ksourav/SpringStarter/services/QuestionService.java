package com.ksourav.SpringStarter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksourav.SpringStarter.models.Question;
import com.ksourav.SpringStarter.repository.QuestionRepository;

@Service
public class QuestionService {
    
    @Autowired
    private QuestionRepository questionRepository;

    public Optional<Question> getById(long id){
        return questionRepository.findById(id);
    }

    public  List<Question> getAll(){

        return questionRepository.findAll();
    }

   

    public void delete(Question question){
        questionRepository.delete(question);
    }

    public Question save(Question question){
        return questionRepository.save(question);
    }

}
