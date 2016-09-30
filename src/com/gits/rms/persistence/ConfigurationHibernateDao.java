package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.ConfigurationVO;

public class ConfigurationHibernateDao implements ConfigurationDao{
    @Override
    public void insertConfiguration(ConfigurationVO config) {
        Boolean isUnique = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(config);
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
    public ConfigurationVO getConfiguration(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ConfigurationVO as c where c.hcmoConfigurationId =:hcmoConfigurationId");
            q.setInteger("hcmoConfigurationId", id);
            return (ConfigurationVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllConfiguration(Integer clientId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ConfigurationVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("clientId", clientId));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void updateConfiguration(ConfigurationVO config) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ConfigurationVO set Status=:Status,"
                + "MailConfiguration=:MailConfiguration,UpdatedBy=:UpdatedBy where HcmoConfigurationId=:HcmoConfigurationId";
            Query query = session.createQuery(sHql);
            query.setString("Status", config.getStatus());
            query.setInteger("MailConfiguration",1);
            query.setInteger("UpdatedBy", config.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoConfigurationId", config.getHcmoConfigurationId());
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
    public void updateClientConfiguration(ConfigurationVO config) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ConfigurationVO set Status=:Status,"
                + "MailConfiguration=:MailConfiguration,Client=:Client,UpdatedBy=:UpdatedBy where HcmoConfigurationId=:HcmoConfigurationId";
            Query query = session.createQuery(sHql);
            query.setString("Status", config.getStatus());
            query.setInteger("MailConfiguration",config.getMailConfiguration());
            query.setInteger("Client",1);
            query.setInteger("UpdatedBy", config.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoConfigurationId", config.getHcmoConfigurationId());
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
    public void updateLocationConfiguration(ConfigurationVO config) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ConfigurationVO set Status=:Status,"
                + "MailConfiguration=:MailConfiguration,Client=:Client,Location=:Location,UpdatedBy=:UpdatedBy where HcmoConfigurationId=:HcmoConfigurationId";
            Query query = session.createQuery(sHql);
            query.setString("Status",config.getStatus());
            query.setInteger("MailConfiguration",config.getMailConfiguration());
            query.setInteger("Client",config.getClient());
            query.setInteger("Location",1);
            query.setInteger("UpdatedBy", config.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoConfigurationId", config.getHcmoConfigurationId());
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
    public void updateRegionConfiguration(ConfigurationVO config){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ConfigurationVO set Status=:Status,"
                + "MailConfiguration=:MailConfiguration,Client=:Client,Location=:Location,Region=:Region,UpdatedBy=:UpdatedBy where HcmoConfigurationId=:HcmoConfigurationId";
            Query query = session.createQuery(sHql);
            query.setString("Status",config.getStatus());
            query.setInteger("MailConfiguration",config.getMailConfiguration());
            query.setInteger("Client",config.getClient());
            query.setInteger("Location",config.getLocation());
            query.setInteger("Region",1);
            query.setInteger("UpdatedBy", config.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoConfigurationId", config.getHcmoConfigurationId());
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
    public void updateSalaryGradeConfiguration(ConfigurationVO config){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ConfigurationVO set Status=:Status,"
                + "MailConfiguration=:MailConfiguration,Client=:Client,Location=:Location,Region=:Region,SalaryGrade=:SalaryGrade,UpdatedBy=:UpdatedBy where HcmoConfigurationId=:HcmoConfigurationId";
            Query query = session.createQuery(sHql);
            query.setString("Status",config.getStatus());
            query.setInteger("MailConfiguration",config.getMailConfiguration());
            query.setInteger("Client",config.getClient());
            query.setInteger("Location",config.getLocation());
            query.setInteger("Region",config.getRegion());
            query.setInteger("SalaryGrade", 1);
            query.setInteger("UpdatedBy", config.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoConfigurationId", config.getHcmoConfigurationId());
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
    public void updateJobTitleConfiguration(ConfigurationVO config){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ConfigurationVO set Status=:Status,"
                + "MailConfiguration=:MailConfiguration,Client=:Client,Location=:Location,Region=:Region,SalaryGrade=:SalaryGrade,JobTitle=:JobTitle,UpdatedBy=:UpdatedBy where HcmoConfigurationId=:HcmoConfigurationId";
            Query query = session.createQuery(sHql);
            query.setString("Status",config.getStatus());
            query.setInteger("MailConfiguration",config.getMailConfiguration());
            query.setInteger("Client",config.getClient());
            query.setInteger("Location",config.getLocation());
            query.setInteger("Region",config.getRegion());
            query.setInteger("SalaryGrade",config.getSalaryGrade());
            query.setInteger("JobTitle", 1);
            query.setInteger("UpdatedBy", config.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoConfigurationId", config.getHcmoConfigurationId());
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
    public void updateDepartmentConfiguration(ConfigurationVO config){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ConfigurationVO set Status=:Status,"
                + "MailConfiguration=:MailConfiguration,Client=:Client,Location=:Location,Region=:Region,SalaryGrade=:SalaryGrade,JobTitle=:JobTitle,Department=:Department,"
                + "UpdatedBy=:UpdatedBy where HcmoConfigurationId=:HcmoConfigurationId";
            Query query = session.createQuery(sHql);
            query.setString("Status",config.getStatus());
            query.setInteger("MailConfiguration",config.getMailConfiguration());
            query.setInteger("Client",config.getClient());
            query.setInteger("Location",config.getLocation());
            query.setInteger("Region",config.getRegion());
            query.setInteger("SalaryGrade",config.getSalaryGrade());
            query.setInteger("JobTitle", config.getJobTitle());
            query.setInteger("Department", 1);
            query.setInteger("UpdatedBy", config.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoConfigurationId", config.getHcmoConfigurationId());
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
    public void updateTeamConfiguration(ConfigurationVO config){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ConfigurationVO set Status=:Status,"
                + "MailConfiguration=:MailConfiguration,Client=:Client,Location=:Location,Region=:Region,SalaryGrade=:SalaryGrade,JobTitle=:JobTitle,Department=:Department,"
                + "Team=:Team,UpdatedBy=:UpdatedBy where HcmoConfigurationId=:HcmoConfigurationId";
            Query query = session.createQuery(sHql);
            query.setString("Status",config.getStatus());
            query.setInteger("MailConfiguration",config.getMailConfiguration());
            query.setInteger("Client",config.getClient());
            query.setInteger("Location",config.getLocation());
            query.setInteger("Region",config.getRegion());
            query.setInteger("SalaryGrade",config.getSalaryGrade());
            query.setInteger("JobTitle", config.getJobTitle());
            query.setInteger("Department", config.getDepartment());
            query.setInteger("Team", 1);
            query.setInteger("UpdatedBy", config.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoConfigurationId", config.getHcmoConfigurationId());
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
    public void updateNationalityConfiguration(ConfigurationVO config){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ConfigurationVO set Status=:Status,"
                + "MailConfiguration=:MailConfiguration,Client=:Client,Location=:Location,Region=:Region,SalaryGrade=:SalaryGrade,JobTitle=:JobTitle,Department=:Department,"
                + "Team=:Team,Nationality=:Nationality,UpdatedBy=:UpdatedBy where HcmoConfigurationId=:HcmoConfigurationId";
            Query query = session.createQuery(sHql);
            query.setString("Status",config.getStatus());
            query.setInteger("MailConfiguration",config.getMailConfiguration());
            query.setInteger("Client",config.getClient());
            query.setInteger("Location",config.getLocation());
            query.setInteger("Region",config.getRegion());
            query.setInteger("SalaryGrade",config.getSalaryGrade());
            query.setInteger("JobTitle", config.getJobTitle());
            query.setInteger("Department", config.getDepartment());
            query.setInteger("Team", config.getTeam());
            query.setInteger("Nationality", 1);
            query.setInteger("UpdatedBy", config.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoConfigurationId", config.getHcmoConfigurationId());
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
    public void updateEthnicRaceConfiguration(ConfigurationVO config){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ConfigurationVO set Status=:Status,"
                + "MailConfiguration=:MailConfiguration,Client=:Client,Location=:Location,Region=:Region,SalaryGrade=:SalaryGrade,JobTitle=:JobTitle,Department=:Department,"
                + "Team=:Team,Nationality=:Nationality,EthnicRace=:EthnicRace,UpdatedBy=:UpdatedBy where HcmoConfigurationId=:HcmoConfigurationId";
            Query query = session.createQuery(sHql);
            query.setString("Status",config.getStatus());
            query.setInteger("MailConfiguration",config.getMailConfiguration());
            query.setInteger("Client",config.getClient());
            query.setInteger("Location",config.getLocation());
            query.setInteger("Region",config.getRegion());
            query.setInteger("SalaryGrade",config.getSalaryGrade());
            query.setInteger("JobTitle", config.getJobTitle());
            query.setInteger("Department", config.getDepartment());
            query.setInteger("Team", config.getTeam());
            query.setInteger("Nationality", config.getNationality());
            query.setInteger("EthnicRace", 1);
            query.setInteger("UpdatedBy", config.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoConfigurationId", config.getHcmoConfigurationId());
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
    public void updateEmployeeConfiguration(ConfigurationVO config){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ConfigurationVO set Status=:Status,"
                + "MailConfiguration=:MailConfiguration,Client=:Client,Location=:Location,Region=:Region,SalaryGrade=:SalaryGrade,JobTitle=:JobTitle,Department=:Department,"
                + "Team=:Team,Nationality=:Nationality,EthnicRace=:EthnicRace,Employee=:Employee,UpdatedBy=:UpdatedBy where HcmoConfigurationId=:HcmoConfigurationId";
            Query query = session.createQuery(sHql);
            query.setString("Status",config.getStatus());
            query.setInteger("MailConfiguration",config.getMailConfiguration());
            query.setInteger("Client",config.getClient());
            query.setInteger("Location",config.getLocation());
            query.setInteger("Region",config.getRegion());
            query.setInteger("SalaryGrade",config.getSalaryGrade());
            query.setInteger("JobTitle", config.getJobTitle());
            query.setInteger("Department", config.getDepartment());
            query.setInteger("Team", config.getTeam());
            query.setInteger("Nationality", config.getNationality());
            query.setInteger("EthnicRace", config.getEthnicRace());
            query.setInteger("Employee", 1);
            query.setInteger("UpdatedBy", config.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoConfigurationId", config.getHcmoConfigurationId());
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
    public void updateStatus(ConfigurationVO config) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ConfigurationVO set Status=:Status,"
                + "UpdatedBy=:UpdatedBy where HcmoConfigurationId=:HcmoConfigurationId";
            Query query = session.createQuery(sHql);
            query.setString("Status", config.getStatus());
            query.setInteger("UpdatedBy", config.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoConfigurationId", config.getHcmoConfigurationId());
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
    public void updateSkip(ConfigurationVO config) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ConfigurationVO set Skip=:Skip, Status=:Status,"
                + "UpdatedBy=:UpdatedBy where HcmoConfigurationId=:HcmoConfigurationId";
            Query query = session.createQuery(sHql);
            query.setString("Status", config.getStatus());
            query.setInteger("Skip", config.getSkip());
            query.setInteger("UpdatedBy", config.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoConfigurationId", config.getHcmoConfigurationId());
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
