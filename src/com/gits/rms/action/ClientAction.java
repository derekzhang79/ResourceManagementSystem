
package com.gits.rms.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.ClientDaoService;
import com.gits.rms.service.ClientService;
import com.gits.rms.service.ConfigurationDaoService;
import com.gits.rms.service.ConfigurationService;
import com.gits.rms.service.CountryDaoService;
import com.gits.rms.service.CountryService;
import com.gits.rms.service.RegionDaoService;
import com.gits.rms.service.RegionService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.utils.FileUploadUtil;
import com.gits.rms.vo.ClientVO;
import com.gits.rms.vo.ConfigurationVO;
import com.gits.rms.vo.CountryVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RegionVO;

public class ClientAction extends ActionSupport {
    private static final long serialVersionUID = 6757829924296144850L;
    private ClientService cliService = new ClientDaoService();
    private CountryService couService = new CountryDaoService();
    private RegionService regService = new RegionDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private FileUploadUtil fileupload = new FileUploadUtil();
    private ClientVO cli;
    private List<ClientVO> client;
    private List<CountryVO> country;
    private List<RegionVO> region;
    private File logoName;
    private String logoNameFileName;
    private ConfigurationVO config;
    private ConfigurationService configService=new ConfigurationDaoService();
    private List<ConfigurationVO> configList;

    // To get List of Client
    @SkipValidation
    public String getAllClient() {
        client = cliService.getAllClient();
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Client it shows blank Form to enter New Data
    @SkipValidation
    public String setUpClient() {
        if ((cli != null) && (cli.getHcmoclientId() != null)) {
            cli = cliService.getClient(cli.getHcmoclientId());
        }

        return SUCCESS;
    }

    // To get Particular Client Data
    @SkipValidation
    public String clientView() {
        if ((cli != null) && (cli.getHcmoclientId() != null)) {
            cli = cliService.getClient(cli.getHcmoclientId());
        }

        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new Clent or update
    // particular Client Data
   /* public String insertOrUpdateClient() {
        try {
            if (cli.getHcmoclientId() == null) {
                client = cliService.getAllClient();
                if (client.size() == 1) {
                    addActionError(getText("errors.client.restriction"));
                    return INPUT;
                } else {
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

                    if (logoNameFileName != null) {
                    	String folderLocation =
                    			getText("ApplicationAbsolutePath")+
                    			ServletActionContext.getServletContext().getContextPath()+getText("WebContent")+
                    			"resources/images/clientlogo/"+"MASTER_CLIENTID_"+String.valueOf(session.get("MASTER_CLIENT_ID"));

                    	File foldLocation = new File(folderLocation);
                    	boolean exists = foldLocation.exists();

                    	if(exists) {
                    		fileupload.uploadFile(
                            		logoName.getAbsolutePath(),logoNameFileName,
                            		getText("ApplicationAbsolutePath"),ServletActionContext.getServletContext().getContextPath(),
                            		getText("WebContent")+"resources/images/clientlogo/MASTER_CLIENTID_"+session.get("MASTER_CLIENT_ID")+"/");
                    	}else {
                    		boolean createrFolder = foldLocation.mkdir();

                    		if(createrFolder) {
                    			fileupload.uploadFile(
                                		logoName.getAbsolutePath(),
                                		logoNameFileName,
                                		getText("ApplicationAbsolutePath"),ServletActionContext.getServletContext().getContextPath(),
                                		getText("WebContent")+"resources/images/clientlogo/MASTER_CLIENTID_"+session.get("MASTER_CLIENT_ID")+"/");
                    		}
                    	}
                    	cli.setLogoName(logoNameFileName);
                    	addActionMessage(getText("Please logout and login again to make the logo visible"));

                    }

                    cli.setCreated(DateUtils.getCurrentDateTime());
                    cli.setCreatedBy(oEmp);
                    cli.setUpdatedBy(oEmp);
                    cli.setIsActive(1);
                    cliService.insertClient(cli);
                    addActionMessage(getText("Added Successfully"));
                    session.put("CHECK_CLIENT", "AVAILABLE");
                }
            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

                if (logoNameFileName != null) {
                	String folderLocation =
                			getText("ApplicationAbsolutePath")+
                			ServletActionContext.getServletContext().getContextPath()+getText("WebContent")+
                			"resources/images/clientlogo/"+"MASTER_CLIENTID_"+String.valueOf(session.get("MASTER_CLIENT_ID"));

                	File foldLocation = new File(folderLocation);
                	boolean exists = foldLocation.exists();

                	if(exists) {
                		fileupload.uploadFile(
                        		logoName.getAbsolutePath(),logoNameFileName,
                        		getText("ApplicationAbsolutePath"),ServletActionContext.getServletContext().getContextPath(),
                        		getText("WebContent")+"resources/images/clientlogo/MASTER_CLIENTID_"+session.get("MASTER_CLIENT_ID")+"/");

                	}else {
                		boolean createrFolder = foldLocation.mkdir();

                		if(createrFolder) {
                			fileupload.uploadFile(
                            		logoName.getAbsolutePath(),
                            		logoNameFileName,
                            		getText("ApplicationAbsolutePath"),ServletActionContext.getServletContext().getContextPath(),
                            		getText("WebContent")+"resources/images/clientlogo/MASTER_CLIENTID_"+session.get("MASTER_CLIENT_ID")+"/");
                		}
                	}
                	cli.setLogoName(logoNameFileName);
                	addActionMessage(getText("Please logout and login again to make the logo visible"));
                }
                cli.setUpdatedBy(oEmp);
                cliService.updateClient(cli);
                
                String configure=(String) session.get("CONFIGURATION");
                if(configure != null){
	                if(configure.equals("CONFIGURATION")){
	                    configList=configService.getAllConfiguration();
		                    if (!configList.isEmpty()){
		                        for(int i=0;i<configList.size();i++){
		                            ConfigurationVO configuration=configList.get(i);
		                            config=configService.getConfiguration(configuration.getHcmoConfigurationId());
		                        }
		                        config.setStatus("client");
		                        config.setMailConfiguration(config.getMailConfiguration());
		                        config.setCreated(DateUtils.getCurrentDateTime());
		                        config.setCreatedBy(oEmp);
		                        config.setUpdatedBy(oEmp);
		                        config.setIsActive(1);
		                        configService.updateClientConfiguration(config);
		                        
		                        System.out.println("config :"+config);
		                    }
		                }
		            }
                
                addActionMessage(getText("Updated Sucessfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllClients();
        return SUCCESS;
    }*/

    // To delete particular Client data
    @SkipValidation
    public String deleteClient() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        cli.setUpdatedBy(oEmp);
        cliService.deleteClient(cli);
        addActionMessage(getText("Deleted Sucessfully"));

        // For Drop down List
        loadValues.getAllClients();
        return SUCCESS;
    }   

    
    public ClientVO getCli() {
        return cli;
    }

    public void setCli(ClientVO cli) {
        this.cli = cli;
    }

    public List<ClientVO> getClient() {
        return client;
    }

    public void setClient(List<ClientVO> client) {
        this.client = client;
    }

	public File getLogoName() {
		return logoName;
	}

	public void setLogoName(File logoName) {
		this.logoName = logoName;
	}

	public String getLogoNameFileName() {
		return logoNameFileName;
	}

	public void setLogoNameFileName(String logoNameFileName) {
		this.logoNameFileName = logoNameFileName;
	}

    public ConfigurationVO getConfig() {
        return config;
    }

    public void setConfig(ConfigurationVO config) {
        this.config = config;
    }

    public List<ConfigurationVO> getConfigList() {
        return configList;
    }

    public void setConfigList(List<ConfigurationVO> configList) {
        this.configList = configList;
    }
	
}