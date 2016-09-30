
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpensesAttachmentVO;

public interface ExpensesAttachmentService {

    void deleteExpensesAttachment(Integer id);

    List getAllAttachmentId(Integer id);

    List getAllExpensesAttachment(Integer id);

    ExpensesAttachmentVO getAllExpensesAttachmentFile(Integer id);

    List getExpAttachmentsStartingWith(String sFileName);

    ExpensesAttachmentVO getExpensesAttachment(Integer id);

    void insertExpensesAttach(ExpensesAttachmentVO expAttach);

    void insertExpensesAttachment(ExpensesAttachmentVO expAttach);

    void updateExpensesAttachment(ExpensesAttachmentVO expAttach);

    List viewExpensesAttachment(List<EmployeeExpensesVO> expenseList);

    List viewExpensesAttachmentForApproval(List<EmployeeExpensesVO> expenseApproveList);

}
