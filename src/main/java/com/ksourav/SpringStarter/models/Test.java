package com.ksourav.SpringStarter.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id")
    private List<Question> questions;

    private String timeDuration;

    private float testFullMarks;

public String toSting(){
    return testCode +" " + testName ;
}

}
