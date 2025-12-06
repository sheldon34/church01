package com.example.tryapp2.Controller;

import com.example.tryapp2.Dto.DepartmentsDto;
import com.example.tryapp2.Service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {
    private  final DepartmentService departmentService;


    @PostMapping("/upload")
    public ResponseEntity<DepartmentsDto> createDepartment(
            @RequestParam("departmentName") String departmentName,
            @RequestParam("departmentDescription") String departmentDescription,
            @RequestParam("groupUrl") String groupUrl,
            @RequestParam("Thumbnail") MultipartFile Thumbnail,
            @RequestParam("registrationUrl") String registrationUrl,
            @RequestParam("departmentLocation") String departmentLocation

    ) {
        DepartmentsDto departmentsDto = departmentService.createDepartment( Thumbnail, departmentName, departmentDescription,  departmentLocation,  groupUrl, registrationUrl);
        return new ResponseEntity<>(departmentsDto, HttpStatus.OK);

    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<DepartmentsDto> updateDepartment(
            @PathVariable
            Long id,
            @RequestParam(value = "departmentName", required = false) String departmentName,
            @RequestParam(value = "departmentDescription", required = false) String departmentDescription,
            @RequestParam(value = "groupUrl", required = false) String groupUrl,
            @RequestParam(value = "Thumbnail", required = false) MultipartFile Thumbnail,
            @RequestParam(value = "registrationUrl", required = false) String registrationUrl,
            @RequestParam(value = "departmentLocation", required = false) String departmentLocation
    ) {
        DepartmentsDto departmentsDto = departmentService.updateDepartment(Thumbnail, departmentName, departmentDescription, departmentLocation, groupUrl, registrationUrl,id);
        return new ResponseEntity<>(departmentsDto, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<DepartmentsDto>> getAllDepartments(){
        List<DepartmentsDto> departmentsDto = departmentService.getAllDepartments();
        return new ResponseEntity<>(departmentsDto, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity <String> deleteDepartment(@PathVariable("id") Long id){
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>("Delete department successfully", HttpStatus.GONE);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<DepartmentsDto> getDepartmentById(@PathVariable("id") Long id){
        DepartmentsDto departmentsDto = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(departmentsDto, HttpStatus.OK);
    }

}
