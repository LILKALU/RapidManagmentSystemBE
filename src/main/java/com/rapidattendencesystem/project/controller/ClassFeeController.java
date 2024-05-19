package com.rapidattendencesystem.project.controller;

import java.util.List;

import com.rapidattendencesystem.project.dto.GradeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/getclassfee")
	public ResponseEntity<ResponseDTO> getClassFee(){
		try{
			List<ClassFeeDTO> classfees = classFeeService.getClassFee();
			if(!classfees.isEmpty()){
				responseDTO.setCode("00");
				responseDTO.setMassage("Succuss");
				responseDTO.setContent(classfees);
				return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
			}else{
				responseDTO.setCode("01");
				responseDTO.setMassage("Grades Empty");
				responseDTO.setContent(null);
				return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e){
			responseDTO.setCode("02");
			responseDTO.setMassage(e.getMessage());
			responseDTO.setContent(null);
			return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
