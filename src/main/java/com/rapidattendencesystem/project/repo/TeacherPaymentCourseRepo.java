package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.TeacherPaymentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherPaymentCourseRepo  extends JpaRepository<TeacherPaymentCourse, Integer> {
    @Query("SELECT tpc.month.id, SUM(tpc.amount) FROM TeacherPaymentCourse tpc WHERE tpc.teacherPayment.teacher.isActive = true GROUP BY tpc.month.id")
    List<Object[]> getTeacherPaymentByMonth();
}
