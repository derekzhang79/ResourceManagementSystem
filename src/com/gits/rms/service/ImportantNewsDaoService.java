
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.ImportantNewsDao;
import com.gits.rms.persistence.ImportantNewsHibernateDao;
import com.gits.rms.vo.ImportantNewsVO;

public class ImportantNewsDaoService implements ImportantNewsService {

    private ImportantNewsDao dao;

    public ImportantNewsDaoService() {
        dao = new ImportantNewsHibernateDao();
    }

    @Override
    public void deleteImportantNews(ImportantNewsVO imp) {
        dao.deleteImportantNews(imp);
    }

    @Override
    public List getAllImportantNews() {
        return dao.getAllImportantNews();
    }

    @Override
    public ImportantNewsVO getImportantNews(Integer id) {
        return dao.getImportantNews(id);
    }

    @Override
    public List importantNewsSearchResult(ImportantNewsVO imp) {
        return dao.importantNewsSearchResult(imp);
    }

    @Override
    public void insertImportantNews(ImportantNewsVO imp) {
        dao.insertImportantNews(imp);
    }

    @Override
    public void updateImportantNews(ImportantNewsVO imp) {
        dao.updateImportantNews(imp);
    }
}
