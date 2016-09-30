package com.gits.rms.persistence.performance;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.persistence.HibernateUtil;
import com.gits.rms.vo.CategoryVO;
import com.gits.rms.vo.QuestionVO;
import com.gits.rms.vo.SubCategoryVO;

public class SubCategoryHibernateDao implements SubCategoryDao{
    @Override
    public List getAllSubCategory() {
        Session session=HibernateUtil.getSession();
        try{
            Criteria crit=session.createCriteria(SubCategoryVO.class);
            crit.addOrder(Order.asc("subCategoryName"));
            crit.add(Restrictions.eq("isActive",1));
            return crit.list();
        }finally{
            session.flush();
            session.close();          
        }
    }
    
    @Override
    public SubCategoryVO getSubCategory(Integer id) {
        Session session=HibernateUtil.getSession();
        try{
            Query q=session.createQuery("from SubCategoryVO as s where s.hcmoSubCategoryId=:HcmoSubCategoryId");
            q.setInteger("HcmoSubCategoryId", id);
            return (SubCategoryVO) q.uniqueResult();
        }finally{
            session.flush();
            session.close();
        }
    }
    
    @Override
    public void insertSubCategory(SubCategoryVO subCategory) {
        Boolean isUnique=false;
        Session session=HibernateUtil.getSession();
        Transaction tx=null;
        try{
            tx=session.beginTransaction();
            session.save(subCategory);
            tx.commit();
        }catch(RuntimeException e){
         if(tx!=null){
             tx.rollback();
         }
         isUnique=true;
         e.printStackTrace();
         throw e;
         }finally{
             if(isUnique){
                 session.close();
             }else{
                 session.flush();
                 session.close();
             }
        }
    }
    
    @Override
    public void updateSubCategory(SubCategoryVO subCategory) {
        Session session=HibernateUtil.getSession();
        Transaction tx=null;
        try{
            tx=session.beginTransaction();
            String sHql="update SubCategoryVO set SubCategoryName=:SubCategoryName,HcmoCategoryId=:HcmoCategoryId,UpdatedBy=:UpdatedBy where HcmoSubCategoryId=:HcmoSubCategoryId";
            Query q=session.createQuery(sHql);
            q.setString("SubCategoryName", subCategory.getSubCategoryName());
            q.setInteger("UpdatedBy", subCategory.getUpdatedBy().getEmployeeId());
            q.setInteger("HcmoCategoryId", subCategory.getHcmoCategoryId().getHcmoCategoryId());
            q.setInteger("HcmoSubCategoryId", subCategory.getHcmoSubCategoryId());
            q.executeUpdate();
            tx.commit();
        }catch(RuntimeException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        }finally{
            session.flush();
            session.close();
        }
    }

    @Override
    public void deleteSubCategory(SubCategoryVO subCategory) {
        Session session=HibernateUtil.getSession();
        Transaction tx=null;
        try{
         tx=session.beginTransaction();
         String sHql="update SubCategoryVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoSubCategoryId=:HcmoSubCategoryId";
         Query q=session.createQuery(sHql);
         q.setInteger("UpdatedBy", subCategory.getUpdatedBy().getEmployeeId());
         q.setInteger("IsActive", 0);
         q.setInteger("HcmoSubCategoryId", subCategory.getHcmoSubCategoryId());
         q.executeUpdate();
         tx.commit();
        }catch(RuntimeException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        }finally{
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List subCategorySearchResult(SubCategoryVO subCategory){
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(SubCategoryVO.class);
            if (!(subCategory.getSubCategoryName().isEmpty())) {
                crit.add(Restrictions.like("subCategoryName", subCategory.getSubCategoryName(), MatchMode.ANYWHERE));
            }
            if (subCategory.getHcmoCategoryId().getHcmoCategoryId() != null) {
                crit.add(Restrictions.eq("hcmoCategoryId.hcmoCategoryId", subCategory.getHcmoCategoryId().getHcmoCategoryId()));
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
    public List subCategoryForCategory(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from SubCategoryVO where hcmoCategoryId=:HcmoCategoryId and isActive=:isActive");
            query.setInteger("HcmoCategoryId", id);
            query.setInteger("isActive", 1);
            return query.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List checkSubCategoryInQuestion(SubCategoryVO subCategory){
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(QuestionVO.class);
            crit.add(Restrictions.eq("hcmoSubCategoryId.hcmoSubCategoryId", subCategory.getHcmoSubCategoryId()));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
}
