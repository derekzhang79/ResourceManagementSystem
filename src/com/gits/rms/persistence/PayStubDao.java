
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.PayStubVO;
import com.gits.rms.vo.ReportsVO;

public interface PayStubDao {

    List getAllPayStubs();
    
    List getAllSubEmployeePayStubList(List<Integer> empReportToEmpId);

    PayStubVO getPayStub(Integer id);

    PayStubVO getPayStubCronJob(Integer id, String clientId);

    List payStubSearchResult(PayStubVO payStubVO);

    List getEmployeePayStubs(int payStubId);

    void insertPayStub(PayStubVO payStubVO);

    void deletePayStub(PayStubVO payStub);

    void updatePayStub(PayStubVO payStub);

    List getEmployeeAllPayStub(Integer id);

    List getPayStubEmployeeReports(ReportsVO report);

    List getPayStubReports(ReportsVO report);

    List getPayStubLeaveQuotaReports(ReportsVO report);

    List getPayStubLeaveReqApprovalReports(ReportsVO report);
}
