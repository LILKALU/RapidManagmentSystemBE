package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswersRepo  extends JpaRepository<Answers, Integer> {
}
