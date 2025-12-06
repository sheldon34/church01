package com.example.tryapp2.Service.imp;

import com.example.tryapp2.Dto.EventsDto;
import com.example.tryapp2.Entity.Events;
import com.example.tryapp2.Mapper.EventsMapper;
import com.example.tryapp2.R2Config.R2MediaService;
import com.example.tryapp2.Repo.EventRepo;
import com.example.tryapp2.Service.EventService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventServiceImp implements EventService {

private final R2MediaService r2MediaService;
    private final EventRepo eventRepo;
/// uploading an event
    @Override
    public EventsDto createEvent(String eventName, String eventDescription, String eventLocation, String eventDate, MultipartFile Thumbnails) {
        try {
            Events event = new Events();
            event.setEventName(eventName);
            event.setEventDescription(eventDescription);
            event.setEventLocation(eventLocation);
            event.setEventDate(eventDate);

            if (Thumbnails != null && !Thumbnails.isEmpty()) {
                String key = r2MediaService.uploadFile(Thumbnails);
                String thumbNailUrl = r2MediaService.getPublicUrl(key);
                event.setThumbnailUrl(thumbNailUrl);

            }
            Events savedEvent = eventRepo.save(event);
            return EventsMapper.mapToEventsDto(savedEvent);
        }catch(EntityNotFoundException e){
            throw new RuntimeException(e.getMessage());
        }
    }
///  updating an event
    @Override
    public EventsDto updateEvent(Long id, String eventName, String eventDescription, String eventLocation, String eventDate, MultipartFile Thumbnails) {

        try{
        Events event= eventRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Event not found"));

        if (event.getEventName() !=null ){
            event.setEventName(eventName);
        }
        if (event.getEventDescription() !=null ){
            event.setEventDescription(eventDescription);
        }
        if (event.getEventLocation() !=null ){
            event.setEventLocation(eventLocation);
        }
        if (event.getEventDate() !=null ){
            event.setEventDate(eventDate);
        }
        if (Thumbnails!=null && !Thumbnails.isEmpty()) {
            String newKey = r2MediaService.updateFile(event.getEventLocation(), Thumbnails);
            String thumbNails=r2MediaService.getPublicUrl(newKey);
            event.setThumbnailUrl(thumbNails);
        }

        Events updatedEvent = eventRepo.save(event);
        return EventsMapper.mapToEventsDto(updatedEvent);
    }
    catch (EntityNotFoundException e){
            throw new RuntimeException(e.getMessage());
    }

    }
/// deleting all events
    @Override
    public void deleteEvent(Long id) {

        try{
        Events events=eventRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Event not found"));

        if (events.getThumbnailUrl()!=null){
            r2MediaService.deleteFile(events.getThumbnailUrl());
        }

        eventRepo.delete(events);}
        catch (EntityNotFoundException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    /// get Event By name
    @Override

    public EventsDto getEventsByName(String eventName) {
        try{
        Events events=eventRepo.getByEventName(eventName);
        return EventsMapper.mapToEventsDto(events);}
        catch (EntityNotFoundException e){
            throw new RuntimeException(e.getMessage());
        }
    }
/// get all events
    @Override
    public List<EventsDto> getAllEvents() {
        try{
        List<Events> events= eventRepo.findAll();
        return events.stream().map(EventsMapper::mapToEventsDto).collect(Collectors.toList());
    }
    catch (EntityNotFoundException e){
        throw new RuntimeException(e.getMessage());}
    }
}
