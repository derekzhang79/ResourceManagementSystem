package com.gits.rms.persistence;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import com.gits.rms.vo.SaasContractVO;

public class SaasContractHibernateDao implements SaasContractDao{
    
    private List<SaasContractVO> saasContractList;
    private SaasContractVO saasContract;

    public  List getSaasContract() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(
                    "from SaasContractVO as saas where saas.isActive=:IsActive");
            query.setShort("IsActive", (short)1);
            saasContractList = query.list();
            return saasContractList;
        } finally {
            session.flush();session.close();
        }
    }

    public void insertSaasContract(SaasContractVO saasContract) {
        Boolean isUnique = false;
        Session session = HibernateUtil.getSession();
        Transaction tx=null;
        try {
            tx = session.beginTransaction();
            session.save(saasContract);
            tx.commit();
        } catch (RuntimeException e) {
            if(tx != null) tx.rollback();
            isUnique = true;
               throw e;
            } finally {
                if(isUnique){
                    session.close();
                }else {
                    session.flush();session.close();
                }
            }
        }
}
