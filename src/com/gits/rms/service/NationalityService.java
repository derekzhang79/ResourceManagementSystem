
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.NationalityVO;

public interface NationalityService {

    List checkNationalityInEmployee(NationalityVO nati);

    void deleteNationality(NationalityVO nati);

    List getAllNationality();

    List getAllNationalityCronJob(String clientId);

    NationalityVO getNationality(Integer id);

    void insertNationality(NationalityVO nati);

    List nationalitySearchResult(NationalityVO nati);

    void updateNationality(NationalityVO nati);
}
