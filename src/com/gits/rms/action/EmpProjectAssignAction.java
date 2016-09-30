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
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.ProjectAssignEmpService;
import com.gits.rms.service.ProjectAssignEmpDaoService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmpAssetsVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectAssignEmpVO;

/**
 * @author Parveen
 *
 */
public class EmpProjectAssignAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9058966763071995254L;
	static Logger logger = Logger.getLogger(EmpProjectAssignAction.class.getName());// for store log details
    private ProjectAssignEmpVO assProj;
    private List<ProjectAssignEmpVO> assProjList;
    private ProjectAssignEmpService assProjService = new ProjectAssignEmpDaoService();
    private Map session = ActionContext.getContext().getSession();
    private EmployeesVO employee;
    private EmployeesService emplService = new EmployeesDaoService();

    
    public ProjectAssignEmpVO getAssProj() {
		return assProj;
	}



	public void setAssProj(ProjectAssignEmpVO assProj) {
		this.assProj = assProj;
	}



	public List<ProjectAssignEmpVO> getAssProjList() {
		return assProjList;
	}



	public void setAssProjList(List<ProjectAssignEmpVO> assProjList) {
		this.assProjList = assProjList;
	}



	public ProjectAssignEmpService getAssProjService() {
		return assProjService;
	}



	public void setAssProjService(ProjectAssignEmpService assProjService) {
		this.assProjService = assProjService;
	}



	// To insert or update Employee assets Data
    @SkipValidation
    public String insertOrUpdateEmpProjectAssign() {
    	System.out.println("Inside project assign");
    	Integer employeeId = (Integer)session.get("SELECTED_EMPLOYEE_ID");
		employee = emplService.getEmployees(employeeId);
        System.out.println("employee obj: " + employee);
    	logger.debug("control enters into insert project assigning");
  	  
    	logger.debug(" Im in 1  ");
        try {     
        	logger.debug("employee : " + employee);
        	
        	if(employee != null){
        		assProj = employee.getAssProj();
        	}
        	
        	logger.debug("assProj : " + assProj);
        	
        	
        	if(assProj != null){
        		

        	if (assProj.getProjectAssignEmpId() == null) {
        		logger.debug("IF assProj.getProjectAssignEmpId() != null : " + assProj.getProjectAssignEmpId());
        		logger.debug(" Im in 2  ");
                EmployeesVO newAdminEmp = null;
                
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                assProj.setCreated(DateUtils.getCurrentDateTime());
                assProj.setCreatedBy(oEmp);
                assProj.setUpdatedBy(oEmp);
                logger.debug(" Im in 2.1...........................................  ");
                assProjService.insertProjectAssignEmp(assProj);
                System.out.println("added successfully");
                addActionMessage(getText("Added Successfully"));
                logger.info("Project assigned successfully");
            } else {
            	logger.debug("IF assProj.getProjectAssignEmpId() == null : " + assProj.getProjectAssignEmpId());
            	logger.debug(" Im in 3  ");
                // To Update the EEO
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                assProj.setUpdatedBy(oEmp);
                System.out.println(" Im in 4  ");
                assProjService.updateProjectAssignEmpDao(assProj);
                System.out.println("updated successfully");
                addActionMessage(getText("Updated Successfully"));
                logger.info("Project assigned updated successfully");
            }
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("exception " + e);
        	logger.error("exception occurred during employee project insertion or updation" + e);
        }
     return SUCCESS;
   }
}
