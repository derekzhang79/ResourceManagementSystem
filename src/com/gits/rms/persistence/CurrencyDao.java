
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.CurrencyVO;

public interface CurrencyDao {

    List currencySearchResult(CurrencyVO currency);

    void deleteCurrency(CurrencyVO currency);

    List getAllCurrency();

    CurrencyVO getCurrency(Integer id);

    String getCurrencyType();

    void insertCurrency(CurrencyVO currency);

    void updateCurrency(CurrencyVO currency);

}
