
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.MailConfigurationDao;
import com.gits.rms.persistence.MailConfigurationHibernateDao;
import com.gits.rms.vo.MailConfigurationVO;

public class MailConfigurationDaoService implements MailConfigurationService {

    private MailConfigurationDao dao;

    public MailConfigurationDaoService() {
        dao = new MailConfigurationHibernateDao();
    }

    @Override
    public List getAllMailConfig() {
        return dao.getAllMailConfig();
    }

    @Override
    public MailConfigurationVO getMailConfig(Integer id) {
        return dao.getMailConfig(id);
    }

    @Override
    public void insertMailConfig(MailConfigurationVO mail) {
        dao.insertMailConfig(mail);
    }

    @Override
    public void updateMailConfig(MailConfigurationVO mail) {
        dao.updateMailConfig(mail);
    }
}
