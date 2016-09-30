
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.OrganizationVO;

public interface OrganizationDao {

    List getAllOrganization();

    OrganizationVO getOrganization(Integer id);

    void insertOrganization(OrganizationVO org);

    void updateOrganization(OrganizationVO org);

    void deleteOrganization(OrganizationVO org);

    List checkOrganizationInOrganization(OrganizationVO org);

    List organizationSearchResult(OrganizationVO org);

    List checkOrganizationInProject(OrganizationVO org);

    List checkOrganizationInLeaveType(OrganizationVO org);

    List checkOrganizationInRole(OrganizationVO org);

    List checkOrganizationInEmployees(OrganizationVO org);

    List checkOrganizationInEmpLocHistory(OrganizationVO org);
}