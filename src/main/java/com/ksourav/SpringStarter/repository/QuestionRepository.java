package com.ksourav.SpringStarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksourav.SpringStarter.models.Question;

public  interface QuestionRepository extends JpaRepository<Question,Long>{

    
} 
