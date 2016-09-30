
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.MailConfigurationVO;

public interface MailConfigurationService {

    List getAllMailConfig();

    MailConfigurationVO getMailConfig(Integer id);

    void insertMailConfig(MailConfigurationVO mail);

    void updateMailConfig(MailConfigurationVO mail);
}
