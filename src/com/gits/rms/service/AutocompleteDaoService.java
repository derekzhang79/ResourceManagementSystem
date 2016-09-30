package com.gits.rms.service;

import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.gits.rms.bean.autocomplete.CustomerAutocomplete;
import com.gits.rms.bean.autocomplete.DepartmentAutocomplete;
import com.gits.rms.bean.autocomplete.EmployeeAutocomplete;
import com.gits.rms.bean.autocomplete.ProjectActivityAutocomplete;
import com.gits.rms.bean.autocomplete.ProjectAutocomplete;
import com.gits.rms.bean.autocomplete.RoleAutocomplete;
import com.gits.rms.persistence.AutocompleteDao;
import com.gits.rms.persistence.AutocompleteHibernateDao;

public class AutocompleteDaoService implements AutocompleteService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4894350819525572828L;
	
    private AutocompleteDao dao;

    public AutocompleteDaoService() {
        dao = new AutocompleteHibernateDao();
    }

	@Override
	public List<EmployeeAutocomplete> iLikeEmployeeAllByProperty(Integer clientId, String propertyName,
			Object propertyValue, MatchMode matchMode) {
		return dao.iLikeEmployeeAllByProperty(clientId, propertyName, propertyValue, matchMode);
	}

	@Override
	public List<DepartmentAutocomplete> iLikeDepartmentAllByProperty(Integer clientId, String propertyName,
			Object propertyValue, MatchMode matchMode) {
		return dao.iLikeDepartmentAllByProperty(clientId, propertyName, propertyValue, matchMode);
	}

	@Override
	public List<RoleAutocomplete> iLikeRoleAllByProperty(Integer clientId, String propertyName, Object propertyValue,
			MatchMode matchMode) {
		return dao.iLikeRoleAllByProperty(clientId, propertyName, propertyValue, matchMode);
	}

	@Override
	public List<CustomerAutocomplete> iLikeCustomerAllByProperty(Integer clientId, String propertyName,
			Object propertyValue, MatchMode matchMode) {
		return dao.iLikeCustomerAllByProperty(clientId, propertyName, propertyValue, matchMode);
	}

	@Override
	public List<ProjectAutocomplete> iLikeProjectAllByProperty(Integer clientId, String propertyName,
			Object propertyValue, MatchMode matchMode) {
		return dao.iLikeProjectAllByProperty(clientId, propertyName, propertyValue, matchMode);
	}

	@Override
	public List<ProjectActivityAutocomplete> iLikeProjectActivityAllByProperty(Integer clientId, String propertyName,
			Object propertyValue, MatchMode matchMode) {
		return dao.iLikeProjectActivityAllByProperty(clientId, propertyName, propertyValue, matchMode);
	}

}
