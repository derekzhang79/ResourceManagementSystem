
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.PayStubDeductionsVO;

public interface PayStubDeductionDao {

    List getAllEmployeePayStubsDeduction(Integer id);

    List getAllEmployeePayStubsDeductionCronJob(Integer id, String clientId);

    List getAllEmpPayStubsDedWithOutSelectedDeduction(Integer payStubDeductionId, Integer payStubId);

    PayStubDeductionsVO getPayStubDeduction(Integer id);

    void insertPayStubDeduction(PayStubDeductionsVO payStubDeduction);

    void updatePayStubDeduction(PayStubDeductionsVO payStubDeduction);

    void deletePayStubDeduction(PayStubDeductionsVO payStubDeduction);

    void updatePayStubNetSalary(int payStubID, double netSalary);

    void updatePayStubNetSalaryCronJob(int payStubID, double netSalary, String clientId);

    boolean checkTodaysPayStubsDeduction(String clientId);

    List getAllTodaysPayStubsDeduction(String clientId);
}
