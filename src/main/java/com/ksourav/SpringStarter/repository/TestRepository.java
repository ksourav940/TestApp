package com.ksourav.SpringStarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksourav.SpringStarter.models.Test;
import java.util.List;




@Repository
public interface TestRepository extends JpaRepository<Test, Long>{

    
    
}
