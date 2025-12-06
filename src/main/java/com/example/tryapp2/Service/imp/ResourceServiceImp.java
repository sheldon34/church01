package com.example.tryapp2.Service.imp;


import com.example.tryapp2.Dto.ResourcesDto;
import com.example.tryapp2.Entity.Resources;
import com.example.tryapp2.Mapper.ResourcesMapper;
import com.example.tryapp2.R2Config.R2MediaService;
import com.example.tryapp2.Repo.ResourcesRepo;
import com.example.tryapp2.Service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ResourceServiceImp implements ResourceService {
    private final ResourcesRepo resourcesRepo;
private final R2MediaService  r2MediaService;

///  Creating a resource
    @Override
    public ResourcesDto createResources(String resourcesName, MultipartFile thumbnail, String resourcesUrl, String resourcesDescription) {
        try{
        Resources resources= new Resources();
        resources.setResourcesName(resourcesName);
        resources.setResourcesUrl(resourcesUrl);
        resources.setResourcesDescription(resourcesDescription);
        if (thumbnail != null) {
            String Key= r2MediaService.uploadFile(thumbnail);
            String ThumbnailUrl= r2MediaService.getPublicUrl(Key);
resources.setThumbnailUrl(ThumbnailUrl);}
       Resources savedResources= resourcesRepo.save(resources);

        return ResourcesMapper.mapToResourcesDto(savedResources);
    }
    catch (Exception e){
            throw  new RuntimeException(e.getMessage());
    }

    }
///  updating resource
    @Override
    public ResourcesDto updateResources(Long id, String resourcesName, MultipartFile newThumbnail, String resourcesUrl, String resourcesDescription) {

        try{

        Resources resources=resourcesRepo.findById(id).orElseThrow(()->new RuntimeException("Resource not found"));
        if (resourcesName!=null) {
            resources.setResourcesName(resourcesName);
        }
    if (resourcesUrl!=null) {
        resources.setResourcesUrl(resourcesUrl);
    }
    if (newThumbnail!=null) {
        String Key=r2MediaService.updateFile(resources.getThumbnailUrl(),newThumbnail);
        String ThumbnailUrl= r2MediaService.getPublicUrl(Key);
        resources.setThumbnailUrl(ThumbnailUrl);
    }
    if( resourcesDescription!=null) {
        resources.setResourcesDescription(resourcesDescription);}

        Resources savedResources= resourcesRepo.save(resources);

        return ResourcesMapper.mapToResourcesDto(savedResources);}
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
///  deleting a resource
    @Override
    public void deleteResources(Long id) {
        try {
            Resources resources = resourcesRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found"));
            r2MediaService.deleteFile(resources.getResourcesUrl());
            resourcesRepo.delete(resources);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
///  getting resource by id
    @Override
    public ResourcesDto getResourceById(Long id) {
        try{
        Resources resource= resourcesRepo.findById(id).orElseThrow(()->new RuntimeException("Resource not found"));
        return ResourcesMapper.mapToResourcesDto(resource);}
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
/// Getting all resources
    @Override
    public List<ResourcesDto> getAllResources() {
        try {
            List<Resources> resources = resourcesRepo.findAll();
            return resources.stream().map(ResourcesMapper::mapToResourcesDto).collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }





}
