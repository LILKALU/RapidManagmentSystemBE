package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.GradeDTO;
import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.repo.GradeRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class GradeService {

    @Autowired
    private GradeRepo gradeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<GradeDTO> getGrades(){
        try{
            return modelMapper.map(gradeRepo.findByIsActive(true), new TypeToken<List<GradeDTO>>() {}.getType()) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
