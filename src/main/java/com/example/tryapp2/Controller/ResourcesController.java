package com.example.tryapp2.Controller;


import com.example.tryapp2.Dto.ResourcesDto;
import com.example.tryapp2.Service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping ("/api/resource")
@RequiredArgsConstructor
public class ResourcesController {


    private final ResourceService resourceService;

    @PostMapping("/upload")
    public ResponseEntity<ResourcesDto> createResource(
            @RequestParam("resourceName")  String resourcesName,
            @RequestParam("Thumbnail")MultipartFile Thumbnail,
            @RequestParam("resourcesUrl")  String resourcesUrl,
            @RequestParam("resourcesDescription") String resourcesDescription
            ){
        ResourcesDto resourcesDto = resourceService.createResources(resourcesName, Thumbnail, resourcesUrl, resourcesDescription);
        return new  ResponseEntity<>(resourcesDto, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResourcesDto> updateResource(@PathVariable("id")  Long id,
                                                  @RequestParam(value = "resourcesName",required = false) String resourcesName,
                                                     @RequestParam(value = "thumbnail",required = false)  MultipartFile thumbnail,
                                                      @RequestParam(value = "resourcesUrl",required = false) String resourcesUrl,
                                                        @RequestParam(value = "resourcesDescription",required = false) String resourcesDescription
    ){
        ResourcesDto resourcesDto = resourceService.updateResources( id,resourcesName, thumbnail, resourcesUrl, resourcesDescription);
        return new  ResponseEntity<>(resourcesDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteResource(@PathVariable("id")  Long id){
        resourceService.deleteResources(id);
        return new ResponseEntity<>("Delete resources successfully", HttpStatus.GONE);
    }
    @GetMapping("/getAll")
public ResponseEntity<List<ResourcesDto>> getAllResources(){
        List<ResourcesDto> resourcesDto = resourceService.getAllResources();
        return new ResponseEntity<>(resourcesDto, HttpStatus.OK);
}
@GetMapping("/getById/{id}")
public ResponseEntity<ResourcesDto> getResourceById(@PathVariable("id")  Long id){
        ResourcesDto resourcesDto = resourceService.getResourceById(id);
        return new ResponseEntity<>(resourcesDto, HttpStatus.OK);
}


    }
