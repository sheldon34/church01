package com.example.tryapp2.Service.imp;

import com.amazonaws.services.accessanalyzer.model.ResourceNotFoundException;
import com.example.tryapp2.Dto.SiteMediaDto;
import com.example.tryapp2.Entity.siteMedia;
import com.example.tryapp2.Mapper.SiteMapper;
import com.example.tryapp2.R2Config.R2MediaService;
import com.example.tryapp2.Repo.ResourcesRepo;
import com.example.tryapp2.Repo.SiteMediaRepo;
import com.example.tryapp2.Service.SiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static java.rmi.server.LogStream.log;

@Service
@RequiredArgsConstructor
@Slf4j
public class SiteServiceImp  implements SiteService {

    private final SiteMediaRepo siteMediaRepo;
    private final R2MediaService r2MediaService;
    /// creating a new  siteMedia

    @Override
    public SiteMediaDto createSiteMedia(MultipartFile image, String MediaName, String siteDescription) {
        try{
        siteMedia siteMedia = new siteMedia();
        siteMedia.setMediaName(MediaName);
        siteMedia.setDescription(siteDescription);
        if(image!=null){
            String Key= r2MediaService.uploadFile(image);
            String ThumbnailUrl= r2MediaService.getPublicUrl(Key);
            siteMedia.setImageUrl(ThumbnailUrl);
        }
       siteMedia savedMedia= siteMediaRepo.save(siteMedia);
        return SiteMapper.mapToSiteMediaDto(savedMedia);}
        catch(Exception e){
            log.error(e.getMessage());
            log.info(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    /// updating site media
    @Override
    public SiteMediaDto updateSiteMedia(Long id, String siteName, MultipartFile image, String siteDescription) {
        try{
        siteMedia siteMedia = new siteMedia();
      if (siteName != null) {
          siteMedia.setMediaName(siteName);
      }
        if  (image != null) {
            String newKey=r2MediaService.updateFile(siteMedia.getImageUrl(),image);
            String ThumbnailUrl= r2MediaService.getPublicUrl(newKey);
            siteMedia.setImageUrl(ThumbnailUrl);
        }
        siteMedia updatedMedia= siteMediaRepo.save(siteMedia);
        return SiteMapper.mapToSiteMediaDto(updatedMedia);}
        catch(Exception e){
            log.error(e.getMessage());
            log.info(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public SiteMediaDto getSiteMedia(Long id) {
        try{
        siteMedia siteMedia = siteMediaRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("site media not found"));
        return SiteMapper.mapToSiteMediaDto(siteMedia);}
        catch(Exception e){
            log.error(e.getMessage());
            log.info(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteSiteMedia(Long id) {
        try{
        siteMedia siteMedia= siteMediaRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("site media not found"));
        r2MediaService.deleteFile(siteMedia.getImageUrl());
        siteMediaRepo.delete(siteMedia);}
        catch(Exception e)
        {
            log.error(e.getMessage());
            log.info(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }


}
