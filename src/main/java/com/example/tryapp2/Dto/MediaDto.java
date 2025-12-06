package com.example.tryapp2.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaDto {
    private Long id ;

    private String mediaUrl;
    private String mediaName;
    private String description;
    private String mediaThumbnail;
}
