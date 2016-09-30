package com.gits.rms.service.performance;

import java.util.List;

import com.gits.rms.persistence.performance.SubCategoryDao;
import com.gits.rms.persistence.performance.SubCategoryHibernateDao;
import com.gits.rms.vo.SubCategoryVO;

public class SubCategoryDaoService implements SubCategoryService{
    
    private SubCategoryDao dao;
    
    public SubCategoryDaoService(){
        dao = new SubCategoryHibernateDao();
    }

    @Override
    public List getAllSubCategory() {
        return dao.getAllSubCategory();
    }

    @Override
    public SubCategoryVO getSubCategory(Integer id) {
        return dao.getSubCategory(id);
    }

    @Override
    public void insertSubCategory(SubCategoryVO subCategory) {
        dao.insertSubCategory(subCategory);
    }

    @Override
    public void updateSubCategory(SubCategoryVO subCategory) {
        dao.updateSubCategory(subCategory);
    }

    @Override
    public void deleteSubCategory(SubCategoryVO subCategory) {
        dao.deleteSubCategory(subCategory);
    }
    
    @Override
    public List subCategorySearchResult(SubCategoryVO subCategory){
        return dao.subCategorySearchResult(subCategory);
    }
    
    @Override
    public List subCategoryForCategory(Integer id){
        return dao.subCategoryForCategory(id);
    }
    
    @Override
    public List checkSubCategoryInQuestion(SubCategoryVO subCategory){
        return dao.checkSubCategoryInQuestion(subCategory);
    }
}
