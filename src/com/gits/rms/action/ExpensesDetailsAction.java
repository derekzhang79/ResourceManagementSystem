
package com.gits.rms.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.ExpensesDetailsDaoService;
import com.gits.rms.service.ExpensesDetailsService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpensesDetailsVO;
import com.gits.rms.vo.ExpensesTypeVO;

public class ExpensesDetailsAction extends ActionSupport implements ServletRequestAware {

    private static final long serialVersionUID = -6892491634292489013L;
    private ExpensesDetailsService expDetailsService = new ExpensesDetailsDaoService();
    private ExpensesDetailsVO expDetails;
    private List<ExpensesDetailsVO> expDetailsList;
    private EmployeeExpensesVO empExpenses;
    private HttpServletRequest request;

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        request = httpServletRequest;
    }

    @SkipValidation
    public String getAllExpDetails() {
        expDetailsList = expDetailsService.getAllExpDetails();
        return SUCCESS;
    }

    @SkipValidation
    public String setUpInsertOrUpdateExpDetails() {
        if ((expDetails != null) && (expDetails.getHcmoExpensesDetailsId() != null)) {
            expDetails = expDetailsService.getExpDetails(expDetails.getHcmoExpensesDetailsId());
        }
        return SUCCESS;
    }

    public String insertOrUpdateExpDetails() {
        if (expDetails.getHcmoExpensesDetailsId() == null) {
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            expDetails.setCreated(DateUtils.getCurrentDateTime());
            expDetails.setCreatedBy(oEmp);
            expDetails.setUpdatedBy(oEmp);
            expDetails.setIsActive(1);
            expDetailsService.insertExpDetails(expDetails);
        } else {
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            expDetails.setUpdatedBy(oEmp);
            expDetailsService.updateExpDetails(expDetails);
        }
        return SUCCESS;
    }

    public String insertOrUpdateEmpExpensesDetails() {
        BigDecimal amount = null;
        Date expensesDate = null;
        String note = null;
        String description = null;
        String[] amountRequest = null;
        String[] expensesDateRequest = null;
        String[] hcmoExpensesTypeIdRequest = null;
        String[] noteRequest = null;
        String[] descriptionRequest = null;
        SimpleDateFormat simpleDateFormat;
        Date currentDate = new Date();
        ExpensesDetailsVO expensesDetailsVO = null;
        ExpensesTypeVO expensesTypeVO = null;

        if (request.getParameterValues("amount").length != 0) {
            amountRequest = request.getParameterValues("amount");
        }
        if (request.getParameterValues("expensesDate").length != 0) {
            expensesDateRequest = request.getParameterValues("expensesDate");
        }
        if (request.getParameterValues("note").length != 0) {
            noteRequest = request.getParameterValues("note");
        }
        if (request.getParameterValues("description").length != 0) {
            descriptionRequest = request.getParameterValues("description");
        }

        if (request.getParameterValues("hcmoExpensesTypeId").length != 0) {
            hcmoExpensesTypeIdRequest = request.getParameterValues("hcmoExpensesTypeId");
        }
        Map session = ActionContext.getContext().getSession();

        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empExpenses.setHcmoEmployeeId(oEmp);
        if ((empExpenses.getHcmoExpensesId() == null)
            || (expDetails.getHcmoExpensesDetailsId() == null)) {
            empExpenses.setHcmoEmployeeId(oEmp);

            empExpenses.setHcmoEmployeeId(oEmp);
            empExpenses.setCreated(DateUtils.getCurrentDateTime());
            empExpenses.setCreatedBy(oEmp);
            empExpenses.setUpdated(new Timestamp(currentDate.getTime()));
            empExpenses.setUpdatedBy(oEmp);
            empExpenses.setIsActive(1);
            for (int i = 0; i < amountRequest.length; i++) {
                Integer.parseInt(hcmoExpensesTypeIdRequest[i]);
                expensesDetailsVO = new ExpensesDetailsVO();
                amount = new BigDecimal(amountRequest[i]);
                simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    expensesDate = simpleDateFormat.parse(expensesDateRequest[i]);
                } catch (ParseException e) {

                    e.printStackTrace();
                }
                note = noteRequest[i];
                description = descriptionRequest[i];
                expensesDetailsVO.setAmount(amount);
                expensesDetailsVO.setDescription(description);
                expensesDetailsVO.setExpensesDate(expensesDate);
                expensesDetailsVO.setNote(note);
                expensesDetailsVO.setHcmoExpensesTypeId(expensesTypeVO);
                expensesDetailsVO.setCreated(currentDate);
                expensesDetailsVO.setCreatedBy(oEmp);
                expensesDetailsVO.setUpdated(new Timestamp(currentDate.getTime()));
                expensesDetailsVO.setUpdatedBy(oEmp);
                expensesDetailsVO.setIsActive(1);
                expensesDetailsVO.setHcmoExpensesId(empExpenses);
            }

        } else {
            expensesDetailsVO = new ExpensesDetailsVO();
            empExpenses.setUpdated(new Timestamp(currentDate.getTime()));
            empExpenses.setUpdatedBy(oEmp);
            expensesDetailsVO.setUpdated(new Timestamp(currentDate.getTime()));
            expensesDetailsVO.setUpdatedBy(oEmp);
        }
        return SUCCESS;
    }

    @SkipValidation
    public String deleteExpDetails() {
        expDetailsService.deleteExpDetails(expDetails.getHcmoExpensesDetailsId());
        return SUCCESS;
    }

    public ExpensesDetailsVO getExpDetails() {
        return expDetails;
    }

    public void setExpDetails(ExpensesDetailsVO expDetails) {
        this.expDetails = expDetails;
    }

    public List<ExpensesDetailsVO> getExpDetailsList() {
        return expDetailsList;
    }

    public void setExpDetails(List<ExpensesDetailsVO> expDetailsList) {
        this.expDetailsList = expDetailsList;
    }

    /**
     * @return the empExpenses
     */
    public EmployeeExpensesVO getEmpExpenses() {
        return empExpenses;
    }

    /**
     * @param empExpenses
     *            the empExpenses to set
     */
    public void setEmpExpenses(EmployeeExpensesVO empExpenses) {
        this.empExpenses = empExpenses;
    }
}