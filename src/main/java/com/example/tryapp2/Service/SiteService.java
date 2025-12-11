package com.example.tryapp2.Service;

import com.example.tryapp2.Dto.SiteMediaDto;
import org.springframework.web.multipart.MultipartFile;

public interface SiteService {
     SiteMediaDto createSiteMedia(MultipartFile image, String MediaName, String siteDescription );
     SiteMediaDto updateSiteMedia(Long id, String siteName, MultipartFile image, String siteDescription);
     SiteMediaDto getSiteMedia(Long id);
     void deleteSiteMedia(Long id);
}
