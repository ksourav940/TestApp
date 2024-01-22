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
public class Test {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long testCode ;

    private String testName ;

    @OneToMany(mappedBy ="test")
    private List<Question> questions;



    @ManyToOne
    @JoinColumn(name="account_id", referencedColumnName = "id",nullable = true)
    private Account account;

   
 
public String toSting(){
    return testCode +" " + testName ;
}


}
