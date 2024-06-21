package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.MonthWiseIncome;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.service.TeacherPaymentCourseService;
import com.rapidattendencesystem.project.service.TeacherPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/teacherpaymentcoursectrl")
@CrossOrigin("*")
public class TeacherPaymentCourseController {

    @Autowired
    private TeacherPaymentCourseService teacherPaymentCourseService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/getteacherpaymentbymonth")
    public ResponseEntity<ResponseDTO> getTeacherPaymentByMonth(){
        try {

            List<MonthWiseIncome> teacherPaymentByMonth = teacherPaymentCourseService.getTeacherPaymentBymonth();
            System.out.println("returned from service");
            if(!teacherPaymentByMonth.isEmpty()) {
                responseDTO.setCode("00");
                responseDTO.setContent(teacherPaymentByMonth);
                responseDTO.setMassage("Success");
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            }else {
                responseDTO.setCode("01");
                responseDTO.setContent(null);
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
