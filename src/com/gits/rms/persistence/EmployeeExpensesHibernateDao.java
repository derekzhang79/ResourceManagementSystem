
package com.gits.rms.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ExpensesAttachmentVO;
import com.gits.rms.vo.ExpensesDetailsVO;

public class EmployeeExpensesHibernateDao implements EmployeeExpensesDao {

    private List<EmployeeExpensesVO> empExpensesList;
    private List<ExpenseStatusTrackerVO> empStatusTrackerList;
    ExpensesDetailsVO expenseDetails;
    private List<EmployeeExpensesVO> expenseList;
    private List<ExpensesDetailsVO> expensesDetailsList;

    @Override
    public void approveAndSubmitToAccount(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(expensesStatusStracker);
            String sHql = "update EmployeeExpensesVO as eev set eev.status=:status,eev.nextLevelId =:NextLevelId,eev.updatedBy=:UpdatedBy where eev.hcmoExpensesId=:HcmoExpensesId";
            Query query = session.createQuery(sHql);
            query.setString("status", empExpenses.getStatus());
            query.setInteger("HcmoExpensesId", empExpenses.getHcmoExpensesId());
            query.setInteger("NextLevelId", 0);
            query.setInteger("UpdatedBy", empExpenses.getUpdatedBy().getEmployeeId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public Integer checkExpensesReviewer(Integer id, String ApprovalStatus) {
        Session session = HibernateUtil.getSession();
        int count = 0;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select count(*) from EmployeeExpensesVO as eev where eev.isActive=:IsActive and eev.hcmoEmployeeId =:HcmoEmployeeId and status=:ApprovalStatus ");
            query.setInteger("IsActive", 1);
            query.setString("ApprovalStatus", ApprovalStatus);
            query.setInteger("HcmoEmployeeId", id);
            for (Iterator it = query.iterate(); it.hasNext();) {
                count = Integer.valueOf(String.valueOf(it.next()));
            }
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return count;

    }

    @Override
    public Integer checkSubmitToNextLevel(Integer id) {
        Session session = HibernateUtil.getSession();
        int count = 0;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select count(hcmoEmployeeId) from ExpensesApproverVO as ea where ea.hcmoEmployeeId =:ApprovingEmployeeId and ea.isActive=:IsActive");
            query.setInteger("ApprovingEmployeeId", id);
            query.setInteger("IsActive", 1);

            for (Iterator it = query.iterate(); it.hasNext();) {
                count = Integer.valueOf(String.valueOf(it.next()));
            }
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return count;
    }

    @Override
    public ExpensesDetailsVO editExpensesDetails(Integer id) {
        Session session = HibernateUtil.getSession();
        Query q;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            q = session.createQuery("from ExpensesDetailsVO as ed where ed.hcmoExpensesDetailsId =:HcmoExpensesDetailsId and ed.isActive =:IsActive");
            q.setInteger("IsActive", 1);
            q.setInteger("HcmoExpensesDetailsId", id);
            return (ExpensesDetailsVO) q.uniqueResult();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public List forApprovedTab(ExpenseStatusTrackerVO expenseStatusStracker, EmployeeExpensesVO empExpenses) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from EmployeeExpensesVO as eev where eev.isActive=:IsActive and eev.hcmoExpensesId in (select hcmoExpensesId from ExpenseStatusTrackerVO as est where est.approvalStatus =:ApprovalStatus) and eev.hcmoEmployeeId in (select ea.hcmoEmployeeId from ExpensesApproverVO as ea where ea.approvingEmployeeId =:ApprovingEmployeeId and ea.isActive=:IsActive)");
            query.setInteger("IsActive", 1);
            query.setString("ApprovalStatus", expenseStatusStracker.getApprovalStatus());
            query.setInteger("ApprovingEmployeeId", empExpenses.getHcmoEmployeeId().getEmployeeId());
            expenseList = query.list();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return expenseList;
    }

    @Override
    public List forRejectedTab(ExpenseStatusTrackerVO expenseStatusStracker, EmployeeExpensesVO empExpenses) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from EmployeeExpensesVO as eev where eev.isActive=:IsActive and eev.hcmoExpensesId in (select hcmoExpensesId from ExpenseStatusTrackerVO as est where est.approvalStatus =:ApprovalStatus) and eev.hcmoEmployeeId in (select ea.hcmoEmployeeId from ExpensesApproverVO as ea where ea.approvingEmployeeId =:ApprovingEmployeeId and ea.isActive=:IsActive) ");
            query.setInteger("IsActive", 1);
            query.setString("ApprovalStatus", expenseStatusStracker.getApprovalStatus());
            query.setInteger("ApprovingEmployeeId", empExpenses.getHcmoEmployeeId().getEmployeeId());
            expenseList = query.list();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return expenseList;
    }

    @Override
    public List forReviewTab(ExpenseStatusTrackerVO expenseStatusStracker, EmployeeExpensesVO empExpenses) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.beginTransaction();
            Query query = session.createQuery("from EmployeeExpensesVO as eev where eev.isActive=:IsActive and eev.hcmoExpensesId in (select hcmoExpensesId from ExpenseStatusTrackerVO as est where est.approvalStatus =:ApprovalStatus) and eev.hcmoEmployeeId in (select ea.hcmoEmployeeId from ExpensesApproverVO as ea where ea.approvingEmployeeId =:ApprovingEmployeeId and ea.isActive=:IsActive)");
            query.setInteger("IsActive", 1);
            query.setString("ApprovalStatus", expenseStatusStracker.getApprovalStatus());
            query.setInteger("ApprovingEmployeeId", empExpenses.getHcmoEmployeeId().getEmployeeId());
            expenseList = query.list();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return expenseList;
    }

    @Override
    public List getAllEmployeeExpenses() {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.beginTransaction();
            Query query = session.createQuery("from EmployeeExpensesVO where isActive=:IsActive order by hcmoEmployeeId");
            query.setInteger("IsActive", 1);
            empExpensesList = query.list();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return empExpensesList;
    }

    @Override
    public List getAllExpensesApp(EmployeeExpensesVO empExpenses) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.beginTransaction();
            Query query = session.createQuery("from EmployeeExpensesVO as exp where exp.status =:Status  and nextLevelId=:NextLevelId");
            query.setString("Status", empExpenses.getStatus());
            query.setInteger("NextLevelId", empExpenses.getHcmoEmployeeId().getEmployeeId());
            empExpensesList = query.list();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return empExpensesList;
    }

    @Override
    public List getAllExpensesForApproval(EmployeeExpensesVO empExpenses) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.beginTransaction();
            Query query = session.createQuery("from EmployeeExpensesVO as exp where exp.hcmoEmployeeId in (select ea.hcmoEmployeeId from ExpensesApproverVO as ea where ea.approvingEmployeeId =:ApprovingEmployeeId and ea.isActive=:IsActive) and exp.status =:Status and nextLevelId=0");
            query.setString("Status", empExpenses.getStatus());
            query.setInteger("ApprovingEmployeeId", empExpenses.getHcmoEmployeeId().getEmployeeId());
            query.setInteger("IsActive", 1);
            expenseList = query.list();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return expenseList;
    }

    @Override
    public EmployeeExpensesVO getEmployeeExpenses(Integer id) {

        Session session = HibernateUtil.getSession();
        Query q;
        Transaction tx = null;
        EmployeeExpensesVO employeeExpenses = new EmployeeExpensesVO();
        try {
            tx = session.beginTransaction();
            session.beginTransaction();
            q = session.createQuery("from EmployeeExpensesVO as d where d.hcmoExpensesId =:HcmoExpensesId");
            q.setInteger("HcmoExpensesId", id);
            employeeExpenses = (EmployeeExpensesVO) q.uniqueResult();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return employeeExpenses;
    }

    @Override
    public List getExpensesForApprovalEmployee(Integer id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.beginTransaction();
            Query q = session.createQuery("from ExpensesDetailsVO as ed where ed.hcmoExpensesId =:HcmoExpensesId and ed.isActive =:IsActive");
            q.setInteger("IsActive", 1);
            q.setInteger("HcmoExpensesId", id);
            expensesDetailsList = q.list();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return expensesDetailsList;
    }

    @Override
    public List getExpenseStatusTrackerForAccountant(Integer id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.beginTransaction();
            Query q = session.createQuery("from ExpenseStatusTrackerVO as ed where ed.hcmoExpensesId =:HcmoExpensesId");
            q.setInteger("HcmoExpensesId", id);
            empStatusTrackerList = q.list();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return empStatusTrackerList;
    }

    @Override
    public EmployeeExpensesVO getInsertedExpensesInfo(EmployeeExpensesVO empExpenses) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from EmployeeExpensesVO as eev where eev.isActive=:IsActive and eev.hcmoExpensesId =:HcmoExpensesId ");
            query.setInteger("IsActive", 1);
            query.setInteger("HcmoExpensesId", empExpenses.getHcmoExpensesId());
            empExpenses = (EmployeeExpensesVO) query.uniqueResult();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return empExpenses;
    }

    @Override
    public List getMyReviewExpensesDetails(EmployeeExpensesVO empExpenses) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from ExpensesDetailsVO as ed where ed.hcmoExpensesId =:HcmoExpensesId and ed.isActive =:IsActive");
            q.setInteger("IsActive", 1);
            q.setInteger("HcmoExpensesId", empExpenses.getHcmoExpensesId());
            expensesDetailsList = q.list();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return expensesDetailsList;
    }

    @Override
    public void insertEmployeeExpenses(EmployeeExpensesVO empExpenses) {

        Session session = HibernateUtil.getSession();
        Map session1 = ActionContext.getContext().getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(empExpenses);
            tx.commit();
            session1.put("ExpensesId", empExpenses.getTotalAmount());
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertExpenseAttachments(ExpensesAttachmentVO expenseAttach) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(expenseAttach);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertStatusTracker(ExpenseStatusTrackerVO expensesStatusStracker) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(expensesStatusStracker);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List myExpensesReview(EmployeeExpensesVO empExpenses) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from EmployeeExpensesVO as eev where eev.isActive=:IsActive and eev.hcmoEmployeeId =:HcmoEmployeeId and status=:ApprovalStatus ");
            query.setInteger("IsActive", 1);
            query.setString("ApprovalStatus", empExpenses.getStatus());
            query.setInteger("HcmoEmployeeId", empExpenses.getHcmoEmployeeId().getEmployeeId());
            expenseList = query.list();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return expenseList;
    }

    @Override
    public void rejectEmployeeExpense(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(expensesStatusStracker);

            String sHql = "update EmployeeExpensesVO as eev set eev.status=:status,eev.nextLevelId =:NextLevelId,eev.updatedBy=:UpdatedBy where eev.hcmoExpensesId=:HcmoExpensesId";
            Query query = session.createQuery(sHql);
            query.setString("status", empExpenses.getStatus());
            query.setInteger("HcmoExpensesId", empExpenses.getHcmoExpensesId());
            query.setInteger("NextLevelId", 0);
            query.setInteger("UpdatedBy", empExpenses.getUpdatedBy().getEmployeeId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void reviewEmployeeExpense(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(expensesStatusStracker);

            String sHql = "update EmployeeExpensesVO as eev set eev.status=:status,eev.nextLevelId =:NextLevelId,eev.updatedBy=:UpdatedBy where eev.hcmoExpensesId=:HcmoExpensesId";
            Query query = session.createQuery(sHql);
            query.setString("status", empExpenses.getStatus());
            query.setInteger("HcmoExpensesId", empExpenses.getHcmoExpensesId());
            query.setInteger("NextLevelId", 0);
            query.setInteger("UpdatedBy", empExpenses.getUpdatedBy().getEmployeeId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void updateEditedExpenses(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            String sHql = "update EmployeeExpensesVO as eev set eev.status=:Status,eev.totalAmount=:TotalAmount,eev.updatedBy=:UpdatedBy where eev.hcmoExpensesId=:HcmoExpensesId";
            Query query = session.createQuery(sHql);
            query.setBigDecimal("TotalAmount", empExpenses.getTotalAmount());
            query.setString("Status", empExpenses.getStatus());
            query.setInteger("HcmoExpensesId", empExpenses.getHcmoExpensesId());
            query.setInteger("UpdatedBy", empExpenses.getUpdatedBy().getEmployeeId());
            query.executeUpdate();
            tx1.commit();

            try {
                tx = session.beginTransaction();
                session.save(expensesStatusStracker);
                tx.commit();
            } catch (RuntimeException e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
                throw e;

            } finally {
                session.flush();
                session.close();
            }
        } catch (RuntimeException e) {
            if (tx1 != null) {
                tx1.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateExpensesDetails(ExpensesDetailsVO expenseDetails) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ExpensesDetailsVO set hcmoExpensesTypeId=:HcmoExpensesTypeId, "
                + "projectId=:ProjectId, " + "amount=:Amount, " + "note=:Note, "
                + "description=:Description, " + "expensesDate=:ExpensesDate, "
                + "updatedBy=:UpdatedBy " + "where hcmoExpensesDetailsId=:HcmoExpensesDetailsId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoExpensesTypeId", expenseDetails.getHcmoExpensesTypeId().getHcmoExpensesTypeId());
            query.setInteger("ProjectId", expenseDetails.getProjectId().getProjectId());
            query.setBigDecimal("Amount", expenseDetails.getAmount());
            query.setString("Note", expenseDetails.getNote());
            query.setString("Description", expenseDetails.getDescription());
            query.setDate("ExpensesDate", expenseDetails.getExpensesDate());
            query.setInteger("UpdatedBy", expenseDetails.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoExpensesDetailsId", expenseDetails.getHcmoExpensesDetailsId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }
}
