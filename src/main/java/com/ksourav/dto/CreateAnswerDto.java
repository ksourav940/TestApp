package com.ksourav.dto;

import java.util.ArrayList;
import java.util.List;

import com.ksourav.SpringStarter.models.Answer;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CreateAnswerDto {
    private List<Answer> answers;

    // default and parameterized constructor

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
}
