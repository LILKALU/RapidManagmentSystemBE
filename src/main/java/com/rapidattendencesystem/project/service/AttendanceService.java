package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.*;
import com.rapidattendencesystem.project.entity.Attendance;
import com.rapidattendencesystem.project.entity.Course;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.entity.Month;
import com.rapidattendencesystem.project.repo.AttendanceRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<AttendanceDTO> getAllAttendance(){
        try{
            List<AttendanceDTO> attendances = modelMapper.map(attendanceRepo.findAll(), new TypeToken<List<AttendanceDTO>>() {}.getType()) ;
            return attendances;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<AttendanceDTO> getAttendanceByCourseAndDate(AttendanceSearchDTO attendanceSearchDTO){
        try{
            Course c1 = modelMapper.map(attendanceSearchDTO.getCourse() , Course.class);
            Month m1 = modelMapper.map(attendanceSearchDTO.getMonth(), Month.class);
            int y1 = attendanceSearchDTO.getYear();
            int d1 = attendanceSearchDTO.getDate();
            List<AttendanceDTO> attendances = modelMapper.map(attendanceRepo.findByCourseAndYearAndMonthAndDate(c1,y1,m1,d1), new TypeToken<List<AttendanceDTO>>() {}.getType()) ;
            return attendances;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<AttendanceDTO> getAttendanceByCourseAndYearAndMonth(AttendanceSearchDTO attendanceSearchDTO){
        try{
            Course c1 = modelMapper.map(attendanceSearchDTO.getCourse() , Course.class);
            Month m1 = modelMapper.map(attendanceSearchDTO.getMonth(), Month.class);
            int y1 = attendanceSearchDTO.getYear();
            List<AttendanceDTO> attendances = modelMapper.map(attendanceRepo.findByCourseAndYearAndMonth(c1,y1,m1), new TypeToken<List<AttendanceDTO>>() {}.getType()) ;
            return attendances;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Attendance> updateAttendance(List<AttendanceDTO> attendancesDTO){
        try{
            List<Attendance> attendances = modelMapper.map(attendancesDTO , new TypeToken<List<Attendance>>() {}.getType());
            List<Attendance> savedAttendance = attendanceRepo.saveAll(attendances);
            if(savedAttendance.isEmpty()){
                System.out.println("Empty Attendance");
                return attendances;
            }else{
                return savedAttendance;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Attendance> makeAttendance(List<AttendanceDTO> attendancesDTO){
        try{
            List<Attendance> attendances = modelMapper.map(attendancesDTO , new TypeToken<List<Attendance>>() {}.getType());
            List<Attendance> savedAttendance = attendanceRepo.saveAll(attendances);
            if(savedAttendance.isEmpty()){
                System.out.println("Empty Attendance");
                return attendances;
            }else{
                return savedAttendance;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
