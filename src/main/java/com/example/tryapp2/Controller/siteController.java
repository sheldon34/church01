package com.example.tryapp2.Controller;

import com.example.tryapp2.Dto.SiteMediaDto;
import com.example.tryapp2.Service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/siteMedia")
@RequiredArgsConstructor
public class siteController {
    private final SiteService siteService;

    @PostMapping("/upload")
    public ResponseEntity<SiteMediaDto> createSiteMedia(@RequestParam(value = "image",required = true) MultipartFile image,

                                                        @RequestParam(value = "MediaName",required = true) String MediaName,

                                                        @RequestParam(value = "siteDescription",required = true) String siteDescription ) {

        SiteMediaDto siteMediaDto = siteService.createSiteMedia(image, MediaName, siteDescription);
        return new ResponseEntity<>(siteMediaDto, HttpStatus.CREATED);

    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<SiteMediaDto>updateSiteMedia(  @PathVariable Long id,
                                                      @RequestParam(value = "siteName",required = false) String siteName,
                                                      @RequestParam(value = "image",required = false) MultipartFile image,
                                                      @RequestParam(value = "siteDescription", required = false) String siteDescription){
        SiteMediaDto siteMediaDto = siteService.updateSiteMedia(id, siteName, image, siteDescription);
        return new ResponseEntity<>(siteMediaDto, HttpStatus.OK);

    }
    @GetMapping("get/{id}")
    public ResponseEntity<SiteMediaDto>getSiteMedia(@PathVariable Long id){
        SiteMediaDto siteMediaDto = siteService.getSiteMedia(id);
        return new ResponseEntity<>(siteMediaDto, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteSiteMedia(@PathVariable Long id){
        siteService.deleteSiteMedia(id);
        return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
    }

}
