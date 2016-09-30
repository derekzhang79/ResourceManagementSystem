
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.LeaveTypeVO;

public interface LeaveTypeService {

    void deleteLeaveType(LeaveTypeVO leave);

    List getAllLeaveType();

    LeaveTypeVO getLeaveType(Integer id);

    List getValidateLeaveQuota(LeaveTypeVO leave);

    void insertLeaveType(LeaveTypeVO leave);

    List leaveTypeSearchResult(LeaveTypeVO leave);

    void updateLeaveType(LeaveTypeVO leave);
}