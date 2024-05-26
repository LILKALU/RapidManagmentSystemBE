package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.EmailDataDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.service.CourseService;
import com.rapidattendencesystem.project.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/emailctrl")
@CrossOrigin("*")
public class EmailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/sendreciptemail")
    public ResponseEntity<ResponseDTO> sendReceiptEmail(@RequestBody EmailDataDTO emailData){
        try{
            String res = mailService.SendEmailWithAttachment(emailData);
            if(res.equals("Message Sent")){
                responseDTO.setCode("00");
                responseDTO.setContent(res);
                responseDTO.setMassage(res);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setContent(res);
                responseDTO.setMassage(res);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setContent(e.getMessage());
            responseDTO.setMassage(e.getMessage());
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
