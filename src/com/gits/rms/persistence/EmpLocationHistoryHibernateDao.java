
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.constants.Constants;
import com.gits.rms.vo.EducationVO;
import com.gits.rms.vo.EmpLocationHistoryVO;
import com.gits.rms.vo.ProjectVO;

public class EmpLocationHistoryHibernateDao implements EmpLocationHistoryDao {

    @Override
    public void deleteEmpLocationHistory(EmpLocationHistoryVO elhist) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmpLocationHistoryVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoEmpLocHistoryId=:hcmoEmpLocHistoryId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", elhist.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("hcmoEmpLocHistoryId", elhist.getHcmoEmpLocHistoryId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List empLocHistSearchResult(EmpLocationHistoryVO elhist) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmpLocationHistoryVO.class);
            if (elhist.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", elhist.getEmpIdObj().getEmployeeId()));
            }
            if (elhist.getLocationIdObj().getHcmolocationId() != null) {
                crit.add(Restrictions.eq("locationIdObj.hcmolocationId", elhist.getLocationIdObj().getHcmolocationId()));
            }
            if (elhist.getClientIdObj().getHcmoclientId() != null) {
                crit.add(Restrictions.eq("clientIdObj.hcmoclientId", elhist.getClientIdObj().getHcmoclientId()));
            }

            if (elhist.getStartDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((elhist.getStartDate() != null) && (elhist.getStartDateEnds() != null)) {
                    crit.add(Restrictions.ge("startDate", elhist.getStartDate()));
                    elhist.getMessage().add("LocHistStartDateMessage");
                } else if ((elhist.getStartDate() != null) && (elhist.getStartDateEnds() == null)) {
                    crit.add(Restrictions.ge("startDate", elhist.getStartDate()));
                } else if ((elhist.getStartDateEnds() != null) && (elhist.getStartDate() == null)) {
                    crit.add(Restrictions.ge("startDate", elhist.getStartDateEnds()));
                }
            } else if (elhist.getStartDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((elhist.getStartDate() != null) && (elhist.getStartDateEnds() != null)) {
                    crit.add(Restrictions.le("startDate", elhist.getStartDate()));
                    elhist.getMessage().add("LocHistStartDateMessage");
                } else if ((elhist.getStartDate() != null) && (elhist.getStartDateEnds() == null)) {
                    crit.add(Restrictions.le("startDate", elhist.getStartDate()));
                } else if ((elhist.getStartDateEnds() != null) && (elhist.getStartDate() == null)) {
                    crit.add(Restrictions.le("startDate", elhist.getStartDateEnds()));
                }
            } else if (elhist.getStartDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((elhist.getStartDate() != null) && (elhist.getStartDateEnds() != null)) {
                    crit.add(Restrictions.eq("startDate", elhist.getStartDate()));
                    elhist.getMessage().add("LocHistStartDateMessage");
                } else if ((elhist.getStartDate() != null) && (elhist.getStartDateEnds() == null)) {
                    crit.add(Restrictions.eq("startDate", elhist.getStartDate()));
                } else if ((elhist.getStartDateEnds() != null) && (elhist.getStartDate() == null)) {
                    crit.add(Restrictions.eq("startDate", elhist.getStartDateEnds()));
                }
            } else if (elhist.getStartDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((elhist.getStartDate() != null) && (elhist.getStartDateEnds() != null)) {
                    if (elhist.getStartDate().before(elhist.getStartDateEnds())) {
                        crit.add(Restrictions.between("startDate", elhist.getStartDate(), elhist.getStartDateEnds()));
                    } else if (elhist.getStartDate().after(elhist.getStartDateEnds())) {
                        crit.add(Restrictions.between("startDate", elhist.getStartDateEnds(), elhist.getStartDate()));
                    } else if (elhist.getStartDate().equals(elhist.getStartDateEnds())) {
                        crit.add(Restrictions.eq("startDate", elhist.getStartDate()));
                    }
                } else if ((elhist.getStartDate() != null) && (elhist.getStartDateEnds() == null)) {
                    crit.add(Restrictions.eq("startDate", elhist.getStartDate()));
                } else if ((elhist.getStartDateEnds() != null) && (elhist.getStartDate() == null)) {
                    crit.add(Restrictions.eq("startDate", elhist.getStartDateEnds()));
                }
            }

            if (elhist.getEndDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((elhist.getEndDate() != null) && (elhist.getEndDateEnds() != null)) {
                    crit.add(Restrictions.ge("endDate", elhist.getEndDate()));
                    elhist.getMessage().add("LocHistEndDateMessage");
                } else if ((elhist.getEndDate() != null) && (elhist.getEndDateEnds() == null)) {
                    crit.add(Restrictions.ge("endDate", elhist.getEndDate()));
                } else if ((elhist.getEndDateEnds() != null) && (elhist.getEndDate() == null)) {
                    crit.add(Restrictions.ge("endDate", elhist.getEndDateEnds()));
                }
            } else if (elhist.getEndDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((elhist.getEndDate() != null) && (elhist.getEndDateEnds() != null)) {
                    crit.add(Restrictions.le("endDate", elhist.getEndDate()));
                    elhist.getMessage().add("LocHistEndDateMessage");
                } else if ((elhist.getEndDate() != null) && (elhist.getEndDateEnds() == null)) {
                    crit.add(Restrictions.le("endDate", elhist.getEndDate()));
                } else if ((elhist.getEndDateEnds() != null) && (elhist.getEndDate() == null)) {
                    crit.add(Restrictions.le("endDate", elhist.getEndDateEnds()));
                }
            } else if (elhist.getEndDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((elhist.getEndDate() != null) && (elhist.getEndDateEnds() != null)) {
                    crit.add(Restrictions.eq("endDate", elhist.getEndDate()));
                    elhist.getMessage().add("LocHistEndDateMessage");
                } else if ((elhist.getEndDate() != null) && (elhist.getEndDateEnds() == null)) {
                    crit.add(Restrictions.eq("endDate", elhist.getEndDate()));
                } else if ((elhist.getEndDateEnds() != null) && (elhist.getEndDate() == null)) {
                    crit.add(Restrictions.eq("endDate", elhist.getEndDateEnds()));
                }
            } else if (elhist.getEndDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((elhist.getEndDate() != null) && (elhist.getEndDateEnds() != null)) {
                    if (elhist.getEndDate().before(elhist.getEndDateEnds())) {
                        crit.add(Restrictions.between("endDate", elhist.getEndDate(), elhist.getEndDateEnds()));
                    } else if (elhist.getEndDate().after(elhist.getEndDateEnds())) {
                        crit.add(Restrictions.between("endDate", elhist.getEndDateEnds(), elhist.getEndDate()));
                    } else if (elhist.getEndDate().equals(elhist.getEndDateEnds())) {
                        crit.add(Restrictions.eq("endDate", elhist.getEndDate()));
                    }
                } else if ((elhist.getEndDate() != null) && (elhist.getEndDateEnds() == null)) {
                    crit.add(Restrictions.eq("endDate", elhist.getEndDate()));
                } else if ((elhist.getEndDateEnds() != null) && (elhist.getEndDate() == null)) {
                    crit.add(Restrictions.eq("endDate", elhist.getEndDateEnds()));
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
    public List getAllEmpLocationHistory() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmpLocationHistoryVO.class);
            crit.addOrder(Order.asc("startDate"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List getAllSubEmployeeLocationHistoryList(List<Integer> empReportToEmpId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmpLocationHistoryVO.class);
            crit.add(Restrictions.in("empIdObj.employeeId", empReportToEmpId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
        	session.flush();
        	session.close();
        }
    }

    @Override
    public EmpLocationHistoryVO getEmpLocationHistory(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmpLocationHistoryVO as lh  left join fetch lh.empIdObj as emp left join fetch lh.locationIdObj as loc left join fetch lh.clientIdObj as cli where lh.hcmoEmpLocHistoryId =:hcmoEmpLocHistoryId ");
            q.setInteger("hcmoEmpLocHistoryId", id);
            return (EmpLocationHistoryVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertEmpLocationHistory(EmpLocationHistoryVO elhist) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(elhist);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void updateEmpLocationHistory(EmpLocationHistoryVO elhist) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmpLocationHistoryVO set HcmoEmployeeId=:HcmoEmployeeId, "
                + "HcmoLocationId=:HcmoLocationId, " + "HcmoClientId=:HcmoClientId, "
                + "StartDate=:StartDate, " + "EndDate=:EndDate, " + "UpdatedBy=:UpdatedBy "
                + "where HcmoEmpLocHistoryId=:HcmoEmpLocHistoryId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoEmployeeId", elhist.getEmpIdObj().getEmployeeId());
            query.setInteger("HcmoLocationId", elhist.getLocationIdObj().getHcmolocationId());
            query.setInteger("HcmoClientId", elhist.getClientIdObj().getHcmoclientId());
            query.setDate("StartDate", elhist.getStartDate());
            query.setDate("EndDate", elhist.getEndDate());
            query.setInteger("UpdatedBy", elhist.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoEmpLocHistoryId", elhist.getHcmoEmpLocHistoryId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    public void updateProject(ProjectVO proj) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ProjectVO set " + "HcmoCustomerId=:HcmoCustomerId, "
                + "ProjectOwner=:ProjectOwner, " + "Name=:Name, " + "Description=:Description, "
                + "UpdatedBy=:UpdatedBy " + "where projectId=:ProjectId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoCustomerId", proj.getCustomerId().getCustomerId());
            query.setInteger("ProjectOwner", proj.getEmpIdObj().getEmployeeId());
            query.setString("Name", proj.getProjectName());
            query.setString("Description", proj.getProjectDesc());
            query.setInteger("UpdatedBy", proj.getUpdatedBy().getEmployeeId());
            query.setInteger("ProjectId", proj.getProjectId());
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
