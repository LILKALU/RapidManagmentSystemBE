package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.GradeDTO;
import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/gradectrl")
@CrossOrigin("*") //allow to access this controller from outside
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/getgrades")
    public ResponseEntity<ResponseDTO> getGrades(){
        try{
            List<GradeDTO> grades = gradeService.getGrades();
            if(!grades.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Succuss");
                responseDTO.setContent(grades);
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
