package com.ksourav.SpringStarter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksourav.SpringStarter.models.AttemptSheet;
import com.ksourav.SpringStarter.repository.AttemptSheetRepository;

@Service
public class TestPanelService {

    @Autowired
    private AttemptSheetRepository attemptSheetRepository;
 
    public void save(AttemptSheet attemptSheet){

     attemptSheetRepository.save(attemptSheet);
    }

    public Optional<AttemptSheet> getAttemptSheetByQuestionId(Long questionId){
       
        return attemptSheetRepository.findByQuestionId(questionId);
    }

}
