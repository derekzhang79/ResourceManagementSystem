
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
import com.gits.rms.vo.EmpPassportVO;

public class EmpPassportHibernateDao implements EmpPassportDao {

    @Override
    public void deleteEmpPassport(EmpPassportVO empPass) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmpPassportVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoEmpPassportId=:HcmoEmpPassportId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", empPass.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoEmpPassportId", empPass.getHcmoEmpPassportId());
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
    public List getAllEmpPassport() {
        Session session = HibernateUtil.getSession();
        try {
            final Criteria crit = session.createCriteria(EmpPassportVO.class);
            crit.addOrder(Order.asc("epPassportNo"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List getAllSubEmployeePasportList(List<Integer> empReportToEmpId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmpPassportVO.class);
            crit.add(Restrictions.in("empIdObj.employeeId", empReportToEmpId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
        	session.flush();
        	session.close();
        }
    }

    @Override
    public EmpPassportVO getEmpPassport(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmpPassportVO as e left join fetch e.empIdObj as emp left join fetch e.country as c where e.hcmoEmpPassportId =:HcmoEmpPassportId");
            q.setInteger("HcmoEmpPassportId", id);
            return (EmpPassportVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertEmpPassport(EmpPassportVO empPass) {
        Boolean isUnique = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(empPass);
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
    public List passportSearchResult(EmpPassportVO pass) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmpPassportVO.class);
            if (pass.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", pass.getEmpIdObj().getEmployeeId()));
            }
            if (!(pass.getEpPassportNo().isEmpty())) {
                crit.add(Restrictions.like("epPassportNo", pass.getEpPassportNo(), MatchMode.ANYWHERE));
            }

            if (pass.getEpPassportIssueDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((pass.getEpPassportIssueDate() != null)
                    && (pass.getEpPassportIssueDateEnds() != null)) {
                    crit.add(Restrictions.ge("epPassportIssueDate", pass.getEpPassportIssueDate()));
                    pass.getMessage().add("passIssueDateMessage");
                } else if ((pass.getEpPassportIssueDate() != null)
                    && (pass.getEpPassportIssueDateEnds() == null)) {
                    crit.add(Restrictions.ge("epPassportIssueDate", pass.getEpPassportIssueDate()));
                } else if ((pass.getEpPassportIssueDateEnds() != null)
                    && (pass.getEpPassportIssueDate() == null)) {
                    crit.add(Restrictions.ge("epPassportIssueDate", pass.getEpPassportIssueDateEnds()));
                }
            } else if (pass.getEpPassportIssueDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((pass.getEpPassportIssueDate() != null)
                    && (pass.getEpPassportIssueDateEnds() != null)) {
                    crit.add(Restrictions.le("epPassportIssueDate", pass.getEpPassportIssueDate()));
                    pass.getMessage().add("passIssueDateMessage");
                } else if ((pass.getEpPassportIssueDate() != null)
                    && (pass.getEpPassportIssueDateEnds() == null)) {
                    crit.add(Restrictions.le("epPassportIssueDate", pass.getEpPassportIssueDate()));
                } else if ((pass.getEpPassportIssueDateEnds() != null)
                    && (pass.getEpPassportIssueDate() == null)) {
                    crit.add(Restrictions.le("epPassportIssueDate", pass.getEpPassportIssueDateEnds()));
                }
            } else if (pass.getEpPassportIssueDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((pass.getEpPassportIssueDate() != null)
                    && (pass.getEpPassportIssueDateEnds() != null)) {
                    crit.add(Restrictions.eq("epPassportIssueDate", pass.getEpPassportIssueDate()));
                    pass.getMessage().add("passIssueDateMessage");
                } else if ((pass.getEpPassportIssueDate() != null)
                    && (pass.getEpPassportIssueDateEnds() == null)) {
                    crit.add(Restrictions.eq("epPassportIssueDate", pass.getEpPassportIssueDate()));
                } else if ((pass.getEpPassportIssueDateEnds() != null)
                    && (pass.getEpPassportIssueDate() == null)) {
                    crit.add(Restrictions.eq("epPassportIssueDate", pass.getEpPassportIssueDateEnds()));
                }
            } else if (pass.getEpPassportIssueDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((pass.getEpPassportIssueDate() != null)
                    && (pass.getEpPassportIssueDateEnds() != null)) {
                    if (pass.getEpPassportIssueDate().before(pass.getEpPassportIssueDateEnds())) {
                        crit.add(Restrictions.between("epPassportIssueDate", pass.getEpPassportIssueDate(), pass.getEpPassportIssueDateEnds()));
                    } else if (pass.getEpPassportIssueDate().after(pass.getEpPassportIssueDateEnds())) {
                        crit.add(Restrictions.between("epPassportIssueDate", pass.getEpPassportIssueDateEnds(), pass.getEpPassportIssueDate()));
                    } else if (pass.getEpPassportIssueDate().equals(pass.getEpPassportIssueDateEnds())) {
                        crit.add(Restrictions.eq("epPassportIssueDate", pass.getEpPassportIssueDate()));
                    }
                } else if ((pass.getEpPassportIssueDate() != null)
                    && (pass.getEpPassportIssueDateEnds() == null)) {
                    crit.add(Restrictions.eq("epPassportIssueDate", pass.getEpPassportIssueDate()));
                } else if ((pass.getEpPassportIssueDateEnds() != null)
                    && (pass.getEpPassportIssueDate() == null)) {
                    crit.add(Restrictions.eq("epPassportIssueDate", pass.getEpPassportIssueDateEnds()));
                }
            }

            if (pass.getEpPassportExpireDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((pass.getEpPassportExpireDate() != null)
                    && (pass.getEpPassportExpireDateEnds() != null)) {
                    crit.add(Restrictions.ge("epPassportExpireDate", pass.getEpPassportExpireDate()));
                    pass.getMessage().add("passExpireDateMessage");
                } else if ((pass.getEpPassportExpireDate() != null)
                    && (pass.getEpPassportExpireDateEnds() == null)) {
                    crit.add(Restrictions.ge("epPassportExpireDate", pass.getEpPassportExpireDate()));
                } else if ((pass.getEpPassportExpireDateEnds() != null)
                    && (pass.getEpPassportExpireDate() == null)) {
                    crit.add(Restrictions.ge("epPassportExpireDate", pass.getEpPassportExpireDateEnds()));
                }
            } else if (pass.getEpPassportExpireDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((pass.getEpPassportExpireDate() != null)
                    && (pass.getEpPassportExpireDateEnds() != null)) {
                    crit.add(Restrictions.le("epPassportExpireDate", pass.getEpPassportExpireDate()));
                    pass.getMessage().add("passExpireDateMessage");
                } else if ((pass.getEpPassportExpireDate() != null)
                    && (pass.getEpPassportExpireDateEnds() == null)) {
                    crit.add(Restrictions.le("epPassportExpireDate", pass.getEpPassportExpireDate()));
                } else if ((pass.getEpPassportExpireDateEnds() != null)
                    && (pass.getEpPassportExpireDate() == null)) {
                    crit.add(Restrictions.le("epPassportExpireDate", pass.getEpPassportExpireDateEnds()));
                }
            } else if (pass.getEpPassportExpireDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((pass.getEpPassportExpireDate() != null)
                    && (pass.getEpPassportExpireDateEnds() != null)) {
                    crit.add(Restrictions.eq("epPassportExpireDate", pass.getEpPassportExpireDate()));
                    pass.getMessage().add("passExpireDateMessage");
                } else if ((pass.getEpPassportExpireDate() != null)
                    && (pass.getEpPassportExpireDateEnds() == null)) {
                    crit.add(Restrictions.eq("epPassportExpireDate", pass.getEpPassportExpireDate()));
                } else if ((pass.getEpPassportExpireDateEnds() != null)
                    && (pass.getEpPassportExpireDate() == null)) {
                    crit.add(Restrictions.eq("epPassportExpireDate", pass.getEpPassportExpireDateEnds()));
                }
            } else if (pass.getEpPassportExpireDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((pass.getEpPassportExpireDate() != null)
                    && (pass.getEpPassportExpireDateEnds() != null)) {
                    if (pass.getEpPassportExpireDate().before(pass.getEpPassportExpireDateEnds())) {
                        crit.add(Restrictions.between("epPassportExpireDate", pass.getEpPassportExpireDate(), pass.getEpPassportExpireDateEnds()));
                    } else if (pass.getEpPassportExpireDate().after(pass.getEpPassportExpireDateEnds())) {
                        crit.add(Restrictions.between("epPassportExpireDate", pass.getEpPassportExpireDateEnds(), pass.getEpPassportExpireDate()));
                    } else if (pass.getEpPassportExpireDate().equals(pass.getEpPassportExpireDateEnds())) {
                        crit.add(Restrictions.eq("epPassportExpireDate", pass.getEpPassportExpireDate()));
                    }
                } else if ((pass.getEpPassportExpireDate() != null)
                    && (pass.getEpPassportExpireDateEnds() == null)) {
                    crit.add(Restrictions.eq("epPassportExpireDate", pass.getEpPassportExpireDate()));
                } else if ((pass.getEpPassportExpireDateEnds() != null)
                    && (pass.getEpPassportExpireDate() == null)) {
                    crit.add(Restrictions.eq("epPassportExpireDate", pass.getEpPassportExpireDateEnds()));
                }
            }

            if (pass.getCountry().getHcmocountryId() != null) {
                crit.add(Restrictions.eq("country.hcmocountryId", pass.getCountry().getHcmocountryId()));
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
    public void updateEmpPassport(EmpPassportVO empPass) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmpPassportVO set EpPassportNum=:EpPassportNum, "
                + "Ep19ReviewDate=:Ep19ReviewDate, " + "HcmoCountryId=:HcmoCountryId, "
                + "HcmoEmployeeId=:HcmoEmployeeId, " + "EpPassportIssueDate=:EpPassportIssueDate, "
                + "EpPassportExpireDate=:EpPassportExpireDate, " + "EpComments=:EpComments, "
                + "EpPassportTypeFlg=:EpPassportTypeFlg, " + "Ep19Status=:Ep19Status, "
                + "UpdatedBy=:UpdatedBy " + "where HcmoEmpPassportId=:HcmoEmpPassportId";
            Query query = session.createQuery(sHql);
            query.setString("EpPassportNum", empPass.getEpPassportNo());
            query.setDate("Ep19ReviewDate", empPass.getEpL9ReviewDate());
            query.setInteger("HcmoCountryId", empPass.getCountry().getHcmocountryId());
            query.setInteger("HcmoEmployeeId", empPass.getEmpIdObj().getEmployeeId());
            query.setDate("EpPassportIssueDate", empPass.getEpPassportIssueDate());
            query.setDate("EpPassportExpireDate", empPass.getEpPassportExpireDate());
            query.setString("EpComments", empPass.getEpComments());
            query.setString("EpPassportTypeFlg", empPass.getEpPassportTypeFlg());
            query.setString("Ep19Status", empPass.getEpL9Status());
            query.setInteger("UpdatedBy", empPass.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoEmpPassportId", empPass.getHcmoEmpPassportId());
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
