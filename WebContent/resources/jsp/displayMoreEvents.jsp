<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page language="java" import="java.util.*,java.text.*"%>
<%@page import="com.gits.rms.utils.DateUtils"%>

<div align="center">
	<table align="center">
		<s:iterator value="list">
			<tr><td><b>Event : </b></td><td align="center"><b><s:property></s:property></b></td></tr>
		</s:iterator>
	</table>
</div>