package com.gits.rms.persistence;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.gits.rms.bean.autocomplete.CustomerAutocomplete;
import com.gits.rms.bean.autocomplete.DepartmentAutocomplete;
import com.gits.rms.bean.autocomplete.EmployeeAutocomplete;
import com.gits.rms.bean.autocomplete.ProjectActivityAutocomplete;
import com.gits.rms.bean.autocomplete.ProjectAutocomplete;
import com.gits.rms.bean.autocomplete.RoleAutocomplete;
import com.gits.rms.vo.CustomerVO;
import com.gits.rms.vo.DepartmentVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.RoleVO;

public class AutocompleteHibernateDao implements AutocompleteDao {

	private Logger logger = Logger.getLogger(AutocompleteHibernateDao.class);
	
	@Override
	@SuppressWarnings("unchecked")
	public List<EmployeeAutocomplete> iLikeEmployeeAllByProperty(Integer clientId, String propertyName, Object propertyValue, MatchMode matchMode){
		logger.debug("propertyName : " + propertyName);
		logger.debug("propertyValue : " + propertyValue);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(EmployeesVO.class);
		//client entity mapped
		//criteria.add(Restrictions.eq("client.clientId", clientId));
		criteria.add(Restrictions.like(propertyName, propertyValue.toString(), matchMode));

		ProjectionList columns = Projections.projectionList();
		columns.add(Projections.property("employeeId"), "id");
		columns.add(Projections.property("empFirstName"), "firstName");
		columns.add(Projections.property("empLastName"), "lastName");
		columns.add(Projections.property("empFullName"), "fullName");
		criteria.setProjection(columns).setResultTransformer( Transformers.aliasToBean(EmployeeAutocomplete.class) );
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DepartmentAutocomplete> iLikeDepartmentAllByProperty(Integer clientId, String propertyName, Object propertyValue, MatchMode matchMode) {
		logger.debug("propertyName : " + propertyName);
		logger.debug("propertyValue : " + propertyValue);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(DepartmentVO.class);
		criteria.add(Restrictions.eq("clientId", clientId));
		criteria.add(Restrictions.ilike(propertyName, propertyValue.toString(), matchMode));
		ProjectionList columns = Projections.projectionList();
		columns.add(Projections.property("hcmoDepartmentId"), "id");
		columns.add(Projections.property("deptName"), "name");
		criteria.setProjection(columns).setResultTransformer( Transformers.aliasToBean(DepartmentAutocomplete.class) );
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<RoleAutocomplete> iLikeRoleAllByProperty(Integer clientId, String propertyName, Object propertyValue, MatchMode matchMode) {
		logger.debug("propertyName : " + propertyName);
		logger.debug("propertyValue : " + propertyValue);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(RoleVO.class);
		//client entity mapped
		//criteria.add(Restrictions.eq("clientId", clientId));
		criteria.add(Restrictions.ilike(propertyName, propertyValue.toString(), matchMode));
		ProjectionList columns = Projections.projectionList();
		columns.add(Projections.property("hcmoRoleId"), "id");
		columns.add(Projections.property("roleName"), "name");
		criteria.setProjection(columns).setResultTransformer( Transformers.aliasToBean(RoleAutocomplete.class) );
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CustomerAutocomplete> iLikeCustomerAllByProperty(Integer clientId, String propertyName, Object propertyValue, MatchMode matchMode) {
		logger.debug("propertyName : " + propertyName);
		logger.debug("propertyValue : " + propertyValue);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(CustomerVO.class);
		criteria.add(Restrictions.eq("clientId", clientId));
		criteria.add(Restrictions.ilike(propertyName, propertyValue.toString(), matchMode));
		ProjectionList columns = Projections.projectionList();
		columns.add(Projections.property("customerId"), "id");
		columns.add(Projections.property("customerName"), "name");
		criteria.setProjection(columns).setResultTransformer( Transformers.aliasToBean(CustomerAutocomplete.class) );
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ProjectAutocomplete> iLikeProjectAllByProperty(Integer clientId, String propertyName, Object propertyValue, MatchMode matchMode) {
		logger.debug("propertyName : " + propertyName);
		logger.debug("propertyValue : " + propertyValue);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(ProjectVO.class);
		criteria.add(Restrictions.eq("clientId", clientId));
		criteria.add(Restrictions.ilike(propertyName, propertyValue.toString(), matchMode));
		ProjectionList columns = Projections.projectionList();
		columns.add(Projections.property("projectId"), "id");
		columns.add(Projections.property("projectName"), "name");
		criteria.setProjection(columns).setResultTransformer( Transformers.aliasToBean(ProjectAutocomplete.class) );
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ProjectActivityAutocomplete> iLikeProjectActivityAllByProperty(Integer clientId, String propertyName, Object propertyValue, MatchMode matchMode) {
		logger.debug("propertyName : " + propertyName);
		logger.debug("propertyValue : " + propertyValue);
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(ProjectActivityVO.class);
		criteria.add(Restrictions.eq("clientId", clientId));
		criteria.add(Restrictions.ilike(propertyName, propertyValue.toString(), matchMode));
		ProjectionList columns = Projections.projectionList();
		columns.add(Projections.property("projectActivityId"), "id");
		columns.add(Projections.property("activityName"), "name");
		criteria.setProjection(columns).setResultTransformer( Transformers.aliasToBean(ProjectActivityAutocomplete.class) );
		return criteria.list();
	}
}
