package com.rapidattendencesystem.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rapidattendencesystem.project.entity.Student;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    List<Student> findByIsActive(Boolean active);
}
