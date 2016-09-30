
package com.gits.rms.persistence;

import java.util.Iterator;
import java.util.LinkedList;
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
import com.gits.rms.constants.Constants;
import com.gits.rms.vo.EducationVO;
import com.gits.rms.vo.EmployeeReportToVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.EmployeeReportToVO;

public class EducationHibernateDao implements EducationDao {
    private List<EducationVO> eduList;

    @Override
    public void deleteEducation(EducationVO edu) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EducationVO set updatedBy=:UpdatedBy,isActive=:IsActive where empEducationId=:EmpEducationId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", edu.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("EmpEducationId", edu.getEmpEducationId());
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
    public List educationSearchResult(EducationVO edu) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EducationVO.class);
            if (edu.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", edu.getEmpIdObj().getEmployeeId()));
            }
            if (!(edu.getEduMajor().isEmpty())) {
                crit.add(Restrictions.like("eduMajor", edu.getEduMajor(), MatchMode.ANYWHERE));
            }
            if (edu.getEduYear() != null) {
                crit.add(Restrictions.eq("eduYear", edu.getEduYear()));
            }

            if (edu.getEduStartDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((edu.getEduStartDate() != null) && (edu.getEduStartDateEnds() != null)) {
                    crit.add(Restrictions.ge("eduStartDate", edu.getEduStartDate()));
                    edu.getMessage().add("EducationStartDateMessage");
                } else if ((edu.getEduStartDate() != null) && (edu.getEduStartDateEnds() == null)) {
                    crit.add(Restrictions.ge("eduStartDate", edu.getEduStartDate()));
                } else if ((edu.getEduStartDateEnds() != null) && (edu.getEduStartDate() == null)) {
                    crit.add(Restrictions.ge("eduStartDate", edu.getEduStartDateEnds()));
                }
            } else if (edu.getEduStartDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((edu.getEduStartDate() != null) && (edu.getEduStartDateEnds() != null)) {
                    crit.add(Restrictions.le("eduStartDate", edu.getEduStartDate()));
                    edu.getMessage().add("EducationStartDateMessage");
                } else if ((edu.getEduStartDate() != null) && (edu.getEduStartDateEnds() == null)) {
                    crit.add(Restrictions.le("eduStartDate", edu.getEduStartDate()));
                } else if ((edu.getEduStartDateEnds() != null) && (edu.getEduStartDate() == null)) {
                    crit.add(Restrictions.le("eduStartDate", edu.getEduStartDateEnds()));
                }
            } else if (edu.getEduStartDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((edu.getEduStartDate() != null) && (edu.getEduStartDateEnds() != null)) {
                    crit.add(Restrictions.eq("eduStartDate", edu.getEduStartDate()));
                    edu.getMessage().add("EducationStartDateMessage");
                } else if ((edu.getEduStartDate() != null) && (edu.getEduStartDateEnds() == null)) {
                    crit.add(Restrictions.eq("eduStartDate", edu.getEduStartDate()));
                } else if ((edu.getEduStartDateEnds() != null) && (edu.getEduStartDate() == null)) {
                    crit.add(Restrictions.eq("eduStartDate", edu.getEduStartDateEnds()));
                }
            } else if (edu.getEduStartDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((edu.getEduStartDate() != null) && (edu.getEduStartDateEnds() != null)) {
                    if (edu.getEduStartDate().before(edu.getEduStartDateEnds())) {
                        crit.add(Restrictions.between("eduStartDate", edu.getEduStartDate(), edu.getEduStartDateEnds()));
                    } else if (edu.getEduStartDate().after(edu.getEduStartDateEnds())) {
                        crit.add(Restrictions.between("eduStartDate", edu.getEduStartDateEnds(), edu.getEduStartDate()));
                    } else if (edu.getEduStartDate().equals(edu.getEduStartDateEnds())) {
                        crit.add(Restrictions.eq("eduStartDate", edu.getEduStartDate()));
                    }
                } else if ((edu.getEduStartDate() != null) && (edu.getEduStartDateEnds() == null)) {
                    crit.add(Restrictions.eq("eduStartDate", edu.getEduStartDate()));
                } else if ((edu.getEduStartDateEnds() != null) && (edu.getEduStartDate() == null)) {
                    crit.add(Restrictions.eq("eduStartDate", edu.getEduStartDateEnds()));
                }
            }

            if (edu.getEduEndDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((edu.getEduEndDate() != null) && (edu.getEduEndDateEnds() != null)) {
                    crit.add(Restrictions.ge("eduEndDate", edu.getEduEndDate()));
                    edu.getMessage().add("EducationEndDateMessage");
                } else if ((edu.getEduEndDate() != null) && (edu.getEduEndDateEnds() == null)) {
                    crit.add(Restrictions.ge("eduEndDate", edu.getEduEndDate()));
                } else if ((edu.getEduEndDateEnds() != null) && (edu.getEduEndDate() == null)) {
                    crit.add(Restrictions.ge("eduEndDate", edu.getEduEndDateEnds()));
                }
            } else if (edu.getEduEndDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((edu.getEduEndDate() != null) && (edu.getEduEndDateEnds() != null)) {
                    crit.add(Restrictions.le("eduEndDate", edu.getEduEndDate()));
                    edu.getMessage().add("EducationEndDateMessage");
                } else if ((edu.getEduEndDate() != null) && (edu.getEduEndDateEnds() == null)) {
                    crit.add(Restrictions.le("eduEndDate", edu.getEduEndDate()));
                } else if ((edu.getEduEndDateEnds() != null) && (edu.getEduEndDate() == null)) {
                    crit.add(Restrictions.le("eduEndDate", edu.getEduEndDateEnds()));
                }
            } else if (edu.getEduEndDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((edu.getEduEndDate() != null) && (edu.getEduEndDateEnds() != null)) {
                    crit.add(Restrictions.eq("eduEndDate", edu.getEduEndDate()));
                    edu.getMessage().add("EducationEndDateMessage");
                } else if ((edu.getEduEndDate() != null) && (edu.getEduEndDateEnds() == null)) {
                    crit.add(Restrictions.eq("eduEndDate", edu.getEduEndDate()));
                } else if ((edu.getEduEndDateEnds() != null) && (edu.getEduEndDate() == null)) {
                    crit.add(Restrictions.eq("eduEndDate", edu.getEduEndDateEnds()));
                }
            } else if (edu.getEduEndDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((edu.getEduEndDate() != null) && (edu.getEduEndDateEnds() != null)) {
                    if (edu.getEduEndDate().before(edu.getEduEndDateEnds())) {
                        crit.add(Restrictions.between("eduEndDate", edu.getEduEndDate(), edu.getEduEndDateEnds()));
                    } else if (edu.getEduEndDate().after(edu.getEduEndDateEnds())) {
                        crit.add(Restrictions.between("eduEndDate", edu.getEduEndDateEnds(), edu.getEduEndDate()));
                    } else if (edu.getEduEndDate().equals(edu.getEduEndDateEnds())) {
                        crit.add(Restrictions.eq("eduEndDate", edu.getEduEndDate()));
                    }
                } else if ((edu.getEduEndDate() != null) && (edu.getEduEndDateEnds() == null)) {
                    crit.add(Restrictions.eq("eduEndDate", edu.getEduEndDate()));
                } else if ((edu.getEduEndDateEnds() != null) && (edu.getEduEndDate() == null)) {
                    crit.add(Restrictions.eq("eduEndDate", edu.getEduEndDateEnds()));
                }
            }

            if (!(edu.getEduUniversity().isEmpty())) {
                crit.add(Restrictions.like("eduUniversity", edu.getEduUniversity(), MatchMode.ANYWHERE));
            }
            if (!(edu.getEduDegree().isEmpty())) {
                crit.add(Restrictions.like("eduDegree", edu.getEduDegree(), MatchMode.ANYWHERE));
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
    public List getAllEducation() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EducationVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllSubEmployeeEducationList(List<Integer> empReportToEmpId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EducationVO.class);
            crit.add(Restrictions.in("empIdObj.employeeId", empReportToEmpId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
        	session.flush();
        	session.close();
        }
    }

    
    @Override
    public EducationVO getEducation(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EducationVO as edu left join fetch edu.empIdObj where edu.empEducationId =:EmpEducationId");
            q.setInteger("EmpEducationId", id);
            return (EducationVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EducationVO getEmpEducation(EducationVO edu) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EducationVO as edu left join fetch edu.empIdObj where edu.empEducationId =:EmpEducationId and edu.empIdObj.employeeId=:HcmoEmployeeId and edu.isActive=:IsActive");
            q.setInteger("EmpEducationId", edu.getEmpEducationId());
            q.setInteger("HcmoEmployeeId", edu.getEmpIdObj().getEmployeeId());
            q.setInteger("IsActive", 1);
            return (EducationVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getEmployeeAllEducation(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EducationVO as edu left join fetch edu.empIdObj where edu.isActive=:IsActive and edu.empIdObj.employeeId=:HcmoEmployeeId");
            query.setInteger("HcmoEmployeeId", id);
            query.setInteger("IsActive", 1);
            eduList = query.list();
            return eduList;
        } finally {
            session.flush();
            session.close();
        }
    }

    public EducationVO getEmployeeEducation(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EducationVO as edu left join fetch edu.empIdObj where edu.empEducationId =:EmpEducationId");
            q.setInteger("EmpEducationId", id);
            return (EducationVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertEducation(EducationVO edu) {
        Boolean isUnique = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(edu);
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
    public void updateEducation(EducationVO edu) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EducationVO set EduMajor=:EduMajor, "
                + "HcmoEmployeeId=:HcmoEmployeeId, " + "EduYear=:EduYear, " + "EduGpa=:EduGpa, "
                + "EduStartDate=:EduStartDate, " + "EduEndDate=:EduEndDate, "
                + "EduDeg=:EduDeg, " + "UpdatedBy=:UpdatedBy "
                + "where empEducationId=:EmpEducationId";
            Query query = session.createQuery(sHql);
            query.setString("EduMajor", edu.getEduMajor());
            query.setInteger("HcmoEmployeeId", edu.getEmpIdObj().getEmployeeId());
            query.setInteger("EduYear", edu.getEduYear());
            query.setString("EduGpa", edu.getEduGrade());
            query.setDate("EduStartDate", edu.getEduStartDate());
            query.setDate("EduEndDate", edu.getEduEndDate());
            //query.setString("EduUni", edu.getEduUniversity());
            query.setString("EduDeg", edu.getEduDegree());
            query.setInteger("UpdatedBy", edu.getUpdatedBy().getEmployeeId());
            query.setInteger("EmpEducationId", edu.getEmpEducationId());
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
