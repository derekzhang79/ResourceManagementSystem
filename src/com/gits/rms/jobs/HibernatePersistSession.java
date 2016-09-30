
package com.gits.rms.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.gits.rms.persistence.EmployeeStatusHibernateDao;

public class HibernatePersistSession implements Job {

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        EmployeeStatusHibernateDao oEmpStatusService = new EmployeeStatusHibernateDao();
    }
}
