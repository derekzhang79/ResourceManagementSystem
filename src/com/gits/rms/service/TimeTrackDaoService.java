
package com.gits.rms.service;

import java.util.Date;
import java.util.List;

import com.gits.rms.persistence.TimeTrackDao;
import com.gits.rms.persistence.TimeTrackHibernateDao;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;
import com.gits.rms.vo.TimeTrackVO;

public class TimeTrackDaoService implements TimeTrackService {
    private TimeTrackDao dao;

    public TimeTrackDaoService() {
        dao = new TimeTrackHibernateDao();
    }

    @Override
    public List chkInOutTimeTrack(TimeTrackVO timetrack) {
        return dao.chkInOutTimeTrack(timetrack);
    }

    @Override
    public List getAllTimeDetails(TimeTrackVO timetrack) {
        return dao.getAllTimeDetails(timetrack);
    }

    @Override
    public List getAllTimeTrack(TimeTrackVO timetrack) {
        return dao.getAllTimeTrack(timetrack);
    }

    @Override
    public List getAllTimeTrackById(TimeTrackVO timetrack) {
        return dao.getAllTimeTrackById(timetrack);
    }

    @Override
    public List getAllTimeTrackByIdCronJob(TimeTrackVO timetrack, String clientId) {
        return dao.getAllTimeTrackByIdCronJob(timetrack, clientId);
    }

    @Override
    public List getAllTimeTrackCronJob(TimeTrackVO timetrack, String clientId) {
        return dao.getAllTimeTrackCronJob(timetrack, clientId);
    }

    @Override
    public void insertInOutTime(TimeTrackVO timetrack) {
        dao.insertInOutTime(timetrack);
    }

    @Override
    public void insertTimeSheet(TimeSheetCategoryAssignVO timeSheetCategoryAssign, int empid, int categoryId) {
        dao.insertTimeSheet(timeSheetCategoryAssign, empid, categoryId);
    }

    @Override
    public void insertTimeSheetActivity(TimeSheetProjectAssignVO timeSheetProjectAssign, int empid) {
        dao.insertTimeSheetActivity(timeSheetProjectAssign, empid);
    }

    @Override
    public void insertTimeSheetActivityCronJob(TimeSheetProjectAssignVO timeSheetProjectAssign, int empid,String clientId) {
        dao.insertTimeSheetActivityCronJob(timeSheetProjectAssign, empid,clientId);
    }

    @Override
    public void insertTimeSheetCronJob(TimeSheetCategoryAssignVO timeSheetCategoryAssign, int empid, int categoryId, String clientId) {
        dao.insertTimeSheetCronJob(timeSheetCategoryAssign, empid, categoryId, clientId);
    }

    @Override
    public List resetimeTrack(TimeTrackVO timetrack, String clientId) {
        return dao.resetimeTrack(timetrack, clientId);
    }

    @Override
    public List timeSheetProjectAssignSearch(Integer EmployeeId, TimeTrackVO timetrackVO, Date endDate) {
        return dao.timeSheetProjectAssignSearch(EmployeeId, timetrackVO, endDate);
    }

    @Override
    public List timeSheetProjectAssignSearchCronJob(Integer EmployeeId, ProjectVO proTimeObj,ProjectActivityVO proActivityTimeObj,Date endDate,String clientId) {
        return dao.timeSheetProjectAssignSearchCronJob(EmployeeId, proTimeObj,proActivityTimeObj, endDate,clientId);
    }

    @Override
    public void updateCheckOut(TimeTrackVO timetrack) {
        dao.updateCheckOut(timetrack);
    }

    @Override
    public void updateCheckOutCronJob(TimeTrackVO timetrack, String clientId) {
        dao.updateCheckOutCronJob(timetrack, clientId);
    }

    @Override
    public void updateTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign, int empid, int categoryId) {
        dao.updateTimeSheetCategoryAssign(timeSheetCategoryAssign, empid, categoryId);
    }

    @Override
    public ProjectVO getProjectAllDetailsCronJob(Integer id,String clientId) {
        return dao.getProjectAllDetailsCronJob(id,clientId);
    }

    @Override
    public ProjectActivityVO getProjectActivityAllDetailsCronJob(Integer id,String clientId) {
        return dao.getProjectActivityAllDetailsCronJob(id,clientId);
    }


    @Override
    public void updateTimeSheetCategoryAssignCronJob(TimeSheetCategoryAssignVO timeSheetCategoryAssign, int empid, int categoryId, String clientId) {
        dao.updateTimeSheetCategoryAssignCronJob(timeSheetCategoryAssign, empid, categoryId, clientId);
    }

    @Override
    public void updateTimeSheetProjectAssign(TimeSheetProjectAssignVO timeSheetProjectAssign, int empid) {
        dao.updateTimeSheetProjectAssign(timeSheetProjectAssign, empid);
    }

    @Override
    public void updateTimeSheetProjectAssignCronJob(TimeSheetProjectAssignVO timeSheetProjectAssign, int empid,String clientId) {
        dao.updateTimeSheetProjectAssignCronJob(timeSheetProjectAssign, empid,clientId);
    }
}
