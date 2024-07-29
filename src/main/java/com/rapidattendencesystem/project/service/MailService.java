package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.EmailDataDTO;
import com.rapidattendencesystem.project.dto.TeacherEmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@EnableAsync
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public String SendTeacherReciptEmailWithAttachment(TeacherEmailDTO emailData){
        try{
            if (emailData.getTeacherEmail() == null || emailData.getTeacherEmail().isEmpty()) {
                throw new IllegalArgumentException("Student email is null or empty");
            }

            String[] recipients = { emailData.getTeacherEmail()};
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(recipients);
            helper.setSubject("Your Receipt from Rapid Institute");
            helper.setText("Please find your receipt attached.");

            byte[] pdfBytes = Base64.getDecoder().decode(emailData.getPdfBase64());
            ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

            helper.addAttachment("receipt.pdf", pdfResource);

            mailSender.send(message);

            return "Message Sent";
        }catch(Exception e){
            System.err.println("MessagingException: " + e.getMessage());
            return "Messageing Exeption Occurd";
        }
    }

    @Async
    public String SendEmailWithAttachment(EmailDataDTO emailData){
        try{
            if (emailData.getStudentEmail() == null || emailData.getStudentEmail().isEmpty()) {
                throw new IllegalArgumentException("Student email is null or empty");
            }
            if (emailData.getParentEmail() == null || emailData.getParentEmail().isEmpty()) {
                throw new IllegalArgumentException("Parent email is null or empty");
            }
            String[] recipients = { emailData.getStudentEmail(), emailData.getParentEmail() };
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(recipients);
            helper.setSubject("Your Receipt from Rapid Institute");
            helper.setText("Please find your receipt attached.");

            byte[] pdfBytes = Base64.getDecoder().decode(emailData.getPdfBase64());
            ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

            helper.addAttachment("receipt.pdf", pdfResource);

            mailSender.send(message);

            return "Message Sent";
        }catch(Exception e){
            System.err.println("MessagingException: " + e.getMessage());
            return "Messageing Exeption Occurd";
        }
    }
}
