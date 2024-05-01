package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepo extends JpaRepository<Grade , Integer> {
    List<Grade> findByIsActive(Boolean active);
}
