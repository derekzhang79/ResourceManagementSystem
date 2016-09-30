package com.gits.rms.action.performance;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.performance.CategoryDaoService;
import com.gits.rms.service.performance.CategoryService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.CategoryVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.QuestionVO;

public class CategoryAction extends ActionSupport{

    private static final long serialVersionUID = 3513662380673942697L;
    private CategoryVO category;
    private List<CategoryVO> categoryList;
    private CategoryService categoryService=new CategoryDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private List<QuestionVO> questionList;
    
    // To get List of Category
    @SkipValidation
    public String getAllCategory(){
        categoryList=categoryService.getAllCategory();
        return SUCCESS;
    }
    
    // when click Edit link it bring particular data into Form or click Add
    // Category it shows blank Form to enter New Data
    @SkipValidation
    public String setUpCategory(){
        if((category!=null)&&(category.getHcmoCategoryId()!=null)){
            category=categoryService.getCategory(category.getHcmoCategoryId());
        }
        return SUCCESS;
    }
    
 // In the New Form when click Submit button To insert new Category or update
    // particular Category Data
    public String insertOrUpdateCategory(){
        try{
        if(category.getHcmoCategoryId()==null){
            Map session=ActionContext.getContext().getSession();
            EmployeesVO oEmp=(EmployeesVO) session.get("EMPLOYEE_OBJECT");
            category.setCreated(DateUtils.getCurrentDateTime());
            category.setCreatedBy(oEmp);
            category.setUpdatedBy(oEmp);
            category.setIsActive(1);
            categoryService.insertCategory(category);
            addActionMessage(getText("Added Successfully"));
        }else{
            Map session=ActionContext.getContext().getSession();
            EmployeesVO oEmp=(EmployeesVO) session.get("EMPLOYEE_OBJECT");
            category.setUpdatedBy(oEmp);
            categoryService.updateCategory(category);
            addActionMessage(getText("Updated Successfully"));
        }
        }catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        loadValues.getAllCategoryName();
        return SUCCESS;
    }
    
    // To delete particular Category data
    @SkipValidation
    public String deleteCategory(){
        Map session=ActionContext.getContext().getSession();
        EmployeesVO oEmp=(EmployeesVO) session.get("EMPLOYEE_OBJECT");
        
        questionList=categoryService.checkCategoryInQuestion(category);
        if (!questionList.isEmpty()) {
            addActionError(getText("label.header.category.msg.deleteQuestion"));
            return SUCCESS;
        }
        category.setUpdatedBy(oEmp);
        categoryService.deleteCategory(category);
        addActionMessage(getText("Deleted Successfully"));
        loadValues.getAllCategoryName();
        return SUCCESS;
    }

    // To get Particular Category Data
    @SkipValidation
    public String categoryView() {
        if ((category != null) && (category.getHcmoCategoryId() != null)) {
            category = categoryService.getCategory(category.getHcmoCategoryId());
        }
        return SUCCESS;
    }
    
    
 // To View Category Search Form
    @SkipValidation
    public String categorySearchForm() {
        return SUCCESS;
    }

    // To View Category Search Form Result
    @SkipValidation
    public String categorySearchResult() {
        categoryList = categoryService.categorySearchResult(category);
        return SUCCESS;
    }

    
    public CategoryVO getCategory() {
        return category;
    }

    public void setCategory(CategoryVO category) {
        this.category = category;
    }

    public List<CategoryVO> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryVO> categoryList) {
        this.categoryList = categoryList;
    }

    public List<QuestionVO> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionVO> questionList) {
        this.questionList = questionList;
    }
    
    
}
