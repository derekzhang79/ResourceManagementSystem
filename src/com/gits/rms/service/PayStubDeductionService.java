
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.PayStubDeductionsVO;

public interface PayStubDeductionService {

    boolean checkTodaysPayStubsDeduction(String clientId);

    void deletePayStubDeduction(PayStubDeductionsVO payStubDeduction);

    List getAllEmployeePayStubsDeduction(Integer id);

    List getAllEmployeePayStubsDeductionCronJob(Integer id, String clientId);

    List getAllEmpPayStubsDedWithOutSelectedDeduction(Integer payStubDeductionId, Integer payStubId);

    List getAllTodaysPayStubsDeduction(String clientId);

    PayStubDeductionsVO getPayStubDeduction(Integer id);

    void insertPayStubDeduction(PayStubDeductionsVO payStubDeduction);

    void updatePayStubDeduction(PayStubDeductionsVO payStubDeduction);

    void updatePayStubNetSalary(int payStubID, double netSalary);

    void updatePayStubNetSalaryCronJob(int payStubID, double netSalary, String clientId);

}
