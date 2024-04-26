package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.SubjectDTO;
import com.rapidattendencesystem.project.dto.TeacherDTO;
import com.rapidattendencesystem.project.entity.Subject;
import com.rapidattendencesystem.project.entity.Teacher;
import com.rapidattendencesystem.project.repo.TeacherRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Teacher updateTeacher(TeacherDTO teacherDTO){
        try {
            Teacher teacher = modelMapper.map(teacherDTO , Teacher.class);
            if(teacherRepo.existsById(teacher.getId())){
                Teacher teacher2 = teacherRepo.save(teacher);
                System.out.println("Successfully Updated");
                return teacher2;
            }else{
                System.out.println("User Not Exist");
                return teacher;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Teacher addTeacher(TeacherDTO teacherDTO){
        try {
            Teacher teacher = modelMapper.map(teacherDTO , Teacher.class);
            if(teacherRepo.existsById(teacher.getId())){
                System.out.println("Already Added");
                return teacher;
            }else{
                Teacher teacher1 = teacherRepo.save(teacher);
                System.out.println("Successfully Saved");
                return teacher1;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<TeacherDTO> getTeachers(){
        try {
            return modelMapper.map(teacherRepo.findByIsActive(true) , new TypeToken<List<TeacherDTO>>() {}.getType());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
