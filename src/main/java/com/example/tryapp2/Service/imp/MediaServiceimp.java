package com.example.tryapp2.Service.imp;

import com.example.tryapp2.Dto.MediaDto;
import com.example.tryapp2.Entity.Media;
import com.example.tryapp2.Mapper.MediaMapper;
import com.example.tryapp2.R2Config.R2MediaService;
import com.example.tryapp2.Repo.MediaRepo;
import com.example.tryapp2.Service.MediaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaServiceimp implements MediaService {
    private final MediaRepo mediaRepo;
    private final R2MediaService r2MediaService;
///  creating media
    @Override
    public MediaDto createMedia(String mediaName, String mediaUrl, String description, MultipartFile Thumbnail) {
        Media media = new Media();
        media.setMediaName(mediaName);
        media.setMediaUrl(mediaUrl);
        media.setDescription(description);
         if (Thumbnail != null){
          String key = r2MediaService.uploadFile(Thumbnail);
          String thumbnailUrl=r2MediaService.getPublicUrl(key);
          media.setMediaThumbnailUrl(thumbnailUrl);
         }
         Media savedMedia = mediaRepo.save(media);

        return MediaMapper.mapToMediaDto(savedMedia);
    }

    ///  updating  media
    @Override
    public MediaDto updateMedia(Long id, String mediaName, String mediaUrl, String description, MultipartFile thumbnail)  {
Media media=mediaRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Media not found"));
        if(media.getMediaName() !=null){
            media.setMediaName(mediaName);
        }
        if(media.getMediaUrl() !=null){
            media.setMediaUrl(mediaUrl);
        }
        if(media.getDescription() !=null){
            media.setDescription(description);
        }
        if(thumbnail !=null){
            String key=r2MediaService.updateFile(media.getMediaThumbnailUrl(),thumbnail);
            String thumbnailUrl=r2MediaService.getPublicUrl(key);
            media.setMediaThumbnailUrl(thumbnailUrl);

        }
Media savedMedia = mediaRepo.save(media);
        return MediaMapper.mapToMediaDto(savedMedia);
    }
///  deleting media
    @Override
    public void deleteMedia(Long mediaId) {
        Media media= mediaRepo.findById(mediaId).orElseThrow(()->new EntityNotFoundException("Media not found"));
        if (media.getMediaThumbnailUrl() !=null){
            r2MediaService.deleteFile(media.getMediaThumbnailUrl());
            mediaRepo.delete(media);
        }

    }
///  getting media by id
    @Override
    public MediaDto getMediaById(Long mediaId) {
        Media media = mediaRepo.findById(mediaId).orElseThrow(()->new EntityNotFoundException("Media not found"));
        return MediaMapper.mapToMediaDto(media);
    }
/// get all media
    @Override
    public List<MediaDto> getAllMedia() {
        List<Media> media= mediaRepo.findAll();
        return media.stream().map(MediaMapper::mapToMediaDto).collect(Collectors.toList());
    }
}
