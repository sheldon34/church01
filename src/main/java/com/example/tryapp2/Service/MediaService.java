package com.example.tryapp2.Service;


import com.example.tryapp2.Dto.MediaDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MediaService {
     MediaDto createMedia(String mediaName, String mediaUrl, String description , MultipartFile Thumbnail);
     MediaDto updateMedia(Long id, String mediaName, String mediaUrl, String description , MultipartFile thumbnail);
    void deleteMedia(Long mediaId);
    MediaDto getMediaById(Long mediaId);
    List<MediaDto> getAllMedia();

}
