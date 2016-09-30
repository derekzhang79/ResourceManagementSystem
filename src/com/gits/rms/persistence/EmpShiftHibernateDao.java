/**
 * 
 */
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.gits.rms.vo.EmpAssetsVO;
import com.gits.rms.vo.EmployeeShiftVO;

/**
 * @author Parveen
 *
 */
public class EmpShiftHibernateDao implements EmpShiftDao {
	
	 private List<EmployeeShiftVO> shiftList;

	@Override
	public void insertEmpShift(EmployeeShiftVO shift) {
		// TODO Auto-generated method stub
		Boolean isUnique = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(shift);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            isUnique = true;
            e.printStackTrace();
            Log.error("Error while inserting shift : " + e);
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
	public void updateEmpShift(EmployeeShiftVO shift) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmployeeShiftVO set HcmoEmployeeId=:HcmoEmployeeId, "
                + "startDate=:startDate,"
                + "endDate=:endDate,"
                + "startTime=:startTime,"
                + "endTime=:endTime,"
                + "shiftType=:shiftType,"
                + "UpdatedBy=:UpdatedBy,"
                + "where HcmoShiftId=:HcmoShiftId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoEmployeeId",shift.getEmployee().getEmployeeId());
            query.setDate("startDate", shift.getStartDate());
            query.setDate("endDate", shift.getEndDate());
            query.setTimestamp("startTime", shift.getStartTime());
            query.setTimestamp("endTime", shift.getEndTime());
            query.setString("shiftType", shift.getShiftType());
            query.setInteger("UpdatedBy", shift.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoShiftId",shift.getHcmoShiftId());
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
	public List<EmployeeShiftVO> getEmpAllShift(Integer employeeId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmployeeShiftVO as shift left join fetch shift.employee where shift.employee.HcmoEmployeeId=:HcmoEmployeeId");
            query.setInteger("HcmoEmployeeId", employeeId);
            query.setInteger("IsActive", 1);
            shiftList = query.list();
            return shiftList;
        } finally {
            session.flush();
            session.close();
        }
	}

}
