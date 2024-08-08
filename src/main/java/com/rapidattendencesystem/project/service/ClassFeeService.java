package com.rapidattendencesystem.project.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rapidattendencesystem.project.dto.*;
import com.rapidattendencesystem.project.entity.*;
import com.rapidattendencesystem.project.repo.ClassFeeCourseRepo;
import com.rapidattendencesystem.project.repo.MonthRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidattendencesystem.project.repo.ClassFeeRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClassFeeService {
	
	@Autowired
	private ClassFeeRepo classFeeRepo;

	@Autowired
	private MonthRepo monthRepo;

	@Autowired
	private ClassFeeCourseRepo classFeeCourseRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	public List<CourseWiseMonthDTO> getFirstPaidClassFee(StudentWiseCourseDTO studentWiseCourseDTO){
		try{
			Student student = studentWiseCourseDTO.getStudent();
			List<Course> courses = studentWiseCourseDTO.getCourses();
			List<CourseWiseMonthDTO> courseWiseMonthDTOS = new ArrayList<>();

			for(Course course : courses){
				List<Month> PaiedMonth = classFeeRepo.findTop1MonthByStudentAndCourseOrderByMonthAsc(student, course);

				CourseWiseMonthDTO courseWiseMonthDTO = new CourseWiseMonthDTO();

				courseWiseMonthDTO.setCourse(course);
				courseWiseMonthDTO.setMonth(PaiedMonth.get(0));
				courseWiseMonthDTOS.add(courseWiseMonthDTO);
			}

			return courseWiseMonthDTOS;
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<CourseWiseMonthsDTO> getUnpaidMonths(StudentWiseCourseDTO studentWiseCourseDTO){
		try{
			List<Month> allMonths = monthRepo.findAll();
			Student student = studentWiseCourseDTO.getStudent();
			List<Course> courses = studentWiseCourseDTO.getCourses();
			List<CourseWiseMonthsDTO> courseWiseMonthsDTOS = new ArrayList<>();

			for(Course course : courses){
				List<Month> paidMonths = classFeeRepo.findPayedMonthByStudentAndCourse(student,course);
				ClassFeeCourse classFeeCourse = classFeeCourseRepo.findTop1ByCourseAndClassFee_StudentOrderByMonthAsc(course,student);
				List<Month> unPaidMonths = new ArrayList<>();
				Month firstPaidMonth = classFeeCourse.getMonth();

				for(Month month : allMonths){
					if(!(paidMonths.contains(month)) && firstPaidMonth.getId() < month.getId()){
						unPaidMonths.add(month);
					}
				}
				CourseWiseMonthsDTO courseWiseMonthsDTO = new CourseWiseMonthsDTO();

				courseWiseMonthsDTO.setMonths(unPaidMonths);
				courseWiseMonthsDTO.setCourse(course);
				courseWiseMonthsDTOS.add(courseWiseMonthsDTO);

			}

			return courseWiseMonthsDTOS;
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Optional<ClassFee> findFirstByStudentAndCourse(StudentCourseDTO studentCourseDTO){
		try{
			Course c1 = modelMapper.map(studentCourseDTO.getCourse() , Course.class);
			Student s1 = modelMapper.map(studentCourseDTO.getStudent() , Student.class);
			return classFeeRepo.findTop1ByStudentAndClassFeeCourse_CourseOrderByClassFeeCourse_MonthAsc(s1,c1);
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Optional<ClassFee> findLastByStudentAndCourse(StudentCourseDTO studentCourseDTO){
		try{
			Course c1 = modelMapper.map(studentCourseDTO.getCourse() , Course.class);
			Student s1 = modelMapper.map(studentCourseDTO.getStudent() , Student.class);
			return classFeeRepo.findTop1ByStudentAndClassFeeCourse_CourseOrderByClassFeeCourse_MonthDesc(s1,c1);
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public ClassFee addClassFee(ClassFeeDTO classfeeDTO) {
		try{
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
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}

	}

	public List<ClassFeeDTO> getClassFee(){
		try{
			return modelMapper.map(classFeeRepo.findAll(), new TypeToken<List<ClassFeeDTO>>() {}.getType()) ;
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

}
