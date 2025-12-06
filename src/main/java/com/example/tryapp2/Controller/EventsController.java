package com.example.tryapp2.Controller;


import com.example.tryapp2.Dto.EventsDto;
import com.example.tryapp2.Service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/events")
@RestController
public class EventsController {
    private final EventService eventService;

    @PostMapping("/upload")
    public ResponseEntity<EventsDto> createEvent(
            @RequestParam(value = "eventName") String eventName,
            @RequestParam("eventDescription") String eventDescription,
            @RequestParam("eventLocation") String eventLocation,
            @RequestParam("eventDate") String eventDate,
            @RequestParam("Thumbnails") MultipartFile Thumbnails
    ) {
        EventsDto newEvent= eventService.createEvent(eventName, eventDescription, eventLocation, eventDate, Thumbnails);
        return  new  ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }


    @PatchMapping("/update/{id}")
    public ResponseEntity<EventsDto> updateEvent(
            @PathVariable("id") Long id,
            @RequestParam (value = "eventName", required = false) String eventName,
            @RequestParam(value = "eventDescription",required = false) String eventDescription,
            @RequestParam(value= "eventLocation",required = false) String eventLocation,
            @RequestParam(value = "eventDate", required = false) String eventDate,
            @RequestParam(value = "Thumbnails", required = false) MultipartFile Thumbnails
    ){
        EventsDto updateEvent= eventService.updateEvent(id, eventName, eventDescription, eventLocation, eventDate, Thumbnails);
        return new ResponseEntity<>(updateEvent, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<EventsDto>> getAllEvents() {
        List<EventsDto> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/getByName/{eventName}")
    public ResponseEntity<EventsDto> getEventByName(@PathVariable("eventName") String eventName) {
        EventsDto events= eventService.getEventsByName(eventName);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteEvent( @PathVariable("id") Long id){
        eventService.deleteEvent(id);
        return new ResponseEntity<>("'Deleted",HttpStatus.GONE );
}

}
