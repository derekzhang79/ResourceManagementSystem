package com.gits.rms.service.performance;

import java.util.List;

import com.gits.rms.persistence.performance.CategoryDao;
import com.gits.rms.persistence.performance.CategoryHibernateDao;
import com.gits.rms.vo.CategoryVO;
import com.gits.rms.vo.QuestionVO;

public class CategoryDaoService implements CategoryService{

    private CategoryDao dao;
    
    public CategoryDaoService(){
        dao = new CategoryHibernateDao();
    }

    @Override
    public List getAllCategory() {
        return dao.getAllCategory();
    }

    @Override
    public CategoryVO getCategory(Integer id) {
        return dao.getCategory(id);
    }

    @Override
    public void insertCategory(CategoryVO category) {
        dao.insertCategory(category);
    }

    @Override
    public void updateCategory(CategoryVO category) {
        dao.updateCategory(category);
    }

    @Override
    public void deleteCategory(CategoryVO category) {
        dao.deleteCategory(category);
    }
    
    @Override
    public List categorySearchResult(CategoryVO category){
        return dao.categorySearchResult(category);
    }
    
    @Override
    public List checkCategoryInQuestion(CategoryVO category){
        return dao.checkCategoryInQuestion(category);
    }
}
