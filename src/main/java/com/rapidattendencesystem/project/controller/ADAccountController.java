package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.ADAccountDTO;
import com.rapidattendencesystem.project.dto.LoginDetailsDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.entity.ADAccount;
import com.rapidattendencesystem.project.service.ADAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/adaccountctrl")
@CrossOrigin("*")
public class ADAccountController {

    @Autowired
    private ADAccountService adAccountService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/createuseraccount")
    public ResponseEntity<ResponseDTO> createUserAccount(@RequestBody ADAccountDTO adAccountDTO){
        try {
            System.out.println(adAccountDTO);
            ADAccount A1 = adAccountService.createUserAccount(adAccountDTO);
            System.out.println(A1);
            if(A1.getId()>0){
                responseDTO.setCode("00");
                responseDTO.setMassage("Account Created");
                responseDTO.setContent(A1);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Error Occered");
                responseDTO.setContent(adAccountDTO);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/checklogin")
    public ResponseEntity<ResponseDTO> checklogin(@RequestBody ADAccountDTO adAccountDTO){
        try {
            System.out.println(adAccountDTO);
            LoginDetailsDTO A1 = adAccountService.checkLogin(adAccountDTO);
            System.out.println(A1);
            if(!A1.getFullName().isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Account Created");
                responseDTO.setContent(A1);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Error Occered");
                responseDTO.setContent(adAccountDTO);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
