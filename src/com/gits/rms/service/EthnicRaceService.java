
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EthnicRaceVO;

public interface EthnicRaceService {

    List checkEthnicRaceInEmployee(EthnicRaceVO ethRace);

    void deleteEthnicRace(EthnicRaceVO ethRace);

    List ethnicRaceSearchResult(EthnicRaceVO ethRace);

    List getAllEthnicRace();

    EthnicRaceVO getEthnicRace(Integer id);

    void insertEthnicRace(EthnicRaceVO ethRace);

    void updateEthnicRace(EthnicRaceVO ethRace);
}
