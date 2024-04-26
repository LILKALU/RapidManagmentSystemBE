package com.rapidattendencesystem.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rapidattendencesystem.project.dto.ClassFeeDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.entity.ClassFee;
import com.rapidattendencesystem.project.service.ClassFeeService;

@RestController
@RequestMapping("api/v1/classfeectrl")
@CrossOrigin("*")
public class ClassFeeController {

	@Autowired
	private ClassFeeService classFeeService;
	
	@Autowired
	private ResponseDTO responseDTO;
	
	@PostMapping("/addclassfee")
	public ResponseEntity<ResponseDTO> addClassFee(@RequestBody ClassFeeDTO classFeeDTOs){
		try {
			System.out.println("enterd to ctrl");
			ClassFee classFees = classFeeService.addClassFee(classFeeDTOs);
			System.out.println("returned from service");
			if(classFees.getId()>0) {
				responseDTO.setCode("00");
				responseDTO.setContent(classFees);
				responseDTO.setMassage("Records Inserted");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
			}else {
				responseDTO.setCode("01");
				responseDTO.setContent(classFeeDTOs);
				responseDTO.setMassage("Bad Request");
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseDTO.setCode("01");
			responseDTO.setContent(null);
			responseDTO.setMassage(e.getMessage());
			return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
