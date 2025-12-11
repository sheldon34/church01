package com.example.tryapp2.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SiteMediaDto {
    private Long id;
    private String mediaName;
    private String description;
    private String imageUrl;

}
