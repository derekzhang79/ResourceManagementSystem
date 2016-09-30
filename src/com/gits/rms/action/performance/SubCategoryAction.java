package com.gits.rms.action.performance;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.performance.SubCategoryDaoService;
import com.gits.rms.service.performance.SubCategoryService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.QuestionVO;
import com.gits.rms.vo.SubCategoryVO;

public class SubCategoryAction extends ActionSupport{
    
    private static final long serialVersionUID = 3513662380673942697L;
    private SubCategoryVO subCategory;
    private List<SubCategoryVO> subCategoryList;
    private SubCategoryService subCategoryService=new SubCategoryDaoService();
    private int hcmoCategoryId;
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private List<QuestionVO> questionList;
    // To get List of SubCategory
    @SkipValidation
    public String getAllSubCategory(){
        subCategoryList=subCategoryService.getAllSubCategory();
        return SUCCESS;
    }
    
    // when click Edit link it bring particular data into Form or click Add
    // SubCategory it shows blank Form to enter New Data
    @SkipValidation
    public String setUpSubCategory(){
        if((subCategory!=null)&&(subCategory.getHcmoSubCategoryId()!=null)){
            subCategory=subCategoryService.getSubCategory(subCategory.getHcmoSubCategoryId());
        }
        return SUCCESS;
    }

    @SkipValidation
    public String subCategoryListForCategory(){
        loadValues.getAllSubCategoryName(hcmoCategoryId);
        return SUCCESS;
    }
    
 // In the New Form when click Submit button To insert new SubCategory or update
    // particular SubCategory Data
    public String insertOrUpdateSubCategory(){
        try{
        if(subCategory.getHcmoSubCategoryId()==null){
            Map session=ActionContext.getContext().getSession();
            EmployeesVO oEmp=(EmployeesVO) session.get("EMPLOYEE_OBJECT");
            subCategory.setCreated(DateUtils.getCurrentDateTime());
            subCategory.setCreatedBy(oEmp);
            subCategory.setUpdatedBy(oEmp);
            subCategory.setIsActive(1);
            subCategoryService.insertSubCategory(subCategory);
            addActionMessage(getText("Added Successfully"));
        }else{
            Map session=ActionContext.getContext().getSession();
            EmployeesVO oEmp=(EmployeesVO) session.get("EMPLOYEE_OBJECT");
            subCategory.setUpdatedBy(oEmp);
            subCategoryService.updateSubCategory(subCategory);
            addActionMessage(getText("Updated Successfully"));
        }
        }catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        loadValues.getAllSubCategoryName();
        return SUCCESS;
    }
    
    // To delete particular SubCategory data
    @SkipValidation
    public String deleteSubCategory(){
        Map session=ActionContext.getContext().getSession();
        EmployeesVO oEmp=(EmployeesVO) session.get("EMPLOYEE_OBJECT");
        questionList=subCategoryService.checkSubCategoryInQuestion(subCategory);
        if (!questionList.isEmpty()) {
            addActionError(getText("label.header.subCategory.msg.deleteQuestion"));
            return SUCCESS;
        }
        subCategory.setUpdatedBy(oEmp);
        subCategoryService.deleteSubCategory(subCategory);
        addActionMessage(getText("Deleted Successfully"));
        loadValues.getAllSubCategoryName();
        return SUCCESS;
    }

    // To get Particular SubCategory Data
    @SkipValidation
    public String subCategoryView() {
        if ((subCategory != null) && (subCategory.getHcmoSubCategoryId() != null)) {
            subCategory = subCategoryService.getSubCategory(subCategory.getHcmoSubCategoryId());
        }
        return SUCCESS;
    }
    
 // To View SubCategory Search Form
    @SkipValidation
    public String subCategorySearchForm() {
        return SUCCESS;
    }

    // To View SubCategory Search Form Result
    @SkipValidation
    public String subCategorySearchResult() {
        subCategoryList = subCategoryService.subCategorySearchResult(subCategory);
        return SUCCESS;
    }

    public SubCategoryVO getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryVO subCategory) {
        this.subCategory = subCategory;
    }

    public List<SubCategoryVO> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<SubCategoryVO> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public int getHcmoCategoryId() {
        return hcmoCategoryId;
    }

    public void setHcmoCategoryId(int hcmoCategoryId) {
        this.hcmoCategoryId = hcmoCategoryId;
    }
    
    
}
