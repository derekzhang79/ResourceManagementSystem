
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gits.rms.vo.EventsVO;

public class EventsHibernateDao implements EventsDao {
    private List<EventsVO> eventList;
    private List<EventsVO> event;
    private List<EventsVO> allEventList;

    @Override
    public void insertEvents(EventsVO events) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(events);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            isUnique = true;
            throw e;

        } finally {
            if (isUnique) {
                session.close();
            } else {
                session.flush();
                session.close();
            }
        }
    }

    @Override
    public List getAllEvents(int empId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EventsVO where EmployeeId=:EmployeeId and isActive=:isActive");
            query.setInteger("EmployeeId", empId);
            query.setInteger("isActive", 1);

            eventList = query.list();
            return eventList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllEventsById(int empId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EventsVO where EmployeeId=:EmployeeId and isActive=:isActive and createdBy=:createdBy");
            query.setInteger("EmployeeId", empId);
            query.setInteger("isActive", 1);
            query.setInteger("createdBy", empId);
            eventList = query.list();
            return eventList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getEventsById(int eventId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from EventsVO where eventId=:EventId and isActive=:isActive");
            query.setInteger("EventId", eventId);
            query.setInteger("isActive", 1);
            event = query.list();
            return event;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void saveOrUpdateEvent(EventsVO events) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            String sHql = "update EventsVO set eventName=:EventName,timeZone=:TimeZone,description=:Description where eventId=:EventId or groupId=:GroupId";
            Query query = session.createQuery(sHql);

            query.setInteger("EventId", events.getEventId());
            query.setString("EventName", events.getEventName());
            query.setString("TimeZone", events.getTimeZone());
            query.setString("Description", events.getDescription());
            query.setInteger("GroupId", events.getGroupId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            isUnique = true;
            throw e;

        } finally {
            if (isUnique) {
                session.close();
            } else {
                session.flush();
                session.close();
            }
        }
    }

    @Override
    public void removeEvent(EventsVO events) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EventsVO set isActive=:isActive where eventId=:EventId or groupId=:GroupId";
            Query query = session.createQuery(sHql);
            query.setInteger("isActive", 0);
            query.setInteger("EventId", events.getEventId());
            query.setInteger("GroupId", events.getGroupId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllEventsByDate(String eventDate, String clientId) {
        Session session = HibernateUtil.getSessionForCronJob(clientId);
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EventsVO where isActive=:isActive and startDate like '%%%"
                + eventDate + "%%%'");

            query.setInteger("isActive", 1);
            allEventList = query.list();
            return allEventList;
        } finally {
            session.flush();
            session.close();
        }
    }

}
