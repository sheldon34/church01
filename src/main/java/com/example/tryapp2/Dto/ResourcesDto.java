package com.example.tryapp2.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResourcesDto {
    private Long id;
    private String resourcesName;
    private String  ThumbnailUrl;
    private  String resourcesDescription;
    private String resourcesUrl;


}
