
package com.gits.rms.persistence;

import java.util.Date;
import java.util.List;

import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;
import com.gits.rms.vo.TimeTrackVO;

public interface TimeTrackDao {

    void insertInOutTime(TimeTrackVO timetrack);

    void updateCheckOut(TimeTrackVO timetrack);

    void updateCheckOutCronJob(TimeTrackVO timetrack, String clientId);

    List getAllTimeTrack(TimeTrackVO timetrack);

    List getAllTimeTrackCronJob(TimeTrackVO timetrack, String clientId);

    List chkInOutTimeTrack(TimeTrackVO timetrack);

    List getAllTimeTrackById(TimeTrackVO timetrack);

    List getAllTimeTrackByIdCronJob(TimeTrackVO timetrack, String clientId);

    void updateTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign, int empid, int categoryId);

    void updateTimeSheetCategoryAssignCronJob(TimeSheetCategoryAssignVO timeSheetCategoryAssign, int empid, int categoryId, String clientId);

    void insertTimeSheet(TimeSheetCategoryAssignVO timeSheetCategoryAssign, int empid, int categoryId);

    void insertTimeSheetCronJob(TimeSheetCategoryAssignVO timeSheetCategoryAssign, int empid, int categoryId, String clientId);

    List resetimeTrack(TimeTrackVO timetrack, String clientId);

    List getAllTimeDetails(TimeTrackVO timetrack);

    ProjectVO getProjectAllDetailsCronJob(Integer id,String clientId);

    ProjectActivityVO getProjectActivityAllDetailsCronJob(Integer id,String clientId);

    List timeSheetProjectAssignSearch(Integer EmployeeId, TimeTrackVO timetrackVO, Date endDate);

    List timeSheetProjectAssignSearchCronJob(Integer EmployeeId, ProjectVO proTimeObj,ProjectActivityVO proActivityTimeObj, Date endDate,String clientId);

    void updateTimeSheetProjectAssign(TimeSheetProjectAssignVO timeSheetProjectAssign, int empid);

    void updateTimeSheetProjectAssignCronJob(TimeSheetProjectAssignVO timeSheetProjectAssign, int empid,String clientId);

    void insertTimeSheetActivity(TimeSheetProjectAssignVO timeSheetProjectAssign, int empid);

    void insertTimeSheetActivityCronJob(TimeSheetProjectAssignVO timeSheetProjectAssign, int empid,String clientId);

}
