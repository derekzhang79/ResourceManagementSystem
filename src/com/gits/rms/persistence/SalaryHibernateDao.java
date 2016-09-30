
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.constants.Constants;
import com.gits.rms.vo.SalaryVO;

public class SalaryHibernateDao implements SalaryDao {

    private List<SalaryVO> salList;

    @Override
    public List getAllSalary() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(SalaryVO.class);
            crit.addOrder(Order.asc("salary"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List salarySearchResult(SalaryVO sal) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(SalaryVO.class);
            if (sal.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", sal.getEmpIdObj().getEmployeeId()));
            }
            if (sal.getSalary() != null) {
                crit.add(Restrictions.eq("salary", sal.getSalary()));
            }
            if (sal.getDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((sal.getDeclarationDate() != null) && (sal.getDeclarationEndDate() != null)) {
                    crit.add(Restrictions.ge("declarationDate", sal.getDeclarationDate()));
                    sal.getMessage().add("Message");
                } else if ((sal.getDeclarationDate() != null)
                    && (sal.getDeclarationEndDate() == null)) {
                    crit.add(Restrictions.ge("declarationDate", sal.getDeclarationDate()));
                } else if ((sal.getDeclarationEndDate() != null)
                    && (sal.getDeclarationDate() == null)) {
                    crit.add(Restrictions.ge("declarationDate", sal.getDeclarationEndDate()));
                }
            } else if (sal.getDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((sal.getDeclarationDate() != null) && (sal.getDeclarationEndDate() != null)) {
                    crit.add(Restrictions.le("declarationDate", sal.getDeclarationDate()));
                    sal.getMessage().add("Message");
                } else if ((sal.getDeclarationDate() != null)
                    && (sal.getDeclarationEndDate() == null)) {
                    crit.add(Restrictions.le("declarationDate", sal.getDeclarationDate()));
                } else if ((sal.getDeclarationEndDate() != null)
                    && (sal.getDeclarationDate() == null)) {
                    crit.add(Restrictions.le("declarationDate", sal.getDeclarationEndDate()));
                }
            } else if (sal.getDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((sal.getDeclarationDate() != null) && (sal.getDeclarationEndDate() != null)) {
                    crit.add(Restrictions.eq("declarationDate", sal.getDeclarationDate()));
                    sal.getMessage().add("Message");
                } else if ((sal.getDeclarationDate() != null)
                    && (sal.getDeclarationEndDate() == null)) {
                    crit.add(Restrictions.eq("declarationDate", sal.getDeclarationDate()));
                } else if ((sal.getDeclarationEndDate() != null)
                    && (sal.getDeclarationDate() == null)) {
                    crit.add(Restrictions.eq("declarationDate", sal.getDeclarationEndDate()));
                }
            } else if (sal.getDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((sal.getDeclarationDate() != null) && (sal.getDeclarationEndDate() != null)) {
                    if (sal.getDeclarationDate().before(sal.getDeclarationEndDate())) {
                        crit.add(Restrictions.between("declarationDate", sal.getDeclarationDate(), sal.getDeclarationEndDate()));
                    } else if (sal.getDeclarationDate().after(sal.getDeclarationEndDate())) {
                        crit.add(Restrictions.between("declarationDate", sal.getDeclarationEndDate(), sal.getDeclarationDate()));
                    } else if (sal.getDeclarationDate().equals(sal.getDeclarationEndDate())) {
                        crit.add(Restrictions.eq("declarationDate", sal.getDeclarationDate()));
                    }
                } else if ((sal.getDeclarationDate() != null)
                    && (sal.getDeclarationEndDate() == null)) {
                    crit.add(Restrictions.eq("declarationDate", sal.getDeclarationDate()));
                } else if ((sal.getDeclarationEndDate() != null)
                    && (sal.getDeclarationDate() == null)) {
                    crit.add(Restrictions.eq("declarationDate", sal.getDeclarationEndDate()));
                }
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
    public List getEmployeeAllSalary(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from SalaryVO  as s left join fetch s.empIdObj where s.isActive=:IsActive and s.empIdObj.employeeId=:HcmoEmployeeId");
            query.setInteger("HcmoEmployeeId", id);
            query.setInteger("IsActive", 1);
            salList = query.list();
            return salList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public SalaryVO getSalary(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from SalaryVO as s left join fetch s.empIdObj where s.hcmosalaryId =:HcmoSalaryId");
            q.setInteger("HcmoSalaryId", id);
            return (SalaryVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public SalaryVO getEmpSalary(SalaryVO sal) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from SalaryVO as s left join fetch s.empIdObj where s.hcmosalaryId =:HcmoSalaryId and s.empIdObj.employeeId=:HcmoEmployeeId and s.isActive=:IsActive");
            q.setInteger("HcmoSalaryId", sal.getHcmosalaryId());
            q.setInteger("HcmoEmployeeId", sal.getEmpIdObj().getEmployeeId());
            q.setInteger("IsActive", 1);
            return (SalaryVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertSalary(SalaryVO sal) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(sal);
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
    public void updateSalary(SalaryVO sal) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update SalaryVO set HcmoEmployeeId=:HcmoEmployeeId, "
                + "DeclarationDate=:DeclarationDate, " + "Salary=:Salary, "
                + "UpdatedBy=:UpdatedBy " + "where HcmoSalaryId=:HcmoSalaryId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoEmployeeId", sal.getEmpIdObj().getEmployeeId());
            query.setDate("DeclarationDate", sal.getDeclarationDate());
            query.setDouble("Salary", sal.getSalary());
            query.setInteger("UpdatedBy", sal.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoSalaryId", sal.getHcmosalaryId());
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
    public void deleteSalary(SalaryVO sal) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update SalaryVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmosalaryId=:HcmosalaryId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", sal.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmosalaryId", sal.getHcmosalaryId());
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
