package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.CourseDTO;
import com.rapidattendencesystem.project.dto.EnrolmentCourseDTO;
import com.rapidattendencesystem.project.dto.GradeDTO;
import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.EnrolmentCourse;
import com.rapidattendencesystem.project.repo.EnrolmentCourseRepo;
import com.rapidattendencesystem.project.repo.GradeRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EnrolmentCourseService {
    @Autowired
    private EnrolmentCourseRepo enrolmentCourseRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<EnrolmentCourseDTO> getEnrolmentCourse(){
        try{
            return modelMapper.map(enrolmentCourseRepo.findByIsActive(true), new TypeToken<List<EnrolmentCourseDTO>>() {}.getType()) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public EnrolmentCourse deleteEnrolmentCourse(EnrolmentCourseDTO enrolmentCourseDTO){
        try{
            EnrolmentCourse EC1 = modelMapper.map(enrolmentCourseDTO , EnrolmentCourse.class);
            return enrolmentCourseRepo.save(EC1);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<EnrolmentCourse> deleteEnrolmentCourses(List<EnrolmentCourseDTO> enrolmentCourseDTO){
        try{
            List<EnrolmentCourse> EC1 = modelMapper.map(enrolmentCourseDTO, new TypeToken<List<EnrolmentCourse>>() {}.getType());
            return enrolmentCourseRepo.saveAll(EC1);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
