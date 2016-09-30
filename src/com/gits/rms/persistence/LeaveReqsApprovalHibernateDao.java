
package com.gits.rms.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LeaveApproverVO;
import com.gits.rms.vo.LeaveHistoryVO;
import com.gits.rms.vo.LeaveReqsApprovalVO;

public class LeaveReqsApprovalHibernateDao implements LeaveReqsApprovalDao {

    private List<LeaveReqsApprovalVO> lrappList;
    private EmployeeLeaveQuotaVO empLeaveQuota1 = new EmployeeLeaveQuotaVO();

    @Override
    public List getAllLeaveReqsApproval(LeaveReqsApprovalVO lrapp) {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from LeaveReqsApprovalVO as lh left join fetch lh.empIdObj as emp left join fetch lh.hcmoApproverId as a left join fetch lh.hcmoApproverId as a left join fetch lh.leaveTypeIdObj leave where lh.leaveReqStatus=:leaveReqStatus and lh.isActive =:IsActive "
                + " and lh.empIdObj=" + oEmp.getEmployeeId());
            query.setInteger("IsActive", 1);
            query.setString("leaveReqStatus", lrapp.getLeaveReqStatus());
            lrappList = query.list();
            return lrappList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllSubEmpLeaveReqsApproval(LeaveReqsApprovalVO lrapp) {
        List<EmployeesVO> empIdObj;
        EmployeesVO newEmployeeId = new EmployeesVO();
        EmployeesService employeeService = new EmployeesDaoService();
        List empId = new LinkedList();

        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");

        Session session = HibernateUtil.getSession();
        try {
            if (lrapp.getEmpIdObj().getEmployeeId() != null) {
                Query query = session.createQuery("from LeaveReqsApprovalVO as lh left join fetch lh.empIdObj as emp left join fetch lh.hcmoApproverId as a left join fetch lh.leaveTypeIdObj as leave where lh.leaveReqStatus=:leaveReqStatus and lh.isActive =:IsActive and lh.empIdObj=:empIdObj");
                query.setInteger("empIdObj", lrapp.getEmpIdObj().getEmployeeId());
                query.setString("leaveReqStatus", lrapp.getLeaveReqStatus());
                query.setInteger("IsActive", 1);
                lrappList = query.list();
                return lrappList;
            } else {
                empIdObj = employeeService.getCurrentSubEmployee();
                empIdObj.add(oEmp);
                for (Iterator<EmployeesVO> it = empIdObj.iterator(); it.hasNext();) {
                    newEmployeeId = it.next();
                    empId.add(newEmployeeId.getEmployeeId());
                }

                Criteria crit = session.createCriteria(LeaveReqsApprovalVO.class);
                crit.add(Restrictions.in("empIdObj.employeeId", empId));
                crit.add(Restrictions.eq("leaveReqStatus", lrapp.getLeaveReqStatus()));
                crit.add(Restrictions.eq("isActive", 1));
                crit.setFetchMode("leaveTypeIdObj", FetchMode.JOIN);
                return crit.list();
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllApprovedList(LeaveReqsApprovalVO lrapp) {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            Query query = session.createQuery("from LeaveReqsApprovalVO as lh left join fetch lh.empIdObj as emp left join fetch lh.hcmoApproverId as a left join fetch lh.leaveTypeIdObj leave where lh.leaveReqStatus=:leaveReqStatus and lh.isActive =:IsActive and lh.empIdObj=:empIdObj");
            query.setInteger("IsActive", 1);
            query.setString("leaveReqStatus", lrapp.getLeaveReqStatus());
            query.setInteger("empIdObj", oEmp.getEmployeeId());
            lrappList = query.list();
            return lrappList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllDisApprovedList(LeaveReqsApprovalVO lrapp) {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            Query query = session.createQuery("from LeaveReqsApprovalVO as lh left join fetch lh.empIdObj as emp left join fetch lh.hcmoApproverId as a left join fetch lh.hcmoApproverId as a left join fetch lh.leaveTypeIdObj leave where lh.leaveReqStatus=:leaveReqStatus and lh.isActive =:IsActive and lh.empIdObj=:empIdObj");
            query.setInteger("IsActive", 1);
            query.setString("leaveReqStatus", lrapp.getLeaveReqStatus());
            query.setInteger("empIdObj", oEmp.getEmployeeId());
            lrappList = query.list();
            return lrappList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllSubEmpApprovedList(LeaveReqsApprovalVO lrapp) {
        List<EmployeesVO> empIdObj;
        EmployeesVO newEmployeeId = new EmployeesVO();
        EmployeesService employeeService = new EmployeesDaoService();
        List empId = new LinkedList();

        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            if (lrapp.getEmpIdObj().getEmployeeId() != null) {
                Query query = session.createQuery("from LeaveReqsApprovalVO as lh left join fetch lh.empIdObj as emp left join fetch lh.hcmoApproverId as a left join fetch lh.leaveTypeIdObj as leave where lh.leaveReqStatus=:leaveReqStatus and lh.isActive =:IsActive and lh.empIdObj=:empIdObj");
                query.setInteger("empIdObj", lrapp.getEmpIdObj().getEmployeeId());
                query.setInteger("IsActive", 1);
                query.setString("leaveReqStatus", lrapp.getLeaveReqStatus());
                lrappList = query.list();
                return lrappList;
            } else {
                empIdObj = employeeService.getCurrentSubEmployee();
                empIdObj.add(oEmp);
                for (Iterator<EmployeesVO> it = empIdObj.iterator(); it.hasNext();) {
                    newEmployeeId = it.next();
                    empId.add(newEmployeeId.getEmployeeId());
                }

                Criteria crit = session.createCriteria(LeaveReqsApprovalVO.class);
                crit.add(Restrictions.in("empIdObj.employeeId", empId));
                crit.add(Restrictions.eq("leaveReqStatus", lrapp.getLeaveReqStatus()));
                crit.add(Restrictions.eq("isActive", 1));
                crit.setFetchMode("leaveTypeIdObj", FetchMode.JOIN);
                return crit.list();
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllSubEmpDisApprovedList(LeaveReqsApprovalVO lrapp) {

        List<EmployeesVO> empIdObj;
        EmployeesVO newEmployeeId = new EmployeesVO();
        EmployeesService employeeService = new EmployeesDaoService();
        List empId = new LinkedList();

        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            if (lrapp.getEmpIdObj().getEmployeeId() != null) {
                Query query = session.createQuery("from LeaveReqsApprovalVO as lh left join fetch lh.empIdObj as emp left join fetch lh.hcmoApproverId as a left join fetch lh.leaveTypeIdObj as leave where lh.leaveReqStatus=:leaveReqStatus and lh.isActive =:IsActive and lh.empIdObj=:empIdObj");
                query.setInteger("empIdObj", lrapp.getEmpIdObj().getEmployeeId());
                query.setInteger("IsActive", 1);
                query.setString("leaveReqStatus", lrapp.getLeaveReqStatus());
                lrappList = query.list();
                return lrappList;
            } else {
                empIdObj = employeeService.getCurrentSubEmployee();
                empIdObj.add(oEmp);
                for (Iterator<EmployeesVO> it = empIdObj.iterator(); it.hasNext();) {
                    newEmployeeId = it.next();
                    empId.add(newEmployeeId.getEmployeeId());
                }

                Criteria crit = session.createCriteria(LeaveReqsApprovalVO.class);
                crit.add(Restrictions.in("empIdObj.employeeId", empId));
                crit.add(Restrictions.eq("leaveReqStatus", lrapp.getLeaveReqStatus()));
                crit.add(Restrictions.eq("isActive", 1));
                crit.setFetchMode("leaveTypeIdObj", FetchMode.JOIN);
                return crit.list();
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getLeaveForApprovalEmployee(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from LeaveReqsApprovalVO as l where l.hcmoLeaveReqsApprovalId =:HcmoLeaveReqsApprovalId and l.isActive =:IsActive");
            q.setInteger("IsActive", 1);
            q.setInteger("HcmoLeaveReqsApprovalId", id);
            lrappList = q.list();
            return lrappList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void approved(LeaveReqsApprovalVO lrapp, EmployeeLeaveQuotaVO empLeaveQuota1, LeaveHistoryVO lhist) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Transaction tx1 = null;
        Transaction tx2 = null;
        try {
            tx = session.beginTransaction();
            session.save(lhist);
            tx.commit();
            try {
                Map msession = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
                tx1 = session.beginTransaction();
                String sHql = "update LeaveReqsApprovalVO as leave set leave.leaveReqStatus=:leaveReqStatus, "
                    + "leave.hcmoApproverId=:hcmoApproverId, "
                    + "leave.approveNotes=:approveNotes, "
                    + "leave.disApproveNotes=:disApproveNotes, "
                    + "leave.hcmoLeaveApproverId=:hcmoLeaveApproverId "
                    + "where leave.hcmoLeaveReqsApprovalId=:HcmoLeaveReqsApprovalId";
                Query query = session.createQuery(sHql);
                query.setString("leaveReqStatus", lrapp.getLeaveReqStatus());
                query.setInteger("hcmoApproverId", oEmp.getEmployeeId());
                query.setString("approveNotes", lrapp.getApproveNotes());
                query.setString("disApproveNotes", lrapp.getDisApproveNotes());
                query.setInteger("hcmoLeaveApproverId", oEmp.getEmployeeId());
                query.setInteger("HcmoLeaveReqsApprovalId", lrapp.getHcmoLeaveReqsApprovalId());
                query.executeUpdate();
                tx1.commit();
                try {
                    tx2 = session.beginTransaction();
                    String sHql1 = "update EmployeeLeaveQuotaVO as emp set emp.prvYearCarryingForward=:prvYearCarryingForward, emp.leaveCarryingForward=:leaveCarryingForward,emp.remainDays=:remainDays,emp.remainHours=:remainHours,emp.remainMinutes=:remainMinutes,emp.leaveTakenHours=:leaveTakenHours,emp.leaveTakenDays=:leaveTakenDays,emp.leaveTakenMinutes=:leaveTakenMinutes where emp.empLeaveQuotaId=:empLeaveQuotaId";
                    Query query1 = session.createQuery(sHql1);
                    query1.setBigDecimal("remainDays", empLeaveQuota1.getRemainDays());
                    query1.setBigDecimal("remainHours", empLeaveQuota1.getRemainHours());
                    query1.setBigDecimal("remainMinutes", empLeaveQuota1.getRemainMinutes());
                    query1.setBigDecimal("leaveTakenDays", empLeaveQuota1.getLeaveTakenDays());
                    query1.setBigDecimal("leaveTakenHours", empLeaveQuota1.getLeaveTakenHours());
                    query1.setBigDecimal("leaveTakenMinutes", empLeaveQuota1.getLeaveTakenMinutes());
                    query1.setBigDecimal("leaveCarryingForward", empLeaveQuota1.getLeaveCarryingForward());
                    query1.setBigDecimal("prvYearCarryingForward", empLeaveQuota1.getPrvYearCarryingForward());
                    query1.setInteger("empLeaveQuotaId", empLeaveQuota1.getEmpLeaveQuotaId());
                    query1.executeUpdate();
                    tx2.commit();
                } catch (RuntimeException e) {
                    if (tx2 != null) {
                        tx2.rollback();
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
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void disApproved(LeaveReqsApprovalVO lrapp, LeaveHistoryVO lhist) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Transaction tx1 = null;
        try {
            tx = session.beginTransaction();
            session.save(lhist);
            tx.commit();
            try {
                Map msession = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
                tx1 = session.beginTransaction();
                String sHql = "update LeaveReqsApprovalVO as leave set leave.leaveReqStatus=:leaveReqStatus, "
                    + "leave.hcmoApproverId=:hcmoApproverId, "
                    + "leave.approveNotes=:approveNotes, "
                    + "leave.disApproveNotes=:disApproveNotes, "
                    + "leave.hcmoLeaveApproverId=:hcmoLeaveApproverId "
                    + " where leave.hcmoLeaveReqsApprovalId=:hcmoLeaveReqsApprovalId";
                Query query = session.createQuery(sHql);
                query.setString("leaveReqStatus", lrapp.getLeaveReqStatus());
                query.setInteger("hcmoApproverId", oEmp.getEmployeeId());
                query.setString("approveNotes", lrapp.getApproveNotes());
                query.setString("disApproveNotes", lrapp.getDisApproveNotes());
                query.setInteger("hcmoLeaveApproverId", oEmp.getEmployeeId());
                query.setInteger("hcmoLeaveReqsApprovalId", lrapp.getHcmoLeaveReqsApprovalId());
                query.executeUpdate();
                tx1.commit();
            } catch (RuntimeException e) {
                if (tx1 != null) {
                    tx1.rollback();
                }
                e.printStackTrace();
                throw e;

            } finally {
                session.close();
            }
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List employeeLeaveApproved(LeaveReqsApprovalVO lrapp) {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            Query query = session.createQuery("from LeaveReqsApprovalVO as leave left join fetch leave.leaveTypeIdObj left join fetch leave.hcmoApproverId as a  where leave.isActive=:IsActive and leave.empIdObj in(select lea.hcmoEmployeeId  from LeaveApproverVO as lea where lea.hcmoApprovingEmpId ="
                + oEmp.getEmployeeId() + " and lea.isActive=:IsActive)");
            query.setInteger("IsActive", 1);
            lrappList = query.list();
            return lrappList;
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public void searchLeaveRequest(LeaveReqsApprovalVO lrapp) {
    }

    @Override
    public LeaveReqsApprovalVO getLeaveReqsApproval(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from LeaveReqsApprovalVO as l left join fetch l.empIdObj as e left join fetch l.hcmoApproverId as a left join fetch l.leaveTypeIdObj  where l.hcmoLeaveReqsApprovalId =:HcmoLeaveReqsApprovalId");
            q.setInteger("HcmoLeaveReqsApprovalId", id);
            return (LeaveReqsApprovalVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insert(LeaveReqsApprovalVO lrapp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {

            tx = session.beginTransaction();
            session.save(lrapp);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(LeaveReqsApprovalVO lrapp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update LeaveReqsApprovalVO set DateApplied=:DateApplied, "
                + "DateApprDisappr=:DateApprDisappr, " + "LeaveReqStatus=:LeaveReqStatus, "
                + "HcmoLeaveTypeId=:HcmoLeaveTypeId, " + "NoOfDays=:NoOfDays, "
                + "NoOfHours=:NoOfHours, " + "NoOfMins=:NoOfMins, " + "Comments=:Comments, "
                + "UpdatedBy=:UpdatedBy "
                + "where HcmoLeaveReqsApprovalId=:HcmoLeaveReqsApprovalId";
            Query query = session.createQuery(sHql);
            query.setDate("DateApplied", lrapp.getDateApplied());
            query.setInteger("HcmoLeaveTypeId", lrapp.getLeaveTypeIdObj().getLeaveTypeId());
            query.setBigDecimal("NoOfDays", lrapp.getNoOfDays());
            query.setBigDecimal("NoOfHours", lrapp.getHours());
            query.setBigDecimal("NoOfMins", lrapp.getMins());
            query.setDate("DateApprDisappr", lrapp.getDateApprDisappr());
            query.setString("LeaveReqStatus", lrapp.getLeaveReqStatus());
            query.setString("Comments", lrapp.getComments());
            query.setInteger("UpdatedBy", lrapp.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoLeaveReqsApprovalId", lrapp.getHcmoLeaveReqsApprovalId());
            query.executeUpdate();
            tx.commit();
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
    public void approve(LeaveReqsApprovalVO lrapp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update LeaveReqsApprovalVO set DateApplied=:DateApplied, "
                + "DateApprDisappr=:DateApprDisappr, " + "LeaveReqStatus=:LeaveReqStatus, "
                + "HcmoLeaveTypeId=:HcmoLeaveTypeId, " + "NoOfDays=:NoOfDays, "
                + "DateApprDisappr=:DateApprDisappr, " + "Comments=:Comments, "
                + "UpdatedBy=:UpdatedBy "
                + "where HcmoLeaveReqsApprovalId=:HcmoLeaveReqsApprovalId";
            Query query = session.createQuery(sHql);
            query.setDate("DateApplied", lrapp.getDateApplied());
            query.setInteger("HcmoLeaveTypeId", lrapp.getLeaveTypeIdObj().getLeaveTypeId());
            query.setString("LeaveReqStatus", lrapp.getLeaveReqStatus());
            query.setBigDecimal("NoOfDays", lrapp.getNoOfDays());
            query.setDate("DateApprDisappr", lrapp.getDateApprDisappr());
            query.setString("Comments", lrapp.getComments());
            query.setInteger("UpdatedBy", lrapp.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoLeaveReqsApprovalId", lrapp.getHcmoLeaveReqsApprovalId());
            query.executeUpdate();
            tx.commit();
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
    public void delete(Integer id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update LeaveReqsApprovalVO set IsActive=:IsActive where hcmoLeaveReqsApprovalId=:hcmoLeaveReqsApprovalId";
            Query query = session.createQuery(sHql);
            query.setInteger("IsActive", 0);
            query.setInteger("hcmoLeaveReqsApprovalId", id);
            query.executeUpdate();
            tx.commit();
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
    public LeaveApproverVO getApproverId(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LeaveApproverVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("hcmoEmployeeId.employeeId", id));
            crit.add(Restrictions.eq("hcmoApprovingEmpId.employeeId", id));
            return (LeaveApproverVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllForApprovalList(LeaveReqsApprovalVO lrapp) {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from LeaveReqsApprovalVO as lh where lh.leaveReqStatus=:leaveReqStatus and lh.isActive =:IsActive and lh.empIdObj in(select ea.hcmoEmployeeId from LeaveApproverVO as ea where ea.isActive=:IsActive and ea.hcmoApprovingEmpId =:hcmoApprovingEmpId)");
            query.setInteger("IsActive", 1);
            query.setString("leaveReqStatus", lrapp.getLeaveReqStatus());
            query.setInteger("hcmoApprovingEmpId", oEmp.getEmployeeId());
            lrappList = query.list();

            Set<String> setEmployeeName = new LinkedHashSet<String>();
            Set<LeaveReqsApprovalVO> setVO = new LinkedHashSet<LeaveReqsApprovalVO>();

            for (LeaveReqsApprovalVO b : lrappList) {
                if (setEmployeeName.add(b.getEmpIdObj().getEmpFirstName())) {
                    setVO.add(b);
                }
            }
            lrappList.clear();
            lrappList.addAll(setVO);

            return lrappList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getDashLeaveForApproval() {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from LeaveHistoryVO as lh where lh.leaveStatus=:leaveStatus and lh.empIdObj=:hcmoEmployeeId and lh.isActive =:IsActive)");
            query.setString("leaveStatus", "For Approval");
            query.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            query.setInteger("IsActive", 1);
            lrappList = query.list();
            return lrappList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getDashLeaveForApprovalToday() {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        Date todayDate = new Date();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from LeaveHistoryVO as lh where lh.leaveStatus=:leaveStatus and lh.empIdObj=:hcmoEmployeeId and lh.created=:Created and lh.isActive =:IsActive)");
            query.setString("leaveStatus", "For Approval");
            query.setDate("Created", todayDate);
            query.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            query.setInteger("IsActive", 1);
            lrappList = query.list();
            return lrappList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getDashLeaveForApprovalThreeDays() {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -3);
        Date threeDays = cal.getTime();
        Date todayDate = new Date();
        Integer year = todayDate.getYear() + 1900;
        Integer month = todayDate.getMonth() + 1;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from LeaveHistoryVO as lh where lh.leaveStatus=:leaveStatus and lh.empIdObj=:hcmoEmployeeId and year(lh.created)="
                + String.valueOf(year)
                + " and day(lh.created)>="
                + String.valueOf(threeDays.getDate())
                + " and day(lh.created)<="
                + String.valueOf(todayDate.getDate())
                + "and month(lh.created)="
                + String.valueOf(month) + "and lh.isActive=:IsActive");
            query.setInteger("IsActive", 1);
            query.setString("leaveStatus", "For Approval");
            query.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            lrappList = query.list();
            return lrappList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getDashLeaveForApprovalOneWeek() {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        Date lastweek = cal.getTime();
        Date todayDate = new Date();
        Integer year = todayDate.getYear() + 1900;
        Integer month = todayDate.getMonth() + 1;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from LeaveHistoryVO as lh where lh.leaveStatus=:leaveStatus and lh.empIdObj=:hcmoEmployeeId and year(lh.created)="
                + String.valueOf(year)
                + " and day(lh.created)>="
                + String.valueOf(lastweek.getDate())
                + " and day(lh.created)<="
                + String.valueOf(todayDate.getDate())
                + "and month(lh.created)="
                + String.valueOf(month) + "and lh.isActive=:IsActive");

            query.setInteger("IsActive", 1);
            query.setString("leaveStatus", "For Approval");
            query.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            lrappList = query.list();
            return lrappList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getLeaveReqsList(EmployeeLeaveQuotaVO empLeave) {
        Calendar calendar = new GregorianCalendar();
        int currentYear = calendar.get(Calendar.YEAR);
        Map msession = ActionContext.getContext().getSession();
        msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from LeaveReqsApprovalVO as lh left join fetch lh.empIdObj as emp left join fetch lh.hcmoApproverId as a where lh.leaveTypeIdObj=:leaveTypeIdObj and year(lh.dateApplied)="
                + String.valueOf(currentYear)
                + " and lh.isActive =:IsActive and lh.empIdObj=:empIdObj");
            query.setInteger("empIdObj", empLeave.getEmpIdObj().getEmployeeId());
            query.setInteger("leaveTypeIdObj", empLeave.getLeaveTypeIdObj().getLeaveTypeId());
            query.setInteger("IsActive", 1);
            lrappList = query.list();
            return lrappList;
        } finally {
            session.flush();
            session.close();
        }
    }

    // Assigned
    @Override
    public void assigned(EmployeeLeaveQuotaVO empLeaveQuota, LeaveHistoryVO lhist) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Transaction tx1 = null;
        Transaction tx2 = null;
        try {
            tx = session.beginTransaction();
            session.save(lhist);
            tx.commit();
            try {
                Map msession = ActionContext.getContext().getSession();
                msession.get("EMPLOYEE_OBJECT");
                tx2 = session.beginTransaction();
                String sHql1 = "update EmployeeLeaveQuotaVO as emp set emp.prvYearCarryingForward=:prvYearCarryingForward, emp.leaveCarryingForward=:leaveCarryingForward,emp.remainDays=:remainDays,emp.remainHours=:remainHours,emp.remainMinutes=:remainMinutes,emp.leaveTakenHours=:leaveTakenHours,emp.leaveTakenDays=:leaveTakenDays,emp.leaveTakenMinutes=:leaveTakenMinutes where emp.empLeaveQuotaId=:empLeaveQuotaId";
                Query query1 = session.createQuery(sHql1);
                query1.setBigDecimal("remainDays", empLeaveQuota.getRemainDays());
                query1.setBigDecimal("remainHours", empLeaveQuota.getRemainHours());
                query1.setBigDecimal("remainMinutes", empLeaveQuota.getRemainMinutes());
                query1.setBigDecimal("leaveTakenDays", empLeaveQuota.getLeaveTakenDays());
                query1.setBigDecimal("leaveTakenHours", empLeaveQuota.getLeaveTakenHours());
                query1.setBigDecimal("leaveTakenMinutes", empLeaveQuota.getLeaveTakenMinutes());
                query1.setBigDecimal("leaveCarryingForward", empLeaveQuota.getLeaveCarryingForward());
                query1.setBigDecimal("prvYearCarryingForward", empLeaveQuota.getPrvYearCarryingForward());
                query1.setInteger("empLeaveQuotaId", empLeaveQuota.getEmpLeaveQuotaId());
                query1.executeUpdate();
                tx2.commit();
            } catch (RuntimeException e) {
                if (tx2 != null) {
                    tx2.rollback();
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
    public List getAllEmpAssignedList(LeaveReqsApprovalVO lrapp) {
        new EmployeesVO();
        new EmployeesDaoService();
        new LinkedList();

        Map msession = ActionContext.getContext().getSession();
        msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LeaveReqsApprovalVO.class);
            if (lrapp.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", lrapp.getEmpIdObj().getEmployeeId()));
            }

            if (!(lrapp.getLeaveReqStatus().isEmpty())) {
                crit.add(Restrictions.eq("leaveReqStatus", lrapp.getLeaveReqStatus()));
            }
            crit.add(Restrictions.eq("isActive", 1));
            crit.setFetchMode("leaveTypeIdObj", FetchMode.JOIN);
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    // Cancel Leave
    @Override
    public void cancel(LeaveReqsApprovalVO lrapp, LeaveHistoryVO lhist) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Transaction tx2 = null;
        try {
            tx = session.beginTransaction();
            session.save(lhist);
            tx.commit();
            try {
                tx2 = session.beginTransaction();
                String sHql = "update LeaveReqsApprovalVO set approveNotes=:ApproveNotes,"
                    + "disApproveNotes=:DisApproveNotes, " + "LeaveReqStatus=:LeaveReqStatus "
                    + "where hcmoLeaveReqsApprovalId=:HcmoLeaveReqsApprovalId";
                Query query = session.createQuery(sHql);
                query.setString("LeaveReqStatus", lrapp.getLeaveReqStatus());
                query.setString("ApproveNotes", lrapp.getApproveNotes());
                query.setString("DisApproveNotes", lrapp.getDisApproveNotes());
                query.setInteger("HcmoLeaveReqsApprovalId", lrapp.getHcmoLeaveReqsApprovalId());
                query.executeUpdate();
                tx2.commit();
            } finally {
                session.flush();
                session.close();
            }
        } catch (RuntimeException e) {
            if (tx2 != null) {
                tx2.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List getAllEmpCancelList(LeaveReqsApprovalVO lrapp) {
        new EmployeesVO();
        new EmployeesDaoService();
        new LinkedList();

        Map msession = ActionContext.getContext().getSession();
        msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LeaveReqsApprovalVO.class);
            if (lrapp.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", lrapp.getEmpIdObj().getEmployeeId()));
            }
            if (!(lrapp.getLeaveReqStatus().isEmpty())) {
                crit.add(Restrictions.eq("leaveReqStatus", lrapp.getLeaveReqStatus()));
            }
            crit.add(Restrictions.eq("isActive", 1));
            crit.setFetchMode("leaveTypeIdObj", FetchMode.JOIN);
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
}
