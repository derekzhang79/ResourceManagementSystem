
package com.gits.rms.persistence;

import java.util.Date;
import java.util.List;

import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.ReportTimeEnteredDislayVO;
import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;

public interface TimeSheetProjectAssignDao {
    List getAllTimeSheetProjectAssign();

    void updateTimeSheetProjectAssign(TimeSheetProjectAssignVO timeSheetProjectAssign);

    void insertTimeSheetProjectAssign(TimeSheetProjectAssignVO timeSheetProjectAssign);

    void deleteTimeSheetProjectAssign(TimeSheetProjectAssignVO timeSheetProjectAssign);

    TimeSheetProjectAssignVO getTimeSheetProjectAssign(Integer timeSheetProjectAssignId);

    List timeSheetProjectAssignSearchResult(Integer EmployeeId, Date date, Date endDate);

    List timeSheetProjectAssignSearch(Integer EmployeeId, Integer timeSheetCategory, Date endDate, Integer CategoryId);

    void timeSheetProjectAssignDelete(EmployeesVO empVo, ProjectVO projVo);

    List getEmployeeAssignedProjectList(Integer employeeId);

    List getEmpPendingApprovalStatus(Integer empId);

    List<TimeSheetProjectAssignVO> getEmpTimesheetDetails(Integer empId);
    
    ProjectActivityVO getEmpProjActivityByName(String projActivityName);
    
    List<TimeSheetProjectAssignVO> getEnteredHoursByProjActivity(int empid,int activityId);
    
    List<ReportTimeEnteredDislayVO> getTimeEstimationReport(ReportsVO report);
}
