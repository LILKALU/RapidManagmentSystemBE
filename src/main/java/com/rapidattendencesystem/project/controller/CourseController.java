package com.rapidattendencesystem.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rapidattendencesystem.project.dto.CourseDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.service.CourseService;

@RestController
@RequestMapping("api/v1/coursectrl")
@CrossOrigin("*")
public class CourseController {
	
	@Autowired
	private CourseService courseService;

	@Autowired
	private ResponseDTO responseDTO;
	
	
	@GetMapping("/getcourses")
	public ResponseEntity<ResponseDTO> getCourses(){
		try {
			List<CourseDTO> courses = courseService.getCourses();
			if(!courses.isEmpty()) {
				responseDTO.setCode("00");
				responseDTO.setMassage("Success");
				responseDTO.setContent(courses);
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
				
			}else {
				responseDTO.setCode("01");
				responseDTO.setMassage("Courses Are Empty");
				responseDTO.setContent(null);
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseDTO.setCode("02");
			responseDTO.setMassage(e.getMessage());
			responseDTO.setContent(null);
			return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
