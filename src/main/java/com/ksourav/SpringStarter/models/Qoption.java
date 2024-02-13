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
public class Qoption {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long optionNumber;

    private String optionName;

     @ManyToOne
    @JoinColumn(name="questionId", referencedColumnName = "questionId",nullable = true)
    private Question question;

   

}
