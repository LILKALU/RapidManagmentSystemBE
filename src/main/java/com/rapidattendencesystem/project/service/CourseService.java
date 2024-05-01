package com.rapidattendencesystem.project.service;

import java.util.List;

import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Hall;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidattendencesystem.project.dto.CourseDTO;
import com.rapidattendencesystem.project.repo.CourseRepo;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseService {
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private ModelMapper modalMapper;
	
	public List<CourseDTO> getCourses() {
		return modalMapper.map(courseRepo.findByIsActive(true), new TypeToken<List<CourseDTO>>() {}.getType()) ;
	}

	public Course deleteCourse(CourseDTO courseDTO){
		try{
			Course C1 = modalMapper.map(courseDTO , Course.class);
			return courseRepo.save(C1);
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Course updateCourse(CourseDTO courseDTO){
		try{
			Course C1 = modalMapper.map(courseDTO , Course.class);
			return courseRepo.save(C1);
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Course createCourse(CourseDTO courseDTO){
		try {
			Course C1 = modalMapper.map(courseDTO, Course.class);
			if(courseRepo.existsById(C1.getId())){
				System.out.println("user exist");
				return C1;
			}else{
				Course C2 = courseRepo.save(C1);
				System.out.println(C2);
				return C2;
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
            return modalMapper.map(courseDTO, Course.class);
		}
	}

}
