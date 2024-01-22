package com.ksourav.SpringStarter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksourav.SpringStarter.models.Test;
import com.ksourav.SpringStarter.repository.TestRepository;

@Service
public class TestService {
    
    @Autowired
    private TestRepository testRepository;

    public Optional<Test> getById(long id){

        return testRepository.findById(id);
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
}
