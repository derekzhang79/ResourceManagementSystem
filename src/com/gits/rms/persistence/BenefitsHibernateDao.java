
package com.gits.rms.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.BenefitsVO;
import com.gits.rms.vo.EducationVO;
import com.gits.rms.vo.EmployeesVO;

public class BenefitsHibernateDao implements BenefitsDao {

    private List<BenefitsVO> benefitList;

    @Override
    public void deleteBenefit(BenefitsVO benefit) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update BenefitsVO set updatedBy=:UpdatedBy, isActive=:IsActive where HcmoBenefitsId=:HcmoBenefitsId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", benefit.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoBenefitsId", benefit.getHcmoBenefitsId());
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
    public List getAllBenefit() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(BenefitsVO.class);
            crit.addOrder(Order.asc("year"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List getAllSubEmployeeBenefitList(List<Integer> empReportToEmpId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(BenefitsVO.class);
            crit.add(Restrictions.in("empId", empReportToEmpId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
        	session.flush();
        	session.close();
        }
    }

    @Override
    public List getAllBenefitYear() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from BenefitsVO as b where b.isActive=:IsActive");
            query.setInteger("IsActive", 1);
            benefitList = query.list();

            Set<Integer> setYears = new LinkedHashSet<Integer>();
            Set<BenefitsVO> setVO = new LinkedHashSet<BenefitsVO>();

            for (BenefitsVO b : benefitList) {
                if (setYears.add(b.getYear())) {
                    setVO.add(b);
                }
            }
            benefitList.clear();
            benefitList.addAll(setVO);
            return benefitList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllBenefitYearSearchResult(BenefitsVO benefit) {
        EmployeesVO newEmpBenefit = null;
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(BenefitsVO.class);
            if (benefit.getYear() != null) {
                crit.add(Restrictions.eq("year", benefit.getYear()));
                crit.add(Restrictions.eq("isActive", 1));
                benefitList = crit.list();
                return benefitList;
            }
            if (benefit.getEmpIdObjList() != null) {
                for (Iterator<EmployeesVO> it = benefit.getEmpIdObjList().iterator(); it.hasNext();) {
                    newEmpBenefit = it.next();
                    newEmpBenefit.getEmployeeId();
                    crit.add(Restrictions.like("empId", newEmpBenefit.getEmployeeId().toString(), MatchMode.ANYWHERE));
                    crit.add(Restrictions.eq("isActive", 1));
                    benefitList = crit.list();
                    return benefitList;
                }
            }
            crit.add(Restrictions.eq("isActive", 1));
            benefitList = crit.list();
            return benefitList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public BenefitsVO getBenefit(Integer id) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            Query q = session.createQuery("from BenefitsVO as b where b.hcmoBenefitsId =:hcmoBenefitsId");
            q.setInteger("hcmoBenefitsId", id);
            return (BenefitsVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getEmployeeBenefit(Integer id) {
        BenefitsVO newBenefitObj = null;
        String EmpIdList = "";
        int l;
        HashSet finalEmpIdHashSet = new HashSet();
        ArrayList finalEmpIdList = new ArrayList();
        List dummyList = new ArrayList();
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            benefitList = getAllBenefit();
            for (Iterator<BenefitsVO> it = benefitList.iterator(); it.hasNext();) {
                newBenefitObj = it.next();
                EmpIdList = newBenefitObj.getEmpId();
                if (EmpIdList.contains(",")) {
                    String[] employeeIdArray = EmpIdList.split(",");
                    l = employeeIdArray.length;
                    for (int j = 0; j < l; j++) {
                        String empIdSingleList = employeeIdArray[j];
                        if (empIdSingleList.equals(String.valueOf(id))) {
                            finalEmpIdHashSet.add(newBenefitObj.getHcmoBenefitsId());
                        }
                    }
                } else {
                    if (EmpIdList.equals(String.valueOf(id))) {
                        finalEmpIdHashSet.add(newBenefitObj.getHcmoBenefitsId());
                    }
                }

            }
            finalEmpIdList.addAll(finalEmpIdHashSet);
            Criteria crit = session.createCriteria(BenefitsVO.class);
            if (!finalEmpIdHashSet.isEmpty()) {
                crit.add(Restrictions.in("hcmoBenefitsId", finalEmpIdHashSet));
                crit.add(Restrictions.eq("isActive", 1));
                benefitList = crit.list();
                return benefitList;
            } else {
                return dummyList;
            }

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EmployeesVO getEmployeeName(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmployeesVO as emp left join fetch emp.teamIdObj left join fetch emp.departmentIdObj left join fetch emp.country left join fetch emp.ethnicRaceIdObj ehtnic left join fetch emp.nationalityIdObj nati left join fetch emp.jobTitleIdObj job left join fetch emp.roleObj as role left join fetch emp.empStatusIdObj as empstat where emp.employeeId =:EmployeeId and emp.isActive =:IsActive");
            q.setInteger("EmployeeId", id);
            q.setInteger("IsActive", 1);
            return (EmployeesVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertBenefit(BenefitsVO benefit) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(benefit);
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
    public void updateBenefit(BenefitsVO benefit) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update BenefitsVO set Year=:Year, " + "BenefitsName=:BenefitsName,"
                + "BenefitsAttachFileName=:BenefitsAttachFileName," + "UpdatedBy=:UpdatedBy"
                + "where HcmoBenefitsId=:HcmoBenefitsId";
            Query query = session.createQuery(sHql);
            query.setInteger("Year", benefit.getYear());
            query.setString("BenefitsName", benefit.getBenefitsName());
            query.setInteger("UpdatedBy", benefit.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoBenefitsId", benefit.getHcmoBenefitsId());
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
