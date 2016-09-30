
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.ExpensesApproverVO;

public interface ExpensesApproverService {
    Integer checkExpensesApprover(Integer id,Integer clientId);

    void deleteExpensesApprover(ExpensesApproverVO expApprover);

    List expAppSearchResult(ExpensesApproverVO exp);

    List getAllEmployeesApprover(ExpensesApproverVO expApprover);

    List getAllExpEmpForApproverSearch(ExpensesApproverVO expApprover);

    List getAllExpensesApprover();

    List<ExpensesApproverVO> getAllSubEmployee(Integer id);

    ExpensesApproverVO getEmpExpensesApprover(ExpensesApproverVO expApprover);

    List<ExpensesApproverVO> getEmployeeAllExpApprover(Integer employeeId);

    Integer getExpApproverCount(ExpensesApproverVO expApprover);

    ExpensesApproverVO getExpensesApprover(Integer id);

    ExpensesApproverVO getSelfApprover(Integer id);

    void insertExpensesApprover(ExpensesApproverVO expApprover);

    void updateExpensesApprover(ExpensesApproverVO expApprover);
}
