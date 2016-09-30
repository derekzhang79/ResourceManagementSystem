
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.JobTitleDao;
import com.gits.rms.persistence.JobTitleHibernateDao;
import com.gits.rms.vo.JobTitleVO;

public class JobTitleDaoService implements JobTitleService {

    private JobTitleDao dao;

    public JobTitleDaoService() {
        dao = new JobTitleHibernateDao();
    }

    @Override
    public List checkJobTitleInEmployee(JobTitleVO jobTitle) {
        return dao.checkJobTitleInEmployee(jobTitle);
    }

    @Override
    public void deleteJobTitle(JobTitleVO jobTitle) {
        dao.deleteJobTitle(jobTitle);
    }

    @Override
    public List getAllJobTitle() {
        return dao.getAllJobTitle();
    }

    @Override
    public JobTitleVO getJobTitle(Integer id) {
        return dao.getJobTitle(id);
    }

    @Override
    public void insertJobTitle(JobTitleVO jobTitle) {
        dao.insertJobTitle(jobTitle);
    }

    @Override
    public List jobTitleSearchResult(JobTitleVO jobTitle) {
        return dao.jobTitleSearchResult(jobTitle);
    }

    @Override
    public void updateJobTitle(JobTitleVO jobTitle) {
        dao.updateJobTitle(jobTitle);
    }
}
