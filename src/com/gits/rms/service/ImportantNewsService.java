
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.ImportantNewsVO;

public interface ImportantNewsService {

    void deleteImportantNews(ImportantNewsVO imp);

    List getAllImportantNews();

    ImportantNewsVO getImportantNews(Integer id);

    List importantNewsSearchResult(ImportantNewsVO imp);

    void insertImportantNews(ImportantNewsVO imp);

    void updateImportantNews(ImportantNewsVO imp);
}
