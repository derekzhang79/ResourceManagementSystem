<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.opensymphony.xwork2.inject.Context"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%-- <s:if test="#session.USER_NAME==null">
	<%
		try {
				response.sendRedirect("doLogout.action");
			} catch (Exception e) {
				e.printStackTrace();
			}
	%>
</s:if>
  --%>
 
<%
/*
String master_Id = session.getAttribute("MASTER_CLIENT_ID").toString();
request.setAttribute("ActivePlanServics", ServletActionContext.getServletContext().getAttribute("ACTIVE_PLAN_LIST"+"_"+master_Id));
*/
		%> 

<div id="submenu_cardMain_list_div_id">
	<div class="submenu_bg">
		<sj:a href="SetUpCardDetail.action" targets="submenu_cardMain_list_div_id"
			indicator="indicatoracardformMain" cssClass="link">
			 Credit Card Details
		</sj:a>	
		<%-- <s:if test="#session.MASTER_CLIENT_HRIS!=null && #session.MASTER_CLIENT_HRIS !=0">
		
			<sj:a href="SetUpSearchBillHistory.action" targets="submenu_cardMain_list_div_id"
				indicator="indicatoracardformMain" cssClass="link">
				 Billing History
			</sj:a>
		</s:if> --%>
			
	</div>
	<br/>
	<img id="indicatoracardformMain"
		src="${pageContext.request.contextPath}/resources/images/indicator.gif"
		alt="Loading..." style="display: none" />
	<jsp:include page="../common/messages.jsp" flush="true" /> 
	
		<table class="maintable">
			<tr>
				<td align="center">
				<table class="formouter">
					<tr>
	         <td>
	         <table class="employeeformiinertable">
	      <tr>
	          <td class="formtitle">	       		
					Update Credit Card Detail	   			
	          </td>
	       </tr>	       
	       <tr>
	          <td class="forminner">
	                 		
	          			<div id="detailBGC_UPDATE_DIV">
		          			<table class="tablealign">
						       	<tr>
						       		<td class="inputtext"><s:text name="label.pay.cardhldname"/> *</td>
						       		<td class="employeeinputtd">
						       			<s:textfield name="cardhldnameBGC" cssClass="employeeinput" placeholder="XXXXXX"/>
						       		</td>
						       		<td class="inputerrormessage"><s:fielderror fieldName="cardhldnameBGC" /></td>
						       	</tr>
						       	<tr>
						       	 	<td class="inputtext"><s:text name="label.pay.cardno"/> *</td>
						       	 	<td class="employeeinputtd"><s:textfield name="cardnoBGC" cssClass="employeeinput" placeholder="XXXXXXXXXXXXXXXX"/></td>
						       	 	<td class="inputerrormessage"><s:fielderror fieldName="cardnoBGC" /></td>
						      	</tr>
						       	<tr>
						       		<td class="inputtext"><s:text name="label.pay.expdate"/> *</td>
						       		<td class="employeeinputtd">
						         		<s:select list="# {'':'Month','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12'}" name="cardexpmonthBGC" id="cardexpmonthBGC" cssClass="employeeinput" cssStyle="width:79px; height:20px;" />        
						   	            <s:select list="2000" name="cardexpyearBGC" id="cardexpyearBGC" headerKey="" headerValue="Year" cssClass="employeeinput" cssStyle="width:79px; height:20px;" />
						       		</td>
							   		<td class="inputerrormessage">
							   	   		<s:fielderror fieldName="cardexpmonthBGC" />  <s:fielderror fieldName="cardexpyearBGC" /> 
							    	</td>
						    	</tr>
						   	</table>
						   	<div style="text-align: center;">
						   		<img id="indicatorplandetailBGC_UPDATE_ONLY"  src="${pageContext.request.contextPath}/resources/images/indicator.gif"   alt="Loading..." style="display: none" />
								<s:submit value=" Submit" cssClass="submitbutton117" onclick="functionBGC_UPDATE_ONLY();"/>
							</div>
						</div>
						<div id="detailLOGOUT_UPDATE_DIV" style="display: none;">
							<table style="width: 96%; font-size: 16px; margin: auto;" >
								<tr>
									<td style="width: 100%; height: 100px; font-size: 14px;" >
										<ul class="successMessageSingle">
											<li>
												<span id="UpdateLogoutContentId">Credit card details updated successfully.</span>
											</li>
										</ul>
									</td>
								</tr>
							</table>
						</div>
	          		
	          		
		          		
	       <%--    <s:else>
	          		<s:form name="cardUpdationForm" id="cardUpdationForm" action="cardUpdationAction">
	                 	<table class="tablealign">
				         	<tr>
				         		<td class="inputtext"><s:text name="label.pay.cardhldname"/> *</td>
				         		<td class="employeeinputtd">
				         			<s:textfield name="cardhldname" cssClass="employeeinput" placeholder="XXXXXX"/>
				         		</td>
				         		<td class="inputerrormessage"><s:fielderror fieldName="cardhldname" /></td>
				        	</tr>
				         	<tr>
				         	 	<td class="inputtext"><s:text name="label.pay.cardno"/> *</td>
				         	 	<td class="employeeinputtd"><s:textfield name="cardno" cssClass="employeeinput" placeholder="XXXXXXXXXXXXXXXX"/></td>
				         	 	<td class="inputerrormessage"><s:fielderror fieldName="cardno" /></td>
				        	</tr>
				         	<tr>
				         		<td class="inputtext"><s:text name="label.pay.expdate"/> *</td>
				         		<td class="employeeinputtd">
				         		<s:select list="# {'':'Month','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12'}" name="cardexpmonth" id="cardexpmonth" cssClass="employeeinput" cssStyle="width:79px; height:20px;" />        
		                        <s:select list="#request.PAY_EXPIRY_YEAR" name="cardexpyear" id="cardexpyear" headerKey="" headerValue="Year" cssClass="employeeinput" cssStyle="width:79px; height:20px;" />
				         		</td>
				         		<td class="inputerrormessage">
			                		<s:fielderror fieldName="cardexpmonth" />  <s:fielderror fieldName="cardexpyear" />
			           		    </td>
				        	</tr>
				    	</table>
		    			<table align="center"> 
				    	    <tr>
				    			<td>
				    		    	<img id="indicatorcardAddForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_cardMain_list_div_id" indicator="indicatorcardAddForm"/>
				    		    	 <s:reset name="cardrest" id="cardrest" cssClass="resetbutton117" />
				    		    </td>			    	       
				    		 </tr>
				    	</table>
						<s:hidden name="customerid" id="customerid" />
				</s:form>
	       	</s:else> --%>
		    </td>
		    </tr>
		    </table>
		    </td>
		    </tr>	
		  </table>
		 </td>
	</tr>
		</table>
		  
	
	
</div>