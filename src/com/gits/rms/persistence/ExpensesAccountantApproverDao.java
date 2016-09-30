
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.ExpensesAccountantApproverVO;

public interface ExpensesAccountantApproverDao {
    List getAllExpensesAccountantApprover();

    void updateExpensesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover);

    void insertExpensesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover);

    void deleteExpensesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover);

    ExpensesAccountantApproverVO getExpensesAccountantApprover(Integer id);

    ExpensesAccountantApproverVO getEmpExpenseAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover);

    List<ExpensesAccountantApproverVO> getEmployeeAllExpAccountantApprover(Integer employeeId);

    List getAllEmployeesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover);

    Integer checkExpensesAccountantApprover(Integer id);

    List accountantSearchResult(ExpensesAccountantApproverVO empAcc);

    Integer checkAccountantExists(ExpensesAccountantApproverVO empAcc);
}
