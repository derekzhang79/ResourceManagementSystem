
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.ImportantNewsVO;

public interface ImportantNewsDao {
    List getAllImportantNews();

    ImportantNewsVO getImportantNews(Integer id);

    void insertImportantNews(ImportantNewsVO imp);

    void updateImportantNews(ImportantNewsVO imp);

    void deleteImportantNews(ImportantNewsVO imp);

    List importantNewsSearchResult(ImportantNewsVO imp);
}
