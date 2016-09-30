package com.gits.rms.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.gits.rms.bean.autocomplete.CustomerAutocomplete;
import com.gits.rms.bean.autocomplete.DepartmentAutocomplete;
import com.gits.rms.bean.autocomplete.EmployeeAutocomplete;
import com.gits.rms.bean.autocomplete.ProjectActivityAutocomplete;
import com.gits.rms.bean.autocomplete.ProjectAutocomplete;
import com.gits.rms.bean.autocomplete.RoleAutocomplete;

public interface AutocompleteService extends Serializable {

	List<EmployeeAutocomplete> iLikeEmployeeAllByProperty(Integer clientId, String propertyName, Object propertyValue,
			MatchMode matchMode);

	List<DepartmentAutocomplete> iLikeDepartmentAllByProperty(Integer clientId, String propertyName,
			Object propertyValue, MatchMode matchMode);

	List<RoleAutocomplete> iLikeRoleAllByProperty(Integer clientId, String propertyName, Object propertyValue,
			MatchMode matchMode);

	List<CustomerAutocomplete> iLikeCustomerAllByProperty(Integer clientId, String propertyName, Object propertyValue,
			MatchMode matchMode);

	List<ProjectAutocomplete> iLikeProjectAllByProperty(Integer clientId, String propertyName, Object propertyValue,
			MatchMode matchMode);

	List<ProjectActivityAutocomplete> iLikeProjectActivityAllByProperty(Integer clientId, String propertyName,
			Object propertyValue, MatchMode matchMode);
}
