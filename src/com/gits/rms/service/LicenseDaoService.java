
package com.gits.rms.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.persistence.HibernateUtil;
import com.gits.rms.persistence.LicenseDao;
import com.gits.rms.persistence.LicenseHibernateDao;
import com.gits.rms.vo.EducationVO;
import com.gits.rms.vo.LicenseVO;

public class LicenseDaoService implements LicenseService {

    private LicenseDao dao;

    public LicenseDaoService() {
        dao = new LicenseHibernateDao();
    }

    @Override
    public void deleteLicense(LicenseVO license) {
        dao.deleteLicense(license);
    }

    @Override
    public List getAllLicense() {
        return dao.getAllLicense();
    }
    
    @Override
    public List getAllSubEmployeeLicenseList(List<Integer> empReportToEmpId) {
    	return dao.getAllSubEmployeeLicenseList(empReportToEmpId);
    }
    
    @Override
    public LicenseVO getEmpLicense(LicenseVO license) {
        return dao.getEmpLicense(license);
    }

    @Override
    public List<LicenseVO> getEmployeeAllLicense(Integer employeeId) {
        return dao.getEmployeeAllLicense(employeeId);
    }

    @Override
    public LicenseVO getLicense(Integer id) {
        return dao.getLicense(id);
    }

    @Override
    public void insertLicense(LicenseVO license) {
        dao.insertLicense(license);
    }

    @Override
    public List licenseSearchResult(LicenseVO license) {
        return dao.licenseSearchResult(license);
    }

    @Override
    public void updateLicense(LicenseVO license) {
        dao.updateLicense(license);
    }
}
