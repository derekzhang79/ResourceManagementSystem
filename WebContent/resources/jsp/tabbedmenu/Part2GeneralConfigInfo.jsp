<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<s:url var="getSubTabEmployeeStatus" action="getAllEmployeeStatus"/>
	<s:url var="getSubTabVendor" action="getAllVendor"/>
	<s:url var="getSubTabHoliday" action="getAllHoliday"/>
	<s:url var="getSubTabLeaveType" action="getAllLeaveType"/>
	<s:url var="getSubTabExpenseType" action="getAllExpType"/>
	<s:url var="getTabCategoryList" action="getAllCategory"/>
	<s:url var="getTabSubCategoryList" action="getAllSubCategory"/>
	<sj:tabbedpanel id="remoteTabbedPannelGenertalConfigInfo" animate="true">
		
		<s:if test="#session.EMPLOYEESTATUS_ADD == true || #session.EMPLOYEESTATUS_VIEW == true">
			<sj:tab id="EmpStatusSub" key="MTEmployeeStatus" href="%{getSubTabEmployeeStatus}"/>
		</s:if>
		<sj:tab id="VendorSubTab" key="MTVendor" href="%{getSubTabVendor}"/>
		<s:if test="#session.HOLIDAY_ADD == true || #session.HOLIDAY_VIEW == true">
			<sj:tab id="HolidaySubTab" key="MTHoliday" href="%{getSubTabHoliday}"/>
		</s:if>
		
		<s:if test="#session.LEAVETYPE_ADD == true || #session.LEAVETYPE_VIEW == true">
			<sj:tab id="LeaveTypeSubTab" key="MTLeaveType" href="%{getSubTabLeaveType}"/>
		</s:if>
		
		<s:if test="#session.EXPENSETYPE_ADD == true || #session.EXPENSETYPE_VIEW == true">
			<sj:tab id="ExpenseTypeSubTab" key="MTExpenseType" href="%{getSubTabExpenseType}"/>
		</s:if>
		<s:if test="#session.PERFORMANCECATEGORY_ADD == true || #session.PERFORMANCECATEGORY_VIEW == true">
			<sj:tab id="CategoryMainTab" key="MTCategory" href="%{getTabCategoryList}"/>
		</s:if>
		<s:if test="#session.PERFORMANCESUBCATEGORY_ADD == true || #session.PERFORMANCESUBCATEGORY_VIEW == true">
			<sj:tab id="SubCategoryMainTab" key="MTSubCategory" href="%{getTabSubCategoryList}"/>
		</s:if>
	</sj:tabbedpanel>