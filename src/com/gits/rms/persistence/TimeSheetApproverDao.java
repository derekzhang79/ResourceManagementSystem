
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.TimeSheetApproverVO;

public interface TimeSheetApproverDao {
    List getAllTimeSheetApprover();

    void updateTimeSheetApprover(TimeSheetApproverVO TimeSheetApprover);

    void insertTimeSheetApprover(TimeSheetApproverVO TimeSheetApprover);

    void deleteTimeSheetApprover(TimeSheetApproverVO TimeSheetApprover);

    TimeSheetApproverVO getTimeSheetApprover(Integer id);

    Integer checkTimesheetApprover(Integer id);

    TimeSheetApproverVO getEmpTimeSheetApprover(TimeSheetApproverVO timeSheetApprover);

    List<TimeSheetApproverVO> getEmployeeAllTimeSheetApprover(Integer employeeId);

    TimeSheetApproverVO getEmployeeTimeSheetApprover(Integer id);

    Integer getTimeSheetApproverCount(TimeSheetApproverVO timeSheetApprover);

    List getAllTsSubEmployees();

    List timeAppSearchResult(TimeSheetApproverVO time);

    TimeSheetApproverVO getEmployeeTimeSheetApproverDetails(Integer id);

    List<TimeSheetApproverVO> getAllTimeSheeetSubEmployee(Integer id);

    TimeSheetApproverVO getTsSelfApprover(Integer id);

    // DashBoard TimeSheet For Approval
    Integer getDashTsForApproval();

    Integer getDashTsForApprovalToday();

    Integer getDashTsForApprovalThreeDays();

    Integer getDashTsForApprovalOneWeek();
}
