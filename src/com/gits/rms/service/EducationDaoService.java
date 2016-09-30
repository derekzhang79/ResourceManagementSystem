
package com.gits.rms.service;


import java.util.List;

import com.gits.rms.persistence.EducationDao;
import com.gits.rms.persistence.EducationHibernateDao;
import com.gits.rms.vo.EducationVO;

public class EducationDaoService implements EducationService {

    private EducationDao dao;

    public EducationDaoService() {
        dao = new EducationHibernateDao();
    }

    @Override
    public void deleteEducation(EducationVO edu) {
        dao.deleteEducation(edu);
    }

    @Override
    public List educationSearchResult(EducationVO edu) {
        return dao.educationSearchResult(edu);
    }

    @Override
    public List getAllEducation() {
        return dao.getAllEducation();
    }

    @Override
    public EducationVO getEducation(Integer id) {
        return dao.getEducation(id);
    }

    @Override
    public EducationVO getEmpEducation(EducationVO edu) {
        return dao.getEmpEducation(edu);
    }

    @Override
    public List<EducationVO> getEmployeeAllEducation(Integer employeeId) {
        return dao.getEmployeeAllEducation(employeeId);
    }

    @Override
    public void insertEducation(EducationVO edu) {
        dao.insertEducation(edu);
    }

    @Override
    public void updateEducation(EducationVO edu) {
        dao.updateEducation(edu);
    }
    
    @Override
    public List getAllSubEmployeeEducationList(List<Integer> empReportToEmpId){
    	return dao.getAllSubEmployeeEducationList(empReportToEmpId);
    }

}
