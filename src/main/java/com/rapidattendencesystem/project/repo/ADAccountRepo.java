package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.ADAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ADAccountRepo extends JpaRepository<ADAccount, Integer> {
}
