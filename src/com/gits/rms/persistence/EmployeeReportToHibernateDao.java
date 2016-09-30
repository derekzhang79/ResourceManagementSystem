
package com.gits.rms.persistence;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.vo.EducationVO;
import com.gits.rms.vo.EmployeeReportToVO;
import com.gits.rms.vo.EmployeesVO;

public class EmployeeReportToHibernateDao implements EmployeeReportToDao {

    @Override
    public void deleteEmployeeReportTo(EmployeeReportToVO emp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmployeeReportToVO set updatedBy=:UpdatedBy,isActive=:IsActive where empReportToId=:HcmoEmpReportToId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", emp.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoEmpReportToId", emp.getEmpReportToId());
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
    public List empReportToSearchResult(EmployeeReportToVO emp) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Integer repMode = (int) emp.getEmpRepReportingMode();

            Criteria crit = session.createCriteria(EmployeeReportToVO.class);
            if (emp.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", emp.getEmpIdObj().getEmployeeId()));
            }
            if (emp.getSupEmpNumber().getEmployeeId() != null) {
                crit.add(Restrictions.eq("supEmpNumber.employeeId", emp.getSupEmpNumber().getEmployeeId()));
            }
            if (repMode == 0) {
                crit.add(Restrictions.eq("empRepReportingMode", 0));
            } else if (repMode == 1) {
                crit.add(Restrictions.eq("empRepReportingMode", 1));
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
    public List getAllEmployeeReportTo(Integer clientId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeeReportToVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("clientId", clientId));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List getAllSubEmployeeReportToList(List<Integer> empReportToEmpId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeeReportToVO.class);
            crit.add(Restrictions.in("empIdObj.employeeId", empReportToEmpId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
        	session.flush();
        	session.close();
        }
    }

    @Override
    public List getEmployeeAllPreSupervisor(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeeReportToVO.class);
            crit.add(Restrictions.eq("empIdObj.employeeId", id));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public EmployeeReportToVO getEmployeeReportTo(Integer id) {

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmployeeReportToVO as e left join fetch e.empIdObj where e.empReportToId=:EmpReportToId");
            q.setInteger("EmpReportToId", id);
            return (EmployeeReportToVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getempRToInvert(EmployeeReportToVO emp) {

        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeeReportToVO.class);

            crit.add(Restrictions.eq("empIdObj.employeeId", emp.getSupEmpNumber().getEmployeeId()));
            crit.add(Restrictions.eq("supEmpNumber.employeeId", emp.getEmpIdObj().getEmployeeId()));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertEmployeeReportTo(EmployeeReportToVO emp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(emp);
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
    public void updateEmployeeReportTo(EmployeeReportToVO emp) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmployeeReportToVO set HcmoEmployeeId=:HcmoEmployeeId, "
                + "ERepSupEmpNumber=:ERepSupEmpNumber, " + "ERepReportingMode=:ERepReportingMode, "
                + "UpdatedBy=:UpdatedBy " + "where empReportToId=:HcmoEmpReportToId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoEmployeeId", emp.getEmpIdObj().getEmployeeId());
            query.setInteger("ERepSupEmpNumber", emp.getSupEmpNumber().getEmployeeId());
            query.setInteger("ERepReportingMode", emp.getEmpRepReportingMode());
            query.setInteger("UpdatedBy", emp.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoEmpReportToId", emp.getEmpReportToId());
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
    public boolean checkLoginEmployeeIsReportToEmp(int empId){
    	Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeeReportToVO.class);
            crit.add(Restrictions.eq("supEmpNumber.employeeId", empId));
            crit.add(Restrictions.eq("isActive", 1));
            
            if(crit.list().isEmpty()){
            	return false;
            }else{
            	return true;
            }
            
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List<Integer> getSubEmployeeList(int empId){
        Session session = HibernateUtil.getSession();
        List<Integer> empReportToEmpId = new LinkedList<Integer>();
        try {
            Criteria reportToList = session.createCriteria(EmployeeReportToVO.class);
            reportToList.add(Restrictions.eq("supEmpNumber.employeeId", empId));
            reportToList.add(Restrictions.eq("isActive", 1));
            
            for (Iterator<EmployeeReportToVO> it = reportToList.list().iterator(); it.hasNext();) {
              EmployeeReportToVO empReportId=it.next();
              empReportToEmpId.add(empReportId.getEmpIdObj().getEmployeeId());
            }

            return empReportToEmpId;
        } finally {
        	session.flush();
        	session.close();
        }
    }

}
