
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.TimeSheetApproverVO;

public interface TimeSheetApproverService {
    Integer checkTimesheetApprover(Integer id);

    void deleteTimeSheetApprover(TimeSheetApproverVO timeSheetApprover);

    List<TimeSheetApproverVO> getAllTimeSheeetSubEmployee(Integer id);

    List getAllTimeSheetApprover();

    List getAllTsSubEmployees();

    // DashBoard TimeSheet For Approval
    Integer getDashTsForApproval();

    Integer getDashTsForApprovalOneWeek();

    Integer getDashTsForApprovalThreeDays();

    Integer getDashTsForApprovalToday();

    List<TimeSheetApproverVO> getEmployeeAllTimeSheetApprover(Integer employeeId);

    TimeSheetApproverVO getEmployeeTimeSheetApproverDetails(Integer id);

    TimeSheetApproverVO getEmpTimeSheetApprover(TimeSheetApproverVO timeSheetApprover);

    TimeSheetApproverVO getTimeSheetApprover(Integer id);

    Integer getTimeSheetApproverCount(TimeSheetApproverVO timeSheetApprover);

    TimeSheetApproverVO getTsSelfApprover(Integer id);

    void insertTimeSheetApprover(TimeSheetApproverVO TimeSheetApprover);

    List timeAppSearchResult(TimeSheetApproverVO time);

    void updateTimeSheetApprover(TimeSheetApproverVO TimeSheetApprover);
}
