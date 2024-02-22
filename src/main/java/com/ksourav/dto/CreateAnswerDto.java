package com.ksourav.dto;

import java.util.List;

import com.ksourav.SpringStarter.models.Answer;

import lombok.Data;

@Data
public class CreateAnswerDto {
    private List<Answer> answers;

    // default and parameterized constructor

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
}
