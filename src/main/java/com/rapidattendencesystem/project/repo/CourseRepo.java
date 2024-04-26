package com.rapidattendencesystem.project.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rapidattendencesystem.project.entity.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
	
}
