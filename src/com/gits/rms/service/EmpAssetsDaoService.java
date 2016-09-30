/**
 * 
 */
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.DepartmentHibernateDao;
import com.gits.rms.persistence.EmpAssetsDao;
import com.gits.rms.persistence.EmpAssetsHibernateDao;
import com.gits.rms.vo.EmpAssetsVO;

/**
 * @author Parveen
 *
 */
public class EmpAssetsDaoService implements EmpAssetsService {
	
    private EmpAssetsDao dao;
    
    public EmpAssetsDaoService() {
        dao = new EmpAssetsHibernateDao();
    }


	@Override
	public void insertEmpAssets(EmpAssetsVO asset) {
		// TODO Auto-generated method stub
		dao.insertEmpAssets(asset);
	}

	@Override
	public void updateEmpAssets(EmpAssetsVO asset) {
		// TODO Auto-generated method stub
		dao.updateEmpAssets(asset);
	}

	@Override
	public List<EmpAssetsVO> getEmpAllAssets(Integer employeeId) {
		// TODO Auto-generated method stub
		return dao.getEmpAllAssets(employeeId);
	}
}
