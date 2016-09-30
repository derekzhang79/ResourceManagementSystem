package com.gits.rms.service.performance;

import java.util.List;

import com.gits.rms.vo.CategoryVO;
import com.gits.rms.vo.QuestionVO;

public interface CategoryService {

    List getAllCategory();

    CategoryVO getCategory(Integer id);
    
    void insertCategory(CategoryVO category);

    void updateCategory(CategoryVO category);
    
    void deleteCategory(CategoryVO category);
    
    List categorySearchResult(CategoryVO category);
    
    List checkCategoryInQuestion(CategoryVO category);
}
