package com.rapidattendencesystem.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rapidattendencesystem.project.entity.Enrolment;

public interface EnrolmentRepo extends JpaRepository<Enrolment, Integer>{

}
