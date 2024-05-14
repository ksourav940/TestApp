package com.ksourav.SpringStarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksourav.SpringStarter.models.AttemptSheet;
import java.util.List;
import java.util.Optional;


public interface AttemptSheetRepository extends JpaRepository<AttemptSheet,Long>{

   // Optional<Account> findOneByEmailIgnoreCase(String email);
   Optional<AttemptSheet> findByQuestionId(Long questionId);

    
}
