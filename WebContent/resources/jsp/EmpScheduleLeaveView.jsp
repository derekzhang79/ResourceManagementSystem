<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ page  language="java" import="java.util.*,java.text.*"%>
<%@page import="com.gits.rms.utils.DateUtils"%>

<div align="center">
	<table>
		<tr><td><b>Employee Name</b></td><td>:</td><td><s:property value="employeeName" /></td></tr>
		<tr><td><b>Leave Start</b></td><td>:</td><td><s:property value="leaveStart" /></td></tr>
		<tr><td><b>Leave Status</b> </td><td>:</td><td><s:property value="leaveStatus" /></td></tr>
		<tr><td><b>No.of Days</b></td><td>:</td><td><s:property value="noOfDays" /></td></tr>
		<tr><td><b>Hours</b></td><td>:</td><td><s:property value="Hours" /></td></tr>
		<tr><td><b>Minutes</b></td><td>:</td><td><s:property value="Minutes" /></td></tr>
		<tr><td><b>Approve Notes</b></td><td>:</td><td><s:property value="ApproveNotes" /></td></tr>
	</table>
</div>