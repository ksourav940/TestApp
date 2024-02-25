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
public class Question {
   
    @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long questionId;

    private String questionName;

    // @OneToMany(mappedBy ="question")
    // private List<Qoption> qoptions;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String correctAnswer;
    


      @ManyToOne
    @JoinColumn(name="testCode", referencedColumnName = "testCode",nullable = true)
    private Test test; 

    public String toString(){
      return questionId +" "+questionName+" " +optionA+" "+ optionB+""+ optionC+" "+ optionD+" "+ correctAnswer;
    }

}
