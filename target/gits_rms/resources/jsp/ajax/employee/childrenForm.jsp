<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EssChildrenAdd_Form_div">
   <div class="submenu_bg">
   	<s:set name="UniqueChiEmployeeId" value="child.empIdObj.employeeId"></s:set>
	<s:url var="remoteChiForm" value="/setUpEmpChildren.action">
		<s:param name="child.empIdObj.employeeId" value="#UniqueChiEmployeeId"></s:param>
	</s:url>
		<s:set name="UniqueChiEmployeeId" value="child.empIdObj.employeeId"></s:set>
	<s:url var="remoteChiView" value="/getEmployeeAllChildren.action">
		<s:param name="child.empIdObj.employeeId" value="#UniqueChiEmployeeId"></s:param>
	</s:url>
	<s:if test="#session.CHILDREN_ADD==true">
		<sj:a href="%{remoteChiForm}" indicator="indicatorChiList" targets="submenu_EssChildrenAdd_Form_div" cssClass="link"><s:text name="label.header.children.add"/></sj:a> |
	</s:if>
		<s:if test="#session.CHILDREN_VIEW==true">
			<sj:a href="%{remoteChiView}" indicator="indicatorChiList" targets="submenu_EssChildrenAdd_Form_div" cssClass="link"><s:text name="label.header.children.view"/></sj:a>
		</s:if>
	</div>
	<br />
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
    <s:form action="insertOrUpdateChildrenAjax">
      <table class="maintable">
         <tr>
        	<td align="center" ><table class="formouter">
         <tr>
            <td><table class="employeeformiinertable">
         <tr>
                <td class="formtitle">
	        		      <s:if test="child==null || child.empChildrenId == null">
							 <s:text name="label.title.children.add"/>
						   </s:if>
						   <s:else>
							 <s:text name="label.title.children.edit"/>
						   </s:else>
                </td>
         </tr>
     	 <tr>
               <td class="forminner"><table class="tablealign">  
         <tr><td class="inputtext"><s:text name="label.form.fields.children.name"/></td>
         	        <td class="employeeinputtd"><s:textfield name="child.childName" cssClass="employeeinput"/></td>
         	         <td class="inputerrormessage">
						<s:fielderror fieldName="child.childName"/>
					</td>
         </tr>
         <!--Button image added by, R.Deepika-->
         <tr><td class="inputtext"><s:text name="label.form.fields.common.dob"/></td>
  	         <td class="employeeinputtd">
       	        <sj:datepicker name="child.childDOB" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput" />
       	        
         	 </td>
         	 <td class="inputerrormessage"><s:fielderror fieldName="child.childDOB"/></td>
         </tr>
         <!-- Alligned date format text by R.Deepika -->
          <tr>
	         	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr> 
			<s:hidden name="child.empIdObj.employeeId"/>
            <s:hidden name="child.empChildrenId"/>
    </table>
    </td></tr></table></td></tr></table></td></tr></table>
    		 <br/>
    <table align="center"> 
    	     <tr>
    		    <td>
					<img id="indicatorEssChiForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EssChildrenAdd_Form_div" indicator="indicatorEssChiForm"/>
    		    </td>
    		    <td><s:if test="child==null || child.empChildrenId==null">
		    	        	<s:url var="resetChiFormAjax" action="setUpEmpChildren">
		    	        		<s:param name="child.empIdObj.employeeId" value="child.empIdObj.employeeId"></s:param>
		    	        	</s:url>	
		    	            <sj:submit href="%{resetChiFormAjax}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EssChildrenAdd_Form_div" indicator="indicatorEssChiForm"/>
		    	    </s:if>
		    	    <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	    </s:else>
		    	</td>
    </table> 		  		 
    	</s:form>
</div>    