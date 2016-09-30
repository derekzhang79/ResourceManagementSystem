<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_ImportantNewsSearchId_div">
<div class="submenu_bg">
		<sj:a href="setUpImportantNews.action" targets="submenu_ImportantNewsSearchId_div" indicator="indicatorSubMenuImportantNewsSearchId_div" cssClass="link"><s:text name="MTIAddImportantNews" /></sj:a> |
		<sj:a href="getAllImportantNews.action" targets="submenu_ImportantNewsSearchId_div" indicator="indicatorSubMenuImportantNewsSearchId_div" cssClass="link"><s:text name="MTIViewImportantNews"/></sj:a> |
		<sj:a href="importantNewsSearchForm.action" targets="submenu_ImportantNewsSearchId_div" indicator="indicatorSubMenuImportantNewsSearchId_div" cssClass="link"><s:text name="MTISearchImportantNews"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuImportantNewsSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="importantNewsSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.importantNews.search"/>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	     	 
	         <tr><td class="inputtext"><s:text name="label.header.message.subject"/></td>
	         	        <td class="employeeinputtd"><s:textfield name="important.subject" cssClass="employeeinput"/></td>
	         	        <td class="inputerrormessage"></td>
	         </tr>
	          <tr><td class="inputtext"><s:text name="label.header.message.message"/></td>
	         	        <td class="employeeinputtd"><s:textfield name="important.message" cssClass="employeeinput"/></td>
	         	        <td></td>
	         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorSubMenuImportantNewsSearch" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_ImportantNewsSearchId_div" indicator="indicatorSubMenuImportantNewsSearch"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>