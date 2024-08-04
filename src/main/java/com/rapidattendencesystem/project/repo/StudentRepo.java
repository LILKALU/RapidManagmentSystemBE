package com.rapidattendencesystem.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rapidattendencesystem.project.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    List<Student> findByIsActive(Boolean active);
    Student findStudentByScode(String scode);

    @Query("SELECT s FROM Student s JOIN s.enrolment e JOIN e.enrolmentCourses ec JOIN ec.course c WHERE c.teacher.id = :teacherId")
    List<Student> findAllByTeacherId(@Param("teacherId") int teacherId);

    @Query("SELECT s.email FROM Student s JOIN s.enrolment e JOIN e.enrolmentCourses ec JOIN ec.course c WHERE c.teacher.id = :teacherId")
    List<String> findEmailsByTeacherId(@Param("teacherId") int teacherId);
}
