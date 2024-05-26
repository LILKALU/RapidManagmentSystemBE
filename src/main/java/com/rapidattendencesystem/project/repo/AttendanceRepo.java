package com.rapidattendencesystem.project.repo;


import com.rapidattendencesystem.project.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Year;
import java.util.List;

public interface AttendanceRepo extends JpaRepository<Attendance, Integer> {
    List<Attendance> findByCourseAndYearAndMonthAndDate(Course course, int year, Month month, int date);
    List<Attendance> findByCourseAndYearAndMonth(Course course, int year, Month month);
    int countByCourseAndYearAndMonthAndStudentAndIsAttend(Course course, int year, Month month, Student student, Boolean isAttend);
}
