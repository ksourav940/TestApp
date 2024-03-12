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
public class Question {
   
    @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long questionId;

    private String questionName;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String correctAnswer;
    
    public String toString(){
      return questionId +" "+questionName+" " +optionA+" "+ optionB+""+ optionC+" "+ optionD+" "+ correctAnswer;
    }

}
