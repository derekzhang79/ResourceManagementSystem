
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.LeaveApproverDao;
import com.gits.rms.persistence.LeaveApproverHibernateDao;
import com.gits.rms.vo.LeaveApproverVO;

public class LeaveApproverDaoService implements LeaveApproverService {

    private LeaveApproverDao dao;

    public LeaveApproverDaoService() {
        dao = new LeaveApproverHibernateDao();
    }

    @Override
    public Integer checkLeaveApprover(Integer id,Integer clientid) {
        return dao.checkLeaveApprover(id,clientid);
    }

    @Override
    public void deleteLeaveApprover(LeaveApproverVO leaveApprover) {
        dao.deleteLeaveApprover(leaveApprover);
    }

    @Override
    public List getAllLeaveApprover() {
        return dao.getAllLeaveApprover();
    }

    @Override
    public List<LeaveApproverVO> getCurrentLeaveApprover(Integer id) {
        return dao.getCurrentLeaveApprover(id);
    }

    @Override
    public LeaveApproverVO getEmpLeaveApprover(LeaveApproverVO leaveApprover) {
        return dao.getEmpLeaveApprover(leaveApprover);
    }

    @Override
    public List<LeaveApproverVO> getEmployeeAllLeaveApprover(Integer employeeId) {
        return dao.getEmployeeAllLeaveApprover(employeeId);
    }

    @Override
    public LeaveApproverVO getLeaveApprover(Integer id) {
        return dao.getLeaveApprover(id);
    }

    @Override
    public Integer getLeaveApproverCount(LeaveApproverVO leaveApprover) {
        return dao.getLeaveApproverCount(leaveApprover);
    }

    @Override
    public LeaveApproverVO getSelfApprover(Integer id) {
        return dao.getSelfApprover(id);
    }

    @Override
    public void insertLeaveApprover(LeaveApproverVO leaveApprover) {
        dao.insertLeaveApprover(leaveApprover);
    }

    @Override
    public List leaveAppSearchResult(LeaveApproverVO leave) {
        return dao.leaveAppSearchResult(leave);
    }

    @Override
    public void updateLeaveApprover(LeaveApproverVO leaveApprover) {
        dao.updateLeaveApprover(leaveApprover);

    }
}
