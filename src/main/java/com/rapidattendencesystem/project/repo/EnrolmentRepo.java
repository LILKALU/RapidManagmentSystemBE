package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rapidattendencesystem.project.entity.Enrolment;

import java.util.List;

public interface EnrolmentRepo extends JpaRepository<Enrolment, Integer>{
}
