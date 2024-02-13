package com.ksourav.SpringStarter.models;


import java.util.List;

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
public class Question {
   
    @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long questionId;

    private String questionName;

    @OneToMany(mappedBy ="question")
    private List<Qoption> qoptions;

    

      @ManyToOne
    @JoinColumn(name="testCode", referencedColumnName = "testCode",nullable = true)
    private Test test; 


}
