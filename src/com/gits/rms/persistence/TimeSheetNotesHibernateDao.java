
package com.gits.rms.persistence;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.TimesheetNotesVO;

public class TimeSheetNotesHibernateDao implements TimesheetNotesDao {

    @Override
    public TimesheetNotesVO getTimeSheetNotes(String date) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
        	session.beginTransaction();
            Query q = session.createQuery("from TimesheetNotesVO as time where time.date=:Date and time.empIdObj.employeeId=:EmployeeID and time.isActive=:IsActive");
            q.setString("Date", date);
            q.setInteger("EmployeeID", oEmp.getEmployeeId());
            q.setInteger("IsActive", 1);
            return (TimesheetNotesVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List getTimeSheetNotesList(Date startingDate,Date endDate,int empid) {
        Session session = HibernateUtil.getSession();
        try {
        	Criteria crit = session.createCriteria(TimesheetNotesVO.class);
        	crit.add(Restrictions.between("date", startingDate,endDate));
        	crit.add(Restrictions.eq("empIdObj.employeeId", empid));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
            
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public void insertOrUpdateTimesheetNotes(TimesheetNotesVO notesObj){
        Boolean isUnique = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(notesObj);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
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
    public void updateTimesheetNotes(TimesheetNotesVO notesObj) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimesheetNotesVO set notes=:Notes, UpdatedBy=:UpdatedBy "
                + "where hcmoTimesheetNotesId=:HcmoTimesheetNotesId";
            Query query = session.createQuery(sHql);
            query.setString("Notes", notesObj.getNotes());
            query.setInteger("UpdatedBy", notesObj.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoTimesheetNotesId", notesObj.getHcmoTimesheetNotesId());
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
    public TimesheetNotesVO getEmpTimeSheetNotes(String date,int empId) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
        	session.beginTransaction();
            Query q = session.createQuery("from TimesheetNotesVO as time where time.date=:Date and time.empIdObj.employeeId=:EmployeeID and time.isActive=:IsActive");
            q.setString("Date", date);
            q.setInteger("EmployeeID", empId);
            q.setInteger("IsActive", 1);
            return (TimesheetNotesVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

}
