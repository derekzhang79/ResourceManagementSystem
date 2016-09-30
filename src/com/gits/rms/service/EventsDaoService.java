
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.EventsDao;
import com.gits.rms.persistence.EventsHibernateDao;
import com.gits.rms.vo.EventsVO;

public class EventsDaoService implements EventsService {
    private EventsDao dao;

    public EventsDaoService() {
        dao = new EventsHibernateDao();
    }

    @Override
    public List getAllEvents(int empId) {
        return dao.getAllEvents(empId);
    }

    @Override
    public List getAllEventsByDate(String eventDate, String clientId) {
        return dao.getAllEventsByDate(eventDate, clientId);
    }

    @Override
    public List getAllEventsById(int empId) {
        return dao.getAllEventsById(empId);
    }

    @Override
    public List getEventsById(int eventId) {
        return dao.getEventsById(eventId);
    }

    @Override
    public void insertEvents(EventsVO events) {
        dao.insertEvents(events);
    }

    @Override
    public void removeEvent(EventsVO events) {
        dao.removeEvent(events);
    }

    @Override
    public void saveOrUpdateEvent(EventsVO events) {
        dao.saveOrUpdateEvent(events);
    }

}
