package com.ksourav.SpringStarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksourav.SpringStarter.models.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long>{
    
}
