/**
 * 
 */
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.EmployeeEEODao;
import com.gits.rms.persistence.EmployeeEEOHibernateDao;
import com.gits.rms.vo.EmployeeEEOVO;

/**
 * @author Parveen
 *
 */
public class EmployeeEEODaoService implements EmployeeEEOService{

    private EmployeeEEODao dao;

    public EmployeeEEODaoService() {
        dao = new EmployeeEEOHibernateDao();
    }
    
	@Override
	public void insertEmployeeEEO(EmployeeEEOVO eeo) {
		// TODO Auto-generated method stub
        dao.insertEmployeeEEO(eeo);
	}

	@Override
	public void updateEmployeeEEO(EmployeeEEOVO eeo) {
		// TODO Auto-generated method stub
		dao.updateEmployeeEEO(eeo);
	}

	@Override
	public List<EmployeeEEOVO> getEmployeeAllEEO(Integer employeeId) {
		// TODO Auto-generated method stub
		return dao.getEmployeeAllEEO(employeeId);
	}

	@Override
	public EmployeeEEOVO getEEO(Integer id) {
		// TODO Auto-generated method stub
        return dao.getEEO(id);
	}
}
