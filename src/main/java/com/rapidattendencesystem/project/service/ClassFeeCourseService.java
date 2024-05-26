package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.CourseWiseClassFeeDTO;
import com.rapidattendencesystem.project.dto.StudentCourseDTO;
import com.rapidattendencesystem.project.entity.ClassFee;
import com.rapidattendencesystem.project.entity.ClassFeeCourse;
import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Student;
import com.rapidattendencesystem.project.repo.ClassFeeCourseRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClassFeeCourseService {

    @Autowired
    private ClassFeeCourseRepo classFeeCourseRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<CourseWiseClassFeeDTO> findLastByStudentAndCourse(List<StudentCourseDTO> studentCourseDTO){
        try{
            List<CourseWiseClassFeeDTO> courseWiseClassFeeDTOs = new ArrayList<>();
            for (StudentCourseDTO studentCourseDTO1 : studentCourseDTO){
                Course c1 = modelMapper.map(studentCourseDTO1.getCourse() , Course.class);
                Student s1 = modelMapper.map(studentCourseDTO1.getStudent() , Student.class);

                ClassFeeCourse cc1 =  classFeeCourseRepo.findTop1ByCourseAndClassFee_StudentOrderByMonthDesc(c1,s1);
                CourseWiseClassFeeDTO courseWiseClassFeeDTO = new CourseWiseClassFeeDTO();
                courseWiseClassFeeDTO.setCourse(c1);
                courseWiseClassFeeDTO.setClassFeeCourse(cc1);
                courseWiseClassFeeDTOs.add(courseWiseClassFeeDTO);
            }
            if (!courseWiseClassFeeDTOs.isEmpty()){
                return courseWiseClassFeeDTOs;
            }else{
                return null;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<CourseWiseClassFeeDTO> findFirstByStudentAndCourse(List<StudentCourseDTO> studentCourseDTO){
        try{
            List<CourseWiseClassFeeDTO> courseWiseClassFeeDTOs = new ArrayList<>();
            for (StudentCourseDTO studentCourseDTO1 : studentCourseDTO){
                Course c1 = modelMapper.map(studentCourseDTO1.getCourse() , Course.class);
                Student s1 = modelMapper.map(studentCourseDTO1.getStudent() , Student.class);

                ClassFeeCourse cc1 =  classFeeCourseRepo.findTop1ByCourseAndClassFee_StudentOrderByMonthAsc(c1,s1);
                CourseWiseClassFeeDTO courseWiseClassFeeDTO = new CourseWiseClassFeeDTO();
                courseWiseClassFeeDTO.setCourse(c1);
                courseWiseClassFeeDTO.setClassFeeCourse(cc1);
                courseWiseClassFeeDTOs.add(courseWiseClassFeeDTO);
            }

            if (!courseWiseClassFeeDTOs.isEmpty()){
                return courseWiseClassFeeDTOs;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
