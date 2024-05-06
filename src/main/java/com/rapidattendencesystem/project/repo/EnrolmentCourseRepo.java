package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.EnrolmentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrolmentCourseRepo extends JpaRepository<EnrolmentCourse,Integer> {
    List<EnrolmentCourse> findByIsActive(Boolean active);
}
