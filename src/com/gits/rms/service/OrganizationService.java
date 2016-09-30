
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.OrganizationVO;

public interface OrganizationService {

    List checkOrganizationInEmpLocHistory(OrganizationVO org);

    List checkOrganizationInEmployees(OrganizationVO org);

    List checkOrganizationInLeaveType(OrganizationVO org);

    List checkOrganizationInOrganization(OrganizationVO org);

    List checkOrganizationInProject(OrganizationVO org);

    List checkOrganizationInRole(OrganizationVO org);

    void deleteOrganization(OrganizationVO org);

    List getAllOrganization();

    OrganizationVO getOrganization(Integer id);

    void insertOrganization(OrganizationVO org);

    List organizationSearchResult(OrganizationVO org);

    void updateOrganization(OrganizationVO org);
}
