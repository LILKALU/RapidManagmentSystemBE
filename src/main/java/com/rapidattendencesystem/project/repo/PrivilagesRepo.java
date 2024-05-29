package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Privilages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivilagesRepo extends JpaRepository<Privilages, Integer> {
    List<Privilages> findByIsActive(Boolean isActive);
}
