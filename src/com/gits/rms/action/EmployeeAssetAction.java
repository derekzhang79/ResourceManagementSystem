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
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmpAssetsVO;
import com.gits.rms.vo.EmployeesVO;

/**
 * @author Parveen
 *
 */
public class EmployeeAssetAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8159439544840132192L;
    static Logger log = Logger.getLogger(EmployeeAssetAction.class.getName());// for store log details
    private EmpAssetsVO assets;
    private List<EmpAssetsVO> assetsList;
    private EmpAssetsService assetsService = new EmpAssetsDaoService();
    private Map session;
    private Integer clientId;
    
    
	

	
 // Based on EmployeeId get All assets of that Employee
    @SkipValidation
    public String getEmpAllAssets() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("assets.employee.employeeId"));
        if (employeeId == null) {
            employeeId = assets.getEmpIdObj().getEmployeeId();
        }
        assetsList = assetsService.getEmpAllAssets(employeeId);
        return SUCCESS;
    }
 
    // To insert or update Employee assets Data
    @SkipValidation
    public String insertOrUpdateEmployeeAsset() {
    	log.debug("control enters into insert employee assets");
    	session = ActionContext.getContext().getSession();
    	clientId = (Integer) session.get("CLIENT_INFO");
    	if(clientId != null)
    		assets.setClientId(clientId);

        try {     
        	if (assets.getHcmoAssetId() == null) {
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                assets.setCreated(DateUtils.getCurrentDateTime());
                assets.setCreatedBy(oEmp);
                assets.setUpdatedBy(oEmp);
                assetsService.insertEmpAssets(assets);
                addActionMessage(getText("Added Successfully"));
                log.info("Asset added successfully");
            } else {
                // To Update the EEO
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                assets.setUpdatedBy(oEmp);
                assetsService.updateEmpAssets(assets);
                addActionMessage(getText("Updated Successfully"));
                log.info("Asset updated successfully");
            }
        
        }catch(Exception e) {
        	e.printStackTrace();
        	log.error("exception occurred during employee asset insertion or updation" + e);
        }
     return SUCCESS;
    }

	public EmpAssetsVO getAssets() {
		return assets;
	}

	public void setAssets(EmpAssetsVO assets) {
		this.assets = assets;
	}

	public List<EmpAssetsVO> getAssetsList() {
		return assetsList;
	}

	public void setAssetsList(List<EmpAssetsVO> assetsList) {
		this.assetsList = assetsList;
	}

	public EmpAssetsService getAssetsService() {
		return assetsService;
	}

	public void setAssetsService(EmpAssetsService assetsService) {
		this.assetsService = assetsService;
	}
}
