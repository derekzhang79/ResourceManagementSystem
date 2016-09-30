
package com.gits.rms.service;

import java.util.Date;
import java.util.List;

import com.gits.rms.persistence.TimeSheetProjectAssignDao;
import com.gits.rms.persistence.TimeSheetProjectAssignHibernateDao;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.ReportTimeEnteredDislayVO;
import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;

public class TimesheetProjectDaoService implements TimeSheetProjectAssignService {

    private TimeSheetProjectAssignDao dao;

    public TimesheetProjectDaoService() {
        dao = new TimeSheetProjectAssignHibernateDao();
    }

    @Override
    public void deleteTimeSheetProjectAssign(TimeSheetProjectAssignVO timeSheetProjectAssign) {
        dao.deleteTimeSheetProjectAssign(timeSheetProjectAssign);
    }

    @Override
    public List getAllTimeSheetProjectAssign() {
        return dao.getAllTimeSheetProjectAssign();
    }

    @Override
    public List getEmployeeAssignedProjectList(Integer employeeId) {
        return dao.getEmployeeAssignedProjectList(employeeId);
    }

    @Override
    public List getEmpPendingApprovalStatus(Integer employeeId) {
        return dao.getEmpPendingApprovalStatus(employeeId);
    }

    @Override
    public List<TimeSheetProjectAssignVO> getEmpTimesheetDetails(Integer empId) {
        return dao.getEmpTimesheetDetails(empId);
    }

    @Override
    public TimeSheetProjectAssignVO getTimeSheetProjectAssign(Integer timeSheetProjectAssignId) {
        return dao.getTimeSheetProjectAssign(timeSheetProjectAssignId);
    }

    @Override
    public void insertTimeSheetProjectAssign(TimeSheetProjectAssignVO timeSheetProjectAssign) {
        dao.insertTimeSheetProjectAssign(timeSheetProjectAssign);

    }

    @Override
    public void timeSheetProjectAssignDelete(EmployeesVO empVo, ProjectVO projVo) {
        dao.timeSheetProjectAssignDelete(empVo, projVo);
    }

    @Override
    public List timeSheetProjectAssignSearch(Integer EmployeeId, Integer timeSheetCategory, Date endDate, Integer CategoryId) {
        return dao.timeSheetProjectAssignSearch(EmployeeId, timeSheetCategory, endDate, CategoryId);
    }

    @Override
    public List timeSheetProjectAssignSearchResult(Integer EmployeeId, Date date, Date endDate) {
        return dao.timeSheetProjectAssignSearchResult(EmployeeId, date, endDate);
    }

    @Override
    public void updateTimeSheetProjectAssign(TimeSheetProjectAssignVO timeSheetProjectAssign) {
        dao.updateTimeSheetProjectAssign(timeSheetProjectAssign);

    }
    
    @Override
    public ProjectActivityVO getEmpProjActivityByName(String projActivityName){
    	return dao.getEmpProjActivityByName(projActivityName);
    }
    
    @Override
    public List<TimeSheetProjectAssignVO> getEnteredHoursByProjActivity(int empid,int activityId) {
    	return dao.getEnteredHoursByProjActivity(empid,activityId);
    }
    
    @Override
    public List<ReportTimeEnteredDislayVO> getTimeEstimationReport(ReportsVO report){
    	return dao.getTimeEstimationReport(report);
    }
}
