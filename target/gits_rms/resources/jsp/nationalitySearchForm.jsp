<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_NationalitySearchId_div">
<div class="submenu_bg">
	<s:if test="#session.NATIONALITY_ADD == true">
		<sj:a href="setUpNationality.action" targets="submenu_NationalitySearchId_div" indicator="indicatorSubMenuNationalitySearchId_div" cssClass="link"><s:text name="MTIAddNationality" /></sj:a> |
	</s:if>
	<s:if test="#session.NATIONALITY_VIEW == true">
		<sj:a href="getAllNationality.action" targets="submenu_NationalitySearchId_div" indicator="indicatorSubMenuNationalitySearchId_div" cssClass="link"><s:text name="MTIViewNationality"/></sj:a> |
		<sj:a href="nationalitySearchForm.action" targets="submenu_NationalitySearchId_div" indicator="indicatorSubMenuNationalitySearchId_div" cssClass="link"><s:text name="MTISearchNationality"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuNationalitySearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="nationalitySearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.nationality.search"/>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	     	 
	         <tr><td class="inputtext"><s:text name="label.header.nationality.name"/></td>
	         	        <td class="employeeinputtd"><s:textfield name="nati.nationalityName" cssClass="employeeinput"/></td>
	         	        <td class="inputerrormessage"></td>
	         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorNationalitySearchForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_NationalitySearchId_div" indicator="indicatorNationalitySearchForm"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>