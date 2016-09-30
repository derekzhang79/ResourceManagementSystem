<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_CurrencySearchFormId_div">
<div class="submenu_bg">
		<s:if test="#session.CURRENCY_ADD == true">
			<sj:a href="setUpCurrency.action" targets="submenu_CurrencySearchFormId_div" indicator="indicatorSubMenuCurrencySearchId_div" cssClass="link"><s:text name="MTIAddCurrency"/></sj:a> |
		</s:if>
		<s:if test="#session.CURRENCY_VIEW == true">
			<sj:a href="getAllCurrency.action" targets="submenu_CurrencySearchFormId_div" indicator="indicatorSubMenuCurrencySearchId_div" cssClass="link"><s:text name="MTIViewCurrency"/></sj:a> |
			<sj:a href="currencySearchForm.action" targets="submenu_CurrencySearchFormId_div" indicator="indicatorSubMenuCurrencySearchId_div" cssClass="link"><s:text name="MTISearchCurrency"/></sj:a>
		</s:if>
</div>
<br/>
<img id="indicatorSubMenuCurrencySearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="currencySearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.currency.search"/>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	     	 
	         <tr><td class="inputtext"><s:text name="label.header.currency.currencyType"/></td>
	         	        <td class="employeeinputtd"><s:textfield name="currency.currencyType" cssClass="employeeinput"/></td>
	         	        <td></td>
	         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorSubMenuCurrencySearchForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_CurrencySearchFormId_div" indicator="indicatorSubMenuCurrencySearchForm"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>