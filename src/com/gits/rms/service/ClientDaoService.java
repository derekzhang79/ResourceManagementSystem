
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.ClientDao;
import com.gits.rms.persistence.ClientHibernateDao;
import com.gits.rms.vo.ClientVO;

public class ClientDaoService implements ClientService {

    private ClientDao dao;

    public ClientDaoService() {
        dao = new ClientHibernateDao();
    }

    @Override
    public void deleteClient(ClientVO client) {
        dao.deleteClient(client);
    }

    @Override
    public List getAllClient() {
        return dao.getAllClient();
    }

    @Override
    public ClientVO getClient(Integer id) {
        return dao.getClient(id);
    }

    @Override
    public void insertClient(ClientVO client) {
        dao.insertClient(client);
    }

    @Override
    public void updateClient(ClientVO client) {
        dao.updateClient(client);
    }

}
