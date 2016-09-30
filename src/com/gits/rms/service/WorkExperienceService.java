
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.WorkExperienceVO;

public interface WorkExperienceService {

    void deleteWorkExperience(WorkExperienceVO workexp);

    List getAllWorkExperience();
    
    List getAllSubEmployeeWorkExperienceList(List<Integer> empReportToEmpId);

    List<WorkExperienceVO> getEmployeeAllWorkExperience(Integer employeeId);

    WorkExperienceVO getEmpWorkExperience(WorkExperienceVO workexp);

    WorkExperienceVO getWorkExperience(Integer id);

    void insertWorkExperience(WorkExperienceVO workexp);

    void updateWorkExperience(WorkExperienceVO workexp);

    List workExpSearchResult(WorkExperienceVO workexp);
}
