package com.example.tryapp2.Email.resend;

import com.example.tryapp2.Service.AuthOService;
import com.resend.Resend;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmailService {
    private final ResendService resendService;
    private final AuthOService authOService;

    public String sendEmailToAllUsers(String subject,String body) throws Exception{
        List<String> emails= authOService.getAllUserEmails();
        String[] to =emails.toArray(new String[0]);
        return resendService.sendEmail(to,subject,body);

    }
    public String sendEmailToSelected(List<String> selectedEmails, String subject, String body) throws Exception{
        String[] to = selectedEmails.toArray(new String[0]);
        return resendService.sendEmail(to,subject,body);
    }
}
