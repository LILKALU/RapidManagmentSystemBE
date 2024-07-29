package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification , Integer> {
    List<Notification> findByIsActive(Boolean isActive);
}