<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ page  language="java" import="java.util.*,java.text.*"%>
<%@page import="com.gits.rms.utils.DateUtils"%>

<div align="center">
	<table>
		<tr><td><b>Employee Name </b> </td><td>:</td><td><s:property value="employeeName" /></td></tr>
		<tr><td><b>Project Name </b> </td><td>:</td><td><s:property value="projectName" /></td></tr>
		<tr><td><b>Activity </b></td><td>:</td><td><s:property value="activityName" /></td></tr>
		<tr><td><b>Start Date </b></td><td>:</td><td><s:property value="startDate" /></td></tr>
		<tr><td><b>End Date </b> </td><td>:</td><td><s:property value="endDate" /></td></tr>
		<tr><td><b>Client Name </b> </td><td>:</td><td><s:property value="clientName" /></td></tr>
		<tr><td><b>Description </b></td><td>:</td><td><s:property value="description" /></td></tr>
	</table>
</div>