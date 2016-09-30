package com.gits.rms.persistence;

import com.gits.rms.vo.ClientRegVO;

public interface ClientRegistrationDao {
	
	void insertRegistration(ClientRegVO regVO);
	
	void updateRegistration(ClientRegVO regVO);
    
	void updateRegistrationStatus(ClientRegVO regVO);
	
	ClientRegVO getRegDetails(Integer id);

}
