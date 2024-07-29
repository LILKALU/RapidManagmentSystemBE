package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.NotificationDTO;
import com.rapidattendencesystem.project.dto.StudentDTO;
import com.rapidattendencesystem.project.entity.Notification;
import com.rapidattendencesystem.project.repo.NotificationRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<NotificationDTO> getAllNotification(){
        try{
            List<Notification> notifications = notificationRepo.findByIsActive(true);
            return modelMapper.map(notifications, new TypeToken<List<NotificationDTO>>() {}.getType()) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public NotificationDTO addNotification(NotificationDTO notificationDTO){
        try {
            Notification notification = modelMapper.map(notificationDTO , Notification.class);
            NotificationDTO notificationDTO1 = modelMapper.map(notificationRepo.save(notification), NotificationDTO.class);
            return notificationDTO1;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
