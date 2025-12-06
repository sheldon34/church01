package com.example.tryapp2.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class EmailPayLoad {
    private String to;
    private String subject;
    private String body;
    private String attachmentUrl;
private String attachmentFile;
//    public EmailPayLoad(){}
}
