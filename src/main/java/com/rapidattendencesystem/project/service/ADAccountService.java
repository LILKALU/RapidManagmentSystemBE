package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.ADAccountDTO;
import com.rapidattendencesystem.project.entity.ADAccount;
import com.rapidattendencesystem.project.repo.ADAccountRepo;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.security.MD5Encoder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;

@Service
@Transactional
public class ADAccountService {

    @Autowired
    private ADAccountRepo adAccountRepo;

    @Autowired
    private ModelMapper modelMapper;

    public ADAccount createUserAccount(ADAccountDTO asAccountDTO){
        try {
            MessageDigest md  = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(asAccountDTO.getPassWord().getBytes());
            byte[] encodedPswd = md.digest();
            BigInteger bigInt = new BigInteger(1,encodedPswd);
            String hashtext = bigInt.toString(16);
            asAccountDTO.setPassWord(hashtext);
            ADAccount A1 = modelMapper.map(asAccountDTO , ADAccount.class);
            A1.setCreatedDateTime(LocalDateTime.now());
            if(A1.getId()>0){
                System.out.println("User Account Already Created");
                return A1;
            }else{
                ADAccount A2 = adAccountRepo.save(A1);
                System.out.println("User Account Created");
               return A2;
            }
        }catch (Exception e){
            ADAccount A1 = modelMapper.map(asAccountDTO , ADAccount.class);
            System.out.println(e.getMessage());
            return A1;
        }
    }
}
