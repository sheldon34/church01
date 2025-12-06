package com.example.tryapp2.Controller;

import com.example.tryapp2.Email.EmailPayLoad;
import com.example.tryapp2.Email.resend.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailController {
    private final EmailService emailService;

@PostMapping("/all")
    public ResponseEntity<?> sendToAllUser(@RequestBody EmailPayLoad payload){
        try{
            String id = emailService.sendEmailToAllUsers(
                    payload.getSubject(),
                    payload.getBody()
            );
            return ResponseEntity.ok("Email sent  ID: " + id);
        } catch (Exception e){
            return ResponseEntity.status(500).body("Error sending email: " + e.getMessage());
        }

}
@PostMapping("/selected")
 public ResponseEntity<?> sendToSelected(@RequestBody Map<String,Object> request){
    try{
        List<String> emails= (List<String>) request.get("emails");
        String subject= (String) request.get("subject");
        String body= (String) request.get("body");
        String id = emailService.sendEmailToSelected(emails,subject,body);
        return ResponseEntity.ok("Email sent  ID: " + id);
    }
    catch (Exception e){
        return ResponseEntity.status(500).body("Error sending email: " + e.getMessage());
    }
}

}