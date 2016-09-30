package com.gits.rms.persistence;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gits.rms.vo.EducationVO;
import com.gits.rms.vo.EmployeeEEOVO;

/**
 * @author Parveen
 *
 */
public class EmployeeEEOHibernateDao implements EmployeeEEODao {

    private List<EmployeeEEOVO> eeoList;
    
    Logger logger = Logger.getLogger(EmployeeEEOHibernateDao.class);

	@Override
	public void insertEmployeeEEO(EmployeeEEOVO eeo) {
		// TODO Auto-generated method stub
		Boolean isUnique = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(eeo);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            isUnique = true;
            e.printStackTrace();
            logger.error("Error while insert employee EEO : " + e);
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
	public void updateEmployeeEEO(EmployeeEEOVO eeo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmployeeEEOVO set HcmoEthnicRaceId=:HcmoEthnicRaceId, "
                + "HcmoEmployeeId=:HcmoEmployeeId, " + "maritalStatus=:maritalStatus, " + "dependents=:dependents, "
                + "dependDetail=:dependDetail, " + "veteran=:veteran, "
                + "militaryStatus=:militaryStatus, " + "disability=:disability " +"ethnicDocumentId=:ethnicDocumentId," 
                + "veteranDocumentId=:veteranDocumentId," 
                + "militaryDocumentId=:militaryDocumentId,"
                + "disabilityDocumentId=:disabilityDocumentId,"
                + "emergenConName=:emergenConName,"
                + "emergenPhNo=:emergenPhNo,"
                + "UpdatedBy=:UpdatedBy,"
                + "where HcmoEEOId=:HcmoEEOId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoEthnicRaceId", eeo.getEthnicRaceIdObj().getEthnicRaceId());
            query.setInteger("HcmoEmployeeId", eeo.getEmployee().getEmployeeId());
            query.setString("maritalStatus", eeo.getMaritalStatus());
            query.setInteger("dependents", eeo.getDependents());
            query.setString("dependDetail", eeo.getDependDetail());
            query.setString("veteran", eeo.getVeteran());
            query.setString("militaryStatus", eeo.getMilitaryStatus());
            query.setString("disability", eeo.getDisability());
          /*  query.setInteger("ethnicDocumentId", eeo.getEthnicDocumentIdObj().getHcmoDocumentId());
            query.setInteger("veteranDocumentId", eeo.getVeteranDocumentIdObj().getHcmoDocumentId());
            query.setInteger("militaryDocumentId",eeo.getMilitaryDocumentIdObj().getHcmoDocumentId());
            query.setInteger("disabilityDocumentId", eeo.getDisabilityDocumentIdObj().getHcmoDocumentId());*/
            query.setString("emergenConName", eeo.getEmergenConName());
            query.setInteger("emergenPhNo", eeo.getEmergenPhNo());
            query.setInteger("UpdatedBy", eeo.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoEEOId", eeo.getHcmoEEOId());
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
	public List<EmployeeEEOVO> getEmployeeAllEEO(Integer employeeId) {
		// TODO Auto-generated method stub
		 Session session = HibernateUtil.getSession();
	        try {
	            session.beginTransaction();
	            Query query = session.createQuery("from EmployeeEEOVO as eeo left join fetch eeo.employee where eeo.employee.HcmoEmployeeId=:HcmoEmployeeId");
	            query.setInteger("HcmoEmployeeId", employeeId);
	            query.setInteger("IsActive", 1);
	            eeoList = query.list();
	            return eeoList;
	        } finally {
	            session.flush();
	            session.close();
	        }
	}

	@Override
	public EmployeeEEOVO getEEO(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
