<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://code.google.com/p/jcaptcha4struts2/taglib/1.0" prefix="jcaptcha" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="java.util.Calendar"%>

<html>
<head>
	<sj:head/>
	<link href="<s:url value="/resources/css/default/black/login.css"/>" rel="stylesheet" type="text/css" />
	<link href="<s:url value="/resources/css/default/content_slider/scrollable-horizontal.css"/>" rel="stylesheet" type="text/css" />
	<link href="<s:url value="/resources/css/default/content_slider/scrollable-buttons.css"/>" rel="stylesheet" type="text/css" />
	<link href="<s:url value="/resources/css/default/contentslider.css"/>" rel="stylesheet" type="text/css" />
	<script src="<s:url value="/resources/js/contentslider.js" />" type="text/javascript" ></script>
		    <title>SAAS Contract</title>
	    <%@ include file="/resources/jsp/common/js.jsp"%>
	<style>
	

	/* styling for the image wrapper  */
	#image_wrap {
		/* dimensions */
		width:900px;
		margin:15px 0 15px 40px;
		padding:15px 0;

		/* centered */
		text-align:center;

		/* some "skinning" */
		background-color:#efefef;
		border:2px solid #fff;
		outline:1px solid #ddd;
		-moz-ouline-radius:4px;
	}
	</style>
</head>
<body>
<%
	Calendar c = Calendar.getInstance();
	String sMillis = String.valueOf(c.getTimeInMillis());
%>
<table class="maintable" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="top"><table class="pixeltable" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="top"><table class="pixeltable" cellspacing="0" cellpadding="0">
          <tr>
            <!-- <td colspan="2" align="center" valign="top" class="header"><img src="resources/images/default/black/client_logo1.png" align="left" /><div id="saaserrorlogoId_div" align="right"><img src="resources/images/default/black/R-light_logo.png"  style="padding-right:15px; padding-top:8px;"/>  -->
            <td colspan="2" align="center" valign="top" class="header"><div align="left"><font color="white" size="4"><b><%=session.getValue("domain_name") %></b></font><div id="saaserrorlogoId_div" align="right"><img src="resources/images/default/black/R-light_logo.png"  style="padding-right:15px; padding-top:8px;"/>
            
               <div><font style="font-family: Verdana,Arial,Helvetica,sans-serif; font-size: 11px;" color="#d9eaed"><s:text name="label.common.rlite.header" /></font><font style="font-weight: bold; font-family: Verdana,Arial,Helvetica,sans-serif; font-size: 11px;" color="#d9eaed"> <s:text name="CompanyName"/></font></div>
            </div>			</td>
          </tr>
          <tr bgcolor="#d9eaed">
          	<td colspan="2" align="left" valign="top">
          	<div id="saaserrortitle"><jsp:include page="/resources/jsp/common/welcome.jsp" flush="true"></jsp:include> 
      		</div>
          	</td>
          </tr>
          <tr bgcolor="#d9eaed">
            <td colspan="2" align="center" valign="top">
            	<span class="alerttext">Some Error Occur Please Contact Administrator.</span>
            <table align="center" cellpadding="0" cellspacing="0" class="maintable">
              <tr>
               <td  align="center" valign="top">
               		
<!-- wrapper element for the large image -->

<div id="slider1" class="sliderwrapper">

<div class="contentdiv">
	<div id="image_wrap">
		<img src="<s:text name="SaasJspImagePath"/>saas_page9.JPG" width="650" height="700"/> <br />
	</div>
</div>

<div class="contentdiv">
	<div id="image_wrap">
		<img src="<s:text name="SaasJspImagePath"/>saas_page2.JPG" width="650" height="700" />
	</div>
</div>

<div class="contentdiv">
	<div id="image_wrap">
		<img src="<s:text name="SaasJspImagePath"/>saas_page3.JPG" width="650" height="700" />
	</div>
</div>

<div class="contentdiv">
	<div id="image_wrap">
		<img src="<s:text name="SaasJspImagePath"/>saas_page4.JPG" width="650" height="700" />
	</div>
</div>
<div class="contentdiv">
	<div id="image_wrap">
		<img src="<s:text name="SaasJspImagePath"/>saas_page5.JPG" width="650" height="700"/>
	</div>
</div>
<div class="contentdiv">
	<div id="image_wrap">
		<img src="<s:text name="SaasJspImagePath"/>saas_page6.JPG" width="650" height="700" />
	</div>
</div>
<div class="contentdiv">
	<div id="image_wrap">
		<img src="<s:text name="SaasJspImagePath"/>saas_page7.JPG" width="650" height="700" />
	</div>
</div>
<div class="contentdiv">
	<div id="image_wrap">
		<img src="<s:text name="SaasJspImagePath"/>saas_page8.JPG" width="650" height="700" />
	</div>
</div>

</div>

<div id="paginate-slider1" class="pagination">

</div>

<script type="text/javascript">

featuredcontentslider.init({
	id: "slider1",  //id of main slider DIV
	contentsource: ["inline", ""],  //Valid values: ["inline", ""] or ["ajax", "path_to_file"]
	toc: "#increment",  //Valid values: "#increment", "markup", ["label1", "label2", etc]
	nextprev: ["Previous", "Next"],  //labels for "prev" and "next" links. Set to "" to hide.
	revealtype: "click", //Behavior of pagination links to reveal the slides: "click" or "mouseover"
	enablefade: [true, 0.2],  //[true/false, fadedegree]
	autorotate: [false, 3000],  //[true/false, pausetime]
	onChange: function(previndex, curindex){  //event handler fired whenever script changes slide
		//previndex holds index of last slide viewed b4 current (1=1st slide, 2nd=2nd etc)
		//curindex holds index of currently shown slide (1=1st slide, 2nd=2nd etc)
	}
})

</script>
               		
               </td>
              </tr>
            </table>
            <br>
            <s:form action="acceptSaasContract">
		             <table cellpadding="1" cellspacing="0" class="tableBackground" Width="500px"><tr><td>
					<table cellpadding="3" cellspacing="1" class="innerTable" Width="700px">
						<tr>
							<td id="label"><s:text name="label.form.fields.saas.by"/></td>
							<td id="control"><s:textfield name="saasContract.companyName" size="25"></s:textfield>(Company Name)</td>
							<td class="fielderrormessage">
								<s:fielderror fieldName="saasContract.companyName"/>
						 	</td>
						</tr>
						<tr>
							<td id="label"><s:text name="label.form.fields.saas.its"/></td>
							<td id="control"><s:textfield name="saasContract.personName" size="25"></s:textfield>(Signatory Person's Name)</td>
							<td class="fielderrormessage">
								<s:fielderror fieldName="saasContract.personName"/>
						 	</td>
						</tr>
						<tr>
							<td id="label"><s:text name="label.form.fields.saas.designation"/></td>
							<td id="control"><s:textfield name="saasContract.designation" size="25"></s:textfield></td>
							<td class="fielderrormessage">
								<s:fielderror fieldName="saasContract.designation"/>
						 	</td>
						</tr>
					</table>
		  			</td></tr>
		  		</table>
		  		<table class="formtable">
								  <tr>
								    <td class="submittd">
								   		   <s:submit name="Accept" value="Accept" cssClass="submitbutton" ></s:submit>
								           <s:submit action="doLogout" name="Decline" value="Decline" cssClass="submitbutton" ></s:submit>
								 	</td>
								  </tr>
							</table>
		  	</s:form>	
            </td>
          </tr>
          <tr>
            <td width="811" align="left" valign="top" class="footer">All Copy Rights Reserved@RoosterHR</td>
            <td width="155" align="center" valign="top" class="footer"><a href="#"><img src="resources/images/default/black/rooster_hr_product.png" width="191" height="39" border="0"></a></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>

</body>
</html>
