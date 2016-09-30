
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.CountryVO;

public interface CountryDao {

    List checkCountryInClient(CountryVO cou);

    List checkCountryInEmployees(CountryVO cou);

    List checkCountryInLocation(CountryVO cou);

    List checkCountryInPassport(CountryVO cou);

    List checkCountryInRegion(CountryVO cou);

    List countrySearchResult(CountryVO cou);

    void deleteCountry(CountryVO cou);

    List getAllCountry();

    List getCountriesStartingWith(String sCountry);

    CountryVO getCountry(Integer id);

    void insertCountry(CountryVO cou);

    void updateCountry(CountryVO cou);
}
