package com.example.tryapp2.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentsDto {
    private Long id;
    private  String ThumbnailUrl;
    private String departmentName;
    private String departmentDescription;
    private String departmentLocation;
    private String  groupUrl;
    private String  registrationUrl;
}
