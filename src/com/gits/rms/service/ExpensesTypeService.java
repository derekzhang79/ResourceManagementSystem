
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.ExpensesTypeVO;

public interface ExpensesTypeService {
    void deleteExpensesType(ExpensesTypeVO expType);

    List expensesTypeSearchResult(ExpensesTypeVO expType);

    List getAllExpensesType();

    ExpensesTypeVO getExpensesType(Integer id);

    ExpensesTypeVO getExpensesTypeId(String sExpTYpeName);

    List getExpensesTypesStartingWith(String sExpTYpe);

    void insertExpensesType(ExpensesTypeVO expType);

    void updateExpensesType(ExpensesTypeVO expType);
}
