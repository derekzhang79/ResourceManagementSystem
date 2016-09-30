
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.TimesheetCategoryDao;
import com.gits.rms.persistence.TimesheetCategoryHibernateDao;
import com.gits.rms.vo.TimesheetCategoryVO;

public class TimesheetCategoryDaoService implements TimesheetCategoryService {

    private TimesheetCategoryDao dao;

    public TimesheetCategoryDaoService() {
        dao = new TimesheetCategoryHibernateDao();
    }

    @Override
    public void deleteTimesheetCategory(TimesheetCategoryVO TimesheetCategory) {
        dao.deleteTimeSheetCategory(TimesheetCategory);
    }

    @Override
    public List getAllTimesheetCategory() {
        return dao.getAllTimeSheetCategory();
    }

    @Override
    public List getAssignedTimesheetCategoryid(Integer id) {

        return dao.getAssignedTimesheetCategoryid(id);
    }

    @Override
    public TimesheetCategoryVO getTimesheetCategory(Integer hcmoTimesheetCategoryId) {
        return dao.getTimeSheetCategory(hcmoTimesheetCategoryId);
    }

    @Override
    public TimesheetCategoryVO getTimesheetCategoryCronJob(Integer hcmoTimesheetCategoryId,String clientId) {
        return dao.getTimesheetCategoryCronJob(hcmoTimesheetCategoryId,clientId);
    }

    @Override
    public TimesheetCategoryVO getTimesheetCategoryId(String hcmoTimesheetCategoryName) {
        return dao.getTimesheetCategoryId(hcmoTimesheetCategoryName);
    }

    @Override
    public TimesheetCategoryVO getTimesheetCategoryIdCronJob(String hcmoTimesheetCategoryName, String clientId) {
        return dao.getTimesheetCategoryIdCronJob(hcmoTimesheetCategoryName, clientId);
    }

    @Override
    public void insertTimesheetCategory(TimesheetCategoryVO TimesheetCategory) {
        dao.insertTimeSheetCategory(TimesheetCategory);
    }

    @Override
    public List timesheetCategorySearchResult(TimesheetCategoryVO TimeSheetCategory) {
        return dao.timesheetCategorySearchResult(TimeSheetCategory);
    }

    @Override
    public void updateTimesheetCategory(TimesheetCategoryVO TimesheetCategory) {
        dao.updateTimeSheetCategory(TimesheetCategory);
    }
}
