package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {
    Manager findManagerByMcode(String mcode);
}
