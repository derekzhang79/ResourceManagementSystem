
package com.gits.rms.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.persistence.HibernateUtil;
import com.gits.rms.vo.EducationVO;
import com.gits.rms.vo.LicenseVO;

public interface LicenseService {

    void deleteLicense(LicenseVO license);

    List getAllLicense();
    
    List getAllSubEmployeeLicenseList(List<Integer> empReportToEmpId);

    LicenseVO getEmpLicense(LicenseVO license);

    List<LicenseVO> getEmployeeAllLicense(Integer employeeId);

    LicenseVO getLicense(Integer id);

    void insertLicense(LicenseVO license);

    List licenseSearchResult(LicenseVO license);

    void updateLicense(LicenseVO license);
}
