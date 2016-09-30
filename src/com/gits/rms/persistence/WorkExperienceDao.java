
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.WorkExperienceVO;

public interface WorkExperienceDao {

    List getAllWorkExperience();
    
    List getAllSubEmployeeWorkExperienceList(List<Integer> empReportToEmpId);

    WorkExperienceVO getWorkExperience(Integer id);

    void insertWorkExperience(WorkExperienceVO workexp);

    void updateWorkExperience(WorkExperienceVO workexp);

    void deleteWorkExperience(WorkExperienceVO workexp);

    WorkExperienceVO getEmpWorkExperience(WorkExperienceVO workexp);

    List<WorkExperienceVO> getEmployeeAllWorkExperience(Integer employeeId);

    List workExpSearchResult(WorkExperienceVO workexp);
}
