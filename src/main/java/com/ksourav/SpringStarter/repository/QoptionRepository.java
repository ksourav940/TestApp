package com.ksourav.SpringStarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksourav.SpringStarter.models.Qoption;

@Repository
public interface QoptionRepository extends JpaRepository<Qoption,Long>{
    
}
