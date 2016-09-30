
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.TimesheetCategoryVO;

public interface TimesheetCategoryDao {
    List getAllTimeSheetCategory();

    void updateTimeSheetCategory(TimesheetCategoryVO TimeSheetCategory);

    void insertTimeSheetCategory(TimesheetCategoryVO TimeSheetCategory);

    void deleteTimeSheetCategory(TimesheetCategoryVO TimeSheetCategory);

    List timesheetCategorySearchResult(TimesheetCategoryVO TimeSheetCategory);

    TimesheetCategoryVO getTimeSheetCategory(Integer hcmoTimesheetCategoryId);

    TimesheetCategoryVO getTimesheetCategoryCronJob(Integer hcmoTimesheetCategoryId,String clientId);

    TimesheetCategoryVO getTimesheetCategoryId(String hcmoTimesheetCategoryName);

    List getAssignedTimesheetCategoryid(Integer id);

    TimesheetCategoryVO getTimesheetCategoryIdCronJob(String hcmoTimesheetCategoryName, String clientId);
}
