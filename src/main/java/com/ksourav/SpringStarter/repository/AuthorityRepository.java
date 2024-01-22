package com.ksourav.SpringStarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksourav.SpringStarter.models.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long>{ 
    
}
