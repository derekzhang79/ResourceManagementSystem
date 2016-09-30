
package com.gits.rms.persistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.vo.EducationVO;
import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.PayStubVO;
import com.gits.rms.vo.ReportsVO;

public class PayStubHibernateDao implements PayStubDao {

    private EmployeesService emplService = new EmployeesDaoService();

    @Override
    public List getAllPayStubs() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(PayStubVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List getAllSubEmployeePayStubList(List<Integer> empReportToEmpId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(PayStubVO.class);
            crit.add(Restrictions.in("employee.employeeId", empReportToEmpId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
        	session.flush();
        	session.close();
        }
    }

    @Override
    public PayStubVO getPayStub(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from PayStubVO as payStub where payStub.payStubId =:PayStubId");
            q.setInteger("PayStubId", id);
            return (PayStubVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public PayStubVO getPayStubCronJob(Integer id, String clientId) {
        Session session = HibernateUtil.getSessionForCronJob(clientId);
        try {
            session.beginTransaction();
            Query q = session.createQuery("from PayStubVO as payStub where payStub.payStubId =:PayStubId");
            q.setInteger("PayStubId", id);
            return (PayStubVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getEmployeePayStubs(int payStubId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(PayStubVO.class);
            crit.add(Restrictions.eq("payStubId", payStubId));
            crit.add(Restrictions.eq("isActive", 1));
            crit.uniqueResult();
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List payStubSearchResult(PayStubVO payStubVO) {
        Session session = HibernateUtil.getSession();
        try {

            Criteria crit = session.createCriteria(PayStubVO.class);

            if (payStubVO.getEmployee().getEmployeeId() != null) {
                crit.add(Restrictions.eq("employeeId", payStubVO.getEmployee().getEmployeeId()));
            }
            crit.add(Restrictions.eq("isActive", 1));

            List list = crit.list();

            return list;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void deletePayStub(PayStubVO payStub) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sHql = "delete PayStubVO where payStubId=:PayStubId";
            Query query = session.createQuery(sHql);
            query.setInteger("PayStubId", payStub.getPayStubId());

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
    public void insertPayStub(PayStubVO payStub) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(payStub);
            tx.commit();             
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            isUnique = true;
            throw e;

        } finally {
            if (isUnique) {
                session.close();
            } else {
                session.flush();
                session.close();
            }
        }
    }

    @Override
    public void updatePayStub(PayStubVO payStub) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update PayStubVO set grossSalary=:GrossSalary,declarationDate=:DeclarationDate, "
                + "UpdatedBy=:UpdatedBy " + "where payStubId=:PayStubId";
            Query query = session.createQuery(sHql);

            query.setDouble("GrossSalary", payStub.getGrossSalary());
            query.setDate("DeclarationDate", payStub.getDeclarationDate());
            query.setInteger("UpdatedBy", payStub.getUpdatedBy().getEmployeeId());
            query.setInteger("PayStubId", payStub.getPayStubId());
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
    public List getEmployeeAllPayStub(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(PayStubVO.class);
            crit.add(Restrictions.eq("employee.employeeId", id));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getPayStubEmployeeReports(ReportsVO report) {
        Session session = HibernateUtil.getSession();
        EmployeesVO newEmployeeId = new EmployeesVO();
        List empId = new LinkedList();

        try {

            Criteria critEmpInfo = session.createCriteria(EmployeesVO.class);

            for (Iterator<EmployeesVO> it = report.getEmpIdObjList().iterator(); it.hasNext();) {
                newEmployeeId = it.next();
                empId.add(newEmployeeId.getEmployeeId());
            }

            critEmpInfo.add(Restrictions.in("employeeId", empId));
            critEmpInfo.add(Restrictions.eq("isActive", 1));

            return critEmpInfo.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getPayStubReports(ReportsVO report) {
        Session session = HibernateUtil.getSession();
        EmployeesVO newEmployeeId = new EmployeesVO();
        ActionContext.getContext().getSession();
        List empId = new LinkedList();
        List<PayStubVO> payStubList;
        Integer employeeId = null;

        try {

            Date date = new Date();
            date = report.getDate();
            SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

            for (Iterator<EmployeesVO> it = report.getEmpIdObjList().iterator(); it.hasNext();) {
                newEmployeeId = it.next();
                empId.add(newEmployeeId.getEmployeeId());
                employeeId = newEmployeeId.getEmployeeId();
            }

            session.beginTransaction();
            Query query = session.createQuery("from PayStubVO as empPayStub where year(created)=:EmpCreatedYear and empPayStub.isActive=:IsActive and "
                + "empPayStub.employee.employeeId in(select empObj.employeeId from EmployeesVO as empObj where empObj.employeeId=:EmployeeId and empObj.isActive=:IsActive)");
            query.setString("EmpCreatedYear", sdfYear.format(date));
            query.setInteger("EmployeeId", employeeId);
            query.setInteger("IsActive", 1);
            payStubList = query.list();
            return payStubList;

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getPayStubLeaveQuotaReports(ReportsVO report) {
        Session session = HibernateUtil.getSession();
        EmployeesVO newEmployeeId = new EmployeesVO();
        ActionContext.getContext().getSession();
        List empId = new LinkedList();
        List<EmployeeLeaveQuotaVO> leaveQuotaList;
        Integer employeeId = null;

        try {

            Date date = new Date();
            date = report.getDate();
            SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

            for (Iterator<EmployeesVO> it = report.getEmpIdObjList().iterator(); it.hasNext();) {
                newEmployeeId = it.next();
                empId.add(newEmployeeId.getEmployeeId());
                employeeId = newEmployeeId.getEmployeeId();
            }

            session.beginTransaction();
            Query query = session.createQuery("from EmployeeLeaveQuotaVO as leaQuo where year=:Year and leaQuo.isActive=:IsActive and "
                + "leaQuo.empIdObj.employeeId in(select empObj.employeeId from EmployeesVO as empObj where empObj.employeeId=:EmployeeId and empObj.isActive=:IsActive)");
            query.setString("Year", sdfYear.format(date));
            query.setInteger("EmployeeId", employeeId);
            query.setInteger("IsActive", 1);
            leaveQuotaList = query.list();
            return leaveQuotaList;

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getPayStubLeaveReqApprovalReports(ReportsVO report) {
        Session session = HibernateUtil.getSession();
        EmployeesVO newEmployeeId = new EmployeesVO();
        List empId = new LinkedList();
        List<EmployeeLeaveQuotaVO> leaveQuotaList;
        Integer employeeId = null;

        try {
            Date date = new Date();
            date = report.getDate();
            SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
            SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

            for (Iterator<EmployeesVO> it = report.getEmpIdObjList().iterator(); it.hasNext();) {
                newEmployeeId = it.next();
                empId.add(newEmployeeId.getEmployeeId());
                employeeId = newEmployeeId.getEmployeeId();
            }

            session.beginTransaction();
            Query query = session.createQuery("from LeaveReqsApprovalVO as leaReq where month(dateApplied)=:Month and year(dateApplied)=:Year and leaReq.isActive=:IsActive and leaReq.leaveReqStatus=:Status and "
                + "leaReq.empIdObj.employeeId in(select empObj.employeeId from EmployeesVO as empObj where empObj.employeeId=:EmployeeId and empObj.isActive=:IsActive)");
            query.setString("Month", sdfMonth.format(date));
            query.setString("Year", sdfYear.format(date));
            query.setString("Status", "Approved");
            query.setInteger("EmployeeId", employeeId);
            query.setInteger("IsActive", 1);
            leaveQuotaList = query.list();
            return leaveQuotaList;

        } finally {
            session.flush();
            session.close();
        }
    }
}