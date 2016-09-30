
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.ClientVO;

public interface ClientDao {
    void deleteClient(ClientVO client);

    List getAllClient();

    ClientVO getClient(Integer id);

    void insertClient(ClientVO client);

    void updateClient(ClientVO client);
}
