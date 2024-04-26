package com.rapidattendencesystem.project.service;

import java.util.List;

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
		return modalMapper.map(courseRepo.findAll(), new TypeToken<List<CourseDTO>>() {}.getType()) ;
	}

}
