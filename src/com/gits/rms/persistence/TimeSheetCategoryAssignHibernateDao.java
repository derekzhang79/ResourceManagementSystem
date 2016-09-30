
package com.gits.rms.persistence;

import java.util.ArrayList;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.vo.CustomerVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.ReportTimeSheetDislayVO;
import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;

public class TimeSheetCategoryAssignHibernateDao implements TimeSheetCategoryAssignDao {

    private List<EmployeesVO> employeeList;
    private EmployeesService employeeService = new EmployeesDaoService();
    private EmployeesVO employee = new EmployeesVO();

    @Override
    public void deleteTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimeSheetCategoryAssignVO set updatedBy=:UpdatedBy,IsActive=:IsActive where timeSheetCategoryAssignId=:timeSheetCategoryAssignId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", timeSheetCategoryAssign.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("timeSheetCategoryAssignId", timeSheetCategoryAssign.getTimeSheetCategoryAssignId());
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
    public List getAllTimeSheetCategoryAssign() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TimeSheetCategoryAssignVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public TimeSheetCategoryAssignVO getTimeSheetCategoryAssign(Integer timeSheetCategoryAssignId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetCategoryAssignVO where timeSheetCategoryAssignId=:timeSheetCategoryAssignId");
            q.setInteger("timeSheetCategoryAssignId", timeSheetCategoryAssignId);
            return (TimeSheetCategoryAssignVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(timeSheetCategoryAssign);
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
    public void updateTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimeSheetCategoryAssignVO set updatedBy=:UpdatedBy,employeeName=:employeeid,timesheetCategoryName=:categoryid,timesheetCategoryName=:categoryid,enterDate=:enter_date,enterTime=:enter_time,rejected=:rejected,rework=:rework,approve=:approve where timeSheetCategoryAssignId=:timeSheetCategoryAssignId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", timeSheetCategoryAssign.getUpdatedBy().getEmployeeId());
            query.setInteger("timeSheetCategoryAssignId", timeSheetCategoryAssign.getTimeSheetCategoryAssignId());
            query.setInteger("employeeid", timeSheetCategoryAssign.getEmployeeName().getEmployeeId());
            query.setInteger("categoryid", timeSheetCategoryAssign.getTimesheetCategoryName().getHcmoTimesheetCategoryId());
            query.setDate("enter_date", timeSheetCategoryAssign.getEnterDate());
            query.setDouble("enter_time", timeSheetCategoryAssign.getEnterTime());
            if (timeSheetCategoryAssign.getRejected() == 1) {
                query.setInteger("rejected", timeSheetCategoryAssign.getRejected());
                query.setInteger("rework", 0);
                query.setInteger("approve", 0);
            } else if (timeSheetCategoryAssign.getRework() == 1) {
                query.setInteger("rework", timeSheetCategoryAssign.getRework());
                query.setInteger("rejected", 0);
                query.setInteger("approve", 0);
            } else {
                query.setInteger("approve", timeSheetCategoryAssign.getApprove());
                query.setInteger("rework", 0);
                query.setInteger("rejected", 0);
            }
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
    public List timeSheetCategoryAssignSearchResult(Integer EmployeeId, Date date, Date endDate) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetCategoryAssignVO as tcavo where tcavo.employeeName=:EmployeeId and (tcavo.enterDate>=:Startdate and tcavo.enterDate<=:Enddate) and tcavo.isActive=:IsActive");
            q.setInteger("EmployeeId", EmployeeId);
            q.setDate("Startdate", date);
            q.setDate("Enddate", endDate);
            q.setInteger("IsActive", 1);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List timeSheetCategoryEmpSearchResult(Integer EmployeeId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetCategoryAssignVO as tcavo where tcavo.employeeName=:EmployeeId and tcavo.isActive=:IsActive");
            q.setInteger("EmployeeId", EmployeeId);
            q.setInteger("IsActive", 1);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List timeSheetCategoryAssignSearch(Integer EmployeeId, Integer timeSheetCategoryId, Date endDate) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetCategoryAssignVO as tcavo where tcavo.employeeName=:EmployeeId and tcavo.enterDate=:enterdate and tcavo.timesheetCategoryName=:CategoryId and tcavo.isActive=:IsActive");
            q.setInteger("EmployeeId", EmployeeId);
            q.setInteger("CategoryId", timeSheetCategoryId);
            q.setDate("enterdate", endDate);
            q.setInteger("IsActive", 1);
            return q.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List timeSheetCategoryAssignSearchCronJob(Integer EmployeeId, Integer timeSheetCategoryId, Date endDate, String clientId) {
        Session session = HibernateUtil.getSessionForCronJob(clientId);
        try {

            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetCategoryAssignVO as tcavo where tcavo.employeeName=:EmployeeId and tcavo.enterDate=:enterdate and tcavo.timesheetCategoryName=:CategoryId and tcavo.isActive=:IsActive");
            q.setInteger("EmployeeId", EmployeeId);
            q.setInteger("CategoryId", timeSheetCategoryId);
            q.setDate("enterdate", endDate);
            q.setInteger("IsActive", 1);
            return q.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List timeSheetProAssignProjectSummationReport(ReportsVO reportsVO) {
        Session session = HibernateUtil.getSession();
        ProjectVO projectId = new ProjectVO();
        List projectIdList = new LinkedList();

        try {
            Criteria crit = session.createCriteria(TimeSheetProjectAssignVO.class);

            // If Project name is being selected
            if (!(reportsVO.getProjIdObjList().isEmpty())) {
                // Iterating the selected list of projects
                for (Iterator<ProjectVO> it = reportsVO.getProjIdObjList().iterator(); it.hasNext();) {
                    projectId = it.next();
                    projectIdList.add(projectId.getProjectId());
                }
            }

            // If We get data for the Approve or Reject or Rework field
            if (!reportsVO.getTimeSheetType().isEmpty()) {
                if (reportsVO.getTimeSheetType().equals("Approved")) {
                    crit.add(Restrictions.eq("approve", 1));
                } else if (reportsVO.getTimeSheetType().equals("Rejected")) {
                    crit.add(Restrictions.eq("rejected", 1));
                } else if (reportsVO.getTimeSheetType().equals("Rework")) {
                    crit.add(Restrictions.eq("rework", 1));
                }
            }
            crit.add(Restrictions.in("projectName.projectId", projectIdList));
            crit.add(Restrictions.eq("isActive", 1));

            return crit.list();
        } finally {
            session.flush();
            session.close();
        }

    }

    // Timesheet employee summation reports for project
    @Override
    public List timeSheetProjectAssignSummationReport(ReportsVO reportsVO) {
        Session session = HibernateUtil.getSession();
        Session newSession = HibernateUtil.getSession();
        EmployeesVO empId = new EmployeesVO();
        List<ReportTimeSheetDislayVO> timeSheetProjectAssignList = new LinkedList();

        try {

            // If employee name is being selected
            if (!(reportsVO.getEmpIdObjList().isEmpty())) {
                // Iterating the selected list of employees
                for (Iterator<EmployeesVO> it = reportsVO.getEmpIdObjList().iterator(); it.hasNext();) {
                    empId = it.next();
                    ReportTimeSheetDislayVO reportTimesheetDisplay = new ReportTimeSheetDislayVO();
                    TimeSheetProjectAssignVO timeSheetProjectAssign = new TimeSheetProjectAssignVO();
                    HashSet projectNameHashSet = new HashSet();
                    String projectName = "";
                    String projectTotalHour = "";

                    Criteria crit = session.createCriteria(TimeSheetProjectAssignVO.class);
                    crit.add(Restrictions.eq("employeeName.employeeId", empId.getEmployeeId()));
                    crit.setProjection(Projections.sum("enterTime"));
                    crit.add(Restrictions.eq("isActive", 1));

                    Criteria criteria = newSession.createCriteria(TimeSheetProjectAssignVO.class);
                    criteria.add(Restrictions.eq("employeeName.employeeId", empId.getEmployeeId()));
                    criteria.add(Restrictions.eq("isActive", 1));

                    // If We get data for the FromDate field
                    if (reportsVO.getFromDate() != null) {
                        crit.add(Restrictions.ge("enterDate", reportsVO.getFromDate()));
                        criteria.add(Restrictions.ge("enterDate", reportsVO.getFromDate()));
                    }

                    // If We get data for the ToDate field
                    if (reportsVO.getToDate() != null) {
                        crit.add(Restrictions.le("enterDate", reportsVO.getToDate()));
                        criteria.add(Restrictions.le("enterDate", reportsVO.getToDate()));
                    }

                    // If We get data for the ToDate field
                    if (!reportsVO.getTimeSheetType().trim().isEmpty()) {
                        if (reportsVO.getTimeSheetType().equals("Approved")) {
                            crit.add(Restrictions.eq("approve", 1));
                            criteria.add(Restrictions.eq("approve", 1));
                            reportTimesheetDisplay.setStatus("approve");
                        } else if (reportsVO.getTimeSheetType().equals("Rejected")) {
                            crit.add(Restrictions.eq("rejected", 1));
                            criteria.add(Restrictions.eq("rejected", 1));
                            reportTimesheetDisplay.setStatus("rejected");
                        } else if (reportsVO.getTimeSheetType().equals("Rework")) {
                            crit.add(Restrictions.eq("rework", 1));
                            criteria.add(Restrictions.eq("rework", 1));
                            reportTimesheetDisplay.setStatus("rework");
                        }
                    } else {
                        reportTimesheetDisplay.setStatus("");
                    }

                    crit.list();
                    criteria.list();

                    for (Iterator<TimeSheetProjectAssignVO> iterator = criteria.list().iterator(); iterator.hasNext();) {
                        timeSheetProjectAssign = iterator.next();
                        projectNameHashSet.add(timeSheetProjectAssign.getProjectName().getProjectName());
                    }

                    for (Iterator<String> iter = projectNameHashSet.iterator(); iter.hasNext();) {
                        projectName += iter.next() + ",";
                    }

                    if (projectName.contains(",")) {
                        projectName = projectName.substring(0, projectName.lastIndexOf(","));
                    }

                    employee = employeeService.getEmployees(empId.getEmployeeId());

                    projectTotalHour = crit.list().toString();
                    if ((projectTotalHour.contains("[")) || (projectTotalHour.contains("]"))) {
                        projectTotalHour = projectTotalHour.replace("[", " ");
                        projectTotalHour = projectTotalHour.replace("]", " ");
                    }

                    if (projectTotalHour.contains("null")) {
                        projectTotalHour = "";
                    }

                    reportTimesheetDisplay.setProjectTotalHour(projectTotalHour);
                    reportTimesheetDisplay.setEmpName(employee.getEmpFullName());
                    reportTimesheetDisplay.setProjectName(projectName);
                    timeSheetProjectAssignList.add(reportTimesheetDisplay);
                }
            }
            return timeSheetProjectAssignList;

        } finally {
            session.flush();
            session.close();
            newSession.flush();
            newSession.close();
        }
    }

    @Override
    public List timeSheetCategoryAssignSummationReport(ReportsVO reportsVO) {
        Session session = HibernateUtil.getSession();
        Session newSession = HibernateUtil.getSession();
        EmployeesVO empId = new EmployeesVO();
        new LinkedList();
        List<ReportTimeSheetDislayVO> timeSheetCategoryAssignList = new LinkedList();

        try {

            // If employee name is being selected
            if (!(reportsVO.getEmpIdObjList().isEmpty())) {
                // Iterating the selected list of employees
                for (Iterator<EmployeesVO> it = reportsVO.getEmpIdObjList().iterator(); it.hasNext();) {
                    empId = it.next();
                    ReportTimeSheetDislayVO reportTimesheetDisplay = new ReportTimeSheetDislayVO();
                    TimeSheetCategoryAssignVO timeSheetCategoryAssign = new TimeSheetCategoryAssignVO();
                    HashSet catNameHashSet = new HashSet();
                    String categoryName = "";
                    String categoryTotalHour = "";

                    Criteria crit = session.createCriteria(TimeSheetCategoryAssignVO.class);
                    crit.add(Restrictions.eq("employeeName.employeeId", empId.getEmployeeId()));
                    crit.setProjection(Projections.sum("enterTime"));
                    crit.add(Restrictions.eq("isActive", 1));

                    Criteria criteria = newSession.createCriteria(TimeSheetCategoryAssignVO.class);
                    criteria.add(Restrictions.eq("employeeName.employeeId", empId.getEmployeeId()));
                    criteria.add(Restrictions.eq("isActive", 1));

                    // If We get data for the FromDate field
                    if (reportsVO.getFromDate() != null) {
                        crit.add(Restrictions.ge("enterDate", reportsVO.getFromDate()));
                        criteria.add(Restrictions.ge("enterDate", reportsVO.getFromDate()));
                    }

                    // If We get data for the ToDate field
                    if (reportsVO.getToDate() != null) {
                        crit.add(Restrictions.le("enterDate", reportsVO.getToDate()));
                        criteria.add(Restrictions.le("enterDate", reportsVO.getToDate()));
                    }

                    // If We get data for the ToDate field
                    if (!reportsVO.getTimeSheetType().trim().isEmpty()) {
                        if (reportsVO.getTimeSheetType().equals("Approved")) {
                            crit.add(Restrictions.eq("approve", 1));
                            criteria.add(Restrictions.eq("approve", 1));
                            reportTimesheetDisplay.setStatus("approve");
                        } else if (reportsVO.getTimeSheetType().equals("Rejected")) {
                            crit.add(Restrictions.eq("rejected", 1));
                            criteria.add(Restrictions.eq("rejected", 1));
                            reportTimesheetDisplay.setStatus("rejected");
                        } else if (reportsVO.getTimeSheetType().equals("Rework")) {
                            crit.add(Restrictions.eq("rework", 1));
                            criteria.add(Restrictions.eq("rework", 1));
                            reportTimesheetDisplay.setStatus("rework");
                        }
                    } else {
                        reportTimesheetDisplay.setStatus("");
                    }

                    crit.list();
                    criteria.list();

                    if (!criteria.list().isEmpty()) {
                        for (Iterator<TimeSheetCategoryAssignVO> iterator = criteria.list().iterator(); iterator.hasNext();) {
                            timeSheetCategoryAssign = iterator.next();
                            catNameHashSet.add(timeSheetCategoryAssign.getTimesheetCategoryName().getName());
                        }

                        for (Iterator<String> iter = catNameHashSet.iterator(); iter.hasNext();) {
                            categoryName += iter.next() + ",";
                        }
                        if (categoryName.contains(",")) {
                            categoryName = categoryName.substring(0, categoryName.lastIndexOf(","));
                        }
                    } else {
                        categoryName = " ";
                    }

                    categoryTotalHour = crit.list().toString();
                    if ((categoryTotalHour.contains("[")) || (categoryTotalHour.contains("]"))) {
                        categoryTotalHour = categoryTotalHour.replace("[", " ");
                        categoryTotalHour = categoryTotalHour.replace("]", " ");
                    }

                    if (categoryTotalHour.contains("null")) {
                        categoryTotalHour = "";
                    }

                    employee = employeeService.getEmployees(empId.getEmployeeId());
                    reportTimesheetDisplay.setCategoryTotalHour(categoryTotalHour);
                    reportTimesheetDisplay.setEmpName(employee.getEmpFullName());
                    reportTimesheetDisplay.setCategoryName(categoryName);
                    timeSheetCategoryAssignList.add(reportTimesheetDisplay);
                }
            }
            return timeSheetCategoryAssignList;

        } finally {
            session.flush();
            session.close();
            newSession.flush();
            newSession.close();
        }
    }

    @Override
    public List timeSheetCategoryAssignReport(ReportsVO reportsVO) {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        String sTimeSheetApprover = String.valueOf(msession.get("TIMESHEET_APPROVER"));
        List critList = new LinkedList();

        EmployeesVO newEmployeeId = new EmployeesVO();
        ProjectVO projectId = new ProjectVO();
        ProjectActivityVO projectActivityId = new ProjectActivityVO();
        ProjectAssignEmpVO projectAssignedId = new ProjectAssignEmpVO();
        ProjectAssignEmpVO projectActivityAssignedId = new ProjectAssignEmpVO();
        CustomerVO customerId = new CustomerVO();

        List empIdList = new LinkedList();
        List projectIdList = new LinkedList();
        List projectActivityIdList = new LinkedList();
        List empProAssignedList = new LinkedList();
        List empProActivityAssignedList = new LinkedList();
        List customerIdList = new LinkedList();
        List empId = new LinkedList();
        // Create a HashSet which allows no duplicates
        HashSet finalEmpIdHashSet = new HashSet();
        // Assign the HashSet to a new ArrayList
        ArrayList finalEmpIdList = new ArrayList();
        try {
            Criteria crit = session.createCriteria(TimeSheetCategoryAssignVO.class);
            if (reportsVO.getFromDate() != null) {
                crit.add(Restrictions.ge("enterDate", reportsVO.getFromDate()));
            }

            if (reportsVO.getToDate() != null) {
                crit.add(Restrictions.le("enterDate", reportsVO.getToDate()));
            }

            if ((oEmp.getRoleObj().getRoleName().equals("Admin"))
                || (oEmp.getRoleObj().getRoleName().equals("admin"))
                || (oEmp.getRoleObj().getRoleName().equals("ADMIN"))) {

                if ((reportsVO.getEmpIdObjList().isEmpty())
                    && (reportsVO.getProjIdObjList().isEmpty())
                    && (reportsVO.getCustIdObjList().isEmpty())) {
                    employeeList = employeeService.getAllEmployees(oEmp.getClientId());
                    for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empIdList.add(newEmployeeId.getEmployeeId());
                    }
                    finalEmpIdHashSet.addAll(empIdList);
                }

                // If employee name is being selected
                if (!(reportsVO.getEmpIdObjList().isEmpty())) {
                    // Iterating the selected list of employees
                    for (Iterator<EmployeesVO> it = reportsVO.getEmpIdObjList().iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empIdList.add(newEmployeeId.getEmployeeId());
                    }
                    finalEmpIdHashSet.addAll(empIdList);
                }

                // If ProjectActivity name is being selected
                if (!(reportsVO.getProjActivityIdObjList().isEmpty())) {
                    // Iterating the selected list of projects
                    for (Iterator<ProjectActivityVO> it = reportsVO.getProjActivityIdObjList().iterator(); it.hasNext();) {
                        projectActivityId = it.next();
                        projectActivityIdList.add(projectActivityId.getProjectActivityId());
                    }

                    Criteria critAssignedProjectActivity = session.createCriteria(ProjectAssignEmpVO.class);
                    critAssignedProjectActivity.add(Restrictions.in("projectActivityId.projectActivityId", projectActivityIdList));
                    critAssignedProjectActivity.add(Restrictions.eq("isActive", 1));

                    for (Iterator<ProjectAssignEmpVO> it = critAssignedProjectActivity.list().iterator(); it.hasNext();) {
                        projectActivityAssignedId = it.next();
                        empProActivityAssignedList.add(projectActivityAssignedId.getEmployeeName().getEmployeeId());
                    }

                    if (empProActivityAssignedList.isEmpty()) {
                        empProActivityAssignedList.add(0);
                        finalEmpIdHashSet.addAll(empProActivityAssignedList);
                    } else {
                        finalEmpIdHashSet.addAll(empProActivityAssignedList);
                    }
                }

                // If Project name is being selected
                if (!(reportsVO.getProjIdObjList().isEmpty())) {
                    // Iterating the selected list of projects
                    for (Iterator<ProjectVO> it = reportsVO.getProjIdObjList().iterator(); it.hasNext();) {
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

                if (!(reportsVO.getCustIdObjList().isEmpty())) {
                    for (Iterator<CustomerVO> it = reportsVO.getCustIdObjList().iterator(); it.hasNext();) {
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
                crit.add(Restrictions.in("employeeName.employeeId", finalEmpIdList));
            } else if (sTimeSheetApprover.equals("BOTH")) {
                employeeList = employeeService.getCurrentTimeSheetSubEmployee();
                employeeList.add(oEmp);
                if (reportsVO.getEmpIdObjList().isEmpty()) {
                    for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empId.add(newEmployeeId.getEmployeeId());
                    }
                } else {
                    for (Iterator<EmployeesVO> it = reportsVO.getEmpIdObjList().iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empId.add(newEmployeeId.getEmployeeId());
                    }
                }
                if (!(empId.isEmpty())) {
                    crit.add(Restrictions.in("employeeName.employeeId", empId));
                }
            } else {
                crit.add(Restrictions.eq("employeeName.employeeId", oEmp.getEmployeeId()));
            }
            crit.add(Restrictions.eq("isActive", 1));
            critList.addAll(crit.list());
            return critList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List timeSheetProjectAssignReport(ReportsVO reportsVO) {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        String sTimeSheetApprover = String.valueOf(msession.get("TIMESHEET_APPROVER"));
        new LinkedList();

        EmployeesVO newEmployeeId = new EmployeesVO();
        ProjectVO projectId = new ProjectVO();
        ProjectActivityVO projectActivityId = new ProjectActivityVO();
        ProjectAssignEmpVO projectAssignedId = new ProjectAssignEmpVO();
        ProjectAssignEmpVO projectActivityAssignedId = new ProjectAssignEmpVO();
        CustomerVO customerId = new CustomerVO();

        List empIdList = new LinkedList();
        List projectIdList = new LinkedList();
        List projectActivityIdList = new LinkedList();
        List empProAssignedList = new LinkedList();
        List empProActivityAssignedList = new LinkedList();
        List customerIdList = new LinkedList();
        List empId = new LinkedList();

        // Create a HashSet which allows no duplicates
        HashSet finalEmpIdHashSet = new HashSet();
        // Assign the HashSet to a new ArrayList
        ArrayList finalEmpIdList = new ArrayList();

        List criteriatList = new LinkedList();
        try {
            Criteria criteria = session.createCriteria(TimeSheetProjectAssignVO.class);
            if (reportsVO.getFromDate() != null) {
                criteria.add(Restrictions.ge("enterDate", reportsVO.getFromDate()));
            }

            if (reportsVO.getToDate() != null) {
                criteria.add(Restrictions.le("enterDate", reportsVO.getToDate()));
            }

            if ((oEmp.getRoleObj().getRoleName().equals("Admin"))
                || (oEmp.getRoleObj().getRoleName().equals("admin"))
                || (oEmp.getRoleObj().getRoleName().equals("ADMIN"))) {
                if ((reportsVO.getEmpIdObjList().isEmpty())
                    && (reportsVO.getProjIdObjList().isEmpty())
                    && (reportsVO.getCustIdObjList().isEmpty())) {
                    employeeList = employeeService.getAllEmployees(oEmp.getClientId());
                    for (Iterator it = employeeList.iterator(); it.hasNext();) {
                        newEmployeeId = (EmployeesVO) it.next();
                        empIdList.add(newEmployeeId.getEmployeeId());
                    }
                    finalEmpIdHashSet.addAll(empIdList);
                }

                // If employee name is being selected
                if (!(reportsVO.getEmpIdObjList().isEmpty())) {
                    // Iterating the selected list of employees
                    for (Iterator it = reportsVO.getEmpIdObjList().iterator(); it.hasNext();) {
                        newEmployeeId = (EmployeesVO) it.next();
                        empIdList.add(newEmployeeId.getEmployeeId());
                    }
                    finalEmpIdHashSet.addAll(empIdList);
                }

                // If ProjectActivity name is being selected
                if (!(reportsVO.getProjActivityIdObjList().isEmpty())) {
                    // Iterating the selected list of projects
                    for (Iterator it = reportsVO.getProjActivityIdObjList().iterator(); it.hasNext();) {
                        projectActivityId = (ProjectActivityVO) it.next();
                        projectActivityIdList.add(projectActivityId.getProjectActivityId());
                    }

                    Criteria critAssignedProjectActivity = session.createCriteria(ProjectAssignEmpVO.class);
                    critAssignedProjectActivity.add(Restrictions.in("projectActivityId.projectActivityId", projectActivityIdList));
                    critAssignedProjectActivity.add(Restrictions.eq("isActive", 1));

                    for (Iterator it = critAssignedProjectActivity.list().iterator(); it.hasNext();) {
                        projectActivityAssignedId = (ProjectAssignEmpVO) it.next();
                        empProActivityAssignedList.add(projectActivityAssignedId.getEmployeeName().getEmployeeId());
                    }

                    if (empProActivityAssignedList.isEmpty()) {
                        empProActivityAssignedList.add(0);
                        finalEmpIdHashSet.addAll(empProActivityAssignedList);
                    } else {
                        finalEmpIdHashSet.addAll(empProActivityAssignedList);
                    }
                }

                // If Project name is being selected
                if (!(reportsVO.getProjIdObjList().isEmpty())) {
                    // Iterating the selected list of projects
                    for (Iterator it = reportsVO.getProjIdObjList().iterator(); it.hasNext();) {
                        projectId = (ProjectVO) it.next();
                        projectIdList.add(projectId.getProjectId());
                    }

                    Criteria critAssignedProject = session.createCriteria(ProjectAssignEmpVO.class);
                    critAssignedProject.add(Restrictions.in("projectName.projectId", projectIdList));
                    critAssignedProject.add(Restrictions.eq("isActive", 1));

                    for (Iterator it = critAssignedProject.list().iterator(); it.hasNext();) {
                        projectAssignedId = (ProjectAssignEmpVO) it.next();
                        empProAssignedList.add(projectAssignedId.getEmployeeName().getEmployeeId());
                    }
                    if (empProAssignedList.isEmpty()) {
                        empProAssignedList.add(0);
                        finalEmpIdHashSet.addAll(empProAssignedList);
                    } else {
                        finalEmpIdHashSet.addAll(empProAssignedList);
                    }
                }

                if (!(reportsVO.getCustIdObjList().isEmpty())) {
                    for (Iterator it = reportsVO.getCustIdObjList().iterator(); it.hasNext();) {
                        customerId = (CustomerVO) it.next();
                        customerIdList.add(customerId.getCustomerId());
                    }

                    // Get the Project id from by passing customer id
                    Criteria critProject = session.createCriteria(ProjectVO.class);
                    critProject.add(Restrictions.in("customerId.customerId", customerIdList));
                    critProject.add(Restrictions.eq("isActive", 1));
                    for (Iterator it = critProject.list().iterator(); it.hasNext();) {
                        projectId = (ProjectVO) it.next();
                        projectIdList.add(projectId.getProjectId());
                    }
                    if (projectIdList.isEmpty()) {
                        projectIdList.add(0);
                    }

                    Criteria critAssignedProject = session.createCriteria(ProjectAssignEmpVO.class);
                    critAssignedProject.add(Restrictions.in("projectName.projectId", projectIdList));
                    critAssignedProject.add(Restrictions.eq("isActive", 1));

                    for (Iterator it = critAssignedProject.list().iterator(); it.hasNext();) {
                        projectAssignedId = (ProjectAssignEmpVO) it.next();
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
                criteria.add(Restrictions.in("employeeName.employeeId", finalEmpIdList));
            } else if (sTimeSheetApprover.equals("BOTH")) {
                employeeList = employeeService.getCurrentTimeSheetSubEmployee();
                employeeList.add(oEmp);
                if (reportsVO.getEmpIdObjList().isEmpty()) {
                    for (Iterator it = employeeList.iterator(); it.hasNext();) {
                        newEmployeeId = (EmployeesVO) it.next();
                        empId.add(newEmployeeId.getEmployeeId());
                    }
                } else {
                    for (Iterator it = reportsVO.getEmpIdObjList().iterator(); it.hasNext();) {
                        newEmployeeId = (EmployeesVO) it.next();
                        empId.add(newEmployeeId.getEmployeeId());
                    }
                }
                if (!(empId.isEmpty())) {
                    criteria.add(Restrictions.in("employeeName.employeeId", empId));
                }
            } else {
                criteria.add(Restrictions.eq("employeeName.employeeId", oEmp.getEmployeeId()));
            }
            criteria.add(Restrictions.eq("isActive", 1));
            criteria.addOrder(Order.asc("enterDate"));
            criteriatList.addAll(criteria.list());
            return criteriatList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.flush();
            session.close();
        }
    }

}