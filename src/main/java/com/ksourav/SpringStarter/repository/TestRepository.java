package com.ksourav.SpringStarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksourav.SpringStarter.models.Test;



@Repository
public interface TestRepository extends JpaRepository<Test, Long>{
    
}
