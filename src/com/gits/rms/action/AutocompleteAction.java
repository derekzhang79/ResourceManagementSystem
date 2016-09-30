package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.bean.autocomplete.CustomerAutocomplete;
import com.gits.rms.bean.autocomplete.DepartmentAutocomplete;
import com.gits.rms.bean.autocomplete.EmployeeAutocomplete;
import com.gits.rms.bean.autocomplete.ProjectActivityAutocomplete;
import com.gits.rms.bean.autocomplete.ProjectAutocomplete;
import com.gits.rms.bean.autocomplete.RoleAutocomplete;
import com.gits.rms.service.AutocompleteDaoService;
import com.gits.rms.service.AutocompleteService;

public class AutocompleteAction extends ActionSupport {

	private Logger logger = Logger.getLogger(AutocompleteAction.class);

	private String search;
	private List<EmployeeAutocomplete> employees;
	private List<RoleAutocomplete> roles;
	private List<CustomerAutocomplete> customers;
	private List<ProjectAutocomplete> projects;
	private List<ProjectActivityAutocomplete> projectActivities;
	private List<DepartmentAutocomplete> departments;

	private AutocompleteService autocompleteService = new AutocompleteDaoService();
	private Map session = ActionContext.getContext().getSession();

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<EmployeeAutocomplete> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeAutocomplete> employees) {
		this.employees = employees;
	}

	public List<RoleAutocomplete> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleAutocomplete> roles) {
		this.roles = roles;
	}

	public List<CustomerAutocomplete> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerAutocomplete> customers) {
		this.customers = customers;
	}

	public List<ProjectAutocomplete> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectAutocomplete> projects) {
		this.projects = projects;
	}

	public List<ProjectActivityAutocomplete> getProjectActivities() {
		return projectActivities;
	}

	public void setProjectActivities(List<ProjectActivityAutocomplete> projectActivities) {
		this.projectActivities = projectActivities;
	}

	public List<DepartmentAutocomplete> getDepartments() {
		return departments;
	}

	public void setDepartments(List<DepartmentAutocomplete> departments) {
		this.departments = departments;
	}

	private Integer getClientId() {
		logger.debug("search : " + search);
		Integer clientId = new Integer(java.sql.Types.NULL);

		if (session.get("CLIENT_INFO") != null) {
			return clientId = (Integer) session.get("CLIENT_INFO");
		}
		logger.debug("clientId : " + clientId);
		clientId = new Integer(1);
		return clientId;
	}

	public String searchEmployees() {

		Integer clientId = this.getClientId();

		if (clientId > 0 && search != null) {

			if (search.length() >= 1) {
				try {
					employees = autocompleteService.iLikeEmployeeAllByProperty(clientId, "empFirstName", search,
							MatchMode.START);
				} catch (Exception e) {
					logger.error("Error while getting employees for autocomplete : " + e);
				}

			}

		}
		return SUCCESS;

	}

	public String searchDepartments() {

		Integer clientId = this.getClientId();

		if (clientId > 0 && search != null) {

			if (search.length() >= 1) {
				departments = autocompleteService.iLikeDepartmentAllByProperty(clientId, "deptName", search,
						MatchMode.START);
			}

		}
		return SUCCESS;

	}

	public String searchProjectss() {

		Integer clientId = this.getClientId();

		if (clientId > 0 && search != null) {

			if (search.length() >= 1) {
				projects = autocompleteService.iLikeProjectAllByProperty(clientId, "projectName", search,
						MatchMode.START);
			}

		}
		return SUCCESS;

	}

	public String searchProjectActivities() {

		Integer clientId = this.getClientId();

		if (clientId > 0 && search != null) {

			if (search.length() >= 1) {
				projectActivities = autocompleteService.iLikeProjectActivityAllByProperty(clientId, "activityName", search,
						MatchMode.START);
			}

		}
		return SUCCESS;

	}

	public String searchRoles() {

		Integer clientId = this.getClientId();

		if (clientId > 0 && search != null) {

			if (search.length() >= 1) {
				roles = autocompleteService.iLikeRoleAllByProperty(clientId, "roleName", search,
						MatchMode.START);
			}

		}
		return SUCCESS;

	}

	public String searchCustomers() {

		Integer clientId = this.getClientId();

		if (clientId > 0 && search != null) {

			if (search.length() >= 1) {
				customers = autocompleteService.iLikeCustomerAllByProperty(clientId, "customerName", search,
						MatchMode.START);
			}

		}
		return SUCCESS;

	}

}
