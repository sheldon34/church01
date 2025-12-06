package com.example.tryapp2.Mapper;

import com.example.tryapp2.Dto.DepartmentsDto;
import com.example.tryapp2.Entity.Departments;

public class DepartmentMapper {
    public static Departments mapToDepartment(DepartmentsDto departmentsDto){
        Departments departments = new Departments();
        departments.setDepartmentName(departmentsDto.getDepartmentName());
        departments.setId(departmentsDto.getId());
        departments.setDepartmentDescription(departmentsDto.getDepartmentDescription());
        departments.setDepartmentLocation(departmentsDto.getDepartmentLocation());
        departments.setGroupUrl(departmentsDto.getGroupUrl());
        departments.setRegistrationUrl(departmentsDto.getRegistrationUrl());
        departments.setThumbnailUrl(departmentsDto.getThumbnailUrl());
        return departments;
    }
    public static DepartmentsDto mapToDepartmentsDto(Departments departments){
        DepartmentsDto departmentsDto = new DepartmentsDto();
        departmentsDto.setId(departments.getId());
        departmentsDto.setDepartmentName(departments.getDepartmentName());
        departmentsDto.setDepartmentDescription(departments.getDepartmentDescription());
        departmentsDto.setDepartmentLocation(departments.getDepartmentLocation());
        departmentsDto.setGroupUrl(departments.getGroupUrl());
        departmentsDto.setRegistrationUrl(departments.getRegistrationUrl());
        departmentsDto.setThumbnailUrl(departments.getThumbnailUrl());
        return departmentsDto;
    }
}
