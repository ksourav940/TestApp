package com.ksourav.SpringStarter.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long answerId;

    private long questionId;

    private String selectedOption;

    //   @ManyToOne
    // @JoinColumn(name="testPanel", referencedColumnName = "testAttemptId",nullable = true)
    // private TestPanel testPanel; 
    
}
