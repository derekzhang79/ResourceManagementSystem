
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.LeaveTypeVO;

public interface LeaveTypeDao {
    List getAllLeaveType();

    LeaveTypeVO getLeaveType(Integer id);

    void insertLeaveType(LeaveTypeVO leave);

    void updateLeaveType(LeaveTypeVO leave);

    void deleteLeaveType(LeaveTypeVO leave);

    List leaveTypeSearchResult(LeaveTypeVO leave);

    List getValidateLeaveQuota(LeaveTypeVO leave);
}