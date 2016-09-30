
package com.gits.rms.persistence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.ReportsVO;

public class ExpenseStatusTrackerHibernateDao implements ExpenseStatusTrackerDao {

    private List<ExpenseStatusTrackerVO> expStatusTrackerList;
    private ExpenseStatusTrackerVO expStatusTracker;
    private List<EmployeesVO> expensesSubEmpIdList;
    private EmployeesService employeeService = new EmployeesDaoService();
    private List<EmployeesVO> employeeList;

    @Override
    public List getAllExpenseStatusTracker() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ExpenseStatusTrackerVO where created=:created order by hcmoExpensesId");
            expStatusTrackerList = query.list();
            return expStatusTrackerList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ExpenseStatusTrackerVO getExpenseStatusTracker(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ExpenseStatusTrackerVO as d where d.hcmoExpensesStatusTrackerId =:hcmoExpensesStatusTrackerId");
            q.setInteger("hcmoExpensesTypeId", id);
            return (ExpenseStatusTrackerVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertExpenseStatusTracker(ExpenseStatusTrackerVO expStatusTracker) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(expStatusTracker);
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
    public void updateExpenseStatusTracker(ExpenseStatusTrackerVO c) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ExpenseStatusTrackerVO set hcmoExpensesId=:hcmoExpensesId,"
                + "approvalStatus=:approvalStatus," + "approverId=:approverId,"
                + "rejectedId=:rejectedId," + "reviewedId=:reviewedId,"
                + "nextLevelId=:nextLevelId," + "accountantId=:accountantId,"
                + "rejectedNotes=:rejectedNotes," + "UpdatedBy=:UpdatedBy, "
                + "where hcmoExpensesTypeId=:hcmoExpensesTypeId";
            Query query = session.createQuery(sHql);
            query.setString("approvalStatus", expStatusTracker.getApprovalStatus());
            query.setInteger("approverId", expStatusTracker.getApproverId().getEmployeeId());
            query.setInteger("rejectedId", expStatusTracker.getRejectedId().getEmployeeId());
            query.setInteger("reviewedId", expStatusTracker.getReviewedId().getEmployeeId());
            query.setInteger("nextLevelId", expStatusTracker.getNextLevelId().getEmployeeId());
            query.setInteger("accountantId", expStatusTracker.getAccountantId().getEmployeeId());
            query.setString("rejectedNotes", expStatusTracker.getRejectedNotes());
            query.setInteger("UpdatedBy", expStatusTracker.getUpdatedBy().getEmployeeId());
            query.setInteger("hcmoExpensesStatusTrackerId", expStatusTracker.getHcmoExpensesStatusTrackerId());

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
    public void deleteExpenseStatusTracker(Integer id) {

    }

    @Override
    public List getExpensesHistReports(ReportsVO report) {
        Session session = HibernateUtil.getSession();
        Map mSession = ActionContext.getContext().getSession();
//      CLIENT ID ADDED BY BALA
        Integer clientId = (Integer) mSession.get("CLIENT_INFO"); 
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        String sExpensesApprover = String.valueOf(mSession.get("EXPENSES_APPROVER"));

        EmployeesVO newEmployeeId = new EmployeesVO();
        ProjectVO projectId = new ProjectVO();
        ProjectAssignEmpVO projectAssignedId = new ProjectAssignEmpVO();
        CustomerVO customerId = new CustomerVO();

        List empIdList = new LinkedList();
        List projectIdList = new LinkedList();
        List empProAssignedList = new LinkedList();
        List customerIdList = new LinkedList();
        List empId = new LinkedList();

        // Create a HashSet which allows no duplicates
        HashSet finalEmpIdHashSet = new HashSet();
        // Assign the HashSet to a new ArrayList
        ArrayList finalEmpIdList = new ArrayList();
        try {

            Criteria crit = session.createCriteria(ExpenseStatusTrackerVO.class);
            crit.createAlias("hcmoExpensesId", "hcmo");
            if (report.getFromDate() != null) {
                crit.add(Restrictions.ge("created", report.getFromDate()));
            }

            if (report.getToDate() != null) {
                crit.add(Restrictions.le("created", report.getToDate()));
            }

            if ((oEmp.getRoleObj().getRoleName().equals("Admin"))
                || (oEmp.getRoleObj().getRoleName().equals("admin"))
                || (oEmp.getRoleObj().getRoleName().equals("ADMIN"))) {

                // If None of the employee name and project name is being
                // selected
                if ((report.getEmpIdObjList().isEmpty()) && (report.getProjIdObjList().isEmpty())
                    && (report.getCustIdObjList().isEmpty())) {
//                	CLIENT ID ADDED BY BALA
                    employeeList = employeeService.getAllEmployees(clientId);
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
                crit.add(Restrictions.in("hcmo.hcmoEmployeeId.employeeId", finalEmpIdList));
            } else if (sExpensesApprover.equals("BOTH")) {
                expensesSubEmpIdList = employeeService.getCurrentExpensesSubEmployee();
                expensesSubEmpIdList.add(oEmp);
                if (report.getEmpIdObjList().isEmpty()) {
                    for (Iterator<EmployeesVO> it = expensesSubEmpIdList.iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empId.add(newEmployeeId.getEmployeeId());
                    }
                } else {
                    for (Iterator<EmployeesVO> it = report.getEmpIdObjList().iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empId.add(newEmployeeId.getEmployeeId());
                    }
                }
                if (!(empId.isEmpty())) {
                    crit.add(Restrictions.in("hcmo.hcmoEmployeeId.employeeId", empId));
                }
            } else {
                crit.add(Restrictions.eq("hcmo.hcmoEmployeeId.employeeId", oEmp.getEmployeeId()));
            }
            return crit.list();

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getDashExpenseForApproval() {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ExpenseStatusTrackerVO as exp where exp.approvalStatus=:approvalStatus and exp.hcmoExpensesId in (select hcmoExpensesId from EmployeeExpensesVO as e where e.hcmoEmployeeId=:hcmoEmployeeId)");
            query.setString("approvalStatus", "ForApproval");
            query.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            return query.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getDashExpenseForApprovalToday() {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        Date todayDate = new Date();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ExpenseStatusTrackerVO as exp where exp.approvalStatus=:approvalStatus and exp.created=:Created and exp.hcmoExpensesId in (select hcmoExpensesId from EmployeeExpensesVO as e where e.hcmoEmployeeId=:hcmoEmployeeId)");
            query.setString("approvalStatus", "ForApproval");
            query.setDate("Created", todayDate);
            query.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            return query.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getDashExpenseForApprovalThreeDays() {
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
            Query query = session.createQuery("from ExpenseStatusTrackerVO as exp where exp.approvalStatus=:approvalStatus and year(exp.created)="
                + String.valueOf(year)
                + " and day(exp.created)>="
                + String.valueOf(threeDays.getDate())
                + " and day(exp.created)<="
                + String.valueOf(todayDate.getDate())
                + "and month(exp.created)="
                + String.valueOf(month)
                + "and exp.hcmoExpensesId in (select hcmoExpensesId from EmployeeExpensesVO as e where e.hcmoEmployeeId=:hcmoEmployeeId)");
            query.setString("approvalStatus", "ForApproval");
            query.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            return query.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getDashExpenseForApprovalOneWeek() {
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
            Query query = session.createQuery("from ExpenseStatusTrackerVO as exp where exp.approvalStatus=:approvalStatus and year(exp.created)="
                + String.valueOf(year)
                + " and day(exp.created)>="
                + String.valueOf(lastweek.getDate())
                + " and day(exp.created)<="
                + String.valueOf(todayDate.getDate())
                + "and month(exp.created)="
                + String.valueOf(month)
                + "and exp.hcmoExpensesId in (select hcmoExpensesId from EmployeeExpensesVO as e where e.hcmoEmployeeId=:hcmoEmployeeId)");

            query.setString("approvalStatus", "ForApproval");
            query.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            return query.list();
        } finally {
            session.flush();
            session.close();
        }
    }
}