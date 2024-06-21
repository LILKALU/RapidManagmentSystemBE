package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.TeacherPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherPaymentRepo  extends JpaRepository<TeacherPayment, Integer> {
}
