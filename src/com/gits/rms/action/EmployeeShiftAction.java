/**
 * 
 */
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.EmpAssetsDaoService;
import com.gits.rms.service.EmpAssetsService;
import com.gits.rms.service.EmpShiftDaoService;
import com.gits.rms.service.EmpShiftService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeeShiftVO;
import com.gits.rms.vo.EmployeesVO;

/**
 * @author Parveen
 *
 */
public class EmployeeShiftAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6412993617238760026L;
	static Logger log = Logger.getLogger(EmployeeShiftAction.class.getName());// for store log details
	private EmployeeShiftVO shift;
    private List<EmployeeShiftVO> shiftList;
    private EmpShiftService shiftService = new EmpShiftDaoService();
    
 // Based on EmployeeId get All assets of that Employee
    @SkipValidation
    public String getEmpAllAssets() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("shift.employee.employeeId"));
        if (employeeId == null) {
            employeeId = shift.getEmployee().getEmployeeId();
        }
        shiftList = shiftService.getEmpAllShift(employeeId);
        return SUCCESS;
    }
 
 // To insert or update Employee shift Data
    @SkipValidation
    public String insertOrUpdateEmployeeShift() {
    	log.debug("control enters into insert employee shift");

        try {     
        	log.debug("shift : " + shift);
        	
        	Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("shift.employee.employeeId"));
        	log.debug("context employeeId : " + employeeId);
            if (employeeId == null) {
                employeeId = shift.getEmployee().getEmployeeId();
            }
        	log.debug("employeeId : " + employeeId);
        	
        	if (shift.getHcmoShiftId() == null) {
        		log.debug("shift.getHcmoShiftId() == null");
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                shift.setCreated(DateUtils.getCurrentDateTime());
                shift.setCreatedBy(oEmp);
                shift.setUpdatedBy(oEmp);
                shiftService.insertEmpShift(shift);
                addActionMessage(getText("Added Successfully"));
                log.info("Shift details added successfully");
            } else {
                // To Update the EEO
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                shift.setUpdatedBy(oEmp);
                shiftService.updateEmpShift(shift);
                addActionMessage(getText("Updated Successfully"));
                log.info("Shift details updated successfully");
            }
        
        }catch(Exception e) {
        	log.error("exception occurred during employee shift insertion or updation" + e);
        }
     return SUCCESS;
    }

	public EmployeeShiftVO getShift() {
		return shift;
	}

	public void setShift(EmployeeShiftVO shift) {
		this.shift = shift;
	}

	public List<EmployeeShiftVO> getShiftList() {
		return shiftList;
	}

	public void setShiftList(List<EmployeeShiftVO> shiftList) {
		this.shiftList = shiftList;
	}

	public EmpShiftService getShiftService() {
		return shiftService;
	}

	public void setShiftService(EmpShiftService shiftService) {
		this.shiftService = shiftService;
	}

}
