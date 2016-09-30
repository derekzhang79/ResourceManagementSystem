
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.ExpensesAccountantApproverVO;

public interface ExpensesAccountantApproverService {
    List accountantSearchResult(ExpensesAccountantApproverVO empAcc);

    Integer checkAccountantExists(ExpensesAccountantApproverVO empAcc);

    Integer checkExpensesAccountantApprover(Integer id);

    void deleteExpensesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover);

    List getAllEmployeesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover);

    List getAllExpensesAccountantApprover();

    ExpensesAccountantApproverVO getEmpExpensesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover);

    List<ExpensesAccountantApproverVO> getEmployeeAllExpAccountantApprover(Integer employeeId);

    ExpensesAccountantApproverVO getExpensesAccountantApprover(Integer id);

    void insertExpensesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover);

    void updateExpensesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover);
}
