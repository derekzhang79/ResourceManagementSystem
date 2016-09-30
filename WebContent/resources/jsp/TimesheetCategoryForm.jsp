<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

 <div id="submenu_TimesheetCategoryFormId_div">
	<div class="submenu_bg">
			<sj:a href="setUpInsertOrUpdateTimesheetCategory.action" targets="submenu_TimesheetCategoryFormId_div" indicator="indicatorSubMenuTimeSheetCategoryFormId_div" cssClass="link"><s:text name="MTAddTimeSheetCategory" /></sj:a> |
			<sj:a href="getAllTimeSheetCategory.action" targets="submenu_TimesheetCategoryFormId_div" indicator="indicatorSubMenuTimeSheetCategoryFormId_div" cssClass="link"><s:text name="MTViewTimeSheetCategory"/></sj:a> |
			<sj:a href="TimesheetCategorySearch.action" targets="submenu_TimesheetCategoryFormId_div" indicator="indicatorSubMenuTimeSheetCategoryFormId_div" cssClass="link"><s:text name="MTSearchTimeSheetCategory"/></sj:a>
		
	</div>
		<br/>
		<img id="indicatorSubMenuTimeSheetCategoryFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<s:if test="#request.error==null || #request.error==''"></s:if>
	<s:else>
	<ul class="actionMessageSingle">
		<li><span><s:property value="#request.error"/></span></li>
	</ul>
	</s:else>
	    <s:form action="insertOrUpdateTimesheetCategory">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
		        		   <s:if test="timesheetCategory==null || timesheetCategory.hcmoTimesheetCategoryId == null">
							 <s:text name="label.title.TimesheetCategory.add"/>
						   </s:if>
						   <s:else>
							 <s:text name="label.title.TimesheetCategory.edit"/>
						   </s:else>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	     	 
	         <tr><td class="inputtext"><s:text name="label.form.fields.TimesheetCategory.name"/></td>
	         	        <td class="employeeinputtd"><s:textfield name="timesheetCategory.name" cssClass="employeeinput"/></td>
	         	        <td class="inputerrormessage">
							<s:fielderror fieldName="timesheetCategory.name" />
				 		</td>
	         </tr>
	            <s:hidden name="timesheetCategory.hcmoTimesheetCategoryId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorTimeSheetCategory" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_TimesheetCategoryFormId_div" indicator="indicatorTimeSheetCategory"/>
	    		    </td>
	    	        <td><s:if test="timesheetCategory==null || timesheetCategory.hcmoTimesheetCategoryId == null">
		    	        	<s:url var="resetTimeSheetCategoryForm" action="resetTimeSheetCategoryForm"></s:url>
		    	            <sj:submit href="%{resetTimeSheetCategoryForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_TimesheetCategoryFormId_div" indicator="indicatorTimeSheetCategory"/>
		    	        </s:if>
		    	        <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	        </s:else></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>