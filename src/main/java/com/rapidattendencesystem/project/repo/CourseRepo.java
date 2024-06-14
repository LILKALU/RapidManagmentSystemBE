package com.rapidattendencesystem.project.repo;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rapidattendencesystem.project.entity.Course;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
    List<Course> findByIsActive(Boolean active);

    @Query("SELECT c FROM Course c WHERE c.teacher.id = :teacherId AND c.isActive = true")
    List<Course> findAllByTeacherId(@Param("teacherId") int teacherId);
}
