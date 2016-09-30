
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.LeaveHistoryDao;
import com.gits.rms.persistence.LeaveHistoryHibernateDao;
import com.gits.rms.vo.LeaveHistoryVO;
import com.gits.rms.vo.ReportsVO;

public class LeaveHistoryDaoService implements LeaveHistoryService {
    private LeaveHistoryDao dao;

    public LeaveHistoryDaoService() {
        dao = new LeaveHistoryHibernateDao();
    }

    @Override
    public List getAllLeaveHistory() {
        return dao.getAllLeaveHistory();
    }

    @Override
    public LeaveHistoryVO getLeaveHistory(Integer id) {
        return dao.getLeaveHistory(id);
    }

    @Override
    public List<LeaveHistoryVO> getLeaveHistoryDetails(Integer employeeId) {
        return dao.getLeaveHistoryDetails(employeeId);
    }

    @Override
    public List getLeaveHistorySearch(LeaveHistoryVO lhist) {
        return dao.getLeaveHistorySearch(lhist);
    }

    @Override
    public List getLeaveHistorySubEmployee() {
        return dao.getLeaveHistorySubEmployee();
    }

    @Override
    public List getLeaveHistReports(ReportsVO report) {
        return dao.getLeaveHistReports(report);
    }

    @Override
    public List getSubEmpLeaveHistorySearch(LeaveHistoryVO lhist) {
        return dao.getSubEmpLeaveHistorySearch(lhist);
    }

    @Override
    public void insertLeaveHistory(LeaveHistoryVO lhist) {
        dao.insert(lhist);
    }

    @Override
    public void updateLeaveHistory(LeaveHistoryVO lhist) {
        dao.update(lhist);
    }
}
