package com.example.tryapp2.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventsDto {
    private Long id;
    private String eventName;
    private String eventDescription;
    private String ThumbnailUrl;
    private String eventDate;
    private String eventLocation;
    private LocalDateTime createdAt;
}
