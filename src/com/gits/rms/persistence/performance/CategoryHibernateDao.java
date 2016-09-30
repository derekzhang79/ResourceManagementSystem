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
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.JobTitleVO;
import com.gits.rms.vo.QuestionVO;

public class CategoryHibernateDao implements CategoryDao{

    @Override
    public List getAllCategory() {
        Session session=HibernateUtil.getSession();
        try{
            Criteria crit=session.createCriteria(CategoryVO.class);
            crit.addOrder(Order.asc("categoryName"));
            crit.add(Restrictions.eq("isActive",1));
            return crit.list();
        }finally{
            session.flush();
            session.close();          
        }
    }

    @Override
    public CategoryVO getCategory(Integer id) {
        Session session=HibernateUtil.getSession();
        try{
            Query q=session.createQuery("from CategoryVO as c where c.hcmoCategoryId=:HcmoCategoryId");
            q.setInteger("HcmoCategoryId", id);
            return (CategoryVO) q.uniqueResult();
        }finally{
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertCategory(CategoryVO category) {
        Boolean isUnique=false;
        Session session=HibernateUtil.getSession();
        Transaction tx=null;
        try{
            tx=session.beginTransaction();
            session.save(category);
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
    public void updateCategory(CategoryVO category) {
        Session session=HibernateUtil.getSession();
        Transaction tx=null;
        try{
            tx=session.beginTransaction();
            String sHql="update CategoryVO set CategoryName=:CategoryName,UpdatedBy=:UpdatedBy where HcmoCategoryId=:HcmoCategoryId";
            Query q=session.createQuery(sHql);
            q.setString("CategoryName", category.getCategoryName());
            q.setInteger("UpdatedBy", category.getUpdatedBy().getEmployeeId());
            q.setInteger("HcmoCategoryId", category.getHcmoCategoryId());
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
    public void deleteCategory(CategoryVO category) {
        Session session=HibernateUtil.getSession();
        Transaction tx=null;
        try{
         tx=session.beginTransaction();
         String sHql="update CategoryVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoCategoryId=:HcmoCategoryId";
         Query q=session.createQuery(sHql);
         q.setInteger("UpdatedBy", category.getUpdatedBy().getEmployeeId());
         q.setInteger("IsActive", 0);
         q.setInteger("HcmoCategoryId", category.getHcmoCategoryId());
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
    public List categorySearchResult(CategoryVO category) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(CategoryVO.class);
            if (!(category.getCategoryName().isEmpty())) {
                crit.add(Restrictions.like("categoryName", category.getCategoryName(), MatchMode.ANYWHERE));
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
    public List checkCategoryInQuestion(CategoryVO category){
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(QuestionVO.class);
            crit.add(Restrictions.eq("hcmoCategoryId.hcmoCategoryId", category.getHcmoCategoryId()));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
}
