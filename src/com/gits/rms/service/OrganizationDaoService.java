
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.OrganizationDao;
import com.gits.rms.persistence.OrganizationHibernateDao;
import com.gits.rms.vo.OrganizationVO;

public class OrganizationDaoService implements OrganizationService {
    private OrganizationDao dao;

    public OrganizationDaoService() {
        dao = new OrganizationHibernateDao();
    }

    @Override
    public List checkOrganizationInEmpLocHistory(OrganizationVO org) {
        return dao.checkOrganizationInEmpLocHistory(org);
    }

    @Override
    public List checkOrganizationInEmployees(OrganizationVO org) {
        return dao.checkOrganizationInEmployees(org);
    }

    @Override
    public List checkOrganizationInLeaveType(OrganizationVO org) {
        return dao.checkOrganizationInLeaveType(org);
    }

    @Override
    public List checkOrganizationInOrganization(OrganizationVO org) {
        return dao.checkOrganizationInOrganization(org);
    }

    @Override
    public List checkOrganizationInProject(OrganizationVO org) {
        return dao.checkOrganizationInProject(org);
    }

    @Override
    public List checkOrganizationInRole(OrganizationVO org) {
        return dao.checkOrganizationInRole(org);
    }

    @Override
    public void deleteOrganization(OrganizationVO org) {
        dao.deleteOrganization(org);
    }

    @Override
    public List getAllOrganization() {
        return dao.getAllOrganization();
    }

    @Override
    public OrganizationVO getOrganization(Integer id) {
        return dao.getOrganization(id);
    }

    @Override
    public void insertOrganization(OrganizationVO org) {
        dao.insertOrganization(org);
    }

    @Override
    public List organizationSearchResult(OrganizationVO org) {
        return dao.organizationSearchResult(org);
    }

    @Override
    public void updateOrganization(OrganizationVO org) {
        dao.updateOrganization(org);
    }
}
