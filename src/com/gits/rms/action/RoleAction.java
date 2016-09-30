     
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jdom.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.persistence.MongoConfig;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.utils.PropertyUtil;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.MenuVO;
import com.gits.rms.vo.OrganizationVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.xml.utils.Reader;

public class RoleAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 3779275783658162065L;
    static Logger log = Logger.getLogger(RoleAction.class.getName());// for store log details
    private RoleService roleService = new RoleDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private RoleVO role;
    private List<RoleVO> roleList;
    private OrganizationVO org;
    private List<OrganizationVO> orgList;
    private List<EmployeesVO> employeeList;
    private Map session;

    // To get List of Roles
    @SkipValidation
    public String getAllRole() {
        roleList = roleService.getAllRole();
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String roleSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String roleSearchResult() {
        roleList = roleService.roleSearchResult(role);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add Role
    // it shows blank Form to enter New Data
    @SkipValidation
    public String setUpRole() {
        Map newSession = ActionContext.getContext().getSession();
        if ((role != null) && (role.getHcmoRoleId() != null)) {
            role = roleService.getRole(role.getHcmoRoleId());
            String clientId = PropertyUtil.getClientId();
            Document xmlDocument = retriveXMLDocumentFromMongo(clientId);
            Reader.populateRoleFromDocument(role, xmlDocument);
            
            /*String sXmlPath = getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath()
                + getText("MenusXmlPath") + "MASTER_CLIENTID_" + newSession.get("MASTER_CLIENT_ID")
                + "/";
            Reader.populateRoleFromXml(role, sXmlPath);*/
        }
        return SUCCESS;
    }

    @SkipValidation
    public String setUpRole(int RoleId) {
        role = roleService.getRole(RoleId);
        return SUCCESS;
    }

    // To get Particular Role Data
    @SkipValidation
    public String roleView() {
        if ((role != null) && (role.getHcmoRoleId() != null)) {
            role = roleService.getRole(role.getHcmoRoleId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new Role or update
    // particular Role Data
    public String insertOrUpdateRole() {
        Map newSession = ActionContext.getContext().getSession();
        /*String sXmlPath = getText("ApplicationAbsolutePath")
            + ServletActionContext.getServletContext().getContextPath() + getText("MenusXmlPath")
            + "MASTER_CLIENTID_" + newSession.get("MASTER_CLIENT_ID") + "/";*/
        String clientId = PropertyUtil.getClientId();
        String sDestFileName = "";

        try {
            if (role.getHcmoRoleId() == null) {
                //sXmlPath = Reader.saveRoleXml(role, sXmlPath);
            	//clientId = Reader.saveRoleXml(role, clientId);
            	sDestFileName = insertXMLDocumentToMongo(role,clientId);
                role.setXmlPath(sDestFileName);
                
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                role.setCreated(DateUtils.getCurrentDateTime());
                role.setCreatedBy(oEmp);
                role.setUpdatedBy(oEmp);           
                role.setIsActive(1);
                roleService.insertRole(role);
                addActionMessage(getText("Added Successfully"));

            } else {
                //sXmlPath = Reader.saveRoleXml(role, sXmlPath);
                //role.setXmlPath(sXmlPath);
            	sDestFileName = insertXMLDocumentToMongo(role,clientId);
                role.setXmlPath(sDestFileName);

                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                role.setUpdatedBy(oEmp);
                roleService.updateRole(role);
                addActionMessage(getText("Updated Successfully"));
                // Put Menu in Session
                if (role.getRoleName().equals(session.get("ROLE"))) {
                    // Put Menu in Session
                    putMenuInSession();
                }

            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllRoleName();
        return SUCCESS;
    }
 
    
    //this method is to insert the xml document into mongo.
    //Here we are using mongo in unsecure mode.
    @SkipValidation
    public String insertXMLDocumentToMongo(RoleVO role, String clientId) {
    	String sDestFileName = "";
    	try {
		    DB db = MongoConfig.createConnection();
		    String collName = getText("mongodb.rolesMenuCollection");
		    
		    DBCollection collection = db.createCollection(collName, null);
            log.info("Collection created successfully");
            
            sDestFileName = new String(clientId + "_" + role.getRoleName() + ".xml");
            Document docObj = Reader.createDocument(role, clientId);
            BasicDBObject document = new BasicDBObject("RoleXMLFileName", sDestFileName).append("xmlContent", docObj);		
            collection.insert(document);
            
            System.out.println("Document inserted successfully");
    	}
    	catch(Exception e) {
    		log.error("exception occurred during mongo connection" + e);
    	}
    	return sDestFileName;
    }
    
     //this method is to retrive the xml document from mongo.
    //Here we are using mongo in unsecure mode.
    @SkipValidation
    public Document retriveXMLDocumentFromMongo(String clientId) {
    	String sDestFileName = "";
    	Document xmlDocument = null;
    	try {
		    DB db = MongoConfig.createConnection();
		    String collName = getText("mongodb.rolesMenuCollection");
		    
		    DBCollection collection = db.getCollection(collName);  
            sDestFileName = new String(clientId + "_" + role.getRoleName() + ".xml");
            BasicDBObject query = new BasicDBObject();

            query.put("RoleXMLFileName", sDestFileName);
            DBCursor cursor = collection.find(query);
            cursor = collection.find(query);  
          //  Document docObj = Reader.createDocument(role, clientId);
            //BasicDBObject document = new BasicDBObject(sDestFileName,docObj);	
            
          //  collection.insert(document);
            while(cursor.hasNext()) {
                xmlDocument = (Document)cursor.next();
            }
            
            System.out.println("Document selected  successfully");
            log.info("Document selected successfully");
    	}
    	catch(Exception e) {
    		log.error("exception occurred during mongo connection" + e);
    	}
    	return xmlDocument;
    }
    
     //this method is to delete the xml document for particular role from mongo.
    //Here we are using mongo in unsecure mode.
    @SkipValidation
    public String deleteXMLDocumentFromMongo(RoleVO role, String clientId) {
    	String sDestFileName = "";
    	try {
		    DB db = MongoConfig.createConnection();
		    String collName = getText("mongodb.rolesMenuCollection");
		    
		    DBCollection collection = db.getCollection(collName);  
            sDestFileName = new String(clientId + "_" + role.getRoleName() + ".xml");
            
            BasicDBObject query = new BasicDBObject();
            query.put("RoleXMLFileName", sDestFileName);
            
            collection.remove(query);
            
            System.out.println("Document deleted successfully");
            log.info("Document deleted successfully");
    	}
    	catch(Exception e) {
    		log.error("exception occurred during mongo connection" + e);
    	}
    	return sDestFileName;
    } 
    
    // To delete Particular Role
    @SkipValidation
    public String deleteRole() {
        // To check that the Role is being used in the Employee.If Yes then we
        // are not allowing the Role to get delete.
        Map newSession = ActionContext.getContext().getSession();
        String clientId = PropertyUtil.getClientId();
        
        employeeList = roleService.checkRoleInEmployee(role);
        if (!employeeList.isEmpty()) {
            addActionError(getText("label.header.role.msg.deleteEmployee"));
            return SUCCESS;
        }
        /*String sXmlPath = getText("ApplicationAbsolutePath")
            + ServletActionContext.getServletContext().getContextPath() + getText("MenusXmlPath")
            + "MASTER_CLIENTID_" + newSession.get("MASTER_CLIENT_ID") + "/";*/
        // Set Up the Role
        setUpRole(role.getHcmoRoleId());
        // Delete Role Xml
        //Reader.deleteRoleXml(role, sXmlPath);
        deleteXMLDocumentFromMongo(role, clientId);
        if (role.getRoleName().equals(session.get("ROLE"))) {
            // Put Menu in Session
            putMenuInSession();
        }
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        role.setUpdatedBy(oEmp);
        roleService.deleteRole(role);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllRoleName();
        return SUCCESS;
    }

    private void putMenuInSession() {
        // Put MenuList in Session
        MenuAction menuAction = new MenuAction();
        List<MenuVO> menuList = menuAction.createAppMenu();
        session.put("menuList", menuList);
    }

    public RoleVO getRole() {
        return role;
    }

    public void setRole(RoleVO role) {
        this.role = role;
    }

    public List<RoleVO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleVO> roleList) {
        this.roleList = roleList;
    }

    public OrganizationVO getOrg() {
        return org;
    }

    public void setOrg(OrganizationVO org) {
        this.org = org;
    }

    public List<OrganizationVO> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<OrganizationVO> orgList) {
        this.orgList = orgList;
    }

    @Override
    public void setSession(Map<String, Object> arg0) {
        session = arg0;
    }

}