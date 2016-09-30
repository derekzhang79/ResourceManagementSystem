
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.DirectDebitVO;

public interface DirectDebitDao {

    void deleteDirectDebit(DirectDebitVO dirDebit);

    List directDebitSearchResult(DirectDebitVO dirDebit);

    List getAllDirectDebit();
    
    List getAllSubEmployeeDirectDebitList(List<Integer> empReportToEmpId);

    List getAllDirectDebitForAEmp(DirectDebitVO dirDebit);

    DirectDebitVO getDirectDebit(Integer id);

    DirectDebitVO getEmpDirectDebit(DirectDebitVO dirDebit);

    List<DirectDebitVO> getEmployeeAllDirectDebit(Integer employeeId);

    void insertDirectDebit(DirectDebitVO dirDebit);

    void updateDirectDebit(DirectDebitVO dirDebit);
}
