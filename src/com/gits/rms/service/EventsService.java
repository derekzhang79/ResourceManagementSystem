
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EventsVO;

public interface EventsService {
    List getAllEvents(int empId);

    List getAllEventsByDate(String eventDate, String clientId);

    List getAllEventsById(int empId);

    List getEventsById(int eventId);

    void insertEvents(EventsVO events);

    void removeEvent(EventsVO events);

    void saveOrUpdateEvent(EventsVO events);
}
