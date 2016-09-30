/**
 * 
 */
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gits.rms.vo.EmpAssetsVO;
import com.gits.rms.vo.EmployeeEEOVO;

/**
 * @author Parveen
 *
 */
public class EmpAssetsHibernateDao implements EmpAssetsDao {

	 private List<EmpAssetsVO> assetsList;
	
	@Override
	public void insertEmpAssets(EmpAssetsVO assets) {
		// TODO Auto-generated method stub
		Boolean isUnique = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(assets);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            isUnique = true;
            e.printStackTrace();
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
	public void updateEmpAssets(EmpAssetsVO assets) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmpAssetsVO set HcmoEmployeeId=:HcmoEmployeeId, "
                + "srNo=:srNo,"
                + "assetTypeId=:assetTypeId,"
                + "assetName=:assetName,"
                + "releasedDate=:releasedDate,"
                + "issuedDate=:issuedDate,"
                + "UpdatedBy=:UpdatedBy,"
                + "where HcmoAssetId=:HcmoAssetId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoEmployeeId",assets.getEmpIdObj().getEmployeeId());
            query.setInteger("srNo",assets.getSrNo());
            query.setInteger("assetTypeId",assets.getAssetIdObj().getAssetTypeId());
            query.setString("assetName", assets.getAssetName());
            query.setDate("releasedDate", assets.getReleasedDate());
            query.setDate("issuedDate",assets.getIssuedDate());
            query.setInteger("UpdatedBy", assets.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoAssetId",assets.getHcmoAssetId());
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
	public List<EmpAssetsVO> getEmpAllAssets(Integer employeeId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmpAssetsVO as asset left join fetch asset.employee where asset.employee.HcmoEmployeeId=:HcmoEmployeeId");
            query.setInteger("HcmoEmployeeId", employeeId);
            query.setInteger("IsActive", 1);
            assetsList = query.list();
            return assetsList;
        } finally {
            session.flush();
            session.close();
        }
	}
}
