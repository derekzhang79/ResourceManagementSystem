
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="support_div">
	<br/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		 <table align="center"> 
    	     <tr>
    		    <td>
    		        <jsp:include page="common/messages.jsp" flush="true"/>
		    	</td>
    		 </tr>
	    </table>
</div>