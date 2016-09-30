
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.constants.Constants;
import com.gits.rms.vo.EducationVO;
import com.gits.rms.vo.WorkExperienceVO;

public class WorkExperienceHibernateDao implements WorkExperienceDao {

    private List<WorkExperienceVO> workexpList;

    @Override
    public List getAllWorkExperience() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(WorkExperienceVO.class);
            crit.addOrder(Order.asc("fromDate"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List getAllSubEmployeeWorkExperienceList(List<Integer> empReportToEmpId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(WorkExperienceVO.class);
            crit.add(Restrictions.in("empIdObj.employeeId", empReportToEmpId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
        	session.flush();
        	session.close();
        }
    }

    @Override
    public List workExpSearchResult(WorkExperienceVO workexp) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(WorkExperienceVO.class);
            if (workexp.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", workexp.getEmpIdObj().getEmployeeId()));
            }
            if (!(workexp.getEmployeerName().isEmpty())) {
                crit.add(Restrictions.like("employeerName", workexp.getEmployeerName(), MatchMode.ANYWHERE));
            }
            if (!(workexp.getEmpJobTitle().isEmpty())) {
                crit.add(Restrictions.like("empJobTitle", workexp.getEmpJobTitle(), MatchMode.ANYWHERE));
            }

            if (workexp.getFromDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((workexp.getFromDate() != null) && (workexp.getFromDateEnds() != null)) {
                    crit.add(Restrictions.ge("fromDate", workexp.getFromDate()));
                    workexp.getMessage().add("WorkExpFromDateMessage");
                } else if ((workexp.getFromDate() != null) && (workexp.getFromDateEnds() == null)) {
                    crit.add(Restrictions.ge("fromDate", workexp.getFromDate()));
                } else if ((workexp.getFromDateEnds() != null) && (workexp.getFromDate() == null)) {
                    crit.add(Restrictions.ge("fromDate", workexp.getFromDateEnds()));
                }
            } else if (workexp.getFromDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((workexp.getFromDate() != null) && (workexp.getFromDateEnds() != null)) {
                    crit.add(Restrictions.le("fromDate", workexp.getFromDate()));
                    workexp.getMessage().add("WorkExpFromDateMessage");
                } else if ((workexp.getFromDate() != null) && (workexp.getFromDateEnds() == null)) {
                    crit.add(Restrictions.le("fromDate", workexp.getFromDate()));
                } else if ((workexp.getFromDateEnds() != null) && (workexp.getFromDate() == null)) {
                    crit.add(Restrictions.le("fromDate", workexp.getFromDateEnds()));
                }
            } else if (workexp.getFromDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((workexp.getFromDate() != null) && (workexp.getFromDateEnds() != null)) {
                    crit.add(Restrictions.eq("fromDate", workexp.getFromDate()));
                    workexp.getMessage().add("WorkExpFromDateMessage");
                } else if ((workexp.getFromDate() != null) && (workexp.getFromDateEnds() == null)) {
                    crit.add(Restrictions.eq("fromDate", workexp.getFromDate()));
                } else if ((workexp.getFromDateEnds() != null) && (workexp.getFromDate() == null)) {
                    crit.add(Restrictions.eq("fromDate", workexp.getFromDateEnds()));
                }
            } else if (workexp.getFromDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((workexp.getFromDate() != null) && (workexp.getFromDateEnds() != null)) {
                    if (workexp.getFromDate().before(workexp.getFromDateEnds())) {
                        crit.add(Restrictions.between("fromDate", workexp.getFromDate(), workexp.getFromDateEnds()));
                    } else if (workexp.getFromDate().after(workexp.getFromDateEnds())) {
                        crit.add(Restrictions.between("fromDate", workexp.getFromDateEnds(), workexp.getFromDate()));
                    } else if (workexp.getFromDate().equals(workexp.getFromDateEnds())) {
                        crit.add(Restrictions.eq("fromDate", workexp.getFromDate()));
                    }
                } else if ((workexp.getFromDate() != null) && (workexp.getFromDateEnds() == null)) {
                    crit.add(Restrictions.eq("fromDate", workexp.getFromDate()));
                } else if ((workexp.getFromDateEnds() != null) && (workexp.getFromDate() == null)) {
                    crit.add(Restrictions.eq("fromDate", workexp.getFromDateEnds()));
                }
            }

            if (workexp.getToDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((workexp.getToDate() != null) && (workexp.getToDateEnds() != null)) {
                    crit.add(Restrictions.ge("toDate", workexp.getToDate()));
                    workexp.getMessage().add("WorkExpToDateMessage");
                } else if ((workexp.getToDate() != null) && (workexp.getToDateEnds() == null)) {
                    crit.add(Restrictions.ge("toDate", workexp.getToDate()));
                } else if ((workexp.getToDateEnds() != null) && (workexp.getToDate() == null)) {
                    crit.add(Restrictions.ge("toDate", workexp.getToDateEnds()));
                }
            } else if (workexp.getToDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((workexp.getToDate() != null) && (workexp.getToDateEnds() != null)) {
                    crit.add(Restrictions.le("toDate", workexp.getToDate()));
                    workexp.getMessage().add("WorkExpToDateMessage");
                } else if ((workexp.getToDate() != null) && (workexp.getToDateEnds() == null)) {
                    crit.add(Restrictions.le("toDate", workexp.getToDate()));
                } else if ((workexp.getToDateEnds() != null) && (workexp.getToDate() == null)) {
                    crit.add(Restrictions.le("toDate", workexp.getToDateEnds()));
                }
            } else if (workexp.getToDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((workexp.getToDate() != null) && (workexp.getToDateEnds() != null)) {
                    crit.add(Restrictions.eq("toDate", workexp.getToDate()));
                    workexp.getMessage().add("WorkExpToDateMessage");
                } else if ((workexp.getToDate() != null) && (workexp.getToDateEnds() == null)) {
                    crit.add(Restrictions.eq("toDate", workexp.getToDate()));
                } else if ((workexp.getToDateEnds() != null) && (workexp.getToDate() == null)) {
                    crit.add(Restrictions.eq("toDate", workexp.getToDateEnds()));
                }
            } else if (workexp.getToDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((workexp.getToDate() != null) && (workexp.getToDateEnds() != null)) {
                    if (workexp.getToDate().before(workexp.getToDateEnds())) {
                        crit.add(Restrictions.between("toDate", workexp.getToDate(), workexp.getToDateEnds()));
                    } else if (workexp.getToDate().after(workexp.getToDateEnds())) {
                        crit.add(Restrictions.between("toDate", workexp.getToDateEnds(), workexp.getToDate()));
                    } else if (workexp.getToDate().equals(workexp.getToDateEnds())) {
                        crit.add(Restrictions.eq("toDate", workexp.getToDate()));
                    }
                } else if ((workexp.getToDate() != null) && (workexp.getToDateEnds() == null)) {
                    crit.add(Restrictions.eq("toDate", workexp.getToDate()));
                } else if ((workexp.getToDateEnds() != null) && (workexp.getToDate() == null)) {
                    crit.add(Restrictions.eq("toDate", workexp.getToDateEnds()));
                }
            }

            if (!(workexp.getComments().isEmpty())) {
                crit.add(Restrictions.like("comments", workexp.getComments(), MatchMode.ANYWHERE));
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
    public List getEmployeeAllWorkExperience(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from WorkExperienceVO as w left join fetch w.empIdObj where w.isActive=:IsActive and w.empIdObj.employeeId=:HcmoEmployeeId");
            query.setInteger("HcmoEmployeeId", id);
            query.setInteger("IsActive", 1);
            workexpList = query.list();
            return workexpList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public WorkExperienceVO getWorkExperience(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from WorkExperienceVO as w left join fetch w.empIdObj where w.empWorkExpId =:EmpWorkExpId");
            q.setInteger("EmpWorkExpId", id);
            return (WorkExperienceVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public WorkExperienceVO getEmpWorkExperience(WorkExperienceVO workexp) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from WorkExperienceVO as w left join fetch w.empIdObj where w.empWorkExpId =:EmpWorkExpId and w.empIdObj.employeeId=:HcmoEmployeeId and w.isActive=:IsActive");
            q.setInteger("EmpWorkExpId", workexp.getEmpWorkExpId());
            q.setInteger("HcmoEmployeeId", workexp.getEmpIdObj().getEmployeeId());
            q.setInteger("IsActive", 1);
            return (WorkExperienceVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    public WorkExperienceVO getEmployeeWorkExperience(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from WorkExperienceVO as w left join fetch w.empIdObj where w.empWorkExpId =:EmpWorkExpId");
            q.setInteger("EmpWorkExpId", id);
            return (WorkExperienceVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertWorkExperience(WorkExperienceVO workexp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(workexp);
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
    public void updateWorkExperience(WorkExperienceVO workexp) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update WorkExperienceVO set HcmoEmployeeId=:HcmoEmployeeId, "
                + "EExpEmployer=:EExpEmployer, " + "EExpJobTit=:EExpJobTit, "
                + "EExpFromDate=:EExpFromDate, " + "EExpToDate=:EExpToDate, "
                + "EExpDescription=:EExpDescription" + "UpdatedBy=:UpdatedBy "
                + "where empWorkExpId=:EmpWorkExpId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoEmployeeId", workexp.getEmpIdObj().getEmployeeId());
            query.setString("EExpEmployer", workexp.getEmployeerName());
            query.setString("EExpJobTit", workexp.getEmpJobTitle());
            query.setDate("EExpFromDate", workexp.getFromDate());
            query.setDate("EExpToDate", workexp.getToDate());
            query.setString("EExpDescription", workexp.getComments());
            query.setInteger("UpdatedBy", workexp.getUpdatedBy().getEmployeeId());
            query.setInteger("EmpWorkExpId", workexp.getEmpWorkExpId());
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
    public void deleteWorkExperience(WorkExperienceVO workexp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update WorkExperienceVO set updatedBy=:UpdatedBy,isActive=:IsActive where empWorkExpId=:EmpWorkExpId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", workexp.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("EmpWorkExpId", workexp.getEmpWorkExpId());
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
