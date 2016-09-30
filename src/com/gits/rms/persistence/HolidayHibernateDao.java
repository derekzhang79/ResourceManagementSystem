
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
import com.gits.rms.vo.HolidayVO;

public class HolidayHibernateDao implements HolidayDao {

    @Override
    public List getAllHoliday() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(HolidayVO.class);
            crit.addOrder(Order.asc("holidayDescription"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List holidaySearchResult(HolidayVO holiday) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(HolidayVO.class);
            if (!(holiday.getHolidayDescription().isEmpty())) {
                crit.add(Restrictions.like("holidayDescription", holiday.getHolidayDescription(), MatchMode.ANYWHERE));
            }

            if (holiday.getHolidayDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((holiday.getHolidayDate() != null) && (holiday.getHolidayEndDate() != null)) {
                    crit.add(Restrictions.ge("holidayDate", holiday.getHolidayDate()));
                    holiday.setMessage("Message");
                } else if ((holiday.getHolidayDate() != null)
                    && (holiday.getHolidayEndDate() == null)) {
                    crit.add(Restrictions.ge("holidayDate", holiday.getHolidayDate()));
                } else if ((holiday.getHolidayEndDate() != null)
                    && (holiday.getHolidayDate() == null)) {
                    crit.add(Restrictions.ge("holidayDate", holiday.getHolidayEndDate()));
                }
            } else if (holiday.getHolidayDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((holiday.getHolidayDate() != null) && (holiday.getHolidayEndDate() != null)) {
                    crit.add(Restrictions.le("holidayDate", holiday.getHolidayDate()));
                    holiday.setMessage("Message");
                } else if ((holiday.getHolidayDate() != null)
                    && (holiday.getHolidayEndDate() == null)) {
                    crit.add(Restrictions.le("holidayDate", holiday.getHolidayDate()));
                } else if ((holiday.getHolidayEndDate() != null)
                    && (holiday.getHolidayDate() == null)) {
                    crit.add(Restrictions.le("holidayDate", holiday.getHolidayEndDate()));
                }
            } else if (holiday.getHolidayDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((holiday.getHolidayDate() != null) && (holiday.getHolidayEndDate() != null)) {
                    crit.add(Restrictions.eq("holidayDate", holiday.getHolidayDate()));
                    holiday.setMessage("Message");
                } else if ((holiday.getHolidayDate() != null)
                    && (holiday.getHolidayEndDate() == null)) {
                    crit.add(Restrictions.eq("holidayDate", holiday.getHolidayDate()));
                } else if ((holiday.getHolidayEndDate() != null)
                    && (holiday.getHolidayDate() == null)) {
                    crit.add(Restrictions.eq("holidayDate", holiday.getHolidayEndDate()));
                }
            } else if (holiday.getHolidayDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((holiday.getHolidayDate() != null) && (holiday.getHolidayEndDate() != null)) {
                    if (holiday.getHolidayDate().before(holiday.getHolidayEndDate())) {
                        crit.add(Restrictions.between("holidayDate", holiday.getHolidayDate(), holiday.getHolidayEndDate()));
                    } else if (holiday.getHolidayDate().after(holiday.getHolidayEndDate())) {
                        crit.add(Restrictions.between("holidayDate", holiday.getHolidayEndDate(), holiday.getHolidayDate()));
                    } else if (holiday.getHolidayDate().equals(holiday.getHolidayEndDate())) {
                        crit.add(Restrictions.eq("holidayDate", holiday.getHolidayDate()));
                    }
                } else if ((holiday.getHolidayDate() != null)
                    && (holiday.getHolidayEndDate() == null)) {
                    crit.add(Restrictions.eq("holidayDate", holiday.getHolidayDate()));
                } else if ((holiday.getHolidayEndDate() != null)
                    && (holiday.getHolidayDate() == null)) {
                    crit.add(Restrictions.eq("holidayDate", holiday.getHolidayEndDate()));
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
    public HolidayVO getHoliday(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from HolidayVO as holi where holi.holidayId =:holidayId");
            q.setInteger("holidayId", id);
            return (HolidayVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertHoliday(HolidayVO child) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(child);
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
    public void updateHoliday(HolidayVO holiday) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update HolidayVO set Description=:Description, " + "Date=:Date, "
                + "recurring=:Recurring, " + "length=:Length, " + "UpdatedBy=:UpdatedBy "
                + "where HcmoHolidaysId=:HcmoHolidaysId";
            Query query = session.createQuery(sHql);
            query.setString("Description", holiday.getHolidayDescription());
            query.setDate("Date", holiday.getHolidayDate());
            query.setBoolean("Recurring", holiday.isRecurring());
            query.setInteger("Length", holiday.getLength());
            query.setInteger("UpdatedBy", holiday.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoHolidaysId", holiday.getHolidayId());
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
    public void deleteHoliday(HolidayVO holiday) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update HolidayVO set updatedBy=:UpdatedBy,IsActive=:IsActive where holidayId=:holidayId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", holiday.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("holidayId", holiday.getHolidayId());
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
