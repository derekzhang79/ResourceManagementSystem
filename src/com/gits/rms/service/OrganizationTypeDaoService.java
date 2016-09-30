
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.OrganizationTypeDao;
import com.gits.rms.persistence.OrganizationTypeHibernateDao;
import com.gits.rms.vo.OrganizationTypeVO;

public class OrganizationTypeDaoService implements OrganizationTypeService {
    private OrganizationTypeDao dao;

    public OrganizationTypeDaoService() {
        dao = new OrganizationTypeHibernateDao();
    }

    @Override
    public List checkOrgTypeInOrganization(OrganizationTypeVO orgtype) {
        return dao.checkOrgTypeInOrganization(orgtype);
    }

    @Override
    public void deleteOrganizationType(OrganizationTypeVO orgtype) {
        dao.deleteOrganizationType(orgtype);
    }

    @Override
    public List getAllOrganizationType() {
        return dao.getAllOrganizationType();
    }

    @Override
    public OrganizationTypeVO getOrganizationType(Integer id) {
        return dao.getOrganizationType(id);
    }

    @Override
    public void insertOrganizationType(OrganizationTypeVO orgtype) {
        dao.insertOrganizationType(orgtype);
    }

    @Override
    public List organizationTypeSearchResult(OrganizationTypeVO orgtype) {
        return dao.organizationTypeSearchResult(orgtype);
    }

    @Override
    public void updateOrganizationType(OrganizationTypeVO orgtype) {
        dao.updateOrganizationType(orgtype);
    }
}
