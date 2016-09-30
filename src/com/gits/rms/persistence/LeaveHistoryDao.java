
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.LeaveHistoryVO;
import com.gits.rms.vo.ReportsVO;

public interface LeaveHistoryDao {
    List getAllLeaveHistory();

    List getLeaveHistorySearch(LeaveHistoryVO lhist);

    LeaveHistoryVO getLeaveHistory(Integer id);

    List getLeaveHistorySubEmployee();

    List getSubEmpLeaveHistorySearch(LeaveHistoryVO lhist);

    // List getApprovedEmpList();
    void update(LeaveHistoryVO lhist);

    void insert(LeaveHistoryVO lhist);

    List getLeaveHistReports(ReportsVO report);

    List<LeaveHistoryVO> getLeaveHistoryDetails(Integer employeeId);
}
