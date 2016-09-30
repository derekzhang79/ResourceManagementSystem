
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.ClientVO;

public interface ClientService {

    void deleteClient(ClientVO cli);

    List getAllClient();

    ClientVO getClient(Integer id);

    void insertClient(ClientVO client);

    void updateClient(ClientVO client);
}