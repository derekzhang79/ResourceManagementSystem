
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpensesAttachmentVO;

public interface ExpensesAttachmentDao {

    List getAllExpensesAttachment(Integer id);

    List getAllAttachmentId(Integer id);

    ExpensesAttachmentVO getAllExpensesAttachmentFile(Integer id);

    void updateExpensesAttachment(ExpensesAttachmentVO expAttach);

    void insertExpensesAttachment(ExpensesAttachmentVO expAttach);

    void deleteExpensesAttachment(Integer id);

    ExpensesAttachmentVO getExpensesAttachment(Integer id);

    List getExpAttachmentsStartingWith(String sFileName);

    void insertExpensesAttach(ExpensesAttachmentVO expAttach);

    List viewExpensesAttachment(List<EmployeeExpensesVO> expenseList);

    List viewExpensesAttachmentForApproval(List<EmployeeExpensesVO> expenseApproveList);
}
