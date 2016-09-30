
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.LeaveApproverVO;

public interface LeaveApproverService {

    Integer checkLeaveApprover(Integer id,Integer clientid);

    void deleteLeaveApprover(LeaveApproverVO leaveApprover);

    List getAllLeaveApprover();

    List<LeaveApproverVO> getCurrentLeaveApprover(Integer id);

    LeaveApproverVO getEmpLeaveApprover(LeaveApproverVO leaveApprover);

    List<LeaveApproverVO> getEmployeeAllLeaveApprover(Integer employeeId);

    LeaveApproverVO getLeaveApprover(Integer id);

    Integer getLeaveApproverCount(LeaveApproverVO leaveApprover);

    LeaveApproverVO getSelfApprover(Integer id);

    void insertLeaveApprover(LeaveApproverVO leaveApprover);

    List leaveAppSearchResult(LeaveApproverVO leave);

    void updateLeaveApprover(LeaveApproverVO leaveApprover);
}
