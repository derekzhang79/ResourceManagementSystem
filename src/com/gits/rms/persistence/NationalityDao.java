
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.NationalityVO;

public interface NationalityDao {

    List checkNationalityInEmployee(NationalityVO nati);

    void deleteNationality(NationalityVO nati);

    List getAllNationality();

    List getAllNationalityCronJob(String clientId);

    NationalityVO getNationality(Integer id);

    void insertNationality(NationalityVO nati);

    List nationalitySearchResult(NationalityVO nati);

    void updateNationality(NationalityVO nati);
}
