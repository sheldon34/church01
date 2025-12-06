package com.example.tryapp2.Service;

import com.example.tryapp2.Dto.EventsDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {
    EventsDto createEvent(String eventName, String eventDescription, String eventLocation, String eventDate, MultipartFile Thumbnails);
    EventsDto updateEvent(Long id, String eventName, String eventDescription, String eventLocation, String eventDate, MultipartFile Thumbnails);
    void deleteEvent(Long id);
    EventsDto getEventsByName(String eventName);
   List <EventsDto> getAllEvents();
}
