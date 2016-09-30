
package com.gits.rms.persistence;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.vo.EducationVO;
import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LeaveApproverVO;
import com.gits.rms.vo.LeaveReqsApprovalVO;

public class EmployeeLeaveQuotaHibernateDao implements EmployeeLeaveQuotaDao {
    private List<EmployeeLeaveQuotaVO> empLeaveList;
    private EmployeeLeaveQuotaVO empLeaveQuota;

    @Override
    public List getAllEmployeeApprover(List sList) {
        Session session = HibernateUtil.getSession();
        EmployeesVO newEmployeeId = new EmployeesVO();
        try {
            Criteria crit = session.createCriteria(EmployeeLeaveQuotaVO.class);
            List list = new LinkedList();
            List empId = new LinkedList();
            for (Iterator<EmployeesVO> it = sList.iterator(); it.hasNext();) {
                newEmployeeId = it.next();
                empId.add(newEmployeeId.getEmployeeId());
            }
            crit.add(Restrictions.in("empIdObj.employeeId", empId));
            crit.add(Restrictions.eq("isActive", 1));
            list = crit.list();
            return list;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllEmployeeLeaveQuota() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeeLeaveQuotaVO.class);
            crit.addOrder(Order.asc("year"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List getAllSubEmployeeLeaveQuotaList(List<Integer> empReportToEmpId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeeLeaveQuotaVO.class);
            crit.add(Restrictions.in("empIdObj.employeeId", empReportToEmpId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
        	session.flush();
        	session.close();
        }
    }

    @Override
    public List getAllEmployeeLeaveQuotaYear() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmployeeLeaveQuotaVO as b where b.isActive=:IsActive");
            query.setInteger("IsActive", 1);
            empLeaveList = query.list();

            Set<Integer> setYears = new LinkedHashSet<Integer>();
            Set<EmployeeLeaveQuotaVO> setVO = new LinkedHashSet<EmployeeLeaveQuotaVO>();

            for (EmployeeLeaveQuotaVO b : empLeaveList) {
                if (setYears.add(b.getYear())) {
                    setVO.add(b);
                }
            }
            empLeaveList.clear();
            empLeaveList.addAll(setVO);
            return empLeaveList;
        } finally {
            session.flush();
            session.close();
        }
    }

    public List getAllValidEmpLeaveQuota(int iYear) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmployeeLeaveQuotaVO as leave left join fetch leave.leaveTypeIdObj where leave.isActive=:IsActive and leave.year=:iYear");
            query.setInteger("IsActive", 1);
            query.setInteger("iYear", iYear);
            empLeaveList = query.list();
            return empLeaveList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getCurrentSubEmployeeForLeaveQuota() {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmployeesVO as emp where emp.isActive=:IsActive and emp.employeeId in(select t.hcmoEmployeeId from LeaveApproverVO as t where t.isActive=:IsActive and t.hcmoApprovingEmpId =:hcmoApprovingEmpId)");
            query.setInteger("hcmoApprovingEmpId", oEmp.getEmployeeId());
            query.setInteger("IsActive", 1);
            return query.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EmployeeLeaveQuotaVO getEmployeeLeaveQuota(Integer id) {
        Map msession = ActionContext.getContext().getSession();
        msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmployeeLeaveQuotaVO as e left join fetch e.empIdObj left join fetch e.leaveTypeIdObj where e.empLeaveQuotaId =:empLeaveQuotaId");
            q.setInteger("empLeaveQuotaId", id);
            return (EmployeeLeaveQuotaVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getEmployeeLeaveQuotaCheckInEmpLeave(EmployeeLeaveQuotaVO empLeave) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmployeeLeaveQuotaVO as leave left join fetch leave.leaveTypeIdObj where leave.year=:Year and leave.leaveTypeIdObj.leaveTypeId=:leaveTypeId and leave.empIdObj.employeeId=:employeeId and leave.isActive=:IsActive");
            query.setInteger("leaveTypeId", empLeave.getLeaveTypeIdObj().getLeaveTypeId());
            query.setInteger("employeeId", empLeave.getEmpIdObj().getEmployeeId());
            query.setInteger("Year", empLeave.getYear());
            query.setInteger("IsActive", 1);
            empLeaveList = query.list();
            return empLeaveList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EmployeeLeaveQuotaVO getEmployeeLeaveQuotaList(LeaveReqsApprovalVO lrapp) {
        Map msession = ActionContext.getContext().getSession();
        msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmployeeLeaveQuotaVO as e left join fetch e.empIdObj left join fetch e.leaveTypeIdObj where e.empIdObj.employeeId =:hcmoEmployeeId and e.leaveTypeIdObj.leaveTypeId=:hcmoLeaveTypeId and e.year=:DateApplied ");
            q.setInteger("hcmoEmployeeId", lrapp.getEmpIdObj().getEmployeeId());
            q.setInteger("hcmoLeaveTypeId", lrapp.getLeaveTypeIdObj().getLeaveTypeId());
            q.setInteger("DateApplied", 1900 + lrapp.getDateApplied().getYear());

            return (EmployeeLeaveQuotaVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getEmployeeLeaveQuotaList(LeaveReqsApprovalVO lrapp, EmployeesVO emp, Integer year) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmployeeLeaveQuotaVO as leave left join fetch leave.leaveTypeIdObj where leave.year=:Year and leave.leaveTypeIdObj.leaveTypeId=:leaveTypeId and leave.empIdObj.employeeId=:employeeId and leave.isActive=:IsActive");
            query.setInteger("IsActive", 1);
            query.setInteger("leaveTypeId", lrapp.getLeaveTypeIdObj().getLeaveTypeId());
            query.setInteger("employeeId", emp.getEmployeeId());
            query.setInteger("Year", year);
            empLeaveList = query.list();
            return empLeaveList;
        } finally {
            session.flush();
            session.close();
        }
    }

    // Already any data present for same employee,leave type and same year
    @Override
    public EmployeeLeaveQuotaVO getEmpPrevYearLeaveQuota(EmployeeLeaveQuotaVO leave, Integer year) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeeLeaveQuotaVO.class);
            crit.add(Restrictions.eq("year", year));
            crit.add(Restrictions.eq("empIdObj.employeeId", leave.getEmpIdObj().getEmployeeId()));
            crit.add(Restrictions.eq("leaveTypeIdObj.leaveTypeId", leave.getLeaveTypeIdObj().getLeaveTypeId()));
            crit.add(Restrictions.eq("isActive", 1));
            return (EmployeeLeaveQuotaVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EmployeeLeaveQuotaVO getPreviousLCFwd(EmployeeLeaveQuotaVO empLeaveQuota) {
        Map msession = ActionContext.getContext().getSession();
        msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        Calendar cal = new GregorianCalendar();
        int currentYear = cal.get(Calendar.YEAR);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from EmployeeLeaveQuotaVO as elq where elq.year=:year and elq.empIdObj.employeeId =:empIdObj and elq.leaveTypeIdObj.leaveTypeId=:LeaveType and elq.isActive=:IsActive");
            query.setInteger("LeaveType", empLeaveQuota.getLeaveTypeIdObj().getLeaveTypeId());
            query.setInteger("IsActive", 1);
            query.setInteger("year", currentYear);
            query.setInteger("empIdObj", empLeaveQuota.getEmpIdObj().getEmployeeId());
            query.list();
            return (EmployeeLeaveQuotaVO) query.uniqueResult();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EmployeeLeaveQuotaVO getPreviousLeaveCarryForward(BigDecimal x) {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Calendar cal = new GregorianCalendar();
        int currentYear = cal.get(Calendar.YEAR);
        if (empLeaveQuota.getYear() == currentYear) {
            empLeaveQuota.setPrvYearCarryingForward(new BigDecimal(0));
        } else {
            Session session = HibernateUtil.getSession();
            try {
                session.beginTransaction();
                Query q = session.createQuery("select leaveCarryingForward from EmployeeLeaveQuotaVO elq where elq.year=:year,elq.leaveTypeIdObj=:leaveTypeIdObj,elq.empLeaveQuotaId =:empLeaveQuotaId and elq.empIdObj not in ("
                    + oEmp.getEmployeeId() + ")");
                q.setBigDecimal("empLeaveQuotaId", x);
                empLeaveQuota.setPrvYearCarryingForward((BigDecimal) q);
                return (EmployeeLeaveQuotaVO) q.uniqueResult();
            } finally {
                session.flush();
                session.close();
            }
        }
        return empLeaveQuota;
    }

    @Override
    public List getSingleEmployeeLeaveQuotaList(Integer employeeId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmployeeLeaveQuotaVO as leave left join fetch leave.leaveTypeIdObj where leave.isActive=:IsActive and leave.empIdObj.employeeId=:EmployeeId");
            query.setInteger("IsActive", 1);
            query.setInteger("EmployeeId", employeeId);
            empLeaveList = query.list();
            return empLeaveList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getSubEmployeeLeaveQuota(Integer empId) {
        List leaveAppList;
        LeaveApproverVO employeeId = new LeaveApproverVO();
        List leaveEmpIdList = new LinkedList();
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LeaveApproverVO.class);
            crit.add(Restrictions.eq("hcmoApprovingEmpId.employeeId", empId));
            crit.add(Restrictions.eq("isActive", 1));
            leaveAppList = crit.list();

            Criteria critLeaveQuota = session.createCriteria(EmployeeLeaveQuotaVO.class);
            for (Iterator<LeaveApproverVO> it = leaveAppList.iterator(); it.hasNext();) {
                employeeId = it.next();
                leaveEmpIdList.add(employeeId.getHcmoEmployeeId().getEmployeeId());
            }
            if (!(leaveAppList.isEmpty())) {
                critLeaveQuota.add(Restrictions.in("empIdObj.employeeId", leaveEmpIdList));
                critLeaveQuota.add(Restrictions.eq("isActive", 1));
            } else {
                critLeaveQuota.add(Restrictions.eq("empIdObj.employeeId", 0));
                critLeaveQuota.add(Restrictions.eq("isActive", 1));
            }

            return critLeaveQuota.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertEmployeeLeaveQuota(EmployeeLeaveQuotaVO empLeaveQuota) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(empLeaveQuota);
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
    public List leaveQuotaSearchResult(EmployeeLeaveQuotaVO leave) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeeLeaveQuotaVO.class);
            if (leave.getLeaveTypeIdObj().getLeaveTypeId() != null) {
                crit.add(Restrictions.eq("leaveTypeIdObj.leaveTypeId", leave.getLeaveTypeIdObj().getLeaveTypeId()));
            }
            if (leave.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", leave.getEmpIdObj().getEmployeeId()));
            }
            if (leave.getYear() != null) {
                crit.add(Restrictions.eq("year", leave.getYear()));
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
    public List leaveQuotaSearchResultForAdminLogin(EmployeeLeaveQuotaVO leave) {
        Session session = HibernateUtil.getSession();
        ActionContext.getContext().getSession();
        try {
            Criteria crit = session.createCriteria(EmployeeLeaveQuotaVO.class);
            if (leave.getLeaveTypeIdObj().getLeaveTypeId() != null) {
                crit.add(Restrictions.eq("leaveTypeIdObj.leaveTypeId", leave.getLeaveTypeIdObj().getLeaveTypeId()));
            }
            if (leave.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", leave.getEmpIdObj().getEmployeeId()));
            }
            if (leave.getYear() != null) {
                crit.add(Restrictions.eq("year", leave.getYear()));
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
    public void updateEmployeeLeaveQuota(EmployeeLeaveQuotaVO empLeaveQuota) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        ActionContext.getContext().getSession();

        try {
            tx = session.beginTransaction();

            String sHql = "update EmployeeLeaveQuotaVO set Year=:Year, "
                + "NoOfDaysAlloted=:NoOfDaysAlloted, " + "Hours=:Hours, " + "Minutes=:Minutes, "
                + "RemainingDays=:RemainingDays, " + "RemainingHours=:RemainingHours, "
                + "RemainingMinutes=:RemainingMinutes, " + "HcmoLeaveTypeId=:HcmoLeaveTypeId, "
                + "LeaveTakenDays=:LeaveTakenDays, " + "LeaveTakenHours=:LeaveTakenHours, "
                + "LeaveTakenMinutes=:LeaveTakenMinutes, " + "PreCarryFwdDays=:PreCarryFwdDays, "
                + "PreCarryFwdHours=:PreCarryFwdHours, "
                + "PreCarryFwdMinutes=:PreCarryFwdMinutes, " + "HcmoEmployeeId=:HcmoEmployeeId, "
                + "UpdatedBy=:UpdatedBy " + " where HcmoEmpLeaveQuotaId=:HcmoEmpLeaveQuotaId";
            Query query = session.createQuery(sHql);
            query.setInteger("Year", empLeaveQuota.getYear());
            query.setBigDecimal("NoOfDaysAlloted", empLeaveQuota.getNoOfDays());
            query.setBigDecimal("Hours", empLeaveQuota.getHours());
            query.setBigDecimal("Minutes", empLeaveQuota.getMinutes());
            query.setInteger("HcmoLeaveTypeId", empLeaveQuota.getLeaveTypeIdObj().getLeaveTypeId());
            query.setInteger("HcmoEmployeeId", empLeaveQuota.getEmpIdObj().getEmployeeId());
            query.setBigDecimal("LeaveTakenDays", empLeaveQuota.getLeaveTakenDays());
            query.setBigDecimal("LeaveTakenHours", empLeaveQuota.getLeaveTakenHours());
            query.setBigDecimal("LeaveTakenMinutes", empLeaveQuota.getLeaveTakenMinutes());
            query.setBigDecimal("RemainingDays", empLeaveQuota.getRemainDays());
            query.setBigDecimal("RemainingHours", empLeaveQuota.getRemainHours());
            query.setBigDecimal("RemainingMinutes", empLeaveQuota.getRemainMinutes());
            query.setBigDecimal("PreCarryFwdDays", empLeaveQuota.getPreviousCarryFwdDays());
            query.setBigDecimal("PreCarryFwdHours", empLeaveQuota.getPreviousCarryFwdHours());
            query.setBigDecimal("PreCarryFwdMinutes", empLeaveQuota.getPreviousCarryFwdMinutes());
            query.setInteger("UpdatedBy", empLeaveQuota.getEmpIdObj().getEmployeeId());
            query.setInteger("HcmoEmpLeaveQuotaId", empLeaveQuota.getEmpLeaveQuotaId());
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

    public void updateEmployeeLeaveQuotaLeaveTaken(EmployeeLeaveQuotaVO empLeaveQuota) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Map mSession = ActionContext.getContext().getSession();
        Integer.parseInt(mSession.get("carryForward").toString());
        try {
            tx = session.beginTransaction();
            String sHql = "update EmployeeLeaveQuotaVO set LeaveTaken=:LeaveTaken "
                + "UpdatedBy=:UpdatedBy " + " where HcmoEmpLeaveQuotaId=:HcmoEmpLeaveQuotaId";
            Query query = session.createQuery(sHql);
            query.setBigDecimal("LeaveTaken", empLeaveQuota.getLeaveTaken());
            query.setInteger("UpdatedBy", empLeaveQuota.getEmpIdObj().getEmployeeId());
            query.setInteger("HcmoEmpLeaveQuotaId", empLeaveQuota.getEmpLeaveQuotaId());
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
