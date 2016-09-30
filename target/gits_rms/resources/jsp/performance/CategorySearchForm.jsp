<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_CategorySearchId_div">
<div class="submenu_bg">
	<s:if test="#session.PERFORMANCECATEGORY_ADD == true">
		<sj:a href="setUpCategory.action" targets="submenu_CategorySearchId_div" indicator="indicatorSubMenuCatSearch" cssClass="link"><s:text name="MTIAddCategory" /></sj:a> |
	</s:if>
	<s:if test="#session.PERFORMANCECATEGORY_VIEW == true">
		<sj:a href="getAllCategory.action" targets="submenu_CategorySearchId_div" indicator="indicatorSubMenuCatSearch" cssClass="link"><s:text name="MTIViewCategory"/></sj:a> |
		<sj:a href="categorySearchForm.action" targets="submenu_CategorySearchId_div" indicator="indicatorSubMenuCatSearch" cssClass="link"><s:text name="MTISearchCategory"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuCatSearch" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	    <s:form action="categorySearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.category.search"/>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	     	 
	         <tr><td class="inputtext"><s:text name="label.header.category.name"/></td>
	         	        <td class="employeeinputtd"><s:textfield name="category.categoryName" cssClass="employeeinput"/></td>
	         	        <td class="inputerrormessage"></td>
	         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorCategorySearchForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_CategorySearchId_div" indicator="indicatorCategorySearchForm"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>