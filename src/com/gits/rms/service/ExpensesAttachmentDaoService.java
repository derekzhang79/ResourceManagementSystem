
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.ExpensesAttachmentDao;
import com.gits.rms.persistence.ExpensesAttachmentHibernateDao;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpensesAttachmentVO;

public class ExpensesAttachmentDaoService implements ExpensesAttachmentService {

    private ExpensesAttachmentDao dao;

    public ExpensesAttachmentDaoService() {
        dao = new ExpensesAttachmentHibernateDao();
    }

    @Override
    public void deleteExpensesAttachment(Integer id) {
        dao.deleteExpensesAttachment(id);
    }

    @Override
    public List getAllAttachmentId(Integer id) {
        return dao.getAllAttachmentId(id);
    }

    @Override
    public List getAllExpensesAttachment(Integer id) {
        return dao.getAllExpensesAttachment(id);
    }

    @Override
    public ExpensesAttachmentVO getAllExpensesAttachmentFile(Integer id) {
        return dao.getAllExpensesAttachmentFile(id);
    }

    @Override
    public List getExpAttachmentsStartingWith(String fileName) {
        return dao.getExpAttachmentsStartingWith(fileName);
    }

    @Override
    public ExpensesAttachmentVO getExpensesAttachment(Integer id) {
        return dao.getExpensesAttachment(id);
    }

    @Override
    public void insertExpensesAttach(ExpensesAttachmentVO expAttach) {
        dao.insertExpensesAttach(expAttach);
    }

    @Override
    public void insertExpensesAttachment(ExpensesAttachmentVO expAttach) {
        dao.insertExpensesAttachment(expAttach);
    }

    @Override
    public void updateExpensesAttachment(ExpensesAttachmentVO expAttach) {
        dao.updateExpensesAttachment(expAttach);
    }

    @Override
    public List viewExpensesAttachment(List<EmployeeExpensesVO> expenseList) {
        return dao.viewExpensesAttachment(expenseList);
    }

    @Override
    public List viewExpensesAttachmentForApproval(List<EmployeeExpensesVO> expenseApproveList) {
        return dao.viewExpensesAttachmentForApproval(expenseApproveList);
    }

}