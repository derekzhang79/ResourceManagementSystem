package com.gits.rms.service;

import com.gits.rms.persistence.ClientRegHiberanateDao;
import com.gits.rms.persistence.ClientRegistrationDao;
import com.gits.rms.vo.ClientRegVO;

public class ClientRegistrationDaoService implements ClientRegistrationService {
	
	ClientRegistrationDao clientdao;
	
	public ClientRegistrationDaoService(){
		clientdao = new ClientRegHiberanateDao();
	}

	@Override
	public void insertRegistration(ClientRegVO regVO) {
		System.out.println("inside dao service layer");
		clientdao.insertRegistration(regVO);

	}

	@Override
	public ClientRegVO getRegDetails(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRegistration(ClientRegVO regVO) {
		// TODO Auto-generated method stub
		System.out.println("inside dao update service layer");
		clientdao.updateRegistration(regVO);
	}

	@Override
	public void updateRegistrationStatus(ClientRegVO regVO) {
		// TODO Auto-generated method stub
		System.out.println("inside dao update status service layer");
		clientdao.updateRegistrationStatus(regVO);
	}

}
