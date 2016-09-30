
package com.gits.rms.persistence;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.constants.Constants;
import com.gits.rms.vo.EmpSpaceVO;
import com.gits.rms.vo.EmployeesVO;

public class EmpSpaceHibernateDao implements EmpSpaceDao {

    private List<EmpSpaceVO> empSpaceList;
    private List<EmpSpaceVO> empSpaceListForSharedAndUploaded;

    @Override
    public void deleteEmpSpace(EmpSpaceVO empSpace) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmpSpaceVO set updatedBy=:UpdatedBy, isActive=:IsActive where hcmoEmpSpaceId=:HcmoEmpSpaceId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", empSpace.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoEmpSpaceId", empSpace.getHcmoEmpSpaceId());
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
    public List empspaceSearchResult(EmpSpaceVO empSpace) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            Criteria crit = session.createCriteria(EmpSpaceVO.class);
            if (!(empSpace.getSharedFileTitle().isEmpty())) {
                crit.add(Restrictions.like("sharedFileTitle", empSpace.getSharedFileTitle(), MatchMode.ANYWHERE));
            }
            if (!(empSpace.getSharedFileDesc().isEmpty())) {
                crit.add(Restrictions.like("sharedFileDesc", empSpace.getSharedFileDesc(), MatchMode.ANYWHERE));
            }
            if (!(empSpace.getType().isEmpty())) {
                if (empSpace.getType().equals(Constants.EMPLOYEE_SPACE_MYFILE)) {
                    crit.add(Restrictions.eq("empIdObj.employeeId", oEmp.getEmployeeId()));
                } else if (empSpace.getType().equals(Constants.EMPLOYEE_SPACE_SHAREDBYME)) {
                    crit.add(Restrictions.eq("empIdObj.employeeId", oEmp.getEmployeeId()));
                    crit.add(Restrictions.sqlRestriction("sharedEmpIds not like '%"
                        + oEmp.getEmployeeId().toString() + "%'"));
                } else if (empSpace.getType().equals(Constants.EMPLOYEE_SPACE_SHAREDTOME)) {
                    crit.add(Restrictions.like("sharedEmpIds", oEmp.getEmployeeId().toString(), MatchMode.ANYWHERE));
                }
            }
            if ((empSpace.getSharedFileTitle().isEmpty())
                && (empSpace.getSharedFileDesc().isEmpty()) && (empSpace.getType().isEmpty())) {
                crit.add(Restrictions.or(Restrictions.eq("empIdObj.employeeId", oEmp.getEmployeeId()), Restrictions.like("sharedEmpIds", oEmp.getEmployeeId().toString(), MatchMode.ANYWHERE)));
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
    public List getAllEmpSpace(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmpSpaceVO as space left join fetch space.empIdObj as emp where space.isActive=:IsActive and space.empIdObj=:EmpIdObj");
            query.setInteger("IsActive", 1);
            query.setInteger("EmpIdObj", id);
            empSpaceList = query.list();
            return empSpaceList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EmpSpaceVO getAllEmpSpaceList(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmpSpaceVO as space where space.hcmoEmpSpaceId =:hcmoEmpSpaceId and space.isActive=:IsActive");
            q.setInteger("hcmoEmpSpaceId", id);
            q.setInteger("IsActive", 1);
            return (EmpSpaceVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllSharedAndUploadedList() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmpSpaceVO as space where space.isActive=:IsActive");
            query.setInteger("IsActive", 1);
            empSpaceListForSharedAndUploaded = query.list();
            return empSpaceListForSharedAndUploaded;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllSharedEmpSpace(String eMail) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            String SharedEmpEmailId = eMail;
            Query query = session.createQuery("from EmpSpaceVO as space left join fetch space.empIdObj as emp where space.isActive=:IsActive and space.sharedEmpEmailId like '%"
                + SharedEmpEmailId + "%'");
            query.setInteger("IsActive", 1);
            empSpaceList = query.list();
            return empSpaceList;
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
    public EmpSpaceVO getEmpSpace(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmpSpaceVO as space left join fetch space.empIdObj as emp where space.hcmoEmpSpaceId =:hcmoEmpSpaceId and space.isActive=:IsActive");
            q.setInteger("hcmoEmpSpaceId", id);
            q.setInteger("IsActive", 1);
            return (EmpSpaceVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getEmpSpaceSharedId(String empId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmpSpaceVO as space left join fetch space.empIdObj as emp where space.sharedEmpIds =:SharedEmpIds and space.isActive=:IsActive");
            q.setString("SharedEmpIds", empId);
            q.setInteger("IsActive", 1);
            empSpaceList = q.list();
            return empSpaceList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertEmpSpace(EmpSpaceVO empSpace) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(empSpace);
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
    public void makeShared(EmpSpaceVO empSpace) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmpSpaceVO set sharedEmpIds=:SharedEmpIds,"
                + "sharedEmpEmailId=:SharedEmpEmailId," + "sharedEmpFirstName=:SharedEmpFirstName,"
                + "updatedBy=:UpdatedBy " + "where hcmoEmpSpaceId=:HcmoEmpSpaceId";
            Query query = session.createQuery(sHql);
            query.setString("SharedEmpIds", empSpace.getSharedEmpIds());
            query.setString("SharedEmpEmailId", empSpace.getSharedEmpEmailId());
            query.setString("SharedEmpFirstName", empSpace.getSharedEmpFirstName());
            query.setInteger("UpdatedBy", empSpace.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoEmpSpaceId", empSpace.getHcmoEmpSpaceId());
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
    public void makeUnshare(EmpSpaceVO empSpace) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmpSpaceVO set sharedEmpIds=:SharedEmpIds,"
                + "sharedEmpEmailId=:SharedEmpEmailId," + "sharedEmpFirstName=:SharedEmpFirstName,"
                + "updatedBy=:UpdatedBy " + "where hcmoEmpSpaceId=:HcmoEmpSpaceId";
            Query query = session.createQuery(sHql);
            query.setString("SharedEmpIds", null);
            query.setString("SharedEmpEmailId", null);
            query.setString("SharedEmpFirstName", null);
            query.setInteger("UpdatedBy", empSpace.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoEmpSpaceId", empSpace.getHcmoEmpSpaceId());
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
    public void updateEmpSpace(EmpSpaceVO empSpace) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
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
