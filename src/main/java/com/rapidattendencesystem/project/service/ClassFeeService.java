package com.rapidattendencesystem.project.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidattendencesystem.project.dto.ClassFeeCourseDTO;
import com.rapidattendencesystem.project.dto.ClassFeeDTO;
import com.rapidattendencesystem.project.entity.ClassFee;
import com.rapidattendencesystem.project.entity.ClassFeeCourse;
import com.rapidattendencesystem.project.repo.ClassFeeRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClassFeeService {
	
	@Autowired
	private ClassFeeRepo classFeeRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ClassFee addClassFee(ClassFeeDTO classfeeDTO) {
		
		if(classFeeRepo.existsById(classfeeDTO.getId())) {
			return null;
		}else {
			ClassFee clssFee = modelMapper.map(classfeeDTO, ClassFee.class);
			List<ClassFeeCourse> classFeeCourses = new ArrayList<>();
			for(ClassFeeCourseDTO classFeeCourse : classfeeDTO.getClassFeeCourse()) {
				ClassFeeCourse clsfeecourse = modelMapper.map(classFeeCourse, ClassFeeCourse.class);
				clsfeecourse.setClassFee(clssFee);
				classFeeCourses.add(clsfeecourse);
			}
			clssFee.setClassFeeCourse(classFeeCourses);
			clssFee.setDate(LocalDateTime.now());
			return classFeeRepo.save(clssFee);
		}
	}

}
