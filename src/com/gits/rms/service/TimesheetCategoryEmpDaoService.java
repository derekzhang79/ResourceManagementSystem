
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.TimesheetCategoryEmpDao;
import com.gits.rms.persistence.TimesheetCategoryEmpHibernateDao;
import com.gits.rms.vo.TimesheetCategoryEmpVO;

public class TimesheetCategoryEmpDaoService implements TimesheetCategoryEmpService {

    private TimesheetCategoryEmpDao dao;

    public TimesheetCategoryEmpDaoService() {
        dao = new TimesheetCategoryEmpHibernateDao();
    }

    @Override
    public List checkTimeSheetCategoryInTimeSheetCategoryAssign(TimesheetCategoryEmpVO timesheetCategoryEmp) {
        return dao.checkTimeSheetCategoryInTimeSheetCategoryAssign(timesheetCategoryEmp);
    }

    @Override
    public void deleteTimeSheetCategoryEmp(TimesheetCategoryEmpVO timeSheetCategoryEmp) {
        dao.deleteTimeSheetCategoryEmp(timeSheetCategoryEmp);

    }

    @Override
    public List getAllTimeSheetCategoryEmp() {

        return dao.getAllTimeSheetCategoryEmp();
    }

    @Override
    public List getEmpTimeSheetCategory(Integer EmployeeId) {

        return dao.getEmpTimeSheetCategory(EmployeeId);
    }

    @Override
    public TimesheetCategoryEmpVO getTimeSheetCategoryEmp(Integer hcmoTimesheetCategoryEmpId) {
        return dao.getTimeSheetCategoryEmp(hcmoTimesheetCategoryEmpId);
    }

    @Override
    public TimesheetCategoryEmpVO getTimeSheetCategoryEmpCount(Integer id) {
        return dao.getTimeSheetCategoryEmpCount(id);
    }

    @Override
    public TimesheetCategoryEmpVO getTimeSheetCategoryEmpDetail(TimesheetCategoryEmpVO timesheetCategoryEmp) {

        return dao.getTimeSheetCategoryEmpDetail(timesheetCategoryEmp);
    }

    @Override
    public void insertTimeSheetCategoryEmp(TimesheetCategoryEmpVO timeSheetCategoryEmp) {
        dao.insertTimeSheetCategoryEmp(timeSheetCategoryEmp);
    }

    @Override
    public List timeSheetCategoryEmpSearchResult(TimesheetCategoryEmpVO timeSheetCategoryEmp) {
        return dao.timeSheetCategoryEmpSearchResult(timeSheetCategoryEmp);
    }

    @Override
    public void updateTimeSheetCategoryEmp(TimesheetCategoryEmpVO timeSheetCategoryEmp) {
        dao.updateTimeSheetCategoryEmp(timeSheetCategoryEmp);
    }
}
