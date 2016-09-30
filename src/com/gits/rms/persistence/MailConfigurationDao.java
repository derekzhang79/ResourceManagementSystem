
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.MailConfigurationVO;

public interface MailConfigurationDao {

    List getAllMailConfig();

    void insertMailConfig(MailConfigurationVO mail);

    MailConfigurationVO getMailConfig(Integer id);

    void updateMailConfig(MailConfigurationVO mail);
}
