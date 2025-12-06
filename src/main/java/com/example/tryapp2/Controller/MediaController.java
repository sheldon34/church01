package com.example.tryapp2.Controller;

import com.example.tryapp2.Dto.EventsDto;
import com.example.tryapp2.Dto.MediaDto;
import com.example.tryapp2.Service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;

@PostMapping("/upload")
    public ResponseEntity<MediaDto> createMedia(
            @RequestParam("mediaUrl") String mediaUrl,
            @RequestParam("mediaName") String mediaName,
            @RequestParam("description") String description,
            @RequestParam("mediaThumbnail") MultipartFile mediaThumbnail
    ) {
            MediaDto media = mediaService.createMedia(mediaUrl, mediaName, description, mediaThumbnail);
            return new ResponseEntity<>(media, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<MediaDto> updateMedia(
            @PathVariable("id") Long id,
            @RequestParam(value = "mediaUrl",required = false) String mediaUrl,
            @RequestParam(value = "mediaName",required = false) String mediaName,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "mediaThumbnail",required = false) MultipartFile mediaThumbnail
    ){
        MediaDto media = mediaService.updateMedia(id, mediaUrl, mediaName, description, mediaThumbnail);
        return new ResponseEntity<>(media, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MediaDto>> getAllMedia(){
        List<MediaDto> media = mediaService.getAllMedia();
        return new ResponseEntity<>(media, HttpStatus.OK);
    }

@GetMapping("/getById/{mediaId}")
    public ResponseEntity<MediaDto> getMediaById(
            @PathVariable("mediaId") Long mediaId){
    MediaDto media = mediaService.getMediaById(mediaId);
        return new ResponseEntity<>(media, HttpStatus.OK);
    }


@DeleteMapping("/delete/{id}")
    public ResponseEntity<String>  deleteMedia(
            @PathVariable("id")
            Long id){
        mediaService.deleteMedia(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

}
