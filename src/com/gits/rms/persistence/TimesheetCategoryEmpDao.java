
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.TimesheetCategoryEmpVO;

public interface TimesheetCategoryEmpDao {
    List getAllTimeSheetCategoryEmp();

    void updateTimeSheetCategoryEmp(TimesheetCategoryEmpVO timeSheetCategoryEmp);

    void insertTimeSheetCategoryEmp(TimesheetCategoryEmpVO timeSheetCategoryEmp);

    void deleteTimeSheetCategoryEmp(TimesheetCategoryEmpVO timeSheetCategoryEmp);

    List timeSheetCategoryEmpSearchResult(TimesheetCategoryEmpVO timeSheetCategoryEmp);

    TimesheetCategoryEmpVO getTimeSheetCategoryEmp(Integer hcmoTimesheetCategoryEmpId);

    TimesheetCategoryEmpVO getTimeSheetCategoryEmpCount(Integer id);

    List getEmpTimeSheetCategory(Integer EmployeeId);

    TimesheetCategoryEmpVO getTimeSheetCategoryEmpDetail(TimesheetCategoryEmpVO timesheetCategoryEmp);

    List checkTimeSheetCategoryInTimeSheetCategoryAssign(TimesheetCategoryEmpVO timesheetCategoryEmp);
}
