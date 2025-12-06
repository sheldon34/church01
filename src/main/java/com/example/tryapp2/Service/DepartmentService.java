package com.example.tryapp2.Service;

import com.example.tryapp2.Dto.DepartmentsDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DepartmentService {
    DepartmentsDto createDepartment(MultipartFile thumbnailUrl, String departmentName, String departmentDescription, String departmentLocation, String groupUrl, String registrationUrl);
    DepartmentsDto updateDepartment(MultipartFile thumbnailUrl, String departmentName, String departmentDescription, String departmentLocation, String groupUrl, String registrationUrl, Long id);
    DepartmentsDto getDepartmentById(Long id);
    DepartmentsDto getDepartmentByName(String departmentName);
    List<DepartmentsDto> getAllDepartments();
    void deleteDepartment(Long id);
}
