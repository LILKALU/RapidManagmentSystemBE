package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.dto.AttendanceDTO;
import com.rapidattendencesystem.project.dto.AttendanceSearchDTO;
import com.rapidattendencesystem.project.dto.CourseDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.entity.Attendance;
import com.rapidattendencesystem.project.service.AttendanceService;
import com.rapidattendencesystem.project.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/attendancectrl")
@CrossOrigin("*") //allow to access this controller from outside
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/getallattendance")
    public ResponseEntity<ResponseDTO> getAllAtttendance(){
        try{
            List<AttendanceDTO> attendances = attendanceService.getAllAttendance();
            if(!attendances.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(attendances);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("01");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getattendancebycourseandyearandmonth")
    public ResponseEntity<ResponseDTO> getAttendanceByCourseAndYearAndMonth(@RequestBody AttendanceSearchDTO AttendanceSearchDTO){
        try{
            List<AttendanceDTO> attendances = attendanceService.getAttendanceByCourseAndYearAndMonth(AttendanceSearchDTO);
            if(!attendances.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(attendances);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("No Attendance");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setCode("01");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getattendancebycourseanddate")
    public ResponseEntity<ResponseDTO> getAttendanceByCourseAndDate(@RequestBody AttendanceSearchDTO AttendanceSearchDTO){
        try{
            List<AttendanceDTO> attendances = attendanceService.getAttendanceByCourseAndDate(AttendanceSearchDTO);
            if(!attendances.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Success");
                responseDTO.setContent(attendances);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("No Attendance");
                responseDTO.setContent(null);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setCode("01");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateattendance")
    public ResponseEntity<ResponseDTO> updateAttendance(@RequestBody List<AttendanceDTO> attendanceDTOS){
        try{
            List<Attendance> attendances = attendanceService.updateAttendance(attendanceDTOS);
            if(!attendances.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Attendance Updated");
                responseDTO.setContent(attendances);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(attendanceDTOS);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("01");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/markattendance")
    public ResponseEntity<ResponseDTO> markAttendance(@RequestBody List<AttendanceDTO> attendanceDTOS){
        try{
            List<Attendance> attendances = attendanceService.makeAttendance(attendanceDTOS);
            if(!attendances.isEmpty()){
                responseDTO.setCode("00");
                responseDTO.setMassage("Attendance Marked");
                responseDTO.setContent(attendances);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(attendanceDTOS);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode("01");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
