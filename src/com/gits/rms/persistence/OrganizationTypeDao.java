
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.OrganizationTypeVO;

public interface OrganizationTypeDao {

    List getAllOrganizationType();

    OrganizationTypeVO getOrganizationType(Integer id);

    void updateOrganizationType(OrganizationTypeVO orgtype);

    void insertOrganizationType(OrganizationTypeVO orgtype);

    void deleteOrganizationType(OrganizationTypeVO orgtype);

    List organizationTypeSearchResult(OrganizationTypeVO orgtype);

    List checkOrgTypeInOrganization(OrganizationTypeVO orgtype);
}
