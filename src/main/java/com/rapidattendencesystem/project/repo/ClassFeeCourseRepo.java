package com.rapidattendencesystem.project.repo;


import com.rapidattendencesystem.project.entity.ClassFeeCourse;
import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassFeeCourseRepo extends JpaRepository<ClassFeeCourse, Integer> {
    ClassFeeCourse findTop1ByCourseAndClassFee_StudentOrderByMonthDesc(Course course, Student student);
    ClassFeeCourse findTop1ByCourseAndClassFee_StudentOrderByMonthAsc(Course course, Student student);
}
