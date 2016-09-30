<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<div class="textContent">
<div class="informationMessageSingle"><li><span><s:text name="label.title.expenserejected.list"/></span></li></div>

	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.expenseforapproval.empName" var="HEmpName"></s:text>
	  <s:text name="label.header.expenseforapproval.requestDate" var="HReqDate"></s:text>
	  <s:text name="label.header.expenseforapproval.totalAmount" var="HTotalAmt"></s:text>
  <div id="display_tag_expensereject_div_id">
	  <display:table class="tableborder" id="expenseListId" name="expenseList" pagesize="${NO_OF_RECORDS}" requestURI="forRejectedTab.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
	  
	      <display:column property="hcmoEmployeeId.empFirstName" title="${HEmpName}" sortable="true" headerClass="sortable"/>
	      <display:column property="createdDate" title="${HReqDate}" sortable="true" headerClass="sortable" format="{0,date,MM-dd-yyyy}"/>
	      <display:column property="curTypeValueForTotalAmountField" title="${HTotalAmt}" sortable="true" headerClass="sortable" format=" $ {0,number,0.00}"/>
	  	
	  	 <display:setProperty name="export.csv.filename" value="ExpenseReject.csv"/>
		 <display:setProperty name="export.excel.filename" value="ExpenseReject.xls"/>
		 <display:setProperty name="export.xml.filename" value="ExpenseReject.xml"/>
	  </display:table>	
  </div>
</div>

<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_expensereject_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  