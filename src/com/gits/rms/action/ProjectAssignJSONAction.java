/**
 * 
 */
package com.gits.rms.action;

import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.vo.EmployeesVO;

/**
 
 *
 */
public class ProjectAssignJSONAction implements Action{

	private Logger logger = Logger.getLogger(EmployeesAction.class.getName());

	private EmployeesService emplService = new EmployeesDaoService();

    private Map session = ActionContext.getContext().getSession();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
	private EmployeesVO employee;
	private Integer employeeId;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
