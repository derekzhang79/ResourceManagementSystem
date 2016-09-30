
package com.gits.rms.service;

import java.util.Date;
import java.util.List;

import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.ReportTimeEnteredDislayVO;
import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;

public interface TimeSheetProjectAssignService {
    void deleteTimeSheetProjectAssign(TimeSheetProjectAssignVO timeSheetProjectAssign);

    List getAllTimeSheetProjectAssign();

    List getEmployeeAssignedProjectList(Integer employeeId);

    List getEmpPendingApprovalStatus(Integer empId);

    List<TimeSheetProjectAssignVO> getEmpTimesheetDetails(Integer empId);

    TimeSheetProjectAssignVO getTimeSheetProjectAssign(Integer timeSheetProjectAssignId);

    void insertTimeSheetProjectAssign(TimeSheetProjectAssignVO timeSheetProjectAssign);

    void timeSheetProjectAssignDelete(EmployeesVO empVo, ProjectVO projVo);

    List timeSheetProjectAssignSearch(Integer EmployeeId, Integer timeSheetCategory, Date endDate, Integer CategoryId);

    List timeSheetProjectAssignSearchResult(Integer EmployeeId, Date date, Date endDate);

    void updateTimeSheetProjectAssign(TimeSheetProjectAssignVO timeSheetProjectAssign);
    
    ProjectActivityVO getEmpProjActivityByName(String projActivityName);
    
    List<TimeSheetProjectAssignVO> getEnteredHoursByProjActivity(int empid,int activityId);
    
    List<ReportTimeEnteredDislayVO> getTimeEstimationReport(ReportsVO report);
}
