
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.CurrencyVO;

public interface CurrencyService {

    List currencySearchResult(CurrencyVO currency);

    void deleteCurrency(CurrencyVO currency);

    List getAllCurrency();

    CurrencyVO getCurrency(Integer id);

    String getCurrencyType();

    void insertCurrency(CurrencyVO currency);

    void updateCurrency(CurrencyVO currency);

}
