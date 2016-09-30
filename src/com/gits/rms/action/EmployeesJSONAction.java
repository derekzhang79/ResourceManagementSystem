package com.gits.rms.action;

import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.vo.EmployeesVO;

public class EmployeesJSONAction implements Action {

	
	private Logger logger = Logger.getLogger(EmployeesAction.class.getName());

	private EmployeesService emplService = new EmployeesDaoService();

    private Map session = ActionContext.getContext().getSession();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
	private EmployeesVO employee;
	private Integer employeeId;

	@Override
	public String execute() throws Exception {

		logger.debug("INSIDE EXECUTE");
		logger.debug("INSIDE EXECUTE employeeId :> " + employeeId);
		
		Integer selectedEmployeeId = new Integer(java.sql.Types.NULL);
        
        if(session.get("SELECTED_EMPLOYEE_ID") != null){
        	selectedEmployeeId = (Integer) session.get("SELECTED_EMPLOYEE_ID");
        	//logger.debug("selectedEmployeeId : " + selectedEmployeeId);
        }
        logger.debug("INSIDE EXECUTE selectedEmployeeId :> " + selectedEmployeeId);
        
        if(selectedEmployeeId > 0){
        	employeeId = selectedEmployeeId;
        }
        
        logger.debug("INSIDE EXECUTE employeeId :> " + employeeId);
        
		if (employeeId >= 0) {
			employee = emplService.getEmployeeById(employeeId);
			logger.debug("employee : " + employee);
			
			if(employee != null){
				logger.debug("employee.getEmpFirstName() : " + employee.getEmpFirstName());
				logger.debug("employee.getEmpLastName() : " + employee.getEmpLastName());
			}

		}
		loadValues.getAllEmployeeName();
		return SUCCESS;
	}

	public EmployeesService getEmplService() {
		return emplService;
	}

	public void setEmplService(EmployeesService emplService) {
		this.emplService = emplService;
	}

	public EmployeesVO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeesVO employee) {
		this.employee = employee;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

}
