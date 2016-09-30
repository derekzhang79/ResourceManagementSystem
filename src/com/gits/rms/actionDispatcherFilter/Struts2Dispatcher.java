
package com.gits.rms.actionDispatcherFilter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.hibernate.HibernateException;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;

import com.gits.rms.jobs.EventReminderJob;
import com.gits.rms.jobs.NationalityJob;
import com.gits.rms.jobs.PayStubJob;
import com.gits.rms.jobs.TimeTrackJob;
import com.gits.rms.persistence.HibernateUtil;

public class Struts2Dispatcher extends StrutsPrepareAndExecuteFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        try {
            HibernateUtil.createSessionFactory();
        } catch (HibernateException e) {
            throw new ServletException(e);
        }
        
       /* try {
            SchedulerFactory schFactory = new org.quartz.impl.StdSchedulerFactory();
            Scheduler scheduler = schFactory.getScheduler();
            scheduler.start();

            // Timesheet for check-in and check-out option
            JobDetail jobTimeTrack = new JobDetail("TimeTrackJob", null, TimeTrackJob.class);
            Trigger triggerTimeTrack = TriggerUtils.makeMinutelyTrigger(5);
            triggerTimeTrack.setName("TimeTrackTrigger");
            scheduler.scheduleJob(jobTimeTrack, triggerTimeTrack);

            // Events calendar reminder job
            JobDetail eventsReminder = new JobDetail("EventReminderJob", null, EventReminderJob.class);
            Trigger triggerEventReminder = TriggerUtils.makeDailyTrigger(00, 30);
            triggerEventReminder.setName("EventReminderTrigger");
            scheduler.scheduleJob(eventsReminder, triggerEventReminder);

            // PayStub Job Check the job for every 24 hrs(12 pm)
            JobDetail payStub = new JobDetail("PayStubJob", null, PayStubJob.class);
            Trigger triggerPayStub = TriggerUtils.makeDailyTrigger(00, 00);
            triggerPayStub.setName("PayStubJob");
            scheduler.scheduleJob(payStub, triggerPayStub);
            
            // Nationality Job to make the database connection exists
            JobDetail natiJob = new JobDetail("NationalityJob", null, NationalityJob.class);
            Trigger triggerNati = TriggerUtils.makeMinutelyTrigger(15);
            triggerNati.setName("NationalityJob");
            scheduler.scheduleJob(natiJob, triggerNati);
            
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

}
