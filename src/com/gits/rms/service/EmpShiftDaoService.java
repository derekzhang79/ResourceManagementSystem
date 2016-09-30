/**
 * 
 */
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.EmpShiftDao;
import com.gits.rms.persistence.EmpShiftHibernateDao;
import com.gits.rms.vo.EmployeeShiftVO;

/**
 * @author Parveen
 *
 */
public class EmpShiftDaoService implements EmpShiftService {
	
    private EmpShiftDao dao;
    
    public EmpShiftDaoService(){
    	dao = new EmpShiftHibernateDao();
    }

	@Override
	public void insertEmpShift(EmployeeShiftVO shift) {
		// TODO Auto-generated method stub
		dao.insertEmpShift(shift);
	}

	@Override
	public void updateEmpShift(EmployeeShiftVO shift) {
		// TODO Auto-generated method stub
		dao.updateEmpShift(shift);
	}

	@Override
	public List<EmployeeShiftVO> getEmpAllShift(Integer employeeId) {
		// TODO Auto-generated method stub
		return dao.getEmpAllShift(employeeId);
	}
}
