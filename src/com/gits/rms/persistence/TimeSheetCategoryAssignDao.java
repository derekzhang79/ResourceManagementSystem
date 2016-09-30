
package com.gits.rms.persistence;

import java.util.Date;
import java.util.List;

import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;

public interface TimeSheetCategoryAssignDao {
    List getAllTimeSheetCategoryAssign();

    void updateTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign);

    void insertTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign);

    void deleteTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign);

    TimeSheetCategoryAssignVO getTimeSheetCategoryAssign(Integer timeSheetCategoryAssignId);

    List timeSheetCategoryAssignSearchResult(Integer EmployeeId, Date date, Date endDate);

    List timeSheetCategoryAssignSearch(Integer EmployeeId, Integer timeSheetCategory, Date endDate);

    List timeSheetCategoryAssignSearchCronJob(Integer EmployeeId, Integer timeSheetCategory, Date endDate, String clientId);

    List timeSheetCategoryAssignReport(ReportsVO reportsVO);

    List timeSheetProjectAssignReport(ReportsVO reportsVO);

    List timeSheetProjectAssignSummationReport(ReportsVO reportsVO);

    List timeSheetCategoryAssignSummationReport(ReportsVO reportsVO);

    List timeSheetProAssignProjectSummationReport(ReportsVO reportsVO);

    List timeSheetCategoryEmpSearchResult(Integer EmployeeId);
}
