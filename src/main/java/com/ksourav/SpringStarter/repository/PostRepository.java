package com.ksourav.SpringStarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksourav.SpringStarter.models.Post;

@Repository
public  interface PostRepository extends JpaRepository<Post,Long> {

    
}
