
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.PayStubVO;
import com.gits.rms.vo.ReportsVO;

public interface PayStubService {

    void deletePayStub(PayStubVO payStubVO);

    List getAllPayStubs();
    
    List getAllSubEmployeePayStubList(List<Integer> empReportToEmpId);

    List getEmployeeAllPayStub(Integer id);

    List getEmployeePayStubs(int payStubId);

    PayStubVO getPayStub(Integer id);

    PayStubVO getPayStubCronJob(Integer id, String clientId);

    List getPayStubEmployeeReports(ReportsVO report);

    List getPayStubLeaveQuotaReports(ReportsVO report);

    List getPayStubLeaveReqApprovalReports(ReportsVO report);

    List getPayStubReports(ReportsVO report);

    void insertPayStub(PayStubVO payStub);

    List payStubSearchResult(PayStubVO payStubVO);

    void updatePayStub(PayStubVO payStub);
}
