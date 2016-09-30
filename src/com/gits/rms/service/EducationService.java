
package com.gits.rms.service;


import java.util.List;

import com.gits.rms.vo.EducationVO;

public interface EducationService {

    void deleteEducation(EducationVO edu);

    List educationSearchResult(EducationVO edu);

    List getAllEducation();

    EducationVO getEducation(Integer id);

    EducationVO getEmpEducation(EducationVO edu);

    List<EducationVO> getEmployeeAllEducation(Integer employeeId);

    void insertEducation(EducationVO edu);

    void updateEducation(EducationVO edu);
    
    List getAllSubEmployeeEducationList(List<Integer> empReportToEmpId);
}
