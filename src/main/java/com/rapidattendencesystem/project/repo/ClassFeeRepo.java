package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rapidattendencesystem.project.entity.ClassFee;

import java.util.List;
import java.util.Optional;

public interface ClassFeeRepo extends JpaRepository<ClassFee, Integer> {
    Optional<ClassFee> findTop1ByStudentAndClassFeeCourse_CourseOrderByClassFeeCourse_MonthAsc(Student student, Course course);
    Optional<ClassFee> findTop1ByStudentAndClassFeeCourse_CourseOrderByClassFeeCourse_MonthDesc(Student student, Course course);
}
