
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.ExpensesApproverVO;

public interface ExpensesApproverDao {
    List getAllExpensesApprover();

    void updateExpensesApprover(ExpensesApproverVO expApprover);

    void insertExpensesApprover(ExpensesApproverVO expApprover);

    void deleteExpensesApprover(ExpensesApproverVO expApprover);

    ExpensesApproverVO getExpensesApprover(Integer id);

    ExpensesApproverVO getEmpExpenseApprover(ExpensesApproverVO expApprover);

    List<ExpensesApproverVO> getEmployeeAllExpApprover(Integer employeeId);

    List getAllEmployeesApprover(ExpensesApproverVO expApprover);

    List getAllExpEmpForApproverSearch(ExpensesApproverVO expApprover);

    Integer checkExpensesApprover(Integer id,Integer clientId);

    List expAppSearchResult(ExpensesApproverVO exp);

    ExpensesApproverVO getSelfApprover(Integer id);

    Integer getExpApproverCount(ExpensesApproverVO expApprover);

    List<ExpensesApproverVO> getAllSubEmployee(Integer id);
}
