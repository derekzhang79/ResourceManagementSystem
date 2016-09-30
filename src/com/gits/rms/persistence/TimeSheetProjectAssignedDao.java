
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;

public interface TimeSheetProjectAssignedDao {

    List getAllTsProjAss();

    ProjectAssignEmpVO getTsProjAss(Integer id);

    void insertTsProjAss(ProjectAssignEmpVO tsempproj);

    void updateTsProjAss(ProjectAssignEmpVO tsempproj);

    void deleteTsProjAss(ProjectAssignEmpVO tsempproj);

    List tsProjAssSearchResult(ProjectAssignEmpVO tsempproj);

    List getCurrentSubEmployeeForTimeSheet();

    List<ProjectAssignEmpVO> getCurrentSubEmployeeListForTsProjectAssigned();

    List<ProjectAssignEmpVO> searchDuplicateProjectAssign(ProjectAssignEmpVO projAssignEmpVo);

    List<ProjectAssignEmpVO> isExistProjectAssign(ProjectAssignEmpVO projAssign);
    
    List<ProjectAssignEmpVO> getEmpTargetAndGoal(Integer empId);
    
    List<ProjectAssignEmpVO> getEmployeeProjectActivity(Integer empId, Integer projectId);
    
    ProjectAssignEmpVO getEmployeeTargetAndGoal(Integer empId, Integer projectId, Integer projActivityId);
    
    List<TimeSheetProjectAssignVO> checkAssignedProjInTimesheet(ProjectAssignEmpVO assignProj);
}
