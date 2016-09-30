
package com.gits.rms.service;

import java.util.Date;
import java.util.List;

import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;

public interface TimeSheetCategoryAssignService {
    void deleteTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign);

    List getAllTimeSheetCategoryAssign();

    TimeSheetCategoryAssignVO getTimeSheetCategoryAssign(Integer timeSheetCategoryAssignId);

    void insertTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign);

    List timeSheetCategoryAssignReport(ReportsVO reportsVO);

    List timeSheetCategoryAssignSearch(Integer EmployeeId, Integer timeSheetCategory, Date endDate);

    List timeSheetCategoryAssignSearchCronJob(Integer EmployeeId, Integer timeSheetCategory, Date endDate, String clientId);

    List timeSheetCategoryAssignSearchResult(Integer EmployeeId, Date date, Date endDate);

    List timeSheetCategoryAssignSummationReport(ReportsVO reportsVO);

    List timeSheetCategoryEmpSearchResult(Integer EmployeeId);

    List timeSheetProAssignProjectSummationReport(ReportsVO reportsVO);

    List timeSheetProjectAssignReport(ReportsVO reportsVO);

    List timeSheetProjectAssignSummationReport(ReportsVO reportsVO);

    void updateTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign);
}
