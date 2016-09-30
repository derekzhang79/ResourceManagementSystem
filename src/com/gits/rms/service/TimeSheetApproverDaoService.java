
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.TimeSheetApproverDao;
import com.gits.rms.persistence.TimeSheetApproverHibernateDao;
import com.gits.rms.vo.TimeSheetApproverVO;

public class TimeSheetApproverDaoService implements TimeSheetApproverService {

    private TimeSheetApproverDao dao;

    public TimeSheetApproverDaoService() {
        dao = new TimeSheetApproverHibernateDao();
    }

    @Override
    public Integer checkTimesheetApprover(Integer id) {
        return dao.checkTimesheetApprover(id);
    }

    @Override
    public void deleteTimeSheetApprover(TimeSheetApproverVO timeSheetApprover) {
        dao.deleteTimeSheetApprover(timeSheetApprover);
    }

    @Override
    public List<TimeSheetApproverVO> getAllTimeSheeetSubEmployee(Integer id) {
        return dao.getAllTimeSheeetSubEmployee(id);
    }

    @Override
    public List getAllTimeSheetApprover() {
        return dao.getAllTimeSheetApprover();
    }

    @Override
    public List getAllTsSubEmployees() {
        return dao.getAllTsSubEmployees();
    }

    // DashBoard TimeSheet For Approval
    @Override
    public Integer getDashTsForApproval() {
        return dao.getDashTsForApproval();
    }

    @Override
    public Integer getDashTsForApprovalOneWeek() {
        return dao.getDashTsForApprovalOneWeek();
    }

    @Override
    public Integer getDashTsForApprovalThreeDays() {
        return dao.getDashTsForApprovalThreeDays();
    }

    @Override
    public Integer getDashTsForApprovalToday() {
        return dao.getDashTsForApprovalToday();
    }

    @Override
    public List<TimeSheetApproverVO> getEmployeeAllTimeSheetApprover(Integer employeeId) {
        return dao.getEmployeeAllTimeSheetApprover(employeeId);
    }

    @Override
    public TimeSheetApproverVO getEmployeeTimeSheetApproverDetails(Integer id) {

        return dao.getEmployeeTimeSheetApproverDetails(id);
    }

    @Override
    public TimeSheetApproverVO getEmpTimeSheetApprover(TimeSheetApproverVO timeSheetApprover) {
        return dao.getEmpTimeSheetApprover(timeSheetApprover);
    }

    @Override
    public TimeSheetApproverVO getTimeSheetApprover(Integer id) {
        return dao.getTimeSheetApprover(id);
    }

    @Override
    public Integer getTimeSheetApproverCount(TimeSheetApproverVO timeSheetApprover) {
        return dao.getTimeSheetApproverCount(timeSheetApprover);
    }

    @Override
    public TimeSheetApproverVO getTsSelfApprover(Integer id) {
        return dao.getTsSelfApprover(id);
    }

    @Override
    public void insertTimeSheetApprover(TimeSheetApproverVO TimeSheetApprover) {
        dao.insertTimeSheetApprover(TimeSheetApprover);
    }

    @Override
    public List timeAppSearchResult(TimeSheetApproverVO time) {
        return dao.timeAppSearchResult(time);
    }

    @Override
    public void updateTimeSheetApprover(TimeSheetApproverVO TimeSheetApprover) {
        dao.updateTimeSheetApprover(TimeSheetApprover);
    }

}
