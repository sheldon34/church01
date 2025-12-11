package com.example.tryapp2.Mapper;

import com.example.tryapp2.Dto.SiteMediaDto;
import com.example.tryapp2.Entity.siteMedia;

public class SiteMapper {
    public  static siteMedia mapToSiteMedia(SiteMediaDto siteMediaDto) {
        siteMedia siteMedia = new siteMedia();
        siteMedia.setMediaName(siteMediaDto.getMediaName());
        siteMedia.setDescription(siteMediaDto.getDescription());
        siteMedia.setImageUrl(siteMediaDto.getImageUrl());
        return siteMedia;
    }
    public static SiteMediaDto mapToSiteMediaDto (siteMedia siteMedia) {
        SiteMediaDto siteMediaDto = new SiteMediaDto();
        siteMediaDto.setId(siteMedia.getId());
        siteMediaDto.setMediaName(siteMedia.getMediaName());
        siteMediaDto.setDescription(siteMedia.getDescription());
        siteMediaDto.setImageUrl(siteMedia.getImageUrl());
        return siteMediaDto;
    }
}
