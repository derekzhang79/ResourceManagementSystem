<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="ExpenseAttachSuccessId_div">
<jsp:include page="common/messages.jsp" flush="true"/>
<s:iterator value="expensesAttachList" status="status">
<s:form action="deleteExpensesAttachments.action" method="POST" onsubmit="return AIM.submit(this, {'onStart' : startCallbackForDelete, 'onComplete' : completeCallbackForDelete})">
<table >
	<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		<td>
			<s:text name="label.common.message.uploadFileName"/>
		</td>
		<td>
	    <s:url var="expensesFileDownload" action="expensesFileDownload">
			<s:param name="expAttach.hcmoExpensesAttachId" value="hcmoExpensesAttachId"/>
		</s:url>
		<s:a href="%{expensesFileDownload}"><s:property value="expensesAttachFileName"/></s:a></td>
		<td>
		    <img id="indicatorExpAttachTabsForDelete" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			<s:hidden name="expAttach.hcmoExpensesAttachId" value="%{hcmoExpensesAttachId}" id="expAttach.hcmoExpensesAttachId" ></s:hidden>
			<sj:submit key="button.label.submit" cssClass="submitbutton117" value="Delete" targets="ExpenseAttachSuccessId_div" indicator="indicatorExpAttachTabsForDelete"/>
		</td>
	</tr>
</table>
</s:form>
</s:iterator>	
</div>