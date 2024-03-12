package com.ksourav.SpringStarter.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Test {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long testCode ;

    private String testName ;

    private String testDecs ;

    private int noOfQuestion;

    private String timeDuration;

    private float testFullMarks;

public String toSting(){
    return testCode +" " + testName ;
}

}
