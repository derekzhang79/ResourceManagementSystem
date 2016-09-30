
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
import com.gits.rms.vo.LicenseVO;

public class LicenseHibernateDao implements LicenseDao {

    private List<LicenseVO> licenseList;

    @Override
    public List getAllLicense() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LicenseVO.class);
            crit.addOrder(Order.asc("licenseNumber"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List getAllSubEmployeeLicenseList(List<Integer> empReportToEmpId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LicenseVO.class);
            crit.add(Restrictions.in("empIdObj.employeeId", empReportToEmpId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
        	session.flush();
        	session.close();
        }
    }

    @Override
    public List licenseSearchResult(LicenseVO license) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LicenseVO.class);
            if (license.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", license.getEmpIdObj().getEmployeeId()));
            }
            if (!(license.getLicenseNumber().isEmpty())) {
                crit.add(Restrictions.like("licenseNumber", license.getLicenseNumber(), MatchMode.ANYWHERE));
            }
            if (license.getLicenseDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((license.getLicenseDate() != null) && (license.getLicenseEndDate() != null)) {
                    crit.add(Restrictions.ge("licenseDate", license.getLicenseDate()));
                    license.getMessage().add("LicenseDateMessage");
                } else if ((license.getLicenseDate() != null)
                    && (license.getLicenseEndDate() == null)) {
                    crit.add(Restrictions.ge("licenseDate", license.getLicenseDate()));
                } else if ((license.getLicenseEndDate() != null)
                    && (license.getLicenseDate() == null)) {
                    crit.add(Restrictions.ge("licenseDate", license.getLicenseEndDate()));
                }
            } else if (license.getLicenseDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((license.getLicenseDate() != null) && (license.getLicenseEndDate() != null)) {
                    crit.add(Restrictions.le("licenseDate", license.getLicenseDate()));
                    license.getMessage().add("LicenseDateMessage");
                } else if ((license.getLicenseDate() != null)
                    && (license.getLicenseEndDate() == null)) {
                    crit.add(Restrictions.le("licenseDate", license.getLicenseDate()));
                } else if ((license.getLicenseEndDate() != null)
                    && (license.getLicenseDate() == null)) {
                    crit.add(Restrictions.le("licenseDate", license.getLicenseEndDate()));
                }
            } else if (license.getLicenseDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((license.getLicenseDate() != null) && (license.getLicenseEndDate() != null)) {
                    crit.add(Restrictions.eq("licenseDate", license.getLicenseDate()));
                    license.getMessage().add("LicenseDateMessage");
                } else if ((license.getLicenseDate() != null)
                    && (license.getLicenseEndDate() == null)) {
                    crit.add(Restrictions.eq("licenseDate", license.getLicenseDate()));
                } else if ((license.getLicenseEndDate() != null)
                    && (license.getLicenseDate() == null)) {
                    crit.add(Restrictions.eq("licenseDate", license.getLicenseEndDate()));
                }
            } else if (license.getLicenseDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((license.getLicenseDate() != null) && (license.getLicenseEndDate() != null)) {
                    if (license.getLicenseDate().before(license.getLicenseEndDate())) {
                        crit.add(Restrictions.between("licenseDate", license.getLicenseDate(), license.getLicenseEndDate()));
                    } else if (license.getLicenseDate().after(license.getLicenseEndDate())) {
                        crit.add(Restrictions.between("licenseDate", license.getLicenseEndDate(), license.getLicenseDate()));
                    } else if (license.getLicenseDate().equals(license.getLicenseEndDate())) {
                        crit.add(Restrictions.eq("licenseDate", license.getLicenseDate()));
                    }
                } else if ((license.getLicenseDate() != null)
                    && (license.getLicenseEndDate() == null)) {
                    crit.add(Restrictions.eq("licenseDate", license.getLicenseDate()));
                } else if ((license.getLicenseEndDate() != null)
                    && (license.getLicenseDate() == null)) {
                    crit.add(Restrictions.eq("licenseDate", license.getLicenseEndDate()));
                }
            }

            if (license.getLicenseRenewalDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((license.getLicenseRenewalDate() != null)
                    && (license.getLicenseRenewalEndDate() != null)) {
                    crit.add(Restrictions.ge("licenseRenewalDate", license.getLicenseRenewalDate()));
                    license.getMessage().add("LicenseRenewalDateMessage");
                } else if ((license.getLicenseRenewalDate() != null)
                    && (license.getLicenseRenewalEndDate() == null)) {
                    crit.add(Restrictions.ge("licenseRenewalDate", license.getLicenseRenewalDate()));
                } else if ((license.getLicenseRenewalEndDate() != null)
                    && (license.getLicenseRenewalDate() == null)) {
                    crit.add(Restrictions.ge("licenseRenewalDate", license.getLicenseRenewalEndDate()));
                }
            } else if (license.getLicenseRenewalDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((license.getLicenseRenewalDate() != null)
                    && (license.getLicenseRenewalEndDate() != null)) {
                    crit.add(Restrictions.le("licenseRenewalDate", license.getLicenseRenewalDate()));
                    license.getMessage().add("LicenseRenewalDateMessage");
                } else if ((license.getLicenseRenewalDate() != null)
                    && (license.getLicenseRenewalEndDate() == null)) {
                    crit.add(Restrictions.le("licenseRenewalDate", license.getLicenseRenewalDate()));
                } else if ((license.getLicenseRenewalEndDate() != null)
                    && (license.getLicenseRenewalDate() == null)) {
                    crit.add(Restrictions.le("licenseRenewalDate", license.getLicenseRenewalEndDate()));
                }
            } else if (license.getLicenseRenewalDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((license.getLicenseRenewalDate() != null)
                    && (license.getLicenseRenewalEndDate() != null)) {
                    crit.add(Restrictions.eq("licenseRenewalDate", license.getLicenseRenewalDate()));
                    license.getMessage().add("LicenseRenewalDateMessage");
                } else if ((license.getLicenseRenewalDate() != null)
                    && (license.getLicenseRenewalEndDate() == null)) {
                    crit.add(Restrictions.eq("licenseRenewalDate", license.getLicenseRenewalDate()));
                } else if ((license.getLicenseRenewalEndDate() != null)
                    && (license.getLicenseRenewalDate() == null)) {
                    crit.add(Restrictions.eq("licenseRenewalDate", license.getLicenseRenewalEndDate()));
                }
            } else if (license.getLicenseRenewalDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((license.getLicenseRenewalDate() != null)
                    && (license.getLicenseRenewalEndDate() != null)) {
                    if (license.getLicenseRenewalDate().before(license.getLicenseRenewalEndDate())) {
                        crit.add(Restrictions.between("licenseRenewalDate", license.getLicenseRenewalDate(), license.getLicenseRenewalEndDate()));
                    } else if (license.getLicenseRenewalDate().after(license.getLicenseRenewalEndDate())) {
                        crit.add(Restrictions.between("licenseRenewalDate", license.getLicenseRenewalEndDate(), license.getLicenseRenewalDate()));
                    } else if (license.getLicenseRenewalDate().equals(license.getLicenseRenewalEndDate())) {
                        crit.add(Restrictions.eq("licenseRenewalDate", license.getLicenseRenewalDate()));
                    }
                } else if ((license.getLicenseRenewalDate() != null)
                    && (license.getLicenseRenewalEndDate() == null)) {
                    crit.add(Restrictions.eq("licenseRenewalDate", license.getLicenseRenewalDate()));
                } else if ((license.getLicenseRenewalEndDate() != null)
                    && (license.getLicenseRenewalDate() == null)) {
                    crit.add(Restrictions.eq("licenseRenewalDate", license.getLicenseRenewalEndDate()));
                }
            }

            if (!(license.getLicenseDesc().isEmpty())) {
                crit.add(Restrictions.like("licenseDesc", license.getLicenseDesc(), MatchMode.ANYWHERE));
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
    public List<LicenseVO> getEmployeeAllLicense(Integer employeeId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from LicenseVO as lic left join fetch lic.empIdObj where lic.isActive=:IsActive and lic.empIdObj.employeeId=:HcmoEmployeeId");
            query.setInteger("HcmoEmployeeId", employeeId);
            query.setInteger("IsActive", 1);
            licenseList = query.list();
            return licenseList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public LicenseVO getLicense(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from LicenseVO as lic left join fetch lic.empIdObj where lic.empLicenseId =:EmpLicenseId");
            q.setInteger("EmpLicenseId", id);
            return (LicenseVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public LicenseVO getEmpLicense(LicenseVO license) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from LicenseVO as lic left join fetch lic.empIdObj where lic.empLicenseId =:EmpLicenseId and lic.empIdObj.employeeId=:HcmoEmployeeId and lic.isActive=:IsActive");
            q.setInteger("EmpLicenseId", license.getEmpLicenseId());
            q.setInteger("HcmoEmployeeId", license.getEmpIdObj().getEmployeeId());
            q.setInteger("IsActive", 1);
            return (LicenseVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertLicense(LicenseVO license) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(license);
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
    public void updateLicense(LicenseVO license) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update LicenseVO set HcmoEmployeeId=:HcmoEmployeeId	, "
                + "LicenseNumber=:LicenseNumber, " + "LicenseDate=:LicenseDate, "
                + "LicenseRenewalDate=:LicenseRenewalDate, " + "LicenseDesc=:LicenseDesc, "
                + "UpdatedBy=:UpdatedBy " + "where empLicenseId=:EmpLicenseId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoEmployeeId", license.getEmpIdObj().getEmployeeId());
            query.setString("LicenseNumber", license.getLicenseNumber());
            query.setDate("LicenseDate", license.getLicenseDate());
            query.setDate("LicenseRenewalDate", license.getLicenseRenewalDate());
            query.setString("LicenseDesc", license.getLicenseDesc());
            query.setInteger("UpdatedBy", license.getUpdatedBy().getEmployeeId());
            query.setInteger("EmpLicenseId", license.getEmpLicenseId());
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
    public void deleteLicense(LicenseVO license) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update LicenseVO set updatedBy=:UpdatedBy,isActive=:IsActive where empLicenseId=:EmpLicenseId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", license.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("EmpLicenseId", license.getEmpLicenseId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
}
