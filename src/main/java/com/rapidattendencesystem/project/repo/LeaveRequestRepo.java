package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.ApprovingStatus;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.entity.LeaveRequest;
import com.rapidattendencesystem.project.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, Integer> {
    List<LeaveRequest> findLeaveRequestByApprovingStatusAndIsActiveOrderByCreatedDateTimeDesc(ApprovingStatus approvingStatus, Boolean isActive);
    List<LeaveRequest> findByIsActiveOrderByCreatedDateTimeDesc(Boolean isActive);
    List<LeaveRequest> findByIsActive(Boolean isActive);
    List<LeaveRequest> findByIsActiveAndTeacher_Tcode(Boolean isActive, String tcode);
}
