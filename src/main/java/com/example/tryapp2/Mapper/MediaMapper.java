package com.example.tryapp2.Mapper;

import com.example.tryapp2.Dto.MediaDto;
import com.example.tryapp2.Entity.Media;

public class MediaMapper {

    public static MediaDto mapToMediaDto(Media media){
MediaDto mediaDto = new MediaDto();
mediaDto.setId(media.getId());
mediaDto.setMediaUrl(media.getMediaUrl());
mediaDto.setDescription(media.getDescription());
mediaDto.setMediaName(media.getMediaName());
        return mediaDto;
    }
    public static Media mapToMedia (MediaDto mediaDto){
        Media media = new Media();
        media.setMediaName(mediaDto.getMediaName());
       media.setId(mediaDto.getId());
       media.setMediaUrl(mediaDto.getMediaUrl());
       media.setDescription(mediaDto.getDescription());
        return media;
    }

}
