package com.example.tryapp2.Mapper;

import com.example.tryapp2.Dto.EventsDto;
import com.example.tryapp2.Entity.Events;

public class EventsMapper {
    public static Events mapToEvents(EventsDto eventsDto) {
        Events events = new Events();
        events.setId(eventsDto.getId());
        events.setEventName(eventsDto.getEventName());
        events.setEventDescription(eventsDto.getEventDescription());
        events.setEventLocation(eventsDto.getEventLocation());
        events.setEventDate(eventsDto.getEventDate());
        events.setThumbnailUrl(eventsDto.getThumbnailUrl());
         return events;
    }
    public static EventsDto mapToEventsDto(Events events) {
        EventsDto eventsDto= new EventsDto();
        eventsDto.setId(events.getId());
        eventsDto.setEventName(events.getEventName());
        eventsDto.setEventDescription(events.getEventDescription());
        eventsDto.setEventLocation(events.getEventLocation());
        eventsDto.setEventDate(events.getEventDate());
        eventsDto.setThumbnailUrl(events.getThumbnailUrl());
        return eventsDto;
    }
}
