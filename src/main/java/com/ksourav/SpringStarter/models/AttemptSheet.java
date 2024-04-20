package com.ksourav.SpringStarter.models;

import java.util.Date;

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
public class AttemptSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long testAttemptId;

   // private Date testStartTime;

   // private Date testEndTime;

    private Long TestCode;

    private Long questionId;

    private String selectedOption;

    @Override
    public String toString() {
        return "AttemptSheet [testAttemptId=" + testAttemptId + ", TestCode=" + TestCode + ", questionId=" + questionId
                + ", selectedOption=" + selectedOption + "]";
    }

    
    

 
}
