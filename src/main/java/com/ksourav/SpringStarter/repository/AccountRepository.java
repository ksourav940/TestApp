package com.ksourav.SpringStarter.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksourav.SpringStarter.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{

    Optional<Account> findOneByEmailIgnoreCase(String email);
    
}
