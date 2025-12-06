package com.example.tryapp2.Email.resend;

import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class ResendService {
    private final Resend resend;


    public ResendService(@Value("${resend.api-key}") String  apiKey) {
        this.resend = new Resend(apiKey);
    }

    public String sendEmail(String[] to , String subject, String html) throws Exception{

        CreateEmailOptions params= CreateEmailOptions.builder()
                .from("kabomxdon@gmail.com")
                .to(to)
                .subject(subject)
                .html(html)
                .build();
        CreateEmailResponse resp= resend.emails().send(params);
        return resp.getId();
    }
}




