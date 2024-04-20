package com.ksourav.SpringStarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksourav.SpringStarter.models.AttemptSheet;

public interface AttemptSheetRepository extends JpaRepository<AttemptSheet,Long>{
    
}
