package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hallctrl")
@CrossOrigin("*")
public class HallController {

    @Autowired
    private HallService hallService;

    @Autowired
    private ResponseDTO responseDTO;

    @PutMapping("/updatehall")
    public ResponseEntity<ResponseDTO> updateHall(@RequestBody HallDTO hallDTO){
        try{
            Hall h1 = hallService.updateHall(hallDTO);
            if(h1.getId() > 0){
                responseDTO.setContent(h1);
                responseDTO.setMassage("Updated");
                responseDTO.setCode("00");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }else{
                responseDTO.setContent(hallDTO);
                responseDTO.setMassage("Error Occured");
                responseDTO.setCode("01");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setCode("02");
            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/deletehall")
    public ResponseEntity<ResponseDTO> deleteHall(@RequestBody HallDTO hallDTO){
        try{
            Hall h1 = hallService.updateHall(hallDTO);
            if(h1.getId() > 0){
                responseDTO.setContent(h1);
                responseDTO.setMassage("Deleted");
                responseDTO.setCode("00");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
            }else{
                responseDTO.setContent(hallDTO);
                responseDTO.setMassage("Error Occured");
                responseDTO.setCode("01");
                return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setCode("02");
            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/gethall")
    public ResponseEntity<ResponseDTO> getHall(){
        try{
            List<HallDTO> halls = hallService.getHalls();
            if(!halls.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Succuss");
                responseDTO.setContent(halls);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Halls Empty");
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

    @PostMapping("/addhall")
    public ResponseEntity<ResponseDTO> createHall(@RequestBody HallDTO hallDTO){
        try {
            Hall H1 = hallService.createHall(hallDTO);
            if(H1.getId()>0){
                responseDTO.setCode("00");
                responseDTO.setMassage("Hall Inserted");
                responseDTO.setContent(H1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(hallDTO);
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
