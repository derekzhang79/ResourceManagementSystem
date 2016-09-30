
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.EducationVO;
import com.gits.rms.vo.LicenseVO;

public interface LicenseDao {

    List getAllLicense();
    
    List getAllSubEmployeeLicenseList(List<Integer> empReportToEmpId);

    LicenseVO getLicense(Integer id);

    void insertLicense(LicenseVO license);

    void updateLicense(LicenseVO license);

    void deleteLicense(LicenseVO license);

    LicenseVO getEmpLicense(LicenseVO license);

    List<LicenseVO> getEmployeeAllLicense(Integer employeeId);

    List licenseSearchResult(LicenseVO license);
}
