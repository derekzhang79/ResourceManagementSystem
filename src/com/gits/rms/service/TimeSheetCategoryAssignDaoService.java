
package com.gits.rms.service;

import java.util.Date;
import java.util.List;

import com.gits.rms.persistence.TimeSheetCategoryAssignDao;
import com.gits.rms.persistence.TimeSheetCategoryAssignHibernateDao;
import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;

public class TimeSheetCategoryAssignDaoService implements TimeSheetCategoryAssignService {

    private TimeSheetCategoryAssignDao dao;

    public TimeSheetCategoryAssignDaoService() {
        dao = new TimeSheetCategoryAssignHibernateDao();
    }

    @Override
    public void deleteTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign) {
        dao.deleteTimeSheetCategoryAssign(timeSheetCategoryAssign);
    }

    @Override
    public List getAllTimeSheetCategoryAssign() {
        return dao.getAllTimeSheetCategoryAssign();
    }

    @Override
    public TimeSheetCategoryAssignVO getTimeSheetCategoryAssign(Integer timeSheetCategoryAssignId) {
        return dao.getTimeSheetCategoryAssign(timeSheetCategoryAssignId);
    }

    @Override
    public void insertTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign) {
        dao.insertTimeSheetCategoryAssign(timeSheetCategoryAssign);
    }

    @Override
    public List timeSheetCategoryAssignReport(ReportsVO reportsVO) {
        return dao.timeSheetCategoryAssignReport(reportsVO);
    }

    @Override
    public List timeSheetCategoryAssignSearch(Integer EmployeeId, Integer timeSheetCategory, Date endDate) {
        return dao.timeSheetCategoryAssignSearch(EmployeeId, timeSheetCategory, endDate);
    }

    @Override
    public List timeSheetCategoryAssignSearchCronJob(Integer EmployeeId, Integer timeSheetCategory, Date endDate, String clientId) {
        return dao.timeSheetCategoryAssignSearchCronJob(EmployeeId, timeSheetCategory, endDate, clientId);
    }

    @Override
    public List timeSheetCategoryAssignSearchResult(Integer EmployeeId, Date date, Date endDate) {
        return dao.timeSheetCategoryAssignSearchResult(EmployeeId, date, endDate);
    }

    @Override
    public List timeSheetCategoryAssignSummationReport(ReportsVO reportsVO) {
        return dao.timeSheetCategoryAssignSummationReport(reportsVO);
    }

    @Override
    public List timeSheetCategoryEmpSearchResult(Integer EmployeeId) {
        return dao.timeSheetCategoryEmpSearchResult(EmployeeId);
    }

    @Override
    public List timeSheetProAssignProjectSummationReport(ReportsVO reportsVO) {
        return dao.timeSheetProAssignProjectSummationReport(reportsVO);
    }

    @Override
    public List timeSheetProjectAssignReport(ReportsVO reportsVO) {
        return dao.timeSheetProjectAssignReport(reportsVO);
    }

    @Override
    public List timeSheetProjectAssignSummationReport(ReportsVO reportsVO) {
        return dao.timeSheetProjectAssignSummationReport(reportsVO);
    }

    @Override
    public void updateTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign) {
        dao.updateTimeSheetCategoryAssign(timeSheetCategoryAssign);
    }

}
