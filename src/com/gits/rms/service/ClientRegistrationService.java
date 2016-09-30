package com.gits.rms.service;

import com.gits.rms.vo.ClientRegVO;

public interface ClientRegistrationService {
	
	void insertRegistration(ClientRegVO regVO);
	
	void updateRegistration(ClientRegVO regVO);
    
	void updateRegistrationStatus(ClientRegVO regVO);
	
	ClientRegVO getRegDetails(Integer id);

}
