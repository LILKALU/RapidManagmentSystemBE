package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.EnrolmentCourse;
import com.rapidattendencesystem.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrolmentCourseRepo extends JpaRepository<EnrolmentCourse,Integer> {
    List<EnrolmentCourse> findByIsActive(Boolean active);
    List<EnrolmentCourse> findByEnrolment_Student(Student student);
}
