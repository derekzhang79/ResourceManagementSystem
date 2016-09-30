
package com.gits.rms.persistence;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.SignatureVO;

public class SignatureHibernateDao implements SignatureDao {

    private List<SignatureVO> signatureList;

    @Override
    public List getAllSignature() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(SignatureVO.class);
            crit.addOrder(Order.asc("signatureName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public SignatureVO getSignature(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from SignatureVO as signature where signature.hcmoSignatureId =:hcmoSignatureId");
            q.setInteger("hcmoSignatureId", id);
            return (SignatureVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public SignatureVO getSignatureEmployee(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from SignatureVO as signature left join fetch signature.empIdObj where signature.hcmoSignatureId =:hcmoSignatureId");
            q.setInteger("hcmoSignatureId", id);
            return (SignatureVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List signatureSearchResult(SignatureVO signature) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(SignatureVO.class);
            if (!(signature.getSignatureName().isEmpty())) {
                crit.add(Restrictions.like("signatureName", signature.getSignatureName(), MatchMode.ANYWHERE));
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
    public List getEmployeeAllSignature(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from SignatureVO as sig left join fetch sig.empIdObj where sig.isActive=:IsActive and sig.empIdObj.employeeId=:HcmoEmployeeId");
            query.setInteger("HcmoEmployeeId", id);
            query.setInteger("IsActive", 1);
            signatureList = query.list();
            return signatureList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllSignatureForLoginEmp() {
        Map sessionObj = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) sessionObj.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from SignatureVO as signature where signature.isActive=:IsActive and signature.empIdObj.employeeId=:HcmoEmployeeId");
            q.setInteger("HcmoEmployeeId", oEmp.getEmployeeId());
            q.setInteger("IsActive", 1);
            signatureList = q.list();
            return signatureList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllSignatureForEmp(SignatureVO signature) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from SignatureVO as sig left join fetch sig.empIdObj where sig.hcmoSignatureId!=:hcmoSignatureId and sig.isActive=:IsActive and sig.empIdObj.employeeId=:HcmoEmployeeId");
            query.setInteger("hcmoSignatureId", signature.getHcmoSignatureId());
            query.setInteger("HcmoEmployeeId", signature.getEmpIdObj().getEmployeeId());
            query.setInteger("IsActive", 1);
            signatureList = query.list();
            return signatureList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertSignature(SignatureVO signature) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(signature);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
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
    public void updateSignature(SignatureVO signature) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update SignatureVO set HcmoEmployeeId=:HcmoEmployeeId, "
                + "Signature=:Signature, " + "PreferedSignature=:PreferedSignature, "
                + "UpdatedBy=:UpdatedBy " + "where HcmoSignatureId=:HcmoSignatureId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoEmployeeId", signature.getEmpIdObj().getEmployeeId());
            query.setString("Signature", signature.getSignatureName());
            query.setBoolean("PreferedSignature", signature.isPreSignature());
            query.setInteger("UpdatedBy", signature.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoSignatureId", signature.getHcmoSignatureId());
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
    public void deleteSignature(SignatureVO signature) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update SignatureVO set preSignature=:preSignature,updatedBy=:UpdatedBy,isActive=:IsActive where hcmoSignatureId=:hcmoSignatureId";
            Query query = session.createQuery(sHql);
            query.setBoolean("preSignature", signature.isPreSignature());
            query.setInteger("UpdatedBy", signature.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("hcmoSignatureId", signature.getHcmoSignatureId());
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
