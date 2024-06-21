package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher , Integer> {
    List<Teacher> findByIsActive(Boolean active);
    Teacher findTeacherByTcode(String tcode);

    @Query(value = "CALL FindCourseWiseTeacherPayment(:teacherId, :monthId)", nativeQuery = true )
    List<Object[]> getTeacherEarningsForMonthByCourseWise(@Param("teacherId") int teacherId, @Param("monthId") int monthId);
}
