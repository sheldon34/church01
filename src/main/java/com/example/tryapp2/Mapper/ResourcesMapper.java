package com.example.tryapp2.Mapper;

import com.example.tryapp2.Dto.ResourcesDto;
import com.example.tryapp2.Entity.Resources;

public class ResourcesMapper {
    public static ResourcesDto mapToResourcesDto(Resources resources){
        ResourcesDto resourcesDto = new ResourcesDto();
        resourcesDto.setId(resources.getId());
        resourcesDto.setResourcesName(resources.getResourcesName());
        resourcesDto.setThumbnailUrl(resources.getThumbnailUrl());
        resourcesDto.setResourcesUrl(resources.getResourcesUrl());
        resourcesDto.setResourcesDescription(resources.getResourcesDescription());
        return resourcesDto;
    }
    public static Resources mapToResources (ResourcesDto resourcesDto){
        Resources resources = new Resources();
        resources.setResourcesName(resourcesDto.getResourcesName());
        resources.setId(resourcesDto.getId());
      resources.setThumbnailUrl(resourcesDto.getThumbnailUrl());
      resources.setResourcesUrl(resourcesDto.getResourcesUrl());
      resources.setResourcesUrl(resourcesDto.getResourcesUrl());
        return resources;
    }
}
