
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.WorkExperienceDao;
import com.gits.rms.persistence.WorkExperienceHibernateDao;
import com.gits.rms.vo.WorkExperienceVO;

public class WorkExperienceDaoService implements WorkExperienceService {

    private WorkExperienceDao dao;

    public WorkExperienceDaoService() {
        dao = new WorkExperienceHibernateDao();
    }

    @Override
    public void deleteWorkExperience(WorkExperienceVO workexp) {
        dao.deleteWorkExperience(workexp);
    }

    @Override
    public List getAllWorkExperience() {
        return dao.getAllWorkExperience();
    }
    
    @Override
    public List getAllSubEmployeeWorkExperienceList(List<Integer> empReportToEmpId){
    	return dao.getAllSubEmployeeWorkExperienceList(empReportToEmpId);
    }

    @Override
    public List<WorkExperienceVO> getEmployeeAllWorkExperience(Integer employeeId) {
        return dao.getEmployeeAllWorkExperience(employeeId);
    }

    @Override
    public WorkExperienceVO getEmpWorkExperience(WorkExperienceVO workexp) {
        return dao.getEmpWorkExperience(workexp);
    }

    @Override
    public WorkExperienceVO getWorkExperience(Integer id) {
        return dao.getWorkExperience(id);
    }

    @Override
    public void insertWorkExperience(WorkExperienceVO workexp) {
        dao.insertWorkExperience(workexp);
    }

    @Override
    public void updateWorkExperience(WorkExperienceVO workexp) {
        dao.updateWorkExperience(workexp);
    }

    @Override
    public List workExpSearchResult(WorkExperienceVO workexp) {
        return dao.workExpSearchResult(workexp);
    }
}
