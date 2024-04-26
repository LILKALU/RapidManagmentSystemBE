package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepo extends JpaRepository<Subject,Integer> {
    List<Subject> findByIsActive(Boolean active);
}
