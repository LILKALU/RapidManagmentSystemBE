package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Answers;
import com.rapidattendencesystem.project.entity.Assignment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionDTO {

    private int id;
    private String description;
    private Assignment assignment;
    private List<Answers> answers;
    private Answers correctAnswer;
}
