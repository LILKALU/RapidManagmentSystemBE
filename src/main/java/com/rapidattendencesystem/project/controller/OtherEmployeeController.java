package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.OtherEmployeeDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.service.HallService;
import com.rapidattendencesystem.project.service.OtherEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/otheremployee")
@CrossOrigin("*")
public class OtherEmployeeController {
    @Autowired
    private OtherEmployeeService otherEmployeeService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/addemployee")
    public ResponseEntity<ResponseDTO> createemployee(@RequestBody OtherEmployeeDTO otherEmployeeDTO){
        try {
            OtherEmployeeDTO o1 = otherEmployeeService.addEmployee(otherEmployeeDTO);
            if(o1.getId()>0){
                responseDTO.setCode("00");
                responseDTO.setMassage("Hall Inserted");
                responseDTO.setContent(o1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(otherEmployeeDTO);
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
