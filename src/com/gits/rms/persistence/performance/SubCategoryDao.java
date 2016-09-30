package com.gits.rms.persistence.performance;

import java.util.List;

import com.gits.rms.vo.CategoryVO;
import com.gits.rms.vo.SubCategoryVO;

public interface SubCategoryDao {
    
    List getAllSubCategory();

    SubCategoryVO getSubCategory(Integer id);
    
    void insertSubCategory(SubCategoryVO subCategory);

    void updateSubCategory(SubCategoryVO subCategory);
    
    void deleteSubCategory(SubCategoryVO subCategory);
    
    List subCategorySearchResult(SubCategoryVO subCategory);
    
    List subCategoryForCategory(Integer id);
    
    List checkSubCategoryInQuestion(SubCategoryVO subCategory);
}
