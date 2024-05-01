package com.rapidattendencesystem.project.repo;
import com.rapidattendencesystem.project.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rapidattendencesystem.project.entity.Course;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
    List<Course> findByIsActive(Boolean active);
}
