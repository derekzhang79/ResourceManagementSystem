
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.NationalityVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;

public interface TimeSheetProjeectAssignedService {

    void deleteTsProjAss(ProjectAssignEmpVO tsempproj);

    List getAllTsProjAss();

    List getCurrentSubEmployeeForTimeSheet();

    List<ProjectAssignEmpVO> getCurrentSubEmployeeListForTsProjectAssigned();

    ProjectAssignEmpVO getTsProjAss(Integer id);

    void insertTsProjAss(ProjectAssignEmpVO tsempproj);

    List<ProjectAssignEmpVO> isExistProjectAssign(ProjectAssignEmpVO projAssign);

    List<ProjectAssignEmpVO> searchDuplicateProjectAssign(ProjectAssignEmpVO projAssignEmpVo);

    List tsProjAssSearchResult(ProjectAssignEmpVO tsempproj);

    void updateTsProjAss(ProjectAssignEmpVO tsempproj);
    
    List<ProjectAssignEmpVO> getEmpTargetAndGoal(Integer empId);
    
    List<ProjectAssignEmpVO> getEmployeeProjectActivity(Integer empId, Integer projectId);
    
    ProjectAssignEmpVO getEmployeeTargetAndGoal(Integer empId, Integer projectId, Integer projActivityId);
    
    List<TimeSheetProjectAssignVO> checkAssignedProjInTimesheet(ProjectAssignEmpVO assignProj);
}
