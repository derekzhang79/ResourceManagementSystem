
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.OrganizationTypeVO;

public interface OrganizationTypeService {

    List checkOrgTypeInOrganization(OrganizationTypeVO orgtype);

    void deleteOrganizationType(OrganizationTypeVO orgtype);

    List getAllOrganizationType();

    OrganizationTypeVO getOrganizationType(Integer id);

    void insertOrganizationType(OrganizationTypeVO orgtype);

    List organizationTypeSearchResult(OrganizationTypeVO orgtype);

    void updateOrganizationType(OrganizationTypeVO orgtype);
}
