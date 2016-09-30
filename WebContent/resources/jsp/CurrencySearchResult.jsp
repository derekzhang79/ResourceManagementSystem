<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.CurrencyVO"%>

<div id="submenu_Currency_searchList_div_id">
	<div class="submenu_bg">
		<s:if test="#session.CURRENCY_ADD == true">
			<sj:a href="setUpCurrency.action" targets="submenu_Currency_searchList_div_id" indicator="indicatorSubMenuCurrencySearchResId_div" cssClass="link"><s:text name="MTIAddCurrency"/></sj:a> |
		</s:if>
		<s:if test="#session.CURRENCY_VIEW == true">
			<sj:a href="getAllCurrency.action" targets="submenu_Currency_searchList_div_id" indicator="indicatorSubMenuCurrencySearchResId_div" cssClass="link"><s:text name="MTIViewCurrency"/></sj:a> |
			<sj:a href="currencySearchForm.action" targets="submenu_Currency_searchList_div_id" indicator="indicatorSubMenuCurrencySearchResId_div" cssClass="link"><s:text name="MTISearchCurrency"/></sj:a>
		</s:if>
	</div>
<br/>
<img id="indicatorSubMenuCurrencySearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.currency.list"/></span></li></div>
	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.currency.currencyType" var="HCurrencyType"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  		   
	  <div id="display_tag_currencySearchList_div_id">
		  <display:table class="tableborder" id="currencyListId" name="currencyList" pagesize="${NO_OF_RECORDS}" requestURI="getAllCurrency.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sCurrencyId = ((CurrencyVO)pageContext.getAttribute("currencyListId")).getHcmoCurrencyId().toString();
		        	request.setAttribute("CurrencyId", sCurrencyId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="currencyType" title="${HCurrencyType}" sortable="true" headerClass="sortable"/>
		    	<s:if test="#session.CURRENCY_VIEW==true">
					<display:column title="${HView}" class="viewedit" media="html">
						<s:url var="listViewCurrency" action="currencyView">
							<s:param name="currency.hcmoCurrencyId" value="#request.CurrencyId"></s:param>
						</s:url>
						<s:a href="#" onclick="doPostCall('submenu_Currency_searchList_div_id','%{listViewCurrency}','');"><s:text name="View"/></s:a>
					</display:column>
				</s:if>
				<s:if test="#session.CURRENCY_UPDATE==true">
					<display:column title="${HEdit}" class="viewedit" media="html">
						<s:url var="listSetUpCurrency" action="setUpCurrency">
							<s:param name="currency.hcmoCurrencyId" value="#request.CurrencyId"></s:param>
						</s:url>
						<s:a href="#" onclick="doPostCall('submenu_Currency_searchList_div_id','%{listSetUpCurrency}','');"><s:text name="Edit"/></s:a>
					</display:column>
				</s:if>
				<display:setProperty name="export.csv.filename" value="Currency.csv"/>
			    <display:setProperty name="export.excel.filename" value="Currency.xls"/>
			    <display:setProperty name="export.xml.filename" value="Currency.xml"/>
		  </display:table>
	  </div>
	      
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_currencySearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  