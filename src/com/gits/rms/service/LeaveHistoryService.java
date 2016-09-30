
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.LeaveHistoryVO;
import com.gits.rms.vo.ReportsVO;

public interface LeaveHistoryService {

    List getAllLeaveHistory();

    LeaveHistoryVO getLeaveHistory(Integer id);

    List<LeaveHistoryVO> getLeaveHistoryDetails(Integer employeeId);

    List getLeaveHistorySearch(LeaveHistoryVO lhist);

    List getLeaveHistorySubEmployee();

    List getLeaveHistReports(ReportsVO report);

    List getSubEmpLeaveHistorySearch(LeaveHistoryVO lhist);

    void insertLeaveHistory(LeaveHistoryVO lhist);

    void updateLeaveHistory(LeaveHistoryVO lhist);
}
