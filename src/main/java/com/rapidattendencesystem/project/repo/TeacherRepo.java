package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher , Integer> {
    List<Teacher> findByIsActive(Boolean active);
}
