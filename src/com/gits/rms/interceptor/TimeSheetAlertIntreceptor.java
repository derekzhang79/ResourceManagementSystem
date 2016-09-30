
package com.gits.rms.interceptor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.TimeSheetCategoryAssignDaoService;
import com.gits.rms.service.TimeSheetCategoryAssignService;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;

public class TimeSheetAlertIntreceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;
    private List<EmployeesVO> employeesList = new ArrayList<EmployeesVO>();
    private EmployeesService employeesService = new EmployeesDaoService();
    private EmployeesVO employees;
    private ArrayList<EmployeesVO> dailyList = new ArrayList();
    private ArrayList<EmployeesVO> weeklyList = new ArrayList();
    private ArrayList<EmployeesVO> monthlyList = new ArrayList();
    private TimeSheetCategoryAssignService timeSheetCategoryAssignService = new TimeSheetCategoryAssignDaoService();
    private List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList = new ArrayList<TimeSheetCategoryAssignVO>();

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

//      CLIENT ID ADDED BY BALA
    	Map mSession = ActionContext.getContext().getSession();
        Integer clientId = (Integer) mSession.get("CLIENT_INFO"); 
    	dailyList = new ArrayList();
        weeklyList = new ArrayList();
        monthlyList = new ArrayList();
        ActionContext.getContext().getSession();
        HCMOneMailer hcmOneMailer = new HCMOneMailer();
        timeSheetCategoryAssignList = new ArrayList<TimeSheetCategoryAssignVO>();
        employeesList = new ArrayList<EmployeesVO>();
        employeesList = employeesService.getAllEmployees(clientId);
        employees = new EmployeesVO();
        Iterator<EmployeesVO> empItr = employeesList.iterator();
        try {
            while (empItr.hasNext()) {
                employees = empItr.next();
                String wage = employees.getEmployeeWage();
                if (wage.equals("Daily")) {
                    dailyList.add(employees);
                } else if (wage.equals("Weekly")) {
                    weeklyList.add(employees);
                } else {
                    monthlyList.add(employees);
                }
            }

            for (int i = 0; i < dailyList.size(); i++) {
                Date date = new Date();
                timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearchResult(dailyList.get(i).getEmployeeId(), date, date);
                if (timeSheetCategoryAssignList.isEmpty()) {
                    hcmOneMailer.sendTimeSheetAlertEmail(dailyList.get(i).getEmpWorkEmail(), "You Forgot To Enter TimeSheet", "Please Enter Your Time Sheet", "HR", dailyList.get(i));
                }
            }
            for (int i = 0; i < weeklyList.size(); i++) {
                Date startDate = new Date();
                Date endDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, -(endDate.getDay()));
                startDate.setTime(calendar.getTimeInMillis());
                calendar.add(Calendar.DATE, 7);
                endDate.setTime(calendar.getTimeInMillis());
                timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearchResult(weeklyList.get(i).getEmployeeId(), startDate, endDate);
                if (timeSheetCategoryAssignList.isEmpty()) {
                    hcmOneMailer.sendTimeSheetAlertEmail(weeklyList.get(i).getEmpWorkEmail(), "You Forgot To Enter TimeSheet", "Please Enter Your Time Sheet", "HR", dailyList.get(i));
                }
            }
            for (int i = 0; i < monthlyList.size(); i++) {
                Date startDate = new Date();
                Date endDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), 1);
                startDate.setTime(calendar.getTimeInMillis());
                calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                endDate.setTime(calendar.getTimeInMillis());
                timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearchResult(monthlyList.get(i).getEmployeeId(), startDate, endDate);
                if (timeSheetCategoryAssignList.isEmpty()) {
                    hcmOneMailer.sendTimeSheetAlertEmail(monthlyList.get(i).getEmpWorkEmail(), "You Forgot To Enter TimeSheet", "Please Enter Your Time Sheet", "HR", dailyList.get(i));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invocation.invoke();
    }

}
