
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.TimesheetCategoryVO;

public interface TimesheetCategoryService {
    void deleteTimesheetCategory(TimesheetCategoryVO TimesheetCategory);

    List getAllTimesheetCategory();

    List getAssignedTimesheetCategoryid(Integer id);

    TimesheetCategoryVO getTimesheetCategory(Integer hcmoTimesheetCategoryId);

    TimesheetCategoryVO getTimesheetCategoryId(String hcmoTimesheetCategoryName);

    TimesheetCategoryVO getTimesheetCategoryCronJob(Integer hcmoTimesheetCategoryId,String clientId);

    TimesheetCategoryVO getTimesheetCategoryIdCronJob(String hcmoTimesheetCategoryName, String clientId);

    void insertTimesheetCategory(TimesheetCategoryVO TimesheetCategory);

    List timesheetCategorySearchResult(TimesheetCategoryVO TimeSheetCategory);

    void updateTimesheetCategory(TimesheetCategoryVO TimesheetCategory);
}
