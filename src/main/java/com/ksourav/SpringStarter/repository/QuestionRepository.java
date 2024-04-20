package com.ksourav.SpringStarter.repository;



import java.util.ArrayList;
import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ksourav.SpringStarter.models.Question;

public  interface QuestionRepository extends JpaRepository<Question,Long>{

  
   

    
} 
