package com.example.tryapp2.Service.imp;

import com.example.tryapp2.Dto.DepartmentsDto;
import com.example.tryapp2.Entity.Departments;
import com.example.tryapp2.Mapper.DepartmentMapper;
import com.example.tryapp2.R2Config.R2MediaService;
import com.example.tryapp2.Repo.DepartmentRepo;
import com.example.tryapp2.Service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImp implements DepartmentService {
    private final DepartmentRepo departmentRepo;
    private final R2MediaService r2MediaService;

    @Override
    public DepartmentsDto createDepartment(MultipartFile thumbnail, String departmentName, String departmentDescription, String departmentLocation, String groupUrl, String registrationUrl) {

        Departments departments = new Departments();
        departments.setDepartmentName(departmentName);
        departments.setDepartmentDescription(departmentDescription);
        departments.setDepartmentLocation(departmentLocation);
        departments.setGroupUrl(groupUrl);
        departments.setRegistrationUrl(registrationUrl);
        if (thumbnail != null) {
            String key = r2MediaService.uploadFile(thumbnail);
            String thumbnailUrl=r2MediaService.getPublicUrl(key);
            departments.setThumbnailUrl(thumbnailUrl);
        }
        Departments savedDepartment = departmentRepo.save(departments);

        return DepartmentMapper.mapToDepartmentsDto(savedDepartment);
    }



    @Override
    public DepartmentsDto updateDepartment(MultipartFile thumbnail, String departmentName, String departmentDescription, String departmentLocation, String groupUrl, String registrationUrl,Long id) {
        Departments departments = departmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
//        departmentRepo.findByDepartmentName(departmentName).orElseThrow(() -> new RuntimeException("Department not found"))

                ;
        if (departmentName != null) {
            departments.setDepartmentName(departmentName);
        }
        if (departmentDescription != null) {
            departments.setDepartmentDescription(departmentDescription);}
        if (departmentLocation != null) {
            departments.setDepartmentLocation(departmentLocation);}
        if (groupUrl != null) {
            departments.setGroupUrl(groupUrl);}
        if (registrationUrl != null) {
            departments.setRegistrationUrl(registrationUrl);}

        if (thumbnail != null) {
            String key= r2MediaService.uploadFile(thumbnail);
            String thumbnailUrl=r2MediaService.getPublicUrl(key);
            departments.setThumbnailUrl(thumbnailUrl);
        }

        Departments updatedDepartment = departmentRepo.save(departments);
        return DepartmentMapper.mapToDepartmentsDto(updatedDepartment);
    }

    @Override
    public DepartmentsDto getDepartmentById(Long id) {

            Departments departments = departmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));

        return DepartmentMapper.mapToDepartmentsDto(departments);
    }

    @Override
    public DepartmentsDto getDepartmentByName(String departmentName) {
        Departments departments= departmentRepo.findByDepartmentName(departmentName).orElseThrow(()->new RuntimeException("Department not found"));
        return DepartmentMapper.mapToDepartmentsDto(departments);
    }

    @Override
    public List<DepartmentsDto> getAllDepartments() {
        List<Departments> departmentsList = departmentRepo.findAll();

            return departmentsList.stream().map(DepartmentMapper::mapToDepartmentsDto).toList();
    }


    @Override
    public void deleteDepartment(Long id) {
        Departments departments = departmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
        if (departments.getThumbnailUrl() != null) {
            r2MediaService.deleteFile(departments.getThumbnailUrl());
        }
        departmentRepo.delete(departments);

    }


}
