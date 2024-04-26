package com.rapidattendencesystem.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rapidattendencesystem.project.entity.Course;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherDTO {

    private int id;
    private String tCode;
    private String fullName;
    private String title;
    private String highestQulification;
    private String birthday;
    private Boolean isActive;
    private String contactNumber;
    private String email;
    private List<Course> course;
}
