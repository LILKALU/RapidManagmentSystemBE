package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemAdminRepo extends JpaRepository<SystemAdmin, Integer> {
    SystemAdmin findSystemAdminBySyscode(String syscode);
}
