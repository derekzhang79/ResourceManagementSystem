
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.LeaveApproverVO;

public interface LeaveApproverDao {
    List getAllLeaveApprover();

    void updateLeaveApprover(LeaveApproverVO leaveApprover);

    void insertLeaveApprover(LeaveApproverVO leaveApprover);

    void deleteLeaveApprover(LeaveApproverVO leaveApprover);

    LeaveApproverVO getLeaveApprover(Integer id);

    Integer checkLeaveApprover(Integer id,Integer clientid);

    LeaveApproverVO getEmpLeaveApprover(LeaveApproverVO leaveApprover);

    List<LeaveApproverVO> getEmployeeAllLeaveApprover(Integer employeeId);

    List leaveAppSearchResult(LeaveApproverVO leave);

    List<LeaveApproverVO> getCurrentLeaveApprover(Integer id);

    Integer getLeaveApproverCount(LeaveApproverVO leaveApprover);

    LeaveApproverVO getSelfApprover(Integer id);
}
