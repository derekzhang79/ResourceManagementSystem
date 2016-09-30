
package com.gits.rms.persistence;

import java.util.ArrayList;
import java.util.HashSet;
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
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.vo.CustomerVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LeaveHistoryVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.ReportsVO;

public class LeaveHistoryHibernateDao implements LeaveHistoryDao {

    private List<LeaveHistoryVO> lhistList;
    private List<EmployeesVO> leaveSubEmpIdList;
    private List<EmployeesVO> employeeList;
    private EmployeesService employeeService = new EmployeesDaoService();

    @Override
    public List getAllLeaveHistory() {
        Map msession = ActionContext.getContext().getSession();
        Session session = HibernateUtil.getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            session.beginTransaction();
            Query query = session.createQuery("from LeaveHistoryVO as lev left join fetch lev.empIdObj as emp left join fetch lev.leaveTypeIdObj where lev.isActive=:IsActive and lev.empIdObj=:empIdObj");
            query.setInteger("IsActive", 1);
            query.setInteger("empIdObj", oEmp.getEmployeeId());
            lhistList = query.list();
            return lhistList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public LeaveHistoryVO getLeaveHistory(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from LeaveHistoryVO as lh left join fetch lh.empIdObj where lh.hcmoLeaveHistoryId =:hcmoLeaveHistoryId");
            q.setInteger("hcmoLeaveHistoryId", id);
            return (LeaveHistoryVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getLeaveHistorySearch(LeaveHistoryVO lhist) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            Criteria crit = session.createCriteria(LeaveHistoryVO.class);
            if (lhist.getLeaveTypeIdObj().getLeaveTypeId() != null) {
                crit.add(Restrictions.eq("leaveTypeIdObj.leaveTypeId", lhist.getLeaveTypeIdObj().getLeaveTypeId()));
            }
            if (lhist.getLeaveStartDate() != null) {
                crit.add(Restrictions.ge("leaveDate", lhist.getLeaveStartDate()));
            }
            if (lhist.getLeaveEndDate() != null) {
                crit.add(Restrictions.le("leaveDate", lhist.getLeaveEndDate()));
            }
            if (!(lhist.getLeaveStatus().trim().isEmpty())) {
                crit.add(Restrictions.eq("leaveStatus", lhist.getLeaveStatus()));
            }
            crit.add(Restrictions.eq("empIdObj.employeeId", oEmp.getEmployeeId()));
            crit.add(Restrictions.eq("isActive", 1));

            List list = crit.list();
            return list;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getLeaveHistorySubEmployee() {
        Map msession = ActionContext.getContext().getSession();
        Session session = HibernateUtil.getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            session.beginTransaction();
            Query query = session.createQuery("from LeaveHistoryVO as leave left join fetch leave.leaveTypeIdObj where leave.empIdObj in(select ea.hcmoEmployeeId from LeaveApproverVO as ea where ea.isActive=:IsActive and ea.hcmoApprovingEmpId =:hcmoApprovingEmpId)");
            query.setInteger("IsActive", 1);
            query.setInteger("hcmoApprovingEmpId", oEmp.getEmployeeId());
            lhistList = query.list();
            return lhistList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getSubEmpLeaveHistorySearch(LeaveHistoryVO lhist) {
        Map msession = ActionContext.getContext().getSession();
        List<EmployeesVO> empIdObj;
        List employeeIdList = new LinkedList();
        EmployeesVO newEmployeeId = new EmployeesVO();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");

        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LeaveHistoryVO.class);
            if (lhist.getLeaveTypeIdObj().getLeaveTypeId() != null) {
                crit.add(Restrictions.eq("leaveTypeIdObj.leaveTypeId", lhist.getLeaveTypeIdObj().getLeaveTypeId()));
            }
            if (lhist.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", lhist.getEmpIdObj().getEmployeeId()));
            } else {
                empIdObj = employeeService.getCurrentSubEmployee();
                empIdObj.add(oEmp);
                if (!(empIdObj.isEmpty())) {
                    for (Iterator<EmployeesVO> it = empIdObj.iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        employeeIdList.add(newEmployeeId.getEmployeeId());
                    }
                }
                if (!(employeeIdList.isEmpty())) {
                    crit.add(Restrictions.in("empIdObj.employeeId", employeeIdList));
                }
            }
            if (lhist.getLeaveStartDate() != null) {
                crit.add(Restrictions.ge("leaveDate", lhist.getLeaveStartDate()));
            }
            if (lhist.getLeaveEndDate() != null) {
                crit.add(Restrictions.le("leaveDate", lhist.getLeaveEndDate()));
            }
            if (!(lhist.getLeaveStatus().trim().isEmpty())) {
                crit.add(Restrictions.eq("leaveStatus", lhist.getLeaveStatus()));
            }
            crit.add(Restrictions.eq("isActive", 1));
            List list = crit.list();
            return list;
        } finally {
            session.flush();
            session.close();
        }
    }

    public List getApprovedEmpList() {
        Map msession = ActionContext.getContext().getSession();
        Session session = HibernateUtil.getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            session.beginTransaction();
            Query query = session.createQuery("select l.hcmoEmployeeId from LeaveApproverVO as l where l.hcmoApprovingEmpId=:hcmoApprovingEmpId and l.isActive=:IsActive");
            query.setInteger("IsActive", 1);
            query.setInteger("hcmoApprovingEmpId", oEmp.getEmployeeId());
            lhistList = query.list();
            return lhistList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insert(LeaveHistoryVO lhist) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(lhist);
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
    public void update(LeaveHistoryVO lhist) {
    }

    @Override
    public List getLeaveHistReports(ReportsVO report) {
        Session session = HibernateUtil.getSession();
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        String sLeaveApprover = String.valueOf(mSession.get("LEAVE_APPROVER"));

        EmployeesVO newEmployeeId = new EmployeesVO();
        ProjectVO projectId = new ProjectVO();
        ProjectAssignEmpVO projectAssignedId = new ProjectAssignEmpVO();
        CustomerVO customerId = new CustomerVO();

        List empIdList = new LinkedList();
        List projectIdList = new LinkedList();
        List empProAssignedList = new LinkedList();
        List customerIdList = new LinkedList();

        // Create a HashSet which allows no duplicates
        HashSet finalEmpIdHashSet = new HashSet();
        // Assign the HashSet to a new ArrayList
        ArrayList finalEmpIdList = new ArrayList();

        List list = new LinkedList();
        List empId = new LinkedList();
        try {
            Criteria crit = session.createCriteria(LeaveHistoryVO.class);

            // EmployeesVO newEmployeeId = new EmployeesVO();
            if (report.getFromDate() != null) {
                crit.add(Restrictions.ge("leaveDate", report.getFromDate()));
            }
            if (report.getToDate() != null) {
                crit.add(Restrictions.le("leaveDate", report.getToDate()));
            }

            if ((oEmp.getRoleObj().getRoleName().equals("Admin"))
                || (oEmp.getRoleObj().getRoleName().equals("admin"))
                || (oEmp.getRoleObj().getRoleName().equals("ADMIN"))) {

                if (!report.getLeaveStatus().trim().isEmpty()) {
                    crit.add(Restrictions.eq("leaveStatus", report.getLeaveStatus()));
                }

                // If None of the employee name and project name is being
                // selected
                if ((report.getEmpIdObjList().isEmpty()) && (report.getProjIdObjList().isEmpty())
                    && (report.getCustIdObjList().isEmpty())) {
                    employeeList = employeeService.getAllEmployees(oEmp.getClientId());
                    for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empIdList.add(newEmployeeId.getEmployeeId());
                    }
                    finalEmpIdHashSet.addAll(empIdList);
                }

                // If employee name is being selected
                if (!(report.getEmpIdObjList().isEmpty())) {
                    // Iterating the selected list of employees
                    for (Iterator<EmployeesVO> it = report.getEmpIdObjList().iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empIdList.add(newEmployeeId.getEmployeeId());
                    }
                    finalEmpIdHashSet.addAll(empIdList);
                }

                // If Project name is being selected
                if (!(report.getProjIdObjList().isEmpty())) {
                    // Iterating the selected list of projects
                    for (Iterator<ProjectVO> it = report.getProjIdObjList().iterator(); it.hasNext();) {
                        projectId = it.next();
                        projectIdList.add(projectId.getProjectId());
                    }

                    Criteria critAssignedProject = session.createCriteria(ProjectAssignEmpVO.class);
                    critAssignedProject.add(Restrictions.in("projectName.projectId", projectIdList));
                    critAssignedProject.add(Restrictions.eq("isActive", 1));

                    for (Iterator<ProjectAssignEmpVO> it = critAssignedProject.list().iterator(); it.hasNext();) {
                        projectAssignedId = it.next();
                        empProAssignedList.add(projectAssignedId.getEmployeeName().getEmployeeId());
                    }
                    if (empProAssignedList.isEmpty()) {
                        empProAssignedList.add(0);
                        finalEmpIdHashSet.addAll(empProAssignedList);
                    } else {
                        finalEmpIdHashSet.addAll(empProAssignedList);
                    }
                }

                if (!(report.getCustIdObjList().isEmpty())) {
                    for (Iterator<CustomerVO> it = report.getCustIdObjList().iterator(); it.hasNext();) {
                        customerId = it.next();
                        customerIdList.add(customerId.getCustomerId());
                    }

                    // Get the Project id from by passing customer id
                    Criteria critProject = session.createCriteria(ProjectVO.class);
                    critProject.add(Restrictions.in("customerId.customerId", customerIdList));
                    critProject.add(Restrictions.eq("isActive", 1));
                    for (Iterator<ProjectVO> it = critProject.list().iterator(); it.hasNext();) {
                        projectId = it.next();
                        projectIdList.add(projectId.getProjectId());
                    }
                    if (projectIdList.isEmpty()) {
                        projectIdList.add(0);
                    }

                    Criteria critAssignedProject = session.createCriteria(ProjectAssignEmpVO.class);
                    critAssignedProject.add(Restrictions.in("projectName.projectId", projectIdList));
                    critAssignedProject.add(Restrictions.eq("isActive", 1));

                    for (Iterator<ProjectAssignEmpVO> it = critAssignedProject.list().iterator(); it.hasNext();) {
                        projectAssignedId = it.next();
                        empProAssignedList.add(projectAssignedId.getEmployeeName().getEmployeeId());
                    }
                    if (empProAssignedList.isEmpty()) {
                        empProAssignedList.add(0);
                        finalEmpIdHashSet.addAll(empProAssignedList);
                    } else {
                        finalEmpIdHashSet.addAll(empProAssignedList);
                    }
                }

                finalEmpIdList.addAll(finalEmpIdHashSet);
                crit.add(Restrictions.in("empIdObj.employeeId", finalEmpIdList));
            } else if (sLeaveApprover.equals("BOTH")) {
                leaveSubEmpIdList = employeeService.getCurrentSubEmployee();
                leaveSubEmpIdList.add(oEmp);
                if (report.getEmpIdObjList().isEmpty()) {
                    for (Iterator<EmployeesVO> it = leaveSubEmpIdList.iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empId.add(newEmployeeId.getEmployeeId());
                    }
                } else {
                    for (Iterator<EmployeesVO> it = report.getEmpIdObjList().iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empId.add(newEmployeeId.getEmployeeId());
                    }
                }
                if (!report.getLeaveStatus().trim().isEmpty()) {
                    crit.add(Restrictions.eq("leaveStatus", report.getLeaveStatus()));
                }
                if (!(empId.isEmpty())) {
                    crit.add(Restrictions.in("empIdObj.employeeId", empId));
                }
            } else {
                crit.add(Restrictions.eq("empIdObj.employeeId", oEmp.getEmployeeId()));
            }
            crit.add(Restrictions.eq("isActive", 1));
            list = crit.list();
            return list;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<LeaveHistoryVO> getLeaveHistoryDetails(Integer employeeId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from LeaveHistoryVO as lh where lh.empIdObj.employeeId =:employeeId and (lh.leaveStatus=:leaveStatus1 or lh.leaveStatus=:leaveStatus2)");
            q.setInteger("employeeId", employeeId);
            q.setString("leaveStatus1", "Assigned");
            q.setString("leaveStatus2", "Approved");
            return q.list();
        } finally {
            session.flush();
            session.close();
        }
    }

}
