package com.example.tryapp2.Service;

import com.example.tryapp2.Dto.ResourcesDto;
import com.example.tryapp2.Entity.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResourceService  {
    ResourcesDto createResources (String resourcesName, MultipartFile thumbnail, String resourcesUrl, String resourcesDescription);
    ResourcesDto updateResources (Long id, String resourcesName, MultipartFile newThumbnail, String resourcesUrl, String resourcesDescription);
    void deleteResources(Long id);
    ResourcesDto getResourceById(Long id);
    List<ResourcesDto> getAllResources();
}
