
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.ExpensesTypeVO;

public interface ExpensesTypeDao {
    List getAllExpensesType();

    ExpensesTypeVO getExpensesType(Integer id);

    void insertExpensesType(ExpensesTypeVO expType);

    void updateExpensesType(ExpensesTypeVO expType);

    void deleteExpensesType(ExpensesTypeVO expType);

    List getExpensesTypesStartingWith(String sExpType);

    ExpensesTypeVO getExpensesTypeId(String sExpTYpeName);

    List expensesTypeSearchResult(ExpensesTypeVO expType);
}
