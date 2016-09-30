<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_CurrencyFormId_div">
<div class="submenu_bg">
	<s:if test="#session.CURRENCY_ADD == true">
		<s:if test="#session.CHECK_CURRENCY == 'AVAILABLE'"></s:if>
		<s:elseif test="#session.CHECK_CURRENCY == 'NOT_AVAILABLE'">
			<sj:a href="setUpCurrency.action" targets="submenu_CurrencyFormId_div" indicator="indicatorSubMenuCurrencyFormId_div" cssClass="link"><s:text name="MTIAddCurrency"/></sj:a> |
		</s:elseif>
	</s:if>
	<s:if test="#session.CURRENCY_VIEW == true">
		<sj:a href="getAllCurrency.action" targets="submenu_CurrencyFormId_div" indicator="indicatorSubMenuCurrencyFormId_div" cssClass="link"><s:text name="MTIViewCurrency"/></sj:a> 
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuCurrencyFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateCurrency">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
                <td class="formtitle">
	        		<s:if test="currency==null || currency.hcmoCurrencyId == null">
						<s:text name="label.title.currency.add"/>
				    </s:if>
				    <s:else>
						<s:text name="label.title.currency.edit"/>
				    </s:else>
                </td>
	         </tr>
	     	 <tr>
             	<td class="forminner"><table class="tablealign"> 
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.currency.currencyType"/></td>
       	        <td class="employeeinputtd"><s:textfield name="currency.currencyType" cssClass="employeeinput"/></td>
       	        <td class="inputerrormessage">
					<s:fielderror fieldName="currency.currencyType" />
		 		</td>
	         </tr>
	            <s:hidden name="currency.hcmoCurrencyId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table><br/>
	    <table align="center"> 
  	    	<tr>
  		    	<td>
					<img id="indicatorSubMenuCurrencyForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
  		    		<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_CurrencyFormId_div" indicator="indicatorSubMenuCurrencyForm"/>
  		    	</td>
  		    	<s:if test="currency==null || currency.hcmoCurrencyId==null">
	        		<td>
   		    			<s:url var="resetCurrency" action="resetCurrency"></s:url>
   	            		<sj:submit href="%{resetCurrency}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_CurrencyFormId_div" indicator="indicatorSubMenuCurrencyForm"/>
           			</td>
       			</s:if><s:else>
  	           		<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
  	       		</s:else>
  		 	</tr>
	    </table> 		  		 
   	</s:form>
</div>