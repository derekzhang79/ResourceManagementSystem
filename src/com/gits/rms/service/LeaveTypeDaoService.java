
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.LeaveTypeDao;
import com.gits.rms.persistence.LeaveTypeHibernateDao;
import com.gits.rms.vo.LeaveTypeVO;

public class LeaveTypeDaoService implements LeaveTypeService {
    private LeaveTypeDao dao;

    public LeaveTypeDaoService() {
        dao = new LeaveTypeHibernateDao();
    }

    @Override
    public void deleteLeaveType(LeaveTypeVO leave) {
        dao.deleteLeaveType(leave);
    }

    @Override
    public List getAllLeaveType() {
        return dao.getAllLeaveType();
    }

    @Override
    public LeaveTypeVO getLeaveType(Integer id) {
        return dao.getLeaveType(id);
    }

    @Override
    public List getValidateLeaveQuota(LeaveTypeVO leave) {
        return dao.getValidateLeaveQuota(leave);
    }

    @Override
    public void insertLeaveType(LeaveTypeVO leave) {
        dao.insertLeaveType(leave);
    }

    @Override
    public List leaveTypeSearchResult(LeaveTypeVO leave) {
        return dao.leaveTypeSearchResult(leave);
    }

    @Override
    public void updateLeaveType(LeaveTypeVO leave) {
        dao.updateLeaveType(leave);
    }
}
