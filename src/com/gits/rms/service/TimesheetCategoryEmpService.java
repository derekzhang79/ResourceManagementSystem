
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.TimesheetCategoryEmpVO;

public interface TimesheetCategoryEmpService {
    List checkTimeSheetCategoryInTimeSheetCategoryAssign(TimesheetCategoryEmpVO timesheetCategoryEmp);

    void deleteTimeSheetCategoryEmp(TimesheetCategoryEmpVO timeSheetCategoryEmp);

    List getAllTimeSheetCategoryEmp();

    List getEmpTimeSheetCategory(Integer EmployeeId);

    TimesheetCategoryEmpVO getTimeSheetCategoryEmp(Integer hcmoTimesheetCategoryEmpId);

    TimesheetCategoryEmpVO getTimeSheetCategoryEmpCount(Integer id);

    TimesheetCategoryEmpVO getTimeSheetCategoryEmpDetail(TimesheetCategoryEmpVO timesheetCategoryEmp);

    void insertTimeSheetCategoryEmp(TimesheetCategoryEmpVO timeSheetCategoryEmp);

    List timeSheetCategoryEmpSearchResult(TimesheetCategoryEmpVO timeSheetCategoryEmp);

    void updateTimeSheetCategoryEmp(TimesheetCategoryEmpVO timeSheetCategoryEmp);

}
